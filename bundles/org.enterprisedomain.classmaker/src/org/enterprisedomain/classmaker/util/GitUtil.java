/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;

public class GitUtil {

	private static Map<String, Git> gits = new HashMap<String, Git>();

	public synchronized static Git getRepositoryGit(File dir) throws GitAPIException {
		if (gits.containsKey(dir.getName()))
			return gits.get(dir.getName());
		Git git = null;
		try {
			git = Git.open(dir);
		} catch (RepositoryNotFoundException e) {
			InitCommand init = new InitCommand();
			init.setDirectory(dir);
			git = init.call();
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		gits.put(dir.getName(), git);
		return git;
	}

	public synchronized static Git getRepositoryGit(String projectName) throws GitAPIException {
		if (gits.containsKey(projectName))
			return gits.get(projectName);
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath projectGitPath = workspaceRoot.getLocation().append(workspaceRoot.getProject(projectName).getFullPath());
		gits.put(projectName, getRepositoryGit(projectGitPath.toFile()));
		return gits.get(projectName);
	}

	public synchronized static void deleteProject(String projectName) {
		if (gits.containsKey(projectName))
			gits.remove(projectName);
	}

	public static String getCommitMessage(State state, int timestamp) {
		return state.getDeployableUnitName() + " " + timestamp;
	}

	public static int getTimestamp(String commitMessage) {
		try {
			return Integer.parseInt(commitMessage.split(" ")[1]);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static void add(String projectName, String filepattern) throws GitAPIException {
		Git git = GitUtil.getRepositoryGit(projectName);
		synchronized (git) {
			AddCommand add = git.add();
			add.addFilepattern(filepattern);
			add.call();
		}
	}

	public static String commit(String projectName, String commitMessage) throws GitAPIException {
		Git git = GitUtil.getRepositoryGit(projectName);
		String commitId = null;
		synchronized (git) {
			CommitCommand commit = git.commit();
			commit.setAll(true);
			commit.setMessage(commitMessage);
			RevCommit revCommit = commit.call();
			commitId = revCommit.getId().name();
		}
		return commitId;
	}

	public static void checkoutOrphan(String projectName, String branchName, int timestamp)
			throws GitAPIException, IOException {
		Git git = getRepositoryGit(projectName);
		synchronized (git) {
			git.checkout().setOrphan(true).setName(branchName).call();
			git.commit().setMessage(projectName + "_" + branchName + " " + timestamp).call();
		}
	}
}
