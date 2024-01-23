package io.temporal.boston.processorder.backend.orchestrations;

import io.temporal.activity.ActivityOptions;
import io.temporal.boston.processorder.backend.messaging.*;
import io.temporal.failure.ApplicationFailure;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

@WorkflowImpl(taskQueues = "orders")
public class ProcessOrderImpl implements ProcessOrder {
    OrderProcessingActivities acts = Workflow.newActivityStub(OrderProcessingActivities.class,
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(10)).build());
    private Logger logger = Workflow.getLogger(ProcessOrderImpl.class);
    private ChargeResponse chargeResponse;
    private ShipResponse shipmentConfirmation;

    @Override
    public void execute(ProcessOrderRequest params) {
        CheckFraudResponse fraudCheck = acts.checkFraud(
                new CheckFraudRequest(
                        params.getCustomerId(), params.getPaymentInfo(), params.getProductId()));
        if(!fraudCheck.isOk()) {
            throw ApplicationFailure.newFailure(
                    String.format("fraud alert: customer %s cannot place an order",
                            params.getCustomerId()), "fraud_check_failure");
        }
        PrepareShipmentResponse prepareShipmentResponse = acts.prepareShipment(
                new PrepareShipmentRequest(
                        params.getCustomerId(), params.getOrderId()));
        this.chargeResponse = acts.charge(
                new ChargeRequest(
                        params.getCustomerId(), params.getOrderId(), params.getPaymentInfo()));
        this.shipmentConfirmation = acts.ship(
                new ShipRequest(
                        prepareShipmentResponse.getToken()));
        logger.info("order has been processed");
    }
}
