package io.temporal.boston.processorder.orchestrations;

import io.temporal.boston.processorder.messaging.*;

public class OrderProcessingActivitiesImpl implements OrderProcessingActivities {
    @Override
    public CheckFraudResponse checkFraud(CheckFraudRequest cmd) {
        return null;
    }

    @Override
    public PrepareShipmentResponse prepareShipment(PrepareShipmentRequest cmd) {
        return null;
    }

    @Override
    public ChargeResponse charge(ChargeRequest cmd) {
        return null;
    }

    @Override
    public ShipResponse ship(ShipRequest cmd) {
        return null;
    }
}
