package org.classupplier.core;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class ProjectNature implements IProjectNature {

	private IProject project;

	@Override
	public void configure() throws CoreException {
		addToBuildSpec(ProjectBuilder.BUILDER_ID);
	}

	@Override
	public void deconfigure() throws CoreException {
		removeFromBuildSpec(ProjectBuilder.BUILDER_ID);
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

	protected void addToBuildSpec(String builderID) throws CoreException {

		IProjectDescription description = this.project.getDescription();
		int commandIndex = getCommandIndex(description.getBuildSpec(), builderID);

		if (commandIndex == -1) {

			ICommand command = description.newCommand();
			command.setBuilderName(builderID);
			setCommand(description, command);
		}
	}

	protected void removeFromBuildSpec(String builderID) throws CoreException {
		IProjectDescription description = this.project.getDescription();
		ICommand[] commands = description.getBuildSpec();
		for (int i = 0; i < commands.length; ++i) {
			if (commands[i].getBuilderName().equals(builderID)) {
				ICommand[] newCommands = new ICommand[commands.length - 1];
				System.arraycopy(commands, 0, newCommands, 0, i);
				System.arraycopy(commands, i + 1, newCommands, i, commands.length - i - 1);
				description.setBuildSpec(newCommands);
				this.project.setDescription(description, null);
				return;
			}
		}
	}

	private void setCommand(IProjectDescription description, ICommand newCommand) throws CoreException {

		ICommand[] oldBuildSpec = description.getBuildSpec();
		int oldBuilderCommandIndex = getCommandIndex(oldBuildSpec, newCommand.getBuilderName());
		ICommand[] newCommands;

		if (oldBuilderCommandIndex == -1) {
			newCommands = new ICommand[oldBuildSpec.length + 1];
			System.arraycopy(oldBuildSpec, 0, newCommands, 1, oldBuildSpec.length);
			newCommands[0] = newCommand;
		} else {
			oldBuildSpec[oldBuilderCommandIndex] = newCommand;
			newCommands = oldBuildSpec;
		}

		description.setBuildSpec(newCommands);
		this.project.setDescription(description, null);
	}

	private int getCommandIndex(ICommand[] buildSpec, String builderID) {

		for (int i = 0; i < buildSpec.length; ++i) {
			if (buildSpec[i].getBuilderName().equals(builderID)) {
				return i;
			}
		}
		return -1;
	}

}
