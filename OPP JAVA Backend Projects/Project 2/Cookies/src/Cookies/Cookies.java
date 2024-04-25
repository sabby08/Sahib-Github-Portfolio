package Cookies;

/**
 Assignment 3 - Fall 2022
 Question - 3
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

public class Cookies {

    private double bestSTD = Integer.MAX_VALUE;
    private int[] bestDistribution;
    private int[] cookies;
    private int nChildren;


    public Cookies(int[] cookies, int nChildren){

        this.cookies = cookies;
        this.nChildren = nChildren;
    }



    public static double calculateSD(int[] numArray)
    {

        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;

        for(double num : numArray) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    }

    public void distributeCookies() {

        int children[]= new int[this.nChildren];
        bestDistribution = new int[this.nChildren];
        distributeHelper(0,cookies, nChildren, children);

        return;
    }

    private void distributeHelper(int start, int[] cookies, int nChildren, int[] children) {

        if(start == cookies.length)
        {

            double dev = calculateSD(children);

            if(dev < bestSTD){

                bestSTD = dev;
                for(int i =0; i < children.length; i++){
                    bestDistribution[i] = children[i];
                }
            }

            return;
        }
        for(int i = 0; i < nChildren; i++)
        {
            children[i]+=cookies[start];
            distributeHelper(start+1, cookies, nChildren, children);
            children[i]-=cookies[start];
            if(children[i]==0) break;
        }
    }

    public int[] getBestDistribution(){

        return bestDistribution;
    }

    public double getBestSTD(){
        return bestSTD;
    }
}