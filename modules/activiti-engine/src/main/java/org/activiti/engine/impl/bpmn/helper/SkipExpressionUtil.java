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
package org.activiti.engine.impl.bpmn.helper;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;

public class SkipExpressionUtil {

  public static boolean isSkipExpressionEnabled(ActivityExecution execution, String skipExpression) {
    if (skipExpression == null) {
      return false;
    }
    return checkSkipExpressionVariable(execution);
  }

  public static boolean isSkipExpressionEnabled(ActivityExecution execution, Expression skipExpression) {
    if (skipExpression == null) {
      return false;
    }
    return checkSkipExpressionVariable(execution);
  }

  private static boolean checkSkipExpressionVariable(ActivityExecution execution) {
    final String skipExpressionEnabledVariable = "_ACTIVITI_SKIP_EXPRESSION_ENABLED";
    Object isSkipExpressionEnabled = execution.getVariable(skipExpressionEnabledVariable);

    if (isSkipExpressionEnabled == null) {
      return false;

    } else if (isSkipExpressionEnabled instanceof Boolean) {
      return ((Boolean) isSkipExpressionEnabled).booleanValue();

    } else {
      throw new ActivitiIllegalArgumentException(skipExpressionEnabledVariable + " variable does not resolve to a boolean. " + isSkipExpressionEnabled);
    }
  }

  public static boolean shouldSkipFlowElement(CommandContext commandContext, ActivityExecution execution, String skipExpressionString) {
    Expression skipExpression = commandContext.getProcessEngineConfiguration().getExpressionManager().createExpression(skipExpressionString);
    Object value = skipExpression.getValue(execution);

    if (value instanceof Boolean) {
      return ((Boolean) value).booleanValue();

    } else {
      throw new ActivitiIllegalArgumentException("Skip expression does not resolve to a boolean: " + skipExpression.getExpressionText());
    }
  }

  public static boolean shouldSkipFlowElement(ActivityExecution execution, Expression skipExpression) {
    Object value = skipExpression.getValue(execution);

    if (value instanceof Boolean) {
      return ((Boolean) value).booleanValue();

    } else {
      throw new ActivitiIllegalArgumentException("Skip expression does not resolve to a boolean: " + skipExpression.getExpressionText());
    }
  }
}
