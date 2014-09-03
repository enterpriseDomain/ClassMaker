package org.classupplier.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.classupplier.impl.ClassSupplierOSGi;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Version;

public abstract class AbstractExporter implements Exporter {

	private IPath destination;

	private Version version;

	private IPath buildConfigPath;

	protected IPath getBuildConfigPath() {
		return buildConfigPath;
	}

	protected void setBuildConfigPath(IPath buildConfigPath) {
		this.buildConfigPath = buildConfigPath;
	}

	@Override
	public IPath getExportDestination() {
		return destination;
	}

	@Override
	public void setExportDestination(IPath path) {
		this.destination = path;
	}

	@Override
	public Version getVersion() {
		return version;
	}

	@Override
	public void setVersion(Version version) {
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
			throw new CoreException(new Status(IStatus.ERROR, ClassSupplierOSGi.PLUGIN_ID,
					0, e.getLocalizedMessage(), e));
		}
	}

}
