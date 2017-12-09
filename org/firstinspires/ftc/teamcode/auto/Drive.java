package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Drive extends Instruct {
    private OmniDrive drive;
    private OmniDrive.Direction direction;

    public Drive(OmniDrive d, OmniDrive.Direction dir, Condition c) {
        super(c);
        drive = d;
        direction = dir;
    }

    public void init() {

    }

    public void loop() {
        drive.goDirection(direction);
    }

    public void end() {
        drive.stop();
    }
}
