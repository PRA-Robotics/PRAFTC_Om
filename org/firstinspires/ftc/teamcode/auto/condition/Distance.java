package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.*;

public class Distance extends Condition {
    private double goalDistance;
    private double avgDistance;
    private Motor[] motors;
    private double[] initialEncoders;
    private double[] distanceDiffs;

    public Distance(Motor[] m, double dis) {
        goalDistance = dis;
        motors = new Motor[m.length];
        initialEncoders = new Double[m.length];
        distanceDiffs = new Double[m.length];
        for (int i = 0; i < motors.length; i++) {
            motors[i] = m[i];
        }
    }

    public void init() {
        for (int i = 0; i < motors.length; i++) {
            initialEncoders[i] = motors[i].getEncoder();
        }
    }

    public boolean loop() {
        avgDistance = 0;
        for (int i = 0; i < motors.length; i++) {
            distanceDiffs[i] = motors[i].getEncoder() - initialEncoders[i];
            avgDistance += distanceDiffs[i];
        }
        avgDistance = (avgDistance / motors.length);
        return (avgDistance >= goalDistance);
    }
}
