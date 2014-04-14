package org.classupplier.export;

import org.apache.maven.cli.MavenCli;
import org.codehaus.plexus.classworlds.ClassWorld;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

public class MavenExporter extends AbstractExporter {

	@Override
	public void export(IProject project, IProgressMonitor monitor)
			throws CoreException {
		setBuildConfigPath(project.getLocation());
		createBuildFiles(project.getName());

		ClassWorld classWorld = new ClassWorld("plexus.core", getClass()
				.getClassLoader());
		MavenCli.doMain(new String[] {
				"-e",
				"clean",
				"package",
				"-f" + pomPath().toString(),
				"-X",
				"-Dtycho.targetPlatform="
						+ Platform.getInstallLocation().getURL().getPath()
								.toString() }, classWorld);

	}

	private void createBuildFiles(String projectName) throws CoreException {
		POMTemplates templates = new POMTemplates();
		writeFile(parentPomPath(), templates.parentPom());
		writeFile(pomPath(), templates.pom(projectName, getVersion()));
	}

	private IPath parentPomPath() {
		return getBuildConfigPath().removeLastSegments(1).append("pom")
				.addFileExtension("xml");
	}

	private IPath pomPath() {
		return getBuildConfigPath().append("pom").addFileExtension("xml");
	}

}
