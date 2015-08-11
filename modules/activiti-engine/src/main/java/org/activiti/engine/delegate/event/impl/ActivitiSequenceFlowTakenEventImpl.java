package org.activiti.engine.delegate.event.impl;

import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.ActivitiSequenceFlowTakenEvent;

/**
 * @author Joram Barrez
 */
public class ActivitiSequenceFlowTakenEventImpl extends ActivitiEventImpl implements ActivitiSequenceFlowTakenEvent {

  protected String id;
  protected String sourceActivityId;
  protected String sourceActivityName;
  protected String sourceActivityType;
  protected String targetActivityId;
  protected String targetActivityName;
  protected String targetActivityType;
  protected String SourceActivityBehaviorClass;
  protected String TargetActivityBehaviorClass;

  public ActivitiSequenceFlowTakenEventImpl(ActivitiEventType type) {
    super(type);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSourceActivityId() {
    return sourceActivityId;
  }

  public void setSourceActivityId(String sourceActivityId) {
    this.sourceActivityId = sourceActivityId;
  }

  public String getSourceActivityName() {
    return sourceActivityName;
  }

  public void setSourceActivityName(String sourceActivityName) {
    this.sourceActivityName = sourceActivityName;
  }

  public String getSourceActivityType() {
    return sourceActivityType;
  }

  public void setSourceActivityType(String sourceActivityType) {
    this.sourceActivityType = sourceActivityType;
  }

  public String getTargetActivityId() {
    return targetActivityId;
  }

  public void setTargetActivityId(String targetActivityId) {
    this.targetActivityId = targetActivityId;
  }

  public String getTargetActivityName() {
    return targetActivityName;
  }

  public void setTargetActivityName(String targetActivityName) {
    this.targetActivityName = targetActivityName;
  }

  public String getTargetActivityType() {
    return targetActivityType;
  }

  public void setTargetActivityType(String targetActivityType) {
    this.targetActivityType = targetActivityType;
  }

  public String getSourceActivityBehaviorClass() {
    return SourceActivityBehaviorClass;
  }

  public void setSourceActivityBehaviorClass(String sourceActivityBehaviorClass) {
    SourceActivityBehaviorClass = sourceActivityBehaviorClass;
  }

  public String getTargetActivityBehaviorClass() {
    return TargetActivityBehaviorClass;
  }

  public void setTargetActivityBehaviorClass(String targetActivityBehaviorClass) {
    TargetActivityBehaviorClass = targetActivityBehaviorClass;
  }
  
}
