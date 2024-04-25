package StudentAward;

import java.text.NumberFormat;
import java.util.Locale;

/**
 Assignment 2 - Fall 2022
 Question - 2
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

public class FullTime extends Student {

    private static double fullTimeTuition= 18000;
    private double awardValue;


    public FullTime(int studentID, String firstName, String lastName, double GPA) {

        super(studentID, firstName, lastName, GPA);
        awardValue = 0;

    }

    public void award(double value){

        NumberFormat fmt = NumberFormat.getCurrencyInstance(Locale.CANADA);

        awardValue = value;
        System.out.println("Student " + firstName + " " + lastName + " GPA " + getGPA() + "| Awarded " + fmt.getCurrency() + value);

    }

    @Override
    public double getAmountDue() {

        return fullTimeTuition - awardValue;
    }
    public boolean isFullTime(){

        return true;

    }

    @Override
    public String toString() {

        return super.toString() + ", Full-time \n Amount due:" + getAmountDue();
    }

}
