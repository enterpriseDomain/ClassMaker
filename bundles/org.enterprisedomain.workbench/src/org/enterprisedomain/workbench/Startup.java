package org.enterprisedomain.workbench;

import java.io.PrintStream;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {
		MessageConsole out = new MessageConsole("Output", null);
		System.setOut(new PrintStream(out.newOutputStream()));
		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] { out });
		ConsolePlugin.getDefault().getConsoleManager().refresh(out);
	}

}
