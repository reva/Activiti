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
package com.activiti.service.api;

import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.identity.User;

import com.activiti.domain.editor.AbstractModel;
import com.activiti.domain.editor.Model;
import com.activiti.domain.editor.ModelHistory;
import com.activiti.model.editor.ModelKeyRepresentation;
import com.activiti.model.editor.ModelRepresentation;
import com.activiti.model.editor.ReviveModelResultRepresentation;

public interface ModelService {

  Model getModel(Long modelId);

  List<AbstractModel> getModelsByModelType(Integer modelType);
  
  ModelKeyRepresentation validateModelKey(Model model, Integer modelType, String key);
  
  ModelHistory getModelHistory(Long modelId, Long modelHistoryId);

  Long getModelCountForUser(User user, int modelTypeApp);
  
  BpmnModel getBpmnModel(AbstractModel model);

  byte[] getBpmnXML(BpmnModel bpmnMode);
  
  byte[] getBpmnXML(AbstractModel model);
  
  BpmnModel getBpmnModel(AbstractModel model, Map<Long, Model> formMap, Map<Long, Model> decisionTableMap);

  Model createModel(ModelRepresentation model, String editorJson, User createdBy);
  
  Model createModel(Model newModel, User createdBy);

  Model saveModel(Model modelObject);
  
  Model saveModel(Model modelObject, String editorJson, byte[] imageBytes, boolean newVersion, String newVersionComment, User updatedBy);

  Model saveModel(long modelId, String name, String key, String description, String editorJson, 
      boolean newVersion, String newVersionComment, User updatedBy);

  Model createNewModelVersion(Model modelObject, String comment, User updatedBy);
  
  ModelHistory createNewModelVersionAndReturnModelHistory(Model modelObject, String comment, User updatedBy);

  void deleteModel(long modelId, boolean cascadeHistory, boolean deleteRuntimeApp);

  ReviveModelResultRepresentation reviveProcessModelHistory(ModelHistory modelHistory, User user, String newVersionComment);
}
