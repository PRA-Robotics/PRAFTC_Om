package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.auto.condition.Condition;

public abstract class Instruct {
    private boolean firstLoop = true;
    private Condition condition;

    public Instruct(Condition c) {
        condition = c;
    }

    public abstract void init();
    public abstract void loop();
    public abstract void end();

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
}
