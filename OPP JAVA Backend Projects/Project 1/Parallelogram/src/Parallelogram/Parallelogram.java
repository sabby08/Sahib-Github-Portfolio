package Parallelogram;
import java.util.*;
import java.lang.Math;

/**
 Assignment 2 - Fall 2022
 Question - 1
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

public class Parallelogram {
    private double side1;
    private double side2;
    private double angle;

    public Parallelogram(double side1, double side2, double angle){

        this.side1 = side1;
        this.side2 = side2;
        this.angle = convertToRadians(angle);
    }

    private double convertToRadians(double inDegrees ){

        return (Math.PI*inDegrees)/180.0;
    }

    public double getSide1(){

        return side1;
    }

    public double getSide2(){

        return side2;

    }
    public double getAngle(){

        return angle;
    }

    public double getArea(){

        return side1*side2*(Math.sin(angle));
    }
    public double getCircumference(){

        return 2 * (side1 + side2);
    }

    @Override
    public String toString(){

        String Par;
        Par = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Par += "\nParallelogram:";
        Par += "\nSides: "+side1+", "+side2;
        Par += "\nAngle between the sides in radians: "+ getAngle();
        Par += "\nArea: "+getArea();
        Par += "\nCircumference: "+getCircumference();

        return Par;

    }
}
