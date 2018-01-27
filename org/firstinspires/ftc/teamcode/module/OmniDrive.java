package org.firstinspires.ftc.teamcode.module;


import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;
import java.util.ArrayList;


public class OmniDrive {

//variables

    private HardwareMap map;
    private Motor motorA;
    private Motor motorB;
    private Motor motorC;
    private Motor motorD;
    private DcMotor motorS;
    private ArrayList<Motor> driveMotors;
    private ArrayList<Double> initialEncoders;
    private int ticks;
    private double distance;
    private double speed;
    private ColorSensor eye;
    //.private static int TPR = 560;
    //private static double D = 10.0;

    public enum Direction {
        F,
        FR,
        R,
        BR,
        B,
        BL,
        L,
        FL,
        C,
        CC
    }

//Constructors

    public OmniDrive(HardwareMap map) {
        motorA = new Motor((DcMotor)map.get("A"));
        motorB = new Motor((DcMotor)map.get("B"));
        motorC = new Motor((DcMotor)map.get("C"));
        motorD = new Motor((DcMotor)map.get("D"));
        motorS = (DcMotor)map.get("neck");
        eye = (ColorSensor)map.get("eye");
        eye.enableLed(false);
        motorS.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorS.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveMotors = new ArrayList<Motor>();
        initialEncoders = new ArrayList<Double>();
        driveMotors.add(motorA);
        driveMotors.add(motorB);
        driveMotors.add(motorC);
        driveMotors.add(motorD);
        initialEncoders.add(0.0);
        initialEncoders.add(0.0);
        initialEncoders.add(0.0);
        initialEncoders.add(0.0);

        //motorA.setDirection(DcMotor.Direction.REVERSE);
        //motorB.setDirection(DcMotor.Direction.REVERSE);
        ticks = 0;
        distance = 0;
        speed = 1;
    }

//Get/Setters

  public String getDirections() {
    String output = "";
    output+= ("MotorA: " + motorA.getDirection());
    output+= ("MotorB: " + motorB.getDirection());
    output+= ("MotorC: " + motorC.getDirection());
    output+= ("MotorD: " + motorD.getDirection());
    return output;
  }

  public Motor[] getMotors() {
      Motor[] m = {motorA, motorB, motorC, motorD};
      return m;
  }

    public void setSpeed(double s) {
        if (s > 0 && s <= 2) {
            speed = s;
        }
    }

    public double getSpeed() {
        return speed;
    }

    public ColorSensor getColorSensor() {
        return eye;
    }
//Methods

    public void eyeDown() {
        motorS.setTargetPosition((int) (100.0/360.0 * 1120.0));
        motorS.setPower(0.25);
    }

    public void eyeUp() {
        motorS.setTargetPosition(0);
        motorS.setPower(-0.25);
    }

    public boolean eyeDone() {
        /*if (motorS.isBusy()) {
            motorS.setPower(0);
            return true
        }*/
        return motorS.isBusy();
    }

    /*
     * I had this commented but then Half my code disappeared so thats cool i guess
     */

    public void resetDistance() {
        for (int i = 0; i < driveMotors.size(); i++) {
            initialEncoders.set(i, driveMotors.get(i).getEncoder());
        }
    }

    public double getDistance() {
        double totalTicks = 0;
        for (int i = 0; i < driveMotors.size(); i++) {
            totalTicks += (Math.abs(driveMotors.get(i).getEncoder() - initialEncoders.get(i)));
        }
        return (totalTicks/driveMotors.size());
    }

    public String tick() {
        return ("" + motorS.getCurrentPosition());
    }

    public void stop() {
        for (Motor motor : driveMotors) {
            motor.updateSpeed(0);
        }
    }
    public void forward() {
        motorA.updateSpeed(speed);
        motorB.updateSpeed(- speed);
        motorC.updateSpeed(speed);
        motorD.updateSpeed(- speed);
    }

    public void backward() {
        motorA.updateSpeed(-speed);
        motorB.updateSpeed(speed);
        motorC.updateSpeed(-speed);
        motorD.updateSpeed(speed);
    }

    public void left() {
        motorA.updateSpeed( -speed);
        motorB.updateSpeed( -speed);
        motorC.updateSpeed(speed);
        motorD.updateSpeed(speed);
    }

    public void right() {
        motorA.updateSpeed(speed);
        motorB.updateSpeed(speed);
        motorC.updateSpeed( -speed);
        motorD.updateSpeed( -speed);
    }

    public void forwardRight() {
        motorA.updateSpeed( -speed);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed(speed);
    }

    public void forwardLeft() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed(speed);
        motorC.updateSpeed( -speed);
        motorD.updateSpeed( 0);
    }

    public void backwardRight() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed( -speed);
        motorC.updateSpeed(speed);
        motorD.updateSpeed( 0);
    }

    public void backwardLeft() {
        motorA.updateSpeed(speed);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed( -speed);
    }

    public void goDirection(Direction d) {
        switch(d) {
            case F:
                this.forward();
                break;
            case FR:
                this.forwardRight();
                break;
            case R:
                this.right();
                break;
            case BR:
                this.backwardRight();
                break;
            case B:
                this.backward();
                break;
            case BL:
                this.backwardLeft();
                break;
            case L:
                this.left();
                break;
            case FL:
                this.forwardLeft();
                break;
            case C:
                this.rotC();
                break;
            case CC:
                this.rotCC();
                break;
            default:
                this.stop();
                break;
        }
    }

    public void rotCC() {
        for(Motor motor : driveMotors) {
            motor.updateSpeed(-speed);
        }
    }

    public void rotC() {
        for(Motor motor : driveMotors) {
            motor.updateSpeed(speed);
        }
    }

    public boolean isBlue() {
        return (eye.blue() - eye.red()) > 0;
    }

}
