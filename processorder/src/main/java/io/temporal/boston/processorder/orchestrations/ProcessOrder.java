package io.temporal.boston.processorder.orchestrations;

import io.temporal.boston.processorder.messaging.ProcessOrderRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ProcessOrder {
    @WorkflowMethod
    void execute(ProcessOrderRequest params);
}
