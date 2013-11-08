package org.classsupplier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Version {

	protected static final DateFormat QUALIFIER_FORMAT = new SimpleDateFormat(
			"yyyyMMddHHmm");

	private String version;

	// private String qualifier;

	public static Version newVersion() {
		Version version = new Version();
		version.setVersion("1.0.0");
		// version.setQualifier(QUALIFIER_FORMAT.format(new Date()));
		return version;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getQualifier() {
		return "qualifier"; // qualifier;
	}

	public void setQualifier(String qualifier) {
		// this.qualifier = qualifier;
	}

	public String full() {
		return getVersion() + '.' + getQualifier();
	}

}
