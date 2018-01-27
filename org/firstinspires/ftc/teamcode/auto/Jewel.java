package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.OmniDrive;
import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public class Jewel extends Instruct {
    private Color targetColor;
    private boolean turnLeft;

    public enum Color {
        RED,
        BLUE
    }

    public Jewel(Color cr, Condition c) {
        super(c);
        targetColor = cr;
    }

    public void loop() {
        if(chassis.isBlue() && targetColor == Color.BLUE) {
            turnLeft = true;
        } else if (!chassis.isBlue() && targetColor == Color.RED) {
            turnLeft = true;
        } else {
            turnLeft = false;
        }
    }

    public void end() {
        parent.addState("turnLeft", (turnLeft) ? 1 : 0);
    }
}
