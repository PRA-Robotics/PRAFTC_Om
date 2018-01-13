package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Wait extends Instruct {
    private OmniDrive drive;

    public Wait (OmniDrive d, Condition c) {
        super(c);
        drive = d;
    }

    public void init() {

    }

    public void loop() {
        drive.stop();
    }

    public void end() {

    }
}
