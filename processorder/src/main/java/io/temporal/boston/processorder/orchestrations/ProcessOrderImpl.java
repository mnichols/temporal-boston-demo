package io.temporal.boston.processorder.orchestrations;

import io.temporal.boston.processorder.messaging.ProcessOrderRequest;
import io.temporal.spring.boot.WorkflowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

@WorkflowImpl(taskQueues = "orders")
public class ProcessOrderImpl implements ProcessOrder {
    private Logger logger = Workflow.getLogger(ProcessOrderImpl.class);
    @Override
    public void execute(ProcessOrderRequest params) {
        logger.info("received {}", params);
//        check_fraud(order.order_id, order.payment_info)
//        prepare_shipment(order)
//        charge_confirm = charge(order.order_id, order.payment_info)
//        shipment_confirmation = ship(order)
    }
}
