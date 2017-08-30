/**
 * Copyright 2012-2016 Kyrill Zotkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.enterprisedomain.classmaker.scm;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jgit.api.Git;

public class GitSCMRegistry {

	private Map<String, GitSCMOperator> operators = new HashMap<String, GitSCMOperator>();

	private Map<String, Git> gits = new HashMap<String, Git>();

	public boolean contains(String projectName) {
		return operators.containsKey(projectName);
	}

	boolean containsSCM(String projectName) {
		return gits.containsKey(projectName);
	}

	public GitSCMOperator get(String projectName) {
		if (!contains(projectName))
			put(projectName, new GitSCMOperator(projectName));
		return operators.get(projectName);
	}

	Git getSCM(String projectName) {
		if (gits.containsKey(projectName))
			return gits.get(projectName);
		return null;
	}

	void put(String projectName, GitSCMOperator operator) {
		operators.put(projectName, operator);
	}

	void putSCM(String projectName, Git git) {
		gits.put(projectName, git);
	}

	void removeSCM(String projectName) {
		gits.remove(projectName);
	}

}
