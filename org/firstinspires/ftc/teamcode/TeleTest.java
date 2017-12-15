package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.module.*;

@TeleOp

public class TeleTest extends OpMode {

    private OmniDrive drive;
    //private JointedArm arm;

    @Override
    public void init() {
        drive = new OmniDrive(hardwareMap);
        //arm = new JointedArm(hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("Printy Boi: ", drive.getEncoders());
        telemetry.addData("Printy Boi: ", drive.getPowers());
      if(gamepad2.dpad_down){
        //arm.position(1);
      }
      if(gamepad2.dpad_left){
        //arm.position(2);
      }
      if(gamepad2.dpad_right){
        //arm.position(3);
      }
      if(gamepad2.dpad_up){
        //arm.position(4);
      }
      if(gamepad2.a) {
          //arm.toggleClaw();
      }

      //telemetry.addData("Elbow Enc " , arm.tick());
      //arm.tick();

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
