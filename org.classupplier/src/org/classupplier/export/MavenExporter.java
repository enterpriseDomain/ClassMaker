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
		MavenCli.doMain(new String[] { "-e",
				"clean",
				"package",
				"-f" + pomPath().toString(),
				"-X",
				// "-DforceContextQualifier=" + getVersion().getQualifier(),
				"-Dtycho.targetPlatform="
						+ Platform.getInstallLocation().getURL().getPath()
								.toString() }, classWorld);
	}

	private void createBuildFiles(String projectName) throws CoreException {
		POMTemplates templates = new POMTemplates();
		writeFile(parentPomPath(), templates.parentPom());
		writeFile(pomPath(),
				templates.pom(projectName, getVersion().toString()));
	}

	private IPath parentPomPath() {
		return getBuildConfigPath().removeLastSegments(1).append("pom")
				.addFileExtension("xml");
	}

	private IPath pomPath() {
		return getBuildConfigPath().append("pom").addFileExtension("xml");
	}

	private class POMTemplates {

		public CharSequence parentPom() {
			StringBuilder builder = new StringBuilder();
			builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			newLine(builder);
			builder.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
			newLine(builder);
			builder.append("\t");
			builder.append("xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">");
			newLine(builder);
			builder.append("\t");
			builder.append("<modelVersion>4.0.0</modelVersion>");
			newLine(builder);
			builder.append("\t");
			builder.append("<groupId>org.classupplier.dynamic</groupId>");
			newLine(builder);
			builder.append("\t");
			builder.append("<artifactId>org.classupplier.dynamic-parent</artifactId>");
			newLine(builder);
			builder.append("\t");
			builder.append("<version>0.0.1-SNAPSHOT</version>");
			newLine(builder);
			builder.append("\t");
			builder.append("<packaging>pom</packaging>");
			newLine(builder);
			builder.append("\t");
			newLine(builder);
			builder.append("\t");
			builder.append("<properties>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<tycho-version>0.20.0</tycho-version>");
			newLine(builder);
			builder.append("\t");
			builder.append("</properties>");
			newLine(builder);
			builder.append("\t");
			builder.append("<repositories>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<repository>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<id>kepler</id>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<layout>p2</layout>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<url>http://download.eclipse.org/releases/kepler/</url>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("</repository>");
			newLine(builder);
			builder.append("\t");
			builder.append("</repositories>");
			newLine(builder);
			builder.append("\t");
			newLine(builder);
			builder.append("\t");
			builder.append("<pluginRepositories>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<pluginRepository>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<id>tycho</id>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<url>https://oss.sonatype.org/content/groups/public/</url>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<releases>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<enabled>true</enabled>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("</releases>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("</pluginRepository>");
			newLine(builder);
			builder.append("\t");
			builder.append("</pluginRepositories>");
			newLine(builder);
			builder.append("\t");
			newLine(builder);
			builder.append("\t");
			builder.append("<build>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<plugins>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<plugin>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<groupId>org.eclipse.tycho</groupId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<artifactId>tycho-maven-plugin</artifactId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<version>${tycho-version}</version>");
			newLine(builder);
			/*
			 * builder.append("\t\t\t\t"); builder.append("<configuration>");
			 * newLine(builder); builder.append("\t\t\t\t\t");
			 * builder.append("<format>yyyyMMddHHmm</format>");
			 * newLine(builder); builder.append("\t\t\t\t");
			 * builder.append("</configuration>"); newLine(builder);
			 */
			builder.append("\t\t\t\t");
			builder.append("<extensions>true</extensions>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("</plugin>");
			newLine(builder);
			builder.append("\t\t\t");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<plugin>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<groupId>org.eclipse.tycho</groupId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<artifactId>target-platform-configuration</artifactId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<version>${tycho-version}</version>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<configuration>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<environments>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t");
			builder.append("<environment>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<os>");
			String _oS = Platform.getOS();
			builder.append(_oS + "\t\t\t\t\t\t\t");
			builder.append("</os>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<ws>");
			String _wS = Platform.getWS();
			builder.append(_wS + "\t\t\t\t\t\t\t");
			builder.append("</ws>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<arch>");
			String _oSArch = Platform.getOSArch();
			builder.append(_oSArch + "\t\t\t\t\t\t\t");
			builder.append("</arch>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t");
			builder.append("</environment>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("</environments>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("</configuration>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("</plugin>");
			newLine(builder);
			builder.append("\t\t\t");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<plugin>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<groupId>org.eclipse.tycho</groupId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<artifactId>tycho-packaging-plugin</artifactId>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<version>${tycho-version}</version>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("</plugin>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("</plugins>");
			newLine(builder);
			builder.append("\t\t");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<pluginManagement>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("<plugins>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("<plugin>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<groupId>org.eclipse.tycho</groupId>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<artifactId>tycho-p2-plugin</artifactId>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<version>${tycho-version}</version>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<executions>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t");
			builder.append("<execution>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<id>p2-metadata</id>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<goals>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t\t");
			builder.append("<goal>p2-metadata</goal>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("</goals>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t\t");
			builder.append("<phase>verify</phase>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t");
			builder.append("</execution>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("</executions>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("<configuration>");
			newLine(builder);
			builder.append("\t\t\t\t\t\t");
			builder.append("<defaultP2Metadata>false</defaultP2Metadata>");
			newLine(builder);
			builder.append("\t\t\t\t\t");
			builder.append("</configuration>");
			newLine(builder);
			builder.append("\t\t\t\t");
			builder.append("</plugin>");
			newLine(builder);
			builder.append("\t\t\t");
			builder.append("</plugins>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("</pluginManagement>");
			newLine(builder);
			builder.append("\t");
			builder.append("</build>");
			newLine(builder);
			builder.append("</project>");
			newLine(builder);
			return builder;
		}

		public CharSequence pom(final String projectName, final String version) {
			StringBuilder builder = new StringBuilder();
			builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			newLine(builder);
			builder.append("<project");
			newLine(builder);
			builder.append("\t");
			builder.append("xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"");
			newLine(builder);
			builder.append("\t");
			builder.append("xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			newLine(builder);
			builder.append("\t");
			builder.append("<modelVersion>4.0.0</modelVersion>");
			newLine(builder);
			builder.append("\t");
			builder.append("<parent>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<artifactId>org.classupplier.dynamic-parent</artifactId>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<groupId>org.classupplier.dynamic</groupId>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<version>0.0.1-SNAPSHOT</version>");
			newLine(builder);
			builder.append("\t\t");
			builder.append("<relativePath>../</relativePath>");
			newLine(builder);
			builder.append("\t");
			builder.append("</parent>");
			newLine(builder);
			builder.append("\t");
			newLine(builder);
			builder.append("\t");
			builder.append("<groupId>org.classupplier.artifact</groupId>");
			newLine(builder);
			builder.append("\t");
			builder.append("<artifactId>");
			builder.append(projectName);
			builder.append("</artifactId>");
			newLine(builder);
			builder.append("\t");
			builder.append("<version>");
			builder.append(version);
			builder.append("</version>");
			newLine(builder);
			builder.append("\t");
			builder.append("<packaging>eclipse-plugin</packaging>");
			newLine(builder);
			builder.append("</project>");
			newLine(builder);
			return builder;
		}

		private void newLine(StringBuilder builder) {
			builder.append("\n");
		}
	}

}
