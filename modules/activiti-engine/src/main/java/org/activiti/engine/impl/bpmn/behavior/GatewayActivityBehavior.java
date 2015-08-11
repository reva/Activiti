/* Licensed under the Apache License, Version 2.0 (the "License");
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
 */
package org.activiti.engine.impl.bpmn.behavior;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;

/**
 * super class for all gateway activity implementations.
 * 
 * @author Joram Barrez
 */
public abstract class GatewayActivityBehavior extends FlowNodeActivityBehavior {

  protected void lockFirstParentScope(ActivityExecution execution) {
    
    ExecutionEntityManager executionEntityManager = Context.getCommandContext().getExecutionEntityManager();
    
    boolean found = false;
    ExecutionEntity parentScopeExecution = null;
    ExecutionEntity currentExecution = (ExecutionEntity) execution;
    while (!found && currentExecution != null && currentExecution.getParentId() != null) {
      parentScopeExecution = executionEntityManager.findExecutionById(currentExecution.getParentId());
      if (parentScopeExecution != null && parentScopeExecution.isScope()) {
        found = true;
      }
      currentExecution = parentScopeExecution;
    }
    
    parentScopeExecution.forceUpdate();
  }

}
