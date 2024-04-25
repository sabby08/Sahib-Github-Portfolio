package Exceptions;

/**
 Assignment 3 - Fall 2022
 Question - 1
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

import java.util.Scanner;

public class ReadStrings
{

    public static void main(String[] args) throws StringTooLongException
    {
        final int MAX = 20;
        String input = "";

        Scanner scan = new Scanner(System.in);

        StringTooLongException lengthException =
                new StringTooLongException("String has too many characters");

        System.out.println("Enter strings, enter DONE when finished:");

        while(!false) {
            String text = scan.nextLine();
            if(text.length()>=MAX) {
                throw lengthException;
            }
            if(text.equals("DONE")) {
                break;
            }
        }
        System.out.println("Program successfully completed");
    }
}
