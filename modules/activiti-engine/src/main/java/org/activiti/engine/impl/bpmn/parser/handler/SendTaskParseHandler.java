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
package org.activiti.engine.impl.bpmn.parser.handler;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.ImplementationType;
import org.activiti.bpmn.model.SendTask;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Joram Barrez
 */
public class SendTaskParseHandler extends AbstractExternalInvocationBpmnParseHandler<SendTask> {

  private static final Logger logger = LoggerFactory.getLogger(SendTaskParseHandler.class);

  public Class<? extends BaseElement> getHandledType() {
    return SendTask.class;
  }

  protected void executeParse(BpmnParse bpmnParse, SendTask sendTask) {

    if (StringUtils.isNotEmpty(sendTask.getType())) {
      if (sendTask.getType().equalsIgnoreCase("mail")) {
        sendTask.setBehavior(bpmnParse.getActivityBehaviorFactory().createMailActivityBehavior(sendTask));
      } else if (sendTask.getType().equalsIgnoreCase("mule")) {
        sendTask.setBehavior(bpmnParse.getActivityBehaviorFactory().createMuleActivityBehavior(sendTask));
      } else if (sendTask.getType().equalsIgnoreCase("camel")) {
        sendTask.setBehavior(bpmnParse.getActivityBehaviorFactory().createCamelActivityBehavior(sendTask));
      }

      // for web service
    } else if (ImplementationType.IMPLEMENTATION_TYPE_WEBSERVICE.equalsIgnoreCase(sendTask.getImplementationType()) && StringUtils.isNotEmpty(sendTask.getOperationRef())) {

      // if (!bpmnParse.getOperations().containsKey(sendTask.getOperationRef())) {
      // logger.warn(sendTask.getOperationRef() + " does not exist for sendTask " + sendTask.getId());
      // } else {
      // WebServiceActivityBehavior webServiceActivityBehavior = bpmnParse.getActivityBehaviorFactory().createWebServiceActivityBehavior(sendTask);
      // Operation operation = bpmnParse.getOperations().get(sendTask.getOperationRef());
      // webServiceActivityBehavior.setOperation(operation);
      //
      // if (sendTask.getIoSpecification() != null) {
      // IOSpecification ioSpecification = createIOSpecification(bpmnParse, sendTask.getIoSpecification());
      // webServiceActivityBehavior.setIoSpecification(ioSpecification);
      // }
      //
      // for (DataAssociation dataAssociationElement : sendTask.getDataInputAssociations()) {
      // AbstractDataAssociation dataAssociation = createDataInputAssociation(bpmnParse, dataAssociationElement);
      // webServiceActivityBehavior.addDataInputAssociation(dataAssociation);
      // }
      //
      // for (DataAssociation dataAssociationElement : sendTask.getDataOutputAssociations()) {
      // AbstractDataAssociation dataAssociation = createDataOutputAssociation(bpmnParse, dataAssociationElement);
      // webServiceActivityBehavior.addDataOutputAssociation(dataAssociation);
      // }
      //
      // activity.setActivityBehavior(webServiceActivityBehavior);
      // }
    } else {
      logger.warn("One of the attributes 'type' or 'operation' is mandatory on sendTask " + sendTask.getId());
    }
  }

}
