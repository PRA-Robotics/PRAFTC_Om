package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.*;

public class Distance extends Condition {
    private double goalTick;

    public Distance(double tick) {
        goalTick = tick;
    }

    public void init() {
        chassis.resetDistance();
    }

    public boolean loop() {
        return (chassis.getDistance() >= goalTick);
    }
}
