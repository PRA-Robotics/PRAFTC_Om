package org.firstinspires.ftc.teamcode;

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
    private static double SPEED = 0.2;

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
        if (script.size() == 0) {
            return;
        }
        if (ticks == 0) {
            forward();
        }
        if (ticks == script.get(0)[1]) {
            stop();
            ticks = 0;
            script.remove(0);
        }
        ticks++;
    }

    public void stop() {
        for (DcMotor motor : driveMotors) {
            motor.setPower(0);
        }
    }

    public void forward() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, SPEED);
    }

    public void backward() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, -SPEED);
    }

    public void left() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, -SPEED);
    }

    public void right() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, SPEED);
    }

    public void forwardRight() {
      Util.updatePower(motorA, -SPEED);
      Util.updatePower(motorB, 0);
      Util.updatePower(motorC, SPEED);
      Util.updatePower(motorD, 0);
    }

    public void forwardLeft() {
      Util.updatePower(motorA, 0);
      Util.updatePower(motorB, SPEED);
      Util.updatePower(motorC, 0);
      Util.updatePower(motorD, -SPEED);
    }

    public void backwardRight() {
      Util.updatePower(motorA, 0);
      Util.updatePower(motorB, -SPEED);
      Util.updatePower(motorC, 0);
      Util.updatePower(motorD, SPEED);
    }

    public void backwardLeft() {
      Util.updatePower(motorA, SPEED);
      Util.updatePower(motorB, 0);
      Util.updatePower(motorC, -SPEED);
      Util.updatePower(motorD, 0);
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
