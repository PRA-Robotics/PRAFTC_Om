package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.module.*;
import org.firstinspires.ftc.teamcode.util.*;

@TeleOp

public class PRATeleOp extends OpMode {

    private OmniDrive drive;
    private LinearArm arm;
    private DcMotor motorS;
    private ColorSensor eye;
    private int time;
    private boolean justPressed;


    @Override
    public void init() {
        drive = new OmniDrive(hardwareMap);
        arm = new LinearArm(hardwareMap, 0.5);
        motorS = (DcMotor)hardwareMap.get("neck");
        eye = (ColorSensor)hardwareMap.get("eye");
        Util.initMotor(motorS);
        justPressed = false;
    }

    @Override
    public void loop() {
      telemetry.addData("Blue - Red: ", eye.blue() - eye.red());
      if(gamepad2.right_trigger > 0.5){
        arm.extend();
      }else if(gamepad2.left_trigger > 0.5){
        arm.retract();
      }else{
        arm.stop();
      }
      if(gamepad2.a && time > 10) {
        time = 0;
        arm.changeClaw();
      }
      arm.shoulder(gamepad2.left_stick_y);
      arm.waist(gamepad2.right_stick_x);
      time ++;
      //telemetry.addData("Elbow Enc " , arm.tick());
      //arm.tick();
      if(gamepad1.y && !justPressed){
          drive.setSpeed(drive.getSpeed() + .25);
          justPressed = true;
      } else if(gamepad1.x && !justPressed){
          drive.setSpeed(drive.getSpeed() + .25);
          justPressed = true;
      } else if (!gamepad1.y && !gamepad1.x){
          justPressed = false;
      }

      motorS.setPower((gamepad1.left_trigger - gamepad1.right_trigger)/4);

      if(gamepad1.right_stick_y > 0.5) { //forward
        /*if(gamepad1.right_stick_x < -0.5) { //right
          drive.forwardRight();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.forwardLeft();
        } else {
          drive.forward();
      }*/
        drive.forward();
    } else if(gamepad1.right_stick_y < -0.5) { //backward
        /*if(gamepad1.right_stick_x < -0.5) { //right
          drive.backwardRight();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.backwardLeft();
        } else {
          drive.backward();
      }*/
        drive.backward();
      } else {
        if(gamepad1.right_stick_x < -0.5) { //right
          drive.right();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.left();
        } else {
          if(gamepad1.left_stick_x < -0.5) { //rotate right
            drive.rotCC();
        } else if(gamepad1.left_stick_x > 0.5) { //rotate left
            drive.rotC();
          } else { //stop
            drive.stop();
          }

        }
      }
    }
}
