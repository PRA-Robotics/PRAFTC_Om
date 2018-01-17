package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class LowerEye extends Instruct {
    private OmniDrive chassis;

    public LowerEye(OmniDrive ch, Condition c) {
        super(c);
        chassis = ch;
    }

    public void init() {
        chassis.eyeDown();
    }
}
