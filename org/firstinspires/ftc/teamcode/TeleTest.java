package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.teamcode.module.*;

@TeleOp

public class TeleTest extends OpMode {

    private OmniDrive drive;
    private LinearArm arm;
    private ColorSensor eye;
    private int time;


    @Override
    public void init() {
        drive = new OmniDrive(hardwareMap);
        arm = new LinearArm(hardwareMap, 0.5);
        eye = (ColorSensor)hardwareMap.get("eye");
    }

    @Override
    public void loop() {
      telemetry.addData("Printy Boi: ", eye.blue() - eye.red());
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
      if(gamepad1.y){
        drive.cutSpeed();
      }
      if(gamepad1.right_stick_y > 0.5) { //forward
        if(gamepad1.right_stick_x < -0.5) { //right
          drive.forwardRight();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.forwardLeft();
        } else {
          drive.forward();
        }
    } else if(gamepad1.right_stick_y < -0.5) { //backward
        if(gamepad1.right_stick_x < -0.5) { //right
          drive.backwardRight();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.backwardLeft();
        } else {
          drive.backward();
        }
      } else {
        if(gamepad1.right_stick_x < -0.5) { //right
          drive.right();
      } else if(gamepad1.right_stick_x > 0.5) { //left
          drive.left();
        } else {
          if(gamepad1.left_stick_x < -0.5) { //rotate right
            drive.rotRight();
        } else if(gamepad1.left_stick_x > 0.5) { //rotate left
            drive.rotLeft();
          } else { //stop
            drive.stop();
          }

        }
      }
    }
}
