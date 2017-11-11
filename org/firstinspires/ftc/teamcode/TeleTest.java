package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.module.*;

@TeleOp

public class TeleTest extends OpMode {

    private OmniDrive drive;

    @Override
    public void init() {
        drive = new OmniDrive(hardwareMap);
        telemetry.addData("Directions: ", "" + drive.getDirections());
    }

    @Override
    public void loop() {
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
