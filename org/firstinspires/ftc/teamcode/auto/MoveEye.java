package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class MoveEye extends Instruct {
    private Direction dir;

    public enum Direction {
        UP, DOWN
    }

    public MoveEye(Direction d, Condition c) {
        super(c);
        dir = d;
    }

    public void init() {
        if (dir == Direction.UP) {
            chassis.eyeUp();
        }
        else {
            chassis.eyeDown();
        }
    }
}
