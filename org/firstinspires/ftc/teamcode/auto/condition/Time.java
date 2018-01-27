package org.firstinspires.ftc.teamcode.auto.condition;

public class Time extends Condition {
    private long endTime;
    private long currentTime;
    private long initTime;

    public Time(long t) {
        endTime = t;
        currentTime = 0;
    }

    public void init() {
        initTime = System.currentTimeMillis();
    }

    public boolean loop() {
        currentTime = (System.currentTimeMillis() - initTime);
        return (currentTime >= endTime);
    }
}
