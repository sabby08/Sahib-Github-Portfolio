package StudentAward;

/**
 Assignment 2 - Fall 2022
 Question - 2
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

public class PartTime extends Student {
    private static double partTimeTuition= 9500;



    public PartTime(int studentID, String firstName, String lastName, double GPA) {

        super(studentID, firstName, lastName, GPA);
    }
    @Override
    public double getAmountDue(){

        return partTimeTuition;
    }
    public boolean isFullTime(){

        return false;
    }

    @Override
    public String toString() {

        return super.toString() + ", Part-time \n Amount due:" + getAmountDue();

    }


}
