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
package com.activiti.rest.runtime;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activiti.model.common.ResultListDataRepresentation;

/**
 * Rest resource for managing users, specifically related to tasks and processes.
 */
@RestController
public class WorkflowUsersResource extends AbstractWorkflowUsersResource {
	
    @RequestMapping(value = "/rest/workflow-users", method = RequestMethod.GET)
    public ResultListDataRepresentation getUsers(
    		@RequestParam(value="filter", required=false) String filter, 
    		@RequestParam(value="email", required=false) String email,
    		@RequestParam(value="externalId", required=false) String externalId,
            @RequestParam(value="excludeTaskId", required=false) String excludeTaskId,
            @RequestParam(value="excludeProcessId", required=false) String excludeProcessId,
            @RequestParam(value="groupId", required=false) Long groupId,
            @RequestParam(value="tenantId", required=false) Long tenantId) {
    	
    	return super.getUsers(filter, email, excludeTaskId, excludeProcessId, groupId);
    }
    
    @RequestMapping(value = "/rest/workflow-users/{userId}/recent-users", method = RequestMethod.GET)
    public ResultListDataRepresentation getRecentUsers(@PathVariable(value="userId") Long userId,
            @RequestParam(value="excludeTaskId", required=false) String excludeTaskId,
            @RequestParam(value="excludeProcessId", required=false) String excludeProcessId) {
        
    	return new ResultListDataRepresentation();
    }
    
}
