package io.temporal.boston.processorder.orchestrations;

import io.github.thibaultmeyer.cuid.CUID;
import io.temporal.boston.processorder.messaging.*;
import io.temporal.spring.boot.ActivityImpl;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Component
@ActivityImpl(taskQueues = "orders")
public class OrderProcessingActivitiesImpl implements OrderProcessingActivities {
    private String generateRandomString() {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
    @Override
    public CheckFraudResponse checkFraud(CheckFraudRequest cmd) {
        CheckFraudResponse response = new CheckFraudResponse();
        response.setOk(true);
        return response;
    }

    @Override
    public PrepareShipmentResponse prepareShipment(PrepareShipmentRequest cmd) {
        PrepareShipmentResponse response = new PrepareShipmentResponse();
        response.setToken(String.format("ship-%s-%s", cmd.getCustomerId(), cmd.getOrderId()));
        return response;
    }

    @Override
    public ChargeResponse charge(ChargeRequest cmd) {
        // make an API request to payment provider
        ChargeResponse response = new ChargeResponse();
        response.setToken(String.format("charge-%s-%s", cmd.getCustomerId(), cmd.getOrderId() ));
        return response;
    }

    @Override
    public ShipResponse ship(ShipRequest cmd) {
        ShipResponse response = new ShipResponse();

        response.setTrackingNumber(CUID.randomCUID2().toString());
        return response;
    }
}
