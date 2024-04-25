package Parallelogram;

public class Shapes {
    public static void main(String[] args){

        Parallelogram[] shapes  = new Parallelogram[5];
        shapes[0] = new Parallelogram(5,4,30);
        shapes[1] = new Diamond(5, 40);
        shapes[2] = new Square(2.3);
        shapes[3] = new Parallelogram(2.4,6.8, 25);
        shapes[4] = new Rectangle( 5, 4.6);


        for(Parallelogram shape:shapes){
            System.out.println(shape);
        }


    }
}
