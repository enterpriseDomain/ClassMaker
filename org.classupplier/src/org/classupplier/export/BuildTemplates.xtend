package org.classupplier.export

import org.eclipse.core.runtime.Platform

class BuildTemplates {

	def parentPom() '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
			<modelVersion>4.0.0</modelVersion>
			<groupId>org.classupplier.dynamic</groupId>
			<artifactId>org.classupplier.dynamic-parent</artifactId>
			<version>0.0.1-SNAPSHOT</version>
			<packaging>pom</packaging>
			
			<properties>
				<tycho-version>0.19.0</tycho-version>
			</properties>
			<repositories>
				<repository>
					<id>kepler</id>
					<layout>p2</layout>
					<url>http://download.eclipse.org/releases/kepler/</url>
				</repository>
			</repositories>
			
			<pluginRepositories>
				<pluginRepository>
					<id>tycho</id>
					<url>https://oss.sonatype.org/content/groups/public/</url>
					<releases>
						<enabled>true</enabled>
					</releases>
				</pluginRepository>
			</pluginRepositories>
			
			<build>
				<plugins>
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>tycho-maven-plugin</artifactId>
						<version>${tycho-version}</version>
						<extensions>true</extensions>
					</plugin>
					
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>target-platform-configuration</artifactId>
						<version>${tycho-version}</version>
						<configuration>
							<environments>
								<environment>
									<os>«Platform.OS»</os>
									<ws>«Platform.WS»</ws>
									<arch>«Platform.OSArch»</arch>
								</environment>
							</environments>
						</configuration>
					</plugin>
					
					<plugin>
						<groupId>org.eclipse.tycho</groupId>
						<artifactId>tycho-packaging-plugin</artifactId>
						<version>${tycho-version}</version>
					</plugin>
				</plugins>
				
				<pluginManagement>
					<plugins>
						<plugin>
							<groupId>org.eclipse.tycho</groupId>
							<artifactId>tycho-p2-plugin</artifactId>
							<version>${tycho-version}</version>
							<executions>
								<execution>
									<id>p2-metadata</id>
									<goals>
										<goal>p2-metadata</goal>
									</goals>
									<phase>verify</phase>
								</execution>
							</executions>
							<configuration>
								<defaultP2Metadata>false</defaultP2Metadata>
							</configuration>
						</plugin>
					</plugins>
				</pluginManagement>
			</build>
		</project>
	'''

	def pom(String projectName, String version) '''
		<?xml version="1.0" encoding="UTF-8"?>
		<project
			xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"
			xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<modelVersion>4.0.0</modelVersion>
			<parent>
				<artifactId>org.classupplier.dynamic-parent</artifactId>
				<groupId>org.classupplier.dynamic</groupId>
				<version>0.0.1-SNAPSHOT</version>
				<relativePath>../</relativePath>
			</parent>
			
			<groupId>org.classupplier.artifact</groupId>
			<artifactId>«projectName»</artifactId>
			<version>«version»</version>
			<packaging>eclipse-plugin</packaging>
		</project>
	'''

}
