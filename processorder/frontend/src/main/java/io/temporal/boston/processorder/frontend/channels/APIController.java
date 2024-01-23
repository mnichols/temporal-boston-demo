package io.temporal.boston.processorder.frontend.channels;

import io.github.thibaultmeyer.cuid.CUID;
import io.temporal.boston.processorder.backend.messaging.PaymentInfo;
import io.temporal.boston.processorder.backend.messaging.ProcessOrderRequest;
import io.temporal.boston.processorder.backend.orchestrations.ProcessOrder;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class APIController {
    @Autowired
    WorkflowClient temporalClient;

    @PostMapping(
            value = "/orders",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_HTML_VALUE})
    ResponseEntity post(@RequestBody PostOrdersParams params) {

        for(int i = 0; i < params.getCount(); i++) {
            String orderId = String.format("%s-%s", "order",CUID.randomCUID1());
            WorkflowOptions opts = WorkflowOptions.newBuilder().
                    setWorkflowId(orderId).
                    setTaskQueue("orders").
                    build();
            ProcessOrder workflowStub = temporalClient.newWorkflowStub(ProcessOrder.class, opts);
            ProcessOrderRequest request = new ProcessOrderRequest();
            PaymentInfo payment = new PaymentInfo();
            payment.setAccountId(CUID.randomCUID1().toString());
            // this would come from payment processor
            payment.setAuthorizedFundsToken(CUID.randomCUID1().toString());
            request.setProductId(CUID.randomCUID1().toString());
            request.setCustomerId(CUID.randomCUID1().toString());
            request.setOrderId(orderId);
            request.setPaymentInfo(payment);
            WorkflowClient.start(workflowStub::execute, request);
        }

        HttpStatus status = HttpStatus.OK ;
        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + "done" + "\"", status);
    }
}
