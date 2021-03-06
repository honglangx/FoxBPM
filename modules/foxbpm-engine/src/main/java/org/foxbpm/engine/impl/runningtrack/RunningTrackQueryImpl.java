/**
 * Copyright 1996-2014 FoxBPM ORG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author MAENLIANG
 */
package org.foxbpm.engine.impl.runningtrack;

import java.util.List;

import org.foxbpm.engine.impl.interceptor.CommandContext;
import org.foxbpm.engine.impl.interceptor.CommandExecutor;
import org.foxbpm.engine.impl.query.AbstractQuery;
import org.foxbpm.engine.runningtrack.RunningTrack;
import org.foxbpm.engine.runningtrack.RunningTrackQuery;

/**
 * 运行轨迹的查询
 * 
 * @author MAENLIANG
 */
public class RunningTrackQueryImpl extends AbstractQuery<RunningTrackQuery, RunningTrack>
		implements
			RunningTrackQuery {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String processInstanceID;

	public RunningTrackQueryImpl() {
	}

	public RunningTrackQueryImpl(CommandContext commandContext) {
		super(commandContext);
	}

	public RunningTrackQueryImpl(CommandExecutor commandExecutor) {
		super(commandExecutor);
	}

	public List<RunningTrack> executeList(CommandContext commandContext) {
		checkQueryOk();
		return (List<RunningTrack>) commandContext.getRunningTrackManager()
				.findRunningTrackByProcessInstanceId(processInstanceID);
	}
	 
	public RunningTrackQuery processInstanceID(String processInstanceID) {
		this.processInstanceID = processInstanceID;
		return this;
	}

	 
	public long executeCount(CommandContext commandContext) {
		return 0;
	}

}
