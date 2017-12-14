package org.firstinspires.ftc.teamcode.auto.condition;

public class Distance extends Condition {
    private double initialEncoder;
    private double distance;
    private Motor[] motors;
    private double[] distanceDiffs;

    public Distance(Motor[] m, double dis) {
        distance = dis;
        motors = new Motor[m.length];
        distanceDiffs = new Double[m.length];
    }

    public void init() {
        initialEncoder = (double)motor.getEncoder();
    }

    public boolean loop() {
        if
    }
}
