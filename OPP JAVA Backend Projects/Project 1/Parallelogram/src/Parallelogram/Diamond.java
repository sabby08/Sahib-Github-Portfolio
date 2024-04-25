package Parallelogram;

public class Diamond extends Parallelogram{
    public Diamond( double side, double angle){
        super(side,side,angle);
    }

    public String toString() {

        String Dia = "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        Dia  += "\nDiamond:";
        Dia  += "\nSide: "+ getSide1();
        Dia  += "\nAngle: "+ getAngle();
        Dia  += "\nArea: "+ getArea();
        Dia  += "\nCircumference: "+getCircumference();

        return Dia;
    }

}
