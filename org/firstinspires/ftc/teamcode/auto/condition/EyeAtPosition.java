package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.module.OmniDrive;

public class EyeAtPosition extends Condition {

    private OmniDrive chassis;

    public EyeAtPosition(OmniDrive ch) {
        chassis = ch;
    }

    public boolean loop() {
        return chassis.eyeDone();
    }
}
