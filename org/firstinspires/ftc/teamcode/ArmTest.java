/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the  line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Stuff", group="Linear Opmode")  // @Autonomous(...) is the other common choice

public class ArmTest extends LinearOpMode {

    /* Declare OpMode members. */
    DcMotor waist = null;
    DcMotor shoulder = null;
    DcMotor elbow = null;
    Servo claw = null;
    double motorPosition;
    double motorPositionElbow;
    double motorSpeed = 0;
    double motorSpeedElbow = 0;
    double start;
    double elapsed;
    double clawPosition = 0;
    boolean clawClosed = false;
    int clawDelay = 0;

    @Override

    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */
        waist  = hardwareMap.dcMotor.get("waist");
        shoulder = hardwareMap.dcMotor.get("shoulder");
        elbow = hardwareMap.dcMotor.get("elbow");
        claw = hardwareMap.servo.get("claw");

        // eg: Set the drive motor directions:
        // "Reverse" the motor that runs backwards when connected directly to the battery
        //waist.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        shoulder.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        elbow.setDirection(DcMotor.Direction.REVERSE);
        
        shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            start = System.currentTimeMillis();
            double shoulderEncoder = shoulder.getCurrentPosition();
            double elbowEncoder = elbow.getCurrentPosition();
            double distanceFromBeingGood = (motorPosition-shoulderEncoder);
            double distanceFromBeingGoodElbow = (motorPositionElbow-elbowEncoder);
            telemetry.addData("target-value:", motorPositionElbow);
            telemetry.addData("elapsed", elapsed);
            telemetry.addData("shoulder-encoder", elbowEncoder);
            telemetry.update();

            // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
                waist.setPower(-(gamepad1.right_trigger));
                waist.setPower((gamepad1.left_trigger));
                //shoulder.setPower(gamepad1.left_stick_y *.1);
                //elbow.setPower(gamepad1.right_stick_y * .05);
                
                if(gamepad1.left_stick_y > 0.1){
                        motorPosition = (motorPosition + (0.075 * elapsed));
                }
                if(-gamepad1.left_stick_y > 0.1){
                        motorPosition = (motorPosition - (0.075 * elapsed));
                }
                if(motorPosition + 5> shoulderEncoder){
                    shoulder.setPower(motorSpeed);
                }
                if(motorPosition < shoulderEncoder + 5){
                    shoulder.setPower(-motorSpeed);
                }
                if(distanceFromBeingGood<40 &&distanceFromBeingGood>0 ||distanceFromBeingGood>-40 &&distanceFromBeingGood<-0){
                    motorSpeed = 0.075;
                }
                if(distanceFromBeingGood>40|| distanceFromBeingGood<-40){
                    motorSpeed = 0.1;
                }
                
                if(shoulderEncoder <-500 && shoulderEncoder >-1200){
                    motorSpeed = motorSpeed * 1;
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
                //
                //motorSpeed = Math.abs((motorPosition-shoulderEncoder)/Math.PI);
                //motorSpeed = (motorPosition-shoulderEncoder);
                //motorSpeed = distanceFromBeingGood/200;
                //shoulder.setPower(motorSpeed/100);
           
            //shoulder.setPower(-gamepad1.right_stick_y);

        }
    }
}
