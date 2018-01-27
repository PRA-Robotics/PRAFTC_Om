package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Wait extends Instruct {

    public Wait (Condition c) {
        super(c);
    }

    public void loop() {
        chassis.stop();
    }
}
