package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class JointedArm {
    private DcMotor shoulder = null;
    private DcMotor elbow = null;
    private Servo claw = null;
    private double motorPositionShoulder;
    private double motorPositionElbow;
    private double motorSpeedShoulder = 0;
    private double motorSpeedElbow = 0;
    private double start;
    private double elapsed;
    private double clawPosition = 0;
    private boolean clawClosed = false;
    private int clawDelay = 0;
    private double shoulderEncoder;
    private double elbowEncoder;
    private double distanceFromBeingGoodShoulder;
    private double distanceFromBeingGoodElbow;
    private double shoulderErrorSum = 0;
    private double elbowErrorSum = 0;
    private double shoulderLast = 0;
    private double elbowLast = 0;

    private static double SHOULDER_SCALE = 0.3;
    private static double ELBOW_SCALE = 0.75;
    private static final double SHOULDER_P = 0.00575;
    private static final double ELBOW_P = 0.0035;
    private static final double SHOULDER_I = 0.0;
    private static final double ELBOW_I = 0.0;
    private static final double SHOULDER_D = 0.00075;
    private static final double ELBOW_D = 0.00075;

    public JointedArm (HardwareMap map) {
            start = 0;
            elapsed = 0;

            shoulder = map.dcMotor.get("shoulder");
            elbow = map.dcMotor.get("elbow");
            claw = map.servo.get("claw");

            shoulder.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
            elbow.setDirection(DcMotor.Direction.FORWARD);

            Util.initMotor(shoulder);
            Util.initMotor(elbow);
    }

    public double tick() {
        elapsed = start - System.currentTimeMillis();

        shoulderEncoder = shoulder.getCurrentPosition();
        elbowEncoder = elbow.getCurrentPosition();
        double shoulderDiff = shoulderEncoder - shoulderLast;
        double elbowDiff = elbowEncoder - elbowLast;
        distanceFromBeingGoodShoulder = (motorPositionShoulder - shoulderEncoder);
        distanceFromBeingGoodElbow = (motorPositionElbow - elbowEncoder);

        shoulderErrorSum += distanceFromBeingGoodShoulder;
        double shoulderPower = (distanceFromBeingGoodShoulder * SHOULDER_P)
            + (shoulderErrorSum * SHOULDER_I)
            - (shoulderDiff * SHOULDER_D);
        Util.updatePower(shoulder, shoulderPower);

        elbowErrorSum += distanceFromBeingGoodElbow;
        double elbowPower = (distanceFromBeingGoodElbow * ELBOW_P)
            + (elbowErrorSum * ELBOW_I)
            - (elbowDiff * ELBOW_D);
        Util.updatePower(elbow, elbowPower);

        clawDelay ++;
        if(clawClosed){
            claw.setPosition(.6);
        } else {
            claw.setPosition(.75);
        }

        start = System.currentTimeMillis();

        return (elbowEncoder);
    }

    public void position(int pos){
        switch (pos) {
            case 1:
                motorPositionShoulder = 0;
                motorPositionElbow = 400;
                break;
            case 2:
                motorPositionShoulder = 200;
                motorPositionElbow = 620;
                break;
            case 3:
                motorPositionShoulder = 320;
                motorPositionElbow = 780;
                break;
            case 4:
                motorPositionShoulder = 405;
                motorPositionElbow = 750;
                break;
        }

    }

    public void toggleClaw() {
        if(clawDelay > 50) {
            clawClosed = !clawClosed;
            clawDelay = 0;
        }
    }
}
