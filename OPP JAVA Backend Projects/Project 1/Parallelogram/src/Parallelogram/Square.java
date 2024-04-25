package Parallelogram;

public class Square extends Rectangle {
    public Square(double side){
        super(side,side);
    }
    public String toString() {

        String Square = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Square += "\nSquare:";
        Square += "\nSide: "+getSide1();
        Square += "\nArea: "+getArea();
        Square += "\nCircumference: "+getCircumference();

        return Square;
    }
}
