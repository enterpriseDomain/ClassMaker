package org.classupplier.export;

import org.apache.maven.cli.MavenCli;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;

public class MavenExporter extends AbstractExporter {

	@Override
	public void export(IProject project, IProgressMonitor monitor)
			throws CoreException {
		setBuildConfigPath(project.getLocation());
		createBuildFiles(project.getName());

		MavenCli maven = new MavenCli();

		maven.doMain(new String[] {
				"-e",
				"clean",
				"package",
				"-X",
				"-Dtycho.targetPlatform="
						+ Platform.getInstallLocation().getURL().getPath()
								.toString() },
				project.getLocation().toString(), System.out, System.err);

	}

	private void createBuildFiles(String projectName) throws CoreException {
		BuildTemplates templates = new BuildTemplates();
		writeFile(getBuildConfigPath().removeLastSegments(1).append("pom")
				.addFileExtension("xml"), templates.parentPom());
		writeFile(getBuildConfigPath().append("pom").addFileExtension("xml"),
				templates.pom(projectName, getVersion()));
	}
}
