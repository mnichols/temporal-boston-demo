package io.temporal.boston.processorder.orchestrations;

import io.temporal.activity.ActivityOptions;
import io.temporal.boston.processorder.messaging.*;
import io.temporal.common.RetryOptions;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

@WorkflowImpl(taskQueues = "orders")
public class ProcessOrderImpl implements ProcessOrder {
    OrderProcessingActivities acts = Workflow.newActivityStub(OrderProcessingActivities.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).
                setRetryOptions(
                        RetryOptions.newBuilder().
                                setMaximumAttempts(5).build()).build());
    private Logger logger = Workflow.getLogger(ProcessOrderImpl.class);
    private ChargeResponse chargeResponse;
    private ShipResponse shipmentConfirmation;

    @Override
    public void execute(ProcessOrderRequest params) {
        acts.checkFraud(
                new CheckFraudRequest(
                        params.getCustomerId(), params.getPaymentInfo(), params.getProductId()));
        PrepareShipmentResponse prepareShipmentResponse = acts.prepareShipment(
                new PrepareShipmentRequest(
                        params.getCustomerId(), params.getOrderId()));
        this.chargeResponse = acts.charge(
                new ChargeRequest(
                        params.getCustomerId(), params.getOrderId(), params.getPaymentInfo()));
        this.shipmentConfirmation = acts.ship(
                new ShipRequest(
                        prepareShipmentResponse.getToken()));
    }
}
