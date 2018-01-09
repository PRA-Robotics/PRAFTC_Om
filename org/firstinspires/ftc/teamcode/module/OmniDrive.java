package org.firstinspires.ftc.teamcode.module;


import org.firstinspires.ftc.teamcode.util.Util;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.ArrayList;


public class OmniDrive {

//variables

    private HardwareMap map;
    private int speedStatus = 0;
    private Motor motorA;
    private Motor motorB;
    private Motor motorC;
    private Motor motorD;
    private ArrayList<Motor> driveMotors;
    private ArrayList<double> initialEncoders;
    private int ticks;
    private double distance;
    private static double SPEED = 1;
    //.private static int TPR = 560;
    //private static double D = 10.0;

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
        initialEncoders.add(0);
        initialEncoders.add(0);
        initialEncoders.add(0);
        initialEncoders.add(0);

        //motorA.setDirection(DcMotor.Direction.REVERSE);
        //motorB.setDirection(DcMotor.Direction.REVERSE);
        ticks = 0;
        distance = 0;

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

//Methods

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
            totalTicks += (driveMotors.get(i).getEncoder() - initialEncoders.get(i));
        }
        return (totalTicks/driveMotors.size());
    }

    public String tick() {
        return ("" + motorA.getEncoder());
    }

    public void stop() {
        for (Motor motor : driveMotors) {
            motor.updateSpeed(0);
        }
    }
    public void backward() {
        motorA.updateSpeed(SPEED);
        motorB.updateSpeed(- SPEED);
        motorC.updateSpeed(SPEED);
        motorD.updateSpeed(- SPEED);
    }

    public void forward() {
        motorA.updateSpeed(-SPEED);
        motorB.updateSpeed(SPEED);
        motorC.updateSpeed(-SPEED);
        motorD.updateSpeed(SPEED);
    }

    public void right() {
        motorA.updateSpeed( -SPEED);
        motorB.updateSpeed( -SPEED);
        motorC.updateSpeed(SPEED);
        motorD.updateSpeed(SPEED);
    }

    public void left() {
        motorA.updateSpeed(SPEED);
        motorB.updateSpeed(SPEED);
        motorC.updateSpeed( -SPEED);
        motorD.updateSpeed( -SPEED);
    }

    public void forwardRight() {
        motorA.updateSpeed( -SPEED);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed(SPEED);
    }

    public void forwardLeft() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed(SPEED);
        motorC.updateSpeed( -SPEED);
        motorD.updateSpeed( 0);
    }

    public void backwardRight() {
        motorA.updateSpeed( 0);
        motorB.updateSpeed( -SPEED);
        motorC.updateSpeed(SPEED);
        motorD.updateSpeed( 0);
    }

    public void backwardLeft() {
        motorA.updateSpeed(SPEED);
        motorB.updateSpeed( 0);
        motorC.updateSpeed( 0);
        motorD.updateSpeed( -SPEED);
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

    public void cutSpeed(){
        if(speedStatus % 2 == 0){
            SPEED/=2;
        }else{
            SPEED*=2;
        }
        speedStatus++;
    }

}
