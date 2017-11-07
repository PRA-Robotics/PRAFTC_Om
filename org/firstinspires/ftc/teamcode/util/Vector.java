package org.firstinspires.ftc.teamcode.util;


public class Vector {
    
//Variables
    
    private double x;
    private double y;
    
//Constructors

    public Vector() {
        x = 0;
        y = 0;
    }
    
    public Vector(double v1, double v2) {
        x = v1;
        y = v2;
    }
    
//Get/Setters

    public double getX() {
        return x;
    }
    
    public void setX(double v) {
        x = v;
    }
    
    public double getY() {
        return y;
    }
    
    public void setY(double v) {
        y = v;
    }
}
