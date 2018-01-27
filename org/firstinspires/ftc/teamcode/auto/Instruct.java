package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.auto.condition.*;
import org.firstinspires.ftc.teamcode.module.*;

public abstract class Instruct {
    private boolean firstLoop = true;
    private Condition condition;
    protected Script parent;
    protected OmniDrive chassis;

    public Instruct(Condition c) {
        condition = c;
    }

    public void init() {};
    public void loop() {};
    public void end() {};

    public boolean tick() {
        if (firstLoop) {
            init();
            firstLoop = false;
        }
        loop();
        if (condition.tick()) {
            end();
            return true;
        }
        return false;
    }

    public void setScript(Script p) {
        condition.setScript(p);
        parent = p;
    }

    public void setChassis(OmniDrive ch) {
        condition.setChassis(ch);
        chassis = ch;
    }
}
