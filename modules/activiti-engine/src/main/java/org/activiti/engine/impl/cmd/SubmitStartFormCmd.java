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

package org.activiti.engine.impl.cmd;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.form.StartFormHandler;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.util.FormHandlerUtil;
import org.activiti.engine.impl.util.ProcessInstanceUtil;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author Tom Baeyens
 * @author Joram Barrez
 */
public class SubmitStartFormCmd extends NeedsActiveProcessDefinitionCmd<ProcessInstance> {

  private static final long serialVersionUID = 1L;

  protected final String businessKey;
  protected Map<String, String> properties;

  public SubmitStartFormCmd(String processDefinitionId, String businessKey, Map<String, String> properties) {
    super(processDefinitionId);
    this.businessKey = businessKey;
    this.properties = properties;
  }

  protected ProcessInstance execute(CommandContext commandContext, ProcessDefinitionEntity processDefinition) {
    ExecutionEntity processInstance = null;
    
    // TODO: backwards compatibility? Only create the process instance and not start it? How?
    if (businessKey != null) {
      processInstance = (ExecutionEntity) ProcessInstanceUtil.createProcessInstance(processDefinition, businessKey, null, null);
    } else {
      processInstance = (ExecutionEntity) ProcessInstanceUtil.createProcessInstance(processDefinition, null, null, null);
    }

    commandContext.getHistoryManager().recordFormPropertiesSubmitted(processInstance.getExecutions().get(0), properties, null);

    StartFormHandler startFormHandler = FormHandlerUtil.getStartFormHandler(commandContext, processDefinition); 
    startFormHandler.submitFormProperties(properties, processInstance);
    
    ProcessInstanceUtil.startProcessInstance(processInstance, commandContext, convertPropertiesToVariablesMap());

    return processInstance;
  }
  
  protected Map<String, Object> convertPropertiesToVariablesMap() {
    Map<String, Object> vars = new HashMap<String, Object>(properties.size());
    for (String key : properties.keySet()) {
      vars.put(key, properties.get(key));
    }
    return vars;
  }
  
}
