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
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RepositoryNotFoundException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.enterprisedomain.classmaker.State;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.osgi.framework.Version;

public class GitSCMOperator {

	private String projectName;

	public GitSCMOperator(String projectName) {
		this.projectName = projectName;
	}

	public static final String MASTER_BRANCH = "master"; // $NON-NLS-1$

	public static synchronized Git getRepositorySCM(File dir) throws GitAPIException {
		Git git = GitSCMRegistry.getSCM(dir.getName());
		try {
			git = Git.open(dir);
		} catch (RepositoryNotFoundException e) {
			InitCommand init = new InitCommand();
			init.setDirectory(dir);
			git = init.call();
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createWarningStatus(e));
		}
		GitSCMRegistry.putSCM(dir.getName(), git);
		return git;
	}

	public synchronized Git getRepositorySCM() throws GitAPIException {
		Git git = GitSCMRegistry.getSCM(projectName);
		if (git != null)
			return git;
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath projectGitPath = workspaceRoot.getLocation().append(workspaceRoot.getProject(projectName).getFullPath());
		return getRepositorySCM(projectGitPath.toFile());
	}

	public synchronized void ungetRepositorySCM() throws GitAPIException {
		Git git = GitSCMRegistry.getSCM(projectName);
		if (git == null)
			return;
		git.close();
		GitSCMRegistry.removeSCM(projectName);
	}

	public synchronized void deleteProject() {
		if (GitSCMRegistry.containsSCM(projectName))
			GitSCMRegistry.removeSCM(projectName);
	}

	public String getCommitMessage(State state, int timestamp) {
		return state.getDeployableUnitName() + " " + timestamp;
	}

	public Version getVersion(String commitMessage) {
		return Version.parseVersion(commitMessage.split(" ")[0]);
	}

	public int getTimestamp(String commitMessage) {
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

	public void add(String filepattern) throws GitAPIException {
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

	public String commit(String commitMessage) throws GitAPIException {
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

	public void checkout(String branch, String commitId) throws GitAPIException {
		try {
			getRepositorySCM().checkout().setName(branch).setStartPoint(commitId).setForce(true).call();
		} finally {
			ungetRepositorySCM();
		}
	}

	public void checkoutOrphan(String branchName, int timestamp) throws GitAPIException, IOException {
		try {
			Git git = getRepositorySCM();
			synchronized (git) {
				git.checkout().setOrphan(true).setName(branchName).call();
				git.commit().setMessage(projectName + "_" + branchName + " " + timestamp).call();
			}
		} finally {
			ungetRepositorySCM();
		}
	}

}
