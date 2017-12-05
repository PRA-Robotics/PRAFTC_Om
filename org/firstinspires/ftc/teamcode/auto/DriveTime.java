package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.*;

public class DriveTime implements Instruct {
    private OmniDrive drive;
    private int direction;//1-8, 1 = forward, 8 = forwardLeft
    private int time;
    private int currentTime;

    public DriveTime(OmniDrive d, int dir , int t) {
        drive = d;
        direction = dir;
        time = t;
        currentTime = 0;
    }

    public boolean tick() {
        drive.goDirection(direction);
        currentTime++;
        if(currentTime == time) {
            drive.stop();
            return true;
        }
        return false;
    }
}
