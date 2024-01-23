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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class APIController {
    @Autowired
    WorkflowClient temporalClient;

    private Map<String, ProcessOrderRequest> toProcessOrderRequests(PostOrdersParams params) {
        HashMap<String, ProcessOrderRequest> requests = new HashMap<String, ProcessOrderRequest>();
        for(int i = 0; i < params.getCount(); i++) {
            String orderId = String.format("%s-%s", "order",CUID.randomCUID1());
            String productId = params.getProductId();
            if(Objects.equals(productId, "")) {
                productId = String.format("%s-%s","product", CUID.randomCUID1());
            }
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
            request.setProductId(productId);
            request.setCustomerId(CUID.randomCUID1().toString());
            request.setOrderId(orderId);
            request.setPaymentInfo(payment);
            requests.put(orderId, request);
        }
        return requests;
    }
    @PostMapping(
            value = "/orders",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.TEXT_HTML_VALUE})
    ResponseEntity post(@RequestBody PostOrdersParams params) {

        Map<String, ProcessOrderRequest> requests = toProcessOrderRequests(params);
        for(Map.Entry<String, ProcessOrderRequest> entry: requests.entrySet()) {
            WorkflowOptions opts = WorkflowOptions.newBuilder().
                    setWorkflowId(entry.getKey()).
                    setTaskQueue("orders").
                    build();
            ProcessOrder workflowStub = temporalClient.newWorkflowStub(ProcessOrder.class, opts);
            WorkflowClient.start(workflowStub::execute, entry.getValue());
        }

        HttpStatus status = HttpStatus.OK ;
        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + "done" + "\"", status);
    }
}
