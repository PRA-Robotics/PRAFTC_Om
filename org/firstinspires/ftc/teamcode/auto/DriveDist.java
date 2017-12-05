package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.module.*;

public class DriveDist implements Instruct {
    private OmniDrive drive;
    private int direction;
    private int distance;

    public DriveDist(OmniDrive d, int dir, int dis) {
        drive = d;
        direction = dir;
        distance = dis;
    }

    public boolean tick() {
        if(drive.readWheelDist()[0] / 2 < distance) {
            drive.goDirection(direction);
        } else {
            drive.stop();
            return true;
        }
        return false;
    }
}
