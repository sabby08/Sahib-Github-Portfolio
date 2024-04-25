package Parallelogram;

public class Rectangle extends Parallelogram {

    public Rectangle(double side1, double side2) {
        super(side1, side2, 90);
    }

    public String toString() {

        String Rec = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Rec  += "\nRectangle:";
        Rec += "\nSides: " + getSide1() + ", " + getSide2();
        Rec += "\nArea: " + getArea();
        Rec += "\nCircumference: " + getCircumference();

        return Rec;

    }
}