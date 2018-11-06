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
package org.enterprisedomain.classmaker.core;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.enterprisedomain.classmaker.ClassMakerService;

public class ServiceFactory extends ContextFunction {

	@Override
	public Object compute(IEclipseContext context) {
		ClassMakerService plant = ClassMakerPlugin.getClassMaker();
		plant.initialize();
		context.set(ClassMakerService.class, plant);
		return plant;
	}

}
