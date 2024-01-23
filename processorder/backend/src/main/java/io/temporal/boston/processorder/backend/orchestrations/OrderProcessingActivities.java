package io.temporal.boston.processorder.backend.orchestrations;

import io.temporal.activity.ActivityInterface;
import io.temporal.boston.processorder.backend.messaging.*;

@ActivityInterface
public interface OrderProcessingActivities {
    CheckFraudResponse checkFraud(CheckFraudRequest cmd);
    PrepareShipmentResponse prepareShipment(PrepareShipmentRequest cmd);
    ChargeResponse charge(ChargeRequest cmd);
    ShipResponse ship(ShipRequest cmd);
}
