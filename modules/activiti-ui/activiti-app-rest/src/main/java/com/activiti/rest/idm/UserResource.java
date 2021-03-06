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
package com.activiti.rest.idm;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.identity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing users.
 */
@RestController
public class UserResource extends AbstractUserResource {
	
    @RequestMapping(value = "/rest/users/{userId}",
            method = RequestMethod.GET,
            produces = "application/json")
    public User getUser(@PathVariable String userId, HttpServletResponse response) {
       return super.getUser(userId, response);
    }
    
    @RequestMapping(value = "/rest/users/{userId}",
            method = RequestMethod.PUT,
            produces = "application/json")
    public User updateUser(@PathVariable String userId, @RequestBody User userRequest, HttpServletResponse response) {
        return super.updateUser(userId, userRequest, response);
    }
    
}
