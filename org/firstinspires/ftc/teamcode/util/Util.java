package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;


public class Util {

    public static void initMotor (DcMotor motor) {
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public static boolean updatePower (DcMotor motor, double power) {
      if (power != motor.getPower()) {
        motor.setPower(power);
        return true;
      }
      return false;
    }

}
