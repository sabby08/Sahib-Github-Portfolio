package StudentAward;

/**
 Assignment 2 - Fall 2022
 Question - 2
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */

public class Sorting
{

    public static void selectionSort(Comparable[] list)
    {

        int highest;

        for (int index = 0; index < list.length - 1; index++) {
            highest = index;
            for (int innerIndex = index + 1; innerIndex < list.length; innerIndex++) {
                if (list[innerIndex].compareTo(list[highest]) > 0) {
                    highest = innerIndex;
                }
            }

            Comparable temp = list[highest];
            list[highest] = list[index];
            list[index] = temp;
        }

    }

}
