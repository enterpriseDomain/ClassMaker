/**
 * Copyright 2022 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.scm;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.Revision;
import org.enterprisedomain.classmaker.SCMRegistry;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.SCMOperatorImpl;
import org.osgi.framework.Version;

public class GitSCMOperator extends SCMOperatorImpl<Git> {

	@Override
	public synchronized Git getRepositorySCM() throws Exception {
		Git git = (Git) getRegistry().getSCM(getProjectName());
		if (git != null)
			return git;
		IPath root = Platform.getStateLocation(Platform.getBundle(ClassMakerPlugin.PLUGIN_ID));
		IPath projectGitPath = root.append(getProjectName());
		return getRepositorySCM(projectGitPath.toFile(), getRegistry());
	}

	public static synchronized Git getRepositorySCM(File dir, SCMRegistry<Git> scmRegistry) throws Exception {
		Git git = (Git) scmRegistry.getSCM(dir.getName());
		try {
			git = Git.open(dir);
		} catch (RepositoryNotFoundException e) {
			InitCommand init = new InitCommand();
			init.setDirectory(dir);
			git = init.call();
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		((SCMRegistry<Git>) scmRegistry).putSCM(dir.getName(), git);
		return git;
	}

	@Override
	public synchronized void ungetRepositorySCM() throws Exception {
		Git git = (Git) getRegistry().getSCM(getProjectName());
		if (git == null)
			return;
		git.close();
		getRegistry().removeSCM(getProjectName());
	}

	@Override
	public void add(String filepattern) throws Exception {
		try {
			Git git = getRepositorySCM();
			synchronized (git) {
				AddCommand add = git.add();
				add.addFilepattern(filepattern);
				add.call();
			}
		} finally {
			ungetRepositorySCM();
		}
	}

	@Override
	public String commit(String commitMessage) throws Exception {
		String commitId = null;
		try {
			Git git = getRepositorySCM();
			synchronized (git) {
				CommitCommand commit = git.commit();
				commit.setMessage(commitMessage);
				RevCommit revCommit = commit.call();
				commitId = revCommit.getId().name();
			}
		} finally {
			ungetRepositorySCM();
		}
		return commitId;
	}

	@Override
	public long decodeTimestamp(String commitMessage) {
		try {
			String[] parts = commitMessage.split(" ");
			if (parts.length == 1)
				try {
					return (long) Revision.VERSION_QUALIFIER_FORMAT.parse(parts[0]).getTime();
				} catch (ParseException e) {
					return -1;
				}
			if (parts.length == 2)
				return Long.parseLong(parts[1]);
			if (parts.length == 3)
				return Long.parseLong(parts[2]);
			else
				return -1;
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	@Override
	public Version decodeVersion(String commitMessage) {
		return Version.parseVersion(commitMessage.split(" ")[0]);
	}

	@Override
	public String encodeCommitMessage(State state) {
		return state.getDeployableUnitName() + " " + state.getTimestamp()
				+ (state.getCommitId() != null ? " " + state.getCommitId() : "");
	}

	@Override
	public synchronized void deleteProject() {
		if (getRegistry().containsSCM(getProjectName()))
			getRegistry().removeSCM(getProjectName());
	}

	@Override
	public void checkout(String branch, String commitId, boolean forced) throws Exception {
		try {
			getRepositorySCM().checkout().setName(branch).setStartPoint(commitId).setForced(forced)
					.setForceRefUpdate(forced).call();
		} finally {
			ungetRepositorySCM();
		}

	}

	@Override
	public void checkoutOrphan(String branch, long timestamp) throws Exception {
		try {
			Git git = getRepositorySCM();
			synchronized (git) {
				git.checkout().setOrphan(true).setName(branch).call();
				git.commit().setMessage(getProjectName() + "_" + branch + " " + timestamp).call();
			}
		} finally {
			ungetRepositorySCM();
		}

	}

}
