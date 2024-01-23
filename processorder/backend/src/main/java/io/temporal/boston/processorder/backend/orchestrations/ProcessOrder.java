package io.temporal.boston.processorder.backend.orchestrations;

import io.temporal.boston.processorder.backend.messaging.ProcessOrderRequest;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ProcessOrder {
    @WorkflowMethod
    void execute(ProcessOrderRequest params);

    @QueryMethod
    String getTrackingNumber();
}
