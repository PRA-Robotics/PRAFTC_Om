package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class LinearArm {

    private DcMotor drive;
    private double range;
    private double speed;
    private boolean clawClosed;
    private DcMotor shoulder;
    private DcMotor bicep;
    private DcMotor waist;
    private Servo claw;

    public LinearArm(HardwareMap map, double speed) {
        this.speed = speed;
        //drive = (DcMotor)map.get("drive");
        //Util.initMotor(drive);

        range = 0; //how far the arm is extended in totally arbitrary ticks

        shoulder = (DcMotor)map.get("shoulder");
        shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bicep = (DcMotor)map.get("bicep");
        waist = (DcMotor)map.get("waist");
        claw = (Servo)map.get("claw");
    }

    public void stop() {
        if(bicep.getPower() != 0) {
            bicep.setPower(0);
        }
    }

    public void extend() {
        range++;
        if(bicep.getPower() != speed) {
            bicep.setPower(speed);
        }
    }

    public void retract() {
        range++;
        if(bicep.getPower() != -speed) {
            bicep.setPower(-speed);
        }
    }

    public void shoulder(double joystickPosY){
        if(joystickPosY < -0.1){
            shoulder.setPower(-joystickPosY * 0.6);
        }else{
            shoulder.setPower(0.3);
            if(joystickPosY > 0.1){
                shoulder.setPower(-joystickPosY * 0.6);
            }else{//0.05
                shoulder.setPower(0);
            }
        }
        //shoulder.setPower(-joystickPosY * 0.6);
    }

    public void waist(double joystickPosX){
        waist.setPower(-joystickPosX * 0.4);
    }

    public void changeClaw(){
        clawClosed = !clawClosed;
        claw.setPosition(((clawClosed) ? .325 : .90));
    }

}
