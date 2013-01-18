package org.classmaker.test;

import java.io.PrintStream;

import org.eclipse.core.runtime.NullProgressMonitor;

public class StreamProgressMonitor extends NullProgressMonitor {

	protected PrintStream output;

	public StreamProgressMonitor(PrintStream stream) {
		this.output = stream;
	}

	@Override
	public void beginTask(String name, int totalWork) {
		if (isStringEmpty(name)) {
			println(">>> " + name);
		}
		super.beginTask(name, totalWork);
	}

	@Override
	public void setTaskName(String name) {
		if (isStringEmpty(name)) {
			println("<>> " + name);
		}
		super.setTaskName(name);
	}

	@Override
	public void subTask(String name) {
		if (isStringEmpty(name)) {
			println(">> " + name);
		}
		super.subTask(name);
	}

	private void println(String message) {
		output.println(message);
	}

	private boolean isStringEmpty(String str) {
		return str != null && str.length() != 0;
	}

}