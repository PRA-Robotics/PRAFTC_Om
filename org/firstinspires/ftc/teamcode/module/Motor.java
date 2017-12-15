package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Motor {
    private DcMotor motor;
    private double speed;
    private double lastTime;
    private double lastEncoder;
    private double lastSpeedDiff;
    private double errorSum;
    private static double SPEED_P = 0.03;
    private static double SPEED_I = 0.0;//0.00025;
    private static double SPEED_D = 0.0001;
    public Motor(DcMotor m) {
        motor = m;
        Util.initMotor(motor);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setDirection(DcMotor.Direction.BACKWARD);
        lastTime = (double)System.currentTimeMillis();
        lastEncoder = (double)motor.getCurrentPosition();
        speed = 0;
        lastSpeedDiff = 0;
        errorSum = 0;
    }

    public String updateSpeed(double goalSpeed) { //rotations / second
        double timeElapsed = (((double)System.currentTimeMillis()) - lastTime);
        double distanceElapsed = (((double)motor.getCurrentPosition()) - lastEncoder);

        speed = (distanceElapsed / timeElapsed) * (1000.0 / 560.0);

        lastTime = (double)System.currentTimeMillis();
        lastEncoder = (double)motor.getCurrentPosition();

        double speedDiff = goalSpeed - speed;
        double deltaError = lastSpeedDiff - speedDiff;
        errorSum += speedDiff;
        motor.setPower(motor.getPower() + (speedDiff * SPEED_P) + (errorSum * SPEED_I) + (deltaError * SPEED_D));

        lastSpeedDiff = speedDiff;
        return ("" + lastEncoder);
    }

    public String getPower() {
        return ("" + motor.getPower());
    }

    public double getEncoder() {
        return (double)motor.getCurrentPosition();
    }
}
