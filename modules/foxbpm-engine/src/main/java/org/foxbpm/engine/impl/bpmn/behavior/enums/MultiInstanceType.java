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
 * @author ych
 */
package org.foxbpm.engine.impl.bpmn.behavior.enums;

/**
 * 多实例完成条件验证方案
 * @author ych
 *
 */
public enum MultiInstanceType {

	/**
	 * 没有
	 */
	NONE,

	/**
	 * 第一个
	 */
	ONE,

	/**
	 * 所有
	 */
	ALL,

	/**
	 * 复杂
	 */
	COMPLEX
}
