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
    private Motor motorA;
    private Motor motorB;
    private Motor motorC;
    private Motor motorD;
    private ArrayList<Motor> driveMotors;
    private ArrayList<int[]> script;
    private int ticks;
    private static double SPEED = 2;
    private static int TPR = 560;
    private static double D = 10.0;

    public enum Direction {
        F, FR, R, BR, B, BL, L, FL
    }

//Constructors

    public OmniDrive(HardwareMap map) {
        motorA = new Motor((DcMotor)map.get("A"));
        motorB = new Motor((DcMotor)map.get("B"));
        motorC = new Motor((DcMotor)map.get("C"));
        motorD = new Motor((DcMotor)map.get("D"));
        driveMotors = new ArrayList<Motor>();
        driveMotors.add(motorA);
        driveMotors.add(motorB);
        driveMotors.add(motorC);
        driveMotors.add(motorD);
        for (Motor motor : driveMotors) {

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

    public void stop() {
        for (Motor motor : driveMotors) {
            motor.updateSpeed(0);
        }
    }

    public void backward() {
        motorA.updateSpeed(-SPEED);
        motorB.updateSpeed( SPEED);
        motorC.updateSpeed(-SPEED);
        motorD.updateSpeed( SPEED);
    }

    public void forward() {
        motorA.updateSpeed( SPEED);
        motorB.updateSpeed(-SPEED);
        motorC.updateSpeed( SPEED);
        motorD.updateSpeed(-SPEED);
    }

    public void right() {
        motorA.updateSpeed( SPEED);
        motorB.updateSpeed( SPEED);
        motorC.updateSpeed(-SPEED);
        motorD.updateSpeed(-SPEED);
    }

    public void left() {
        motorA.updateSpeed(-SPEED);
        motorB.updateSpeed(-SPEED);
        motorC.updateSpeed( SPEED);
        motorD.updateSpeed( SPEED);
    }

    public void forwardRight() {
        motorA.updateSpeed(-SPEED);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed( SPEED);
    }

    public void forwardLeft() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed( SPEED);
        motorC.updateSpeed(-SPEED);
        motorD.updateSpeed( 0);
    }

    public void backwardRight() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed(-SPEED);
        motorC.updateSpeed( SPEED);
        motorD.updateSpeed( 0);
    }

    public void backwardLeft() {
        motorA.updateSpeed( SPEED);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed(-SPEED);
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
            default:
                this.stop();
                break;
        }
    }

    public void rotRight() {
        for(Motor motor : driveMotors) {
            motor.updateSpeed(-SPEED);
        }
    }

    public void rotLeft() {
        for(Motor motor : driveMotors) {
            motor.updateSpeed( SPEED);
        }
    }

}
