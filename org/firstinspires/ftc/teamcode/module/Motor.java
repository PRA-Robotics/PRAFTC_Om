package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Motor {
    private DcMotor motor;
    private double speed;
    private double lastTime;
    private double lastEncoder;
    private static double SPEED_P = 0.1;
    public Motor(DcMotor m) {
        motor = m;
        Util.initMotor(motor);
        lastTime = (double)System.currentTimeMillis();
        lastEncoder = (double)motor.getCurrentPosition();
        speed = 0;
    }

    public String updateSpeed(double goalSpeed) { //rotations / second
        double timeElapsed = (((double)System.currentTimeMillis()) - lastTime);
        double distanceElapsed = (((double)motor.getCurrentPosition()) - lastEncoder);

        speed = (distanceElapsed / timeElapsed) * (1000.0 / 560.0);

        lastTime = (double)System.currentTimeMillis();
        lastEncoder = (double)motor.getCurrentPosition();

        motor.setPower((goalSpeed - speed) * SPEED_P);

        return ("" + lastEncoder);
    }
}
