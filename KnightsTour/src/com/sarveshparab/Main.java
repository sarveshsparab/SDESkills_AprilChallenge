package com.sarveshparab;

/**
 * Problem: Knight's Tour
 *   -- https://beta.sdeskills.com/30day-challenge/day8
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    public static void main(String[] args) {
        KnightsTour knightsTour = new KnightsTour();
        int[][] tour = knightsTour.computeTour();
        System.out.println("----------------------------------------------");
        if (tour != null){
            System.out.println("Tour Found: ");
            knightsTour.showBoard(tour);
        } else {
            System.out.println("No Tour exists!");
        }
    }
}
