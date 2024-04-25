package TowerOfHanoi;

/**
 Assignment 3 - Fall 2022
 Question - 2
 Student - Sahib Deep Singh
 ID - 219170646
 Class - ITEC 2610 - A

 */


import java.util.Arrays;

public class TowersOfHanoi {
    private int totalDisks;
    private int count = 1;
    private int[][] peg = {{3, 2, 1}, {0, 0, 0}, {0, 0, 0}};

    //-----------------------------------------------------------------
// Sets up the puzzle with the specified number of disks.
//-----------------------------------------------------------------
    public TowersOfHanoi(int disks) {
        this.totalDisks = disks;
    }

    //-----------------------------------------------------------------
// Performs the initial call to moveTower to solve the puzzle.
// Moves the disks from tower 1 to tower 3 using tower 2.
//-----------------------------------------------------------------
    public void solve() {

        System.out.println("Initially:");
        System.out.println("peg0: " + Arrays.toString(peg[0]));
        System.out.println("peg1: " + Arrays.toString(peg[1]));
        System.out.println("peg2: " + Arrays.toString(peg[2]));

        moveTower(totalDisks, 1, 3, 2);

    }

    //-----------------------------------------------------------------
// Moves the specified number of disks from one tower to another
// by moving a subtower of n-1 disks out of the way, moving one
// disk, then moving the subtower back. Base case of 1 disk.
//-----------------------------------------------------------------
    private void moveTower(int numDisks, int start, int end, int temp) {
        if (numDisks == 1) {
            moveOneDisk(start, end, 1);
            return;
        }

        moveTower(numDisks - 1, start, temp, end);
        moveOneDisk(start, end, numDisks);
        moveTower(numDisks - 1, temp, end, start);

    }


    private void moveOneDisk(int start, int end, int numDisks) {

        System.out.println("Step" + count + ": Move disk" + numDisks + " from peg" + (start - 1) + " to peg" + (end - 1)+ " resulting");

        int fromindex = 0, toindex = 0;

        for(int index=0; index < totalDisks; index++)
        {
            if(peg[start-1][index] != 0)
            {
                fromindex = index;
            }
            else
            {
                break;
            }
        }

        for(int j =0; j < totalDisks; j++)
        {
            if(peg[end-1][j] == 0)
            {
                toindex = j;
                break;
            }
        }

        peg[end-1][toindex] = peg[start-1][fromindex];
        peg[start-1][fromindex] = 0;

        System.out.println("peg0: " + Arrays.toString(peg[0]));
        System.out.println("peg1: " + Arrays.toString(peg[1]));
        System.out.println("peg2: " + Arrays.toString(peg[2]));

        count++;

    }

}
