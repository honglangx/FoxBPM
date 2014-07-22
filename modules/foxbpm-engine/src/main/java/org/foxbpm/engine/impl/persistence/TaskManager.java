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
 * @author kenshin
 * @author ych
 */
package org.foxbpm.engine.impl.persistence;

import java.util.List;
import java.util.Map;

import org.foxbpm.engine.exception.FoxBPMBizException;
import org.foxbpm.engine.impl.entity.TaskEntity;
import org.foxbpm.engine.impl.task.TaskQueryImpl;
import org.foxbpm.engine.impl.util.StringUtil;
import org.foxbpm.engine.task.Task;

/**
 * 任务数据管理器
 * @author kenshin
 */
public class TaskManager extends AbstractManager {

	/**
	 * 普通查询
	 * @param parameterMap 
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<Task> findTasksByNativeQuery(Map<String, Object> parameterMap, int firstResult,
			int maxResults) {
		return (List) getSqlSession().selectListWithRawParameter("selectTaskByNativeQuery",
				parameterMap);
	}

	public long findTaskCountByNativeQuery(Map<String, Object> parameterMap) {
		throw new FoxBPMBizException("findTaskCountByNativeQuery 方法未实现");
		// return 0;
	}

	/**
	 * 根据id查询任务，会优先从sql缓存读取
	 * @param taskId 任务编号
	 * @return
	 */
	public TaskEntity findTaskById(String taskId) {
		if (StringUtil.isEmpty(taskId)) {
			return null;
		}
		return selectById(TaskEntity.class, taskId);
	}

	/**
	 * 根据流程实例编号查询任务
	 * @param processInstanceId
	 * @return
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public List<TaskEntity> findTasksByProcessInstanceId(String processInstanceId) {
		return (List) getSqlSession().selectListWithRawParameter("selectTasksByProcessInstanceId",
				processInstanceId);
	}

	@SuppressWarnings("unchecked")
	public List<Task> findTasksByQueryCriteria(TaskQueryImpl taskQuery) {
		return getSqlSession().selectList("findTasksByQueryCriteria", taskQuery);
	}

	public long findTaskCountByQueryCriteria(TaskQueryImpl taskQuery) {
		return (Long) getSqlSession().selectOne("findTaskCountByQueryCriteria", taskQuery);
	}

	/**
	 * 根据令牌编号查询任务
	 * @param tokenId 令牌编号
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskEntity> findTasksByTokenId(String tokenId) {
		return (List<TaskEntity>) getSqlSession().selectListWithRawParameter(
				"selectTasksByTokenId", tokenId);
	}
	
	/**
	 * 根据taskId删除任务信息
	 * @param taskId
	 */
	public void deleteTaskById(String taskId){
		getSqlSession().delete("deleteTaskById", taskId);
	}

	/**
	 * 根据ProcessInstanceId删除任务 ，会级联删除任务下的所有候选人（taskIdentityLink）
	 * @param processInstanceId 流程实例编号
	 */
	public void deleteTaskByProcessInstanceId(String processInstanceId) {
		// 查询出相关任务
		List<TaskEntity> taskList = findTasksByProcessInstanceId(processInstanceId);
		// 根据流程实例号删除任务实例
		getSqlSession().delete("deleteTaskByProcessInstanceId", processInstanceId);
		// 删除任务候选人信息
		if (taskList != null) {
			IdentityLinkManager identityLinkManager = getIdentityLinkManager();
			for (TaskEntity task : taskList) {
				identityLinkManager.deleteIdentityLinkByTaskId(task.getId());
			}
		}
	}
}
