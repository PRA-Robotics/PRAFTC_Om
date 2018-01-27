package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Drive extends Instruct {
    private OmniDrive.Direction direction;

    public Drive(OmniDrive.Direction dir, Condition c) {
        super(c);
        direction = dir;
    }

    public void loop() {
        chassis.goDirection(direction);
    }

    public void end() {
        chassis.stop();
    }
}
