package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class LinearArm {

    private DcMotor drive;
    private double range;
    private double speed;

    public LinearArm(HardwareMap map, double speed) {
        this.speed = speed;
        drive = (DcMotor)map.get("drive");
        Util.initMotor(drive);

        range = 0; //how far the arm is extended in totally arbitrary ticks
    }

    public void stop() {
        if(drive.getPower() != 0) {
            drive.setPower(0);
        }
    }

    public void extend() {
        range++;
        if(drive.getPower() != speed) {
            drive.setPower(speed);
        }
    }

    public void retract() {
        range++;
        if(drive.getPower() != -speed) {
            drive.setPower(-speed);
        }
    }
}
