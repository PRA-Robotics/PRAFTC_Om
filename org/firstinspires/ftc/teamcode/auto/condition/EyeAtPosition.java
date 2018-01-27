package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.OmniDrive;

public class EyeAtPosition extends Condition {

    public EyeAtPosition() {
    }

    public boolean loop() {
        return chassis.eyeDone();
    }
}
