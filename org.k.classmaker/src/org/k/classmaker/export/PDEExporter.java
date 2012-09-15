package org.k.classmaker.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.k.classmaker.ClassMaker;

public class PDEExporter implements Exporter {
	
	private IPath scriptPath;

	private IPath destination;

	private String qualifier;

	private void createBuildFile(String projectName)
			throws CoreException {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<project default=\"plugin_export\" name=\"build\">\n");
		sb.append("<target name=\"plugin_export\">\n");
		sb.append("<pde.exportPlugins destination=\"");
		sb.append(getDestination().toString());
		sb.append("\" exportSource=\"false\" exportType=\"directory\" plugins=\""
				+ projectName
				+ "\" useJARFormat=\"true\" qualifier=\""
				+ getQualifier() + "\"/>\n");
		sb.append("</target>\n");
		sb.append("</project>\n");
		writeFile(scriptPath, sb);
	}

	@Override
	public void export(IProject project) throws CoreException {
		scriptPath = project.getLocation().append("scripts").append("export")
				.addFileExtension("xml");
		createBuildFile(project.getName());

		AntRunner runner = new AntRunner();
		runner.setBuildFileLocation(scriptPath.toString());
		runner.addBuildLogger("org.apache.tools.ant.DefaultLogger");
		IProgressMonitor monitor = ClassMaker.getInstance().monitor();
		runner.run(monitor);
	}

	@Override
	public IPath getDestination() {
		return destination;
	}

	@Override
	public void setDestination(IPath path) {
		this.destination = path;
	}

	@Override
	public String getQualifier() {
		return qualifier;
	}

	@Override
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	private void writeFile(IPath location, StringBuffer contents)
			throws CoreException {
		location.uptoSegment(location.segmentCount() - 1).toFile().mkdirs();
		File file = location.toFile();
		FileWriter writer = null;
		try {
			file.createNewFile();
			writer = new FileWriter(file);
			writer.append(contents);
			writer.flush();
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, ClassMaker.PLUGIN_ID, 0, e.getLocalizedMessage(),e));
		} finally {
			if (writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					throw new CoreException(new Status(IStatus.ERROR, ClassMaker.PLUGIN_ID, 0, e.getLocalizedMessage(),e));
				}
		}

	}

}
