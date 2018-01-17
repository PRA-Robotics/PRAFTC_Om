package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class RaiseEye extends Instruct {
    private OmniDrive chassis;

    public RaiseEye(OmniDrive ch, Condition c) {
        super(c);
        chassis = ch;
    }

    public void init() {
        chassis.eyeUp();
    }
}
