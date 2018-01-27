package org.firstinspires.ftc.teamcode.auto.condition;

import org.firstinspires.ftc.teamcode.auto.*;
import org.firstinspires.ftc.teamcode.module.*;

public abstract class Condition {
    private boolean firstLoop = true;
    protected Script parent;
    protected OmniDrive chassis;

    public void init() {};
    public abstract boolean loop();
    public boolean tick() {
        if (firstLoop) {
            init();
            firstLoop = false;
        }
        return loop();
    }

    public void setScript(Script p) {
        parent = p;
    }

    public void setChassis(OmniDrive ch) {
        chassis = ch;
    }
}
