/**
 * Copyright 2012-2018 Kyrill Zotkin
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
package org.enterprisedomain.classmaker.jobs.export;

import org.enterprisedomain.classmaker.Messages;
import org.enterprisedomain.classmaker.Stage;
import org.enterprisedomain.classmaker.jobs.ContainerJob;
import org.enterprisedomain.classmaker.jobs.Worker;

public abstract class AbstractExporter extends ContainerJob implements Worker {

	public static final String EXPORT_DESTINATION_PROP = "export.destination";

	public AbstractExporter(int depth, long stateTimestamp) {
		super(Messages.JobNameExport, depth, stateTimestamp);
	}

	@Override
	public Stage getResultStage() {
		return Stage.EXPORTED;
	}

}