package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Jewel extends Instruct {
    private OmniDrive drive;
    private Color targetColor;
    private boolean turnLeft;

    public enum Color {
        RED,
        BLUE
    }

    public Jewel(OmniDrive d, Color cr, Condition c) {
        super(c);
        drive = d;
        targetColor = cr;
    }

    public void init() {
        if(drive.isBlue() && targetColor == Color.BLUE) {
            turnLeft = true;
        } else if (targetColor == Color.RED) {
            turnLeft = true;
        } else {
            turnLeft = false;
        }
    }

    public void loop() {
        if(turnLeft) {
            drive.rotLeft();
        } else {
            drive.rotRight();
        }
    }

    public void end() {
        drive.stop();
    }
}
