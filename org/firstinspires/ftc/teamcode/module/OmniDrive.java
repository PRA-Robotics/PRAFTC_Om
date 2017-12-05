package org.firstinspires.ftc.teamcode.module;

import org.firstinspires.ftc.teamcode.util.Vector;
import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;


public class OmniDrive {

//variables

    private Vector pos; //(x,y)
    private double heading; //0<x<2pi
    private HardwareMap map;
    private DcMotor motorA;
    private DcMotor motorB;
    private DcMotor motorC;
    private DcMotor motorD;
    private ArrayList<DcMotor> driveMotors;
    private ArrayList<int[]> script;
    private int ticks;
    private static double SPEED = 0.15;
    private static int TPR = 560;
    private static double D = 10.0;

//Constructors

    public OmniDrive(HardwareMap map) {
        motorA = (DcMotor)map.get("A");
        motorB = (DcMotor)map.get("B");
        motorC = (DcMotor)map.get("C");
        motorD = (DcMotor)map.get("D");
        driveMotors = new ArrayList<DcMotor>();
        driveMotors.add(motorA);
        driveMotors.add(motorB);
        driveMotors.add(motorC);
        driveMotors.add(motorD);
        for (DcMotor motor : driveMotors) {
            Util.initMotor(motor);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            motor.setDirection(DcMotor.Direction.FORWARD);
        }

        //motorA.setDirection(DcMotor.Direction.REVERSE);
        //motorB.setDirection(DcMotor.Direction.REVERSE);

        pos = new Vector(0,0);
        heading = 0;
        script = new ArrayList<int[]>();
        ticks = 0;

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

//Methods

    /*
     * I had this commented but then Half my code disappeared so thats cool i guess
     */
    public void tick() {

    }

    public void resetEncoders() {
        for (DcMotor motor : driveMotors) {
            Util.initMotor(motor);
        }
    }

    public double[] readWheelDist() {
        double[] values = new double[4];
        values[0] = D * Math.PI * motorA.getCurrentPosition() / TPR;
        values[1] = D * Math.PI * motorB.getCurrentPosition() / TPR;
        values[2] = D * Math.PI * motorC.getCurrentPosition() / TPR;
        values[3] = D * Math.PI * motorD.getCurrentPosition() / TPR;
        return values;
    }

    public void stop() {
        for (DcMotor motor : driveMotors) {
            motor.setPower(0);
        }
    }

    public void backward() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, SPEED);
    }

    public void forward() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, -SPEED);
    }

    public void right() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, -SPEED);
    }

    public void left() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, SPEED);
    }

    public void forwardRight() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, 0);
      Util.updatePower(motorC, 0);
      Util.updatePower(motorD, SPEED);
    }

    public void forwardLeft() {
      Util.updatePower(motorA, 0);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, 0);
    }

    public void backwardRight() {
      Util.updatePower(motorA, 0);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, 0);
    }

    public void backwardLeft() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, 0);
      Util.updatePower(motorC, 0);
      Util.updatePower(motorD, -SPEED);
    }

    public void goDirection(int d) {
        switch(d) {
            case 1:
                this.forward();
                break;
            case 2:
                this.forwardRight();
                break;
            case 3:
                this.right();
                break;
            case 4:
                this.backwardRight();
                break;
            case 5:
                this.backward();
                break;
            case 6:
                this.backwardLeft();
                break;
            case 7:
                this.left();
                break;
            case 8:
                this.forwardLeft();
                break;
            default:
                this.stop();
                break;
        }
    }

    public void rotRight() {
      for(DcMotor motor : driveMotors) {
        Util.updatePower(motor, -SPEED);
      }
    }

    public void rotLeft() {
      for(DcMotor motor : driveMotors) {
        Util.updatePower(motor, SPEED);
      }
    }

}
