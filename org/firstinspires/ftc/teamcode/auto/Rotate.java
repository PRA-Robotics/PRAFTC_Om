package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.Motor;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Rotate extends Instruct {
    private Motor motor;
    private double speed;

    public Rotate(Motor m, double s, Condition c) {
        super(c);
        motor = m;
        speed = s;
    }

    public void init() {

    }

    public void loop() {
        motor.updateSpeed(speed);
    }

    public void end() {
        motor.updateSpeed(0);
    }
}
