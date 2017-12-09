package org.firstinspires.ftc.teamcode.auto.condition;

public abstract class Condition {
    private boolean firstLoop = true;

    public abstract void init();
    public abstract boolean loop();
    public boolean tick() {
        if (firstLoop) {
            init();
            firstLoop = false;
        }
        return loop();
    }
}
