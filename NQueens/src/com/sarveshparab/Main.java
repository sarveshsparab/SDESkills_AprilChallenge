package com.sarveshparab;

/**
 * Problem: N Queens Puzzle
 *   -- https://beta.sdeskills.com/30day-challenge/day6
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        int configurations = nQueens.computeAllConfigurations();

        System.out.println("Found " + configurations + " possible configurations");
        nQueens.showAllConfigurations();
    }
}
