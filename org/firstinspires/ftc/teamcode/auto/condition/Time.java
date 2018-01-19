package org.firstinspires.ftc.teamcode.auto.condition;

public class Time extends Condition {
    private int endTime;
    private int currentTime;

    public Time(int t) {
        endTime = t;
        currentTime = 0;
    }

    public void init() {
        currentTime = 0;
    }

    public boolean loop() {
        currentTime++;
        return (currentTime >= endTime);
    }
}
