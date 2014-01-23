package org.classsupplier.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.classsupplier.impl.OSGi;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public abstract class AbstractExporter implements Exporter {

	private IPath destination;

	private String version;

	private IPath buildConfigPath;

	protected IPath getBuildConfigPath() {
		return buildConfigPath;
	}

	protected void setBuildConfigPath(IPath buildConfigPath) {
		this.buildConfigPath = buildConfigPath;
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
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public void writeFile(IPath location, CharSequence contents)
			throws CoreException {
		location.uptoSegment(location.segmentCount() - 1).toFile().mkdirs();
		File file = location.toFile();
		try {
			file.createNewFile();
			FileWriter writer = null;
			try {
				writer = new FileWriter(file);
				writer.append(contents);
				writer.flush();
			} finally {
				if (writer != null)
					writer.close();
			}
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, OSGi.PLUGIN_ID,
					0, e.getLocalizedMessage(), e));
		}
	}

}
