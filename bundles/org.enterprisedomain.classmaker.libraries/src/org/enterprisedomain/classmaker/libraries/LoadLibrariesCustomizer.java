package org.enterprisedomain.classmaker.libraries;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.enterprisedomain.classmaker.Customizer;
import org.enterprisedomain.classmaker.Workspace;
import org.enterprisedomain.classmaker.core.ClassMakerPlugin;
import org.enterprisedomain.classmaker.impl.CustomizerImpl;

public class LoadLibrariesCustomizer extends CustomizerImpl implements Customizer {

	private String contributionName;

	public LoadLibrariesCustomizer() {
	}

	@Override
	public Object customize(EList<Object> args) {
		Workspace workspace = (Workspace) args.get(0);
		IConfigurationElement[] ces = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.enterprisedomain.classmaker.libraries.libraries");
		for (IConfigurationElement ce : ces) {
			String p = LibrariesPlugin.getPluginDir(ce.getContributor().getName());
			contributionName = ce.getAttribute("contributionName");
			p = p + "contribs/" + contributionName;
			File f = new File(p);
			if (f.exists()) {
				try {
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(contributionName);
					if (!project.exists()) {
						EObject ePackage = workspace.getResourceSet()
								.getResource(URI.createFileURI(
										p + "/model/" + CodeGenUtil.capName(contributionName) + ".ecore"), true)
								.getContents().get(0);
						workspace.createContribution(EcoreUtil.copy(ePackage), ClassMakerPlugin.getProgressMonitor());
						copyFiles(f, null);
					}
					project.refreshLocal(IResource.DEPTH_INFINITE, ClassMakerPlugin.getProgressMonitor());
					project.open(ClassMakerPlugin.getProgressMonitor());
				} catch (CoreException e) {
					ClassMakerPlugin.getInstance().getLog().log(e.getStatus());
				}
			}
		}
		return super.customize(args);
	}

	private void copyFiles(File source, String target) {
		if (target == null)
			target = ResourcesPlugin.getWorkspace().getRoot().getRawLocation().toString() + File.separator
					+ contributionName;
		if (source.isDirectory())
			for (String f : source.list()) {
				File file = new File(source.getPath() + File.separator + f);
				if (file.isDirectory()) {
					copyFile(file, target, f);
					copyFiles(file, target + File.separator + f);
				} else {
					copyFile(file, target, f);
				}
			}
	}

	private void copyFile(File source, String target, String fileName) {
		try {
			Path t = Paths.get(target + File.separator + fileName);
			File c = t.toFile();
			if (source.isDirectory())
				c.mkdirs();
			Files.copy(Path.of(source.getPath()), t, StandardCopyOption.REPLACE_EXISTING);
		} catch (DirectoryNotEmptyException e) {
		} catch (IOException e) {
			ClassMakerPlugin.getInstance().getLog().log(ClassMakerPlugin.createErrorStatus(e));
		}
	}

}
