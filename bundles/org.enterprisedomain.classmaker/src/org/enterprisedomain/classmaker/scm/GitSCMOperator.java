package org.enterprisedomain.classmaker.scm;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.SCMOperatorImpl;
import org.osgi.framework.Version;

public class GitSCMOperator extends SCMOperatorImpl<Git> {

	@Override
	public synchronized Git getRepositorySCM() throws Exception {
		Git git = (Git) ClassMakerPlugin.getClassMaker().getSCMRegistry().getSCM(getProjectName());
		if (git != null)
			return git;
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath projectGitPath = workspaceRoot.getLocation()
				.append(workspaceRoot.getProject(getProjectName()).getFullPath());
		return getRepositorySCM(projectGitPath.toFile());
	}

	public static synchronized Git getRepositorySCM(File dir) throws Exception {
		Git git = (Git) ClassMakerPlugin.getClassMaker().getSCMRegistry().getSCM(dir.getName());
		try {
			git = Git.open(dir);
		} catch (RepositoryNotFoundException e) {
			InitCommand init = new InitCommand();
			init.setDirectory(dir);
			git = init.call();
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		ClassMakerPlugin.getClassMaker().getSCMRegistry().putSCM(dir.getName(), git);
		return git;
	}

	@Override
	public synchronized void ungetRepositorySCM() throws Exception {
		Git git = (Git) ClassMakerPlugin.getClassMaker().getSCMRegistry().getSCM(getProjectName());
		if (git == null)
			return;
		git.close();
		ClassMakerPlugin.getClassMaker().getSCMRegistry().removeSCM(getProjectName());
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
	public int decodeTimestamp(String commitMessage) {
		try {
			String[] parts = commitMessage.split(" ");
			if (parts.length == 2)
				return Integer.parseInt(parts[1]);
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
	public String encodeCommitMessage(State state, int timestamp) {
		return state.getDeployableUnitName() + " " + timestamp;
	}

	@Override
	public synchronized void deleteProject() {
		if (ClassMakerPlugin.getClassMaker().getSCMRegistry().containsSCM(getProjectName()))
			ClassMakerPlugin.getClassMaker().getSCMRegistry().removeSCM(getProjectName());
	}

	@Override
	public void checkout(String branch, String commitId) throws Exception {
		try {
			getRepositorySCM().checkout().setName(branch).setStartPoint(commitId).setForce(true).call();
		} finally {
			ungetRepositorySCM();
		}

	}

	@Override
	public void checkoutOrphan(String branch, int timestamp) throws Exception {
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
