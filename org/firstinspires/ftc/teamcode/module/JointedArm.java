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

    private static double SHOULDER_SCALE = 0.3;
    private static double ELBOW_SCALE = 0.75;

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

    public String tick() {
        elapsed = start - System.currentTimeMillis();
        
        shoulderEncoder = shoulder.getCurrentPosition();
        elbowEncoder = elbow.getCurrentPosition();
        distanceFromBeingGoodShoulder = (motorPositionShoulder - shoulderEncoder);
        distanceFromBeingGoodElbow = (motorPositionElbow - elbowEncoder);

//shoulder

        if(distanceFromBeingGoodShoulder < 40 && distanceFromBeingGoodShoulder > -40 && distanceFromBeingGoodShoulder != 0){
            motorSpeedShoulder = 0.075;
        }
        if(distanceFromBeingGoodShoulder > 40|| distanceFromBeingGoodShoulder < -40){
            motorSpeedShoulder = 0.1;
        }
        if(motorPositionShoulder+ 5> shoulderEncoder){
            Util.updatePower(shoulder, motorSpeedShoulder);
        }
        if(motorPositionShoulder< shoulderEncoder + 5){
            Util.updatePower(shoulder, -motorSpeedShoulder);
        }

//elbow

        if(motorPositionElbow + 5> elbowEncoder){
            elbow.setPower(motorSpeedElbow);
        }
        if(motorPositionElbow < elbowEncoder + 5){
            elbow.setPower(-motorSpeedElbow);
        }
        if(distanceFromBeingGoodElbow < 40 && distanceFromBeingGoodElbow > -40 && distanceFromBeingGoodElbow != 0){
            motorSpeedElbow = 0.075;
        }
        if(distanceFromBeingGoodElbow > 40 || distanceFromBeingGoodElbow < -40){
            motorSpeedElbow = 0.1;
        }

//claw

        clawDelay ++;
        if(clawClosed){
            claw.setPosition(.6);
        }else{
            claw.setPosition(.75);
        }

        start = System.currentTimeMillis();

        return ("");//Debugging
    }

    public void changeShoulderPosition(double d) {
        motorPositionShoulder += (SHOULDER_SCALE * elapsed) * ((d >= 0) ? 1 : -1);
        /*
        if(motorPositionShoulder > 0) {
            motorPositionShoulder = 0;
        } else if(motorPositionShoulder < -1000) {
            motorPositionShoulder = -1000;
        }
        */
    }

    public void changeElbowPosition(double d) {
        motorPositionElbow += (ELBOW_SCALE * elapsed) * ((d >= 0) ? 1 : -1);
        /*
        if(motorPositionShoulder > 1000) {
            motorPositionShoulder = 1000;
        } else if(motorPositionShoulder < -1000) {
            motorPositionShoulder = -1000;
        }
        */
    }

    public void toggleClaw() {
        if(clawDelay > 50) {
            clawClosed = !clawClosed;
            clawDelay = 0;
        }
    }

}
/*
public method update(){
    start = System.currentTimeMillis();
    double shoulderEncoder = shoulder.getCurrentPosition();
    double elbowEncoder = elbow.getCurrentPosition();
    double distanceFromBeingGoodShoulder= (motorPosition-shoulderEncoder);
    double distanceFromBeingGoodElbow = (motorPositionElbow-elbowEncoder);

        if(motorPositionShoulder+ 5> shoulderEncoder){
            shoulder.setPower(motorSpeed);
        }
        if(motorPositionShoulder< shoulderEncoder + 5){
            shoulder.setPower(-motorSpeed);
        }
        if(distanceFromBeingGood<40 &&distanceFromBeingGood>0 ||distanceFromBeingGood>-40 &&distanceFromBeingGood<-0){
            motorSpeedShoulder = 0.075;
        }
        if(distanceFromBeingGood>40|| distanceFromBeingGood<-40){
            motorSpeedShoulder = 0.1;
        }

        if(shoulderEncoder <-500 && shoulderEncoder >-1200){
            motorSpeedShoulder = motorSpeedShoulder * 1;
        }
        //
        if(gamepad1.right_stick_y > 0.1){
                motorPositionElbow = (motorPositionElbow + (0.075 * elapsed));
        }
        if(-gamepad1.right_stick_y > 0.1){
                motorPositionElbow = (motorPositionElbow - (0.075 * elapsed));
        }
        if(motorPositionElbow + 5> elbowEncoder){
            elbow.setPower(motorSpeedElbow);
        }
        if(motorPositionElbow < elbowEncoder + 5){
            elbow.setPower(-motorSpeedElbow);
        }
        if(distanceFromBeingGoodElbow<40 &&distanceFromBeingGoodElbow>0 ||distanceFromBeingGoodElbow>-40 &&distanceFromBeingGoodElbow<-0){
            motorSpeedElbow = 0.075;
        }
        if(distanceFromBeingGoodElbow>40|| distanceFromBeingGoodElbow<-40){
            motorSpeedElbow = 0.1;
        }

        if(elbowEncoder <-500 && elbowEncoder >-1200){
            motorSpeedElbow = motorSpeedElbow * .75;
        }
        if(gamepad1.a && clawDelay > 50){
            clawClosed = !clawClosed;
            clawDelay = 0;
        }
        clawDelay ++;
        if(clawClosed){
            claw.setPosition(.6);
        }else{
            claw.setPosition(.75);
        }
        elapsed = (System.currentTimeMillis() - start) + 1;
}
*/
