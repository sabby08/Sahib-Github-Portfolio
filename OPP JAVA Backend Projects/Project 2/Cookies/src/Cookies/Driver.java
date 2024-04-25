package Cookies;

/**
 Assignment 3 - Fall 2022
 Question - 3
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

import java.util.Arrays;

public class Driver {

    public static void main(String args[]){

        int[] cookies1 = {8,15,10,20,8};
        int k1 = 2;

        Cookies distributer = new Cookies(cookies1,k1);

        distributer.distributeCookies();
        System.out.println("Cookie bags: "+ Arrays.toString(cookies1) +
                "\nNumber of children: " + k1 +
                "\nThe best distribution is: " +
                Arrays.toString(distributer.getBestDistribution()) +
                "\nThe standard deviation for this distribution: " + distributer.getBestSTD());

        System.out.println();
        int[] cookies2 = {6,1,3,2,2,4,1,2};
        int k2 = 3;

        distributer = new Cookies(cookies2,k2);

        distributer.distributeCookies();
        System.out.println("Cookie bags: "+ Arrays.toString(cookies2) +
                "\nNumber of children: " + k2 +
                "\nThe best distribution is: " +
                Arrays.toString(distributer.getBestDistribution()) +
                "\nThe standard deviation for this distribution: " + distributer.getBestSTD());

    }
}