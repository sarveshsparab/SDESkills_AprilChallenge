package com.sarveshparab;

/**
 * Problem: Validate Sudoku
 *   -- https://beta.sdeskills.com/30day-challenge/day2
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    public static void main(String[] args){
        Sudoku sudokuEmpty = new Sudoku();
        System.out.println(sudokuEmpty.checkSudoku());

        int[][] validBoard = {
                {-1,9,3,4,2,7,5,-1,-1},
                {-1,-1,7,-1,1,5,3,-1,-1},
                {-1,2,4,6,8,-1,-1,-1,7},
                {3,-1,-1,7,6,-1,2,1,9},
                {6,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,1,3,-1,-1,-1,-1},
                {4,-1,5,8,-1,-1,9,2,6},
                {-1,-1,1,2,-1,6,-1,7,3},
                {-1,7,-1,-1,-1,9,8,-1,-1}
        };
        Sudoku sudokuValid = new Sudoku(validBoard);
        System.out.println(sudokuValid.checkSudoku());

        int[][] invalidBoard = {
                {-1,9,3,4,2,7,5,-1,-1},
                {-1,-1,7,-1,1,5,3,-1,-1},
                {-1,2,9,6,8,-1,-1,-1,7},
                {3,-1,-1,7,6,-1,2,1,9},
                {6,-1,-1,-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,1,3,-1,-1,-1,-1},
                {4,-1,5,8,-1,-1,9,2,6},
                {-1,-1,1,2,-1,6,-1,7,3},
                {-1,7,-1,-1,-1,9,8,-1,-1}
        };
        Sudoku sudokuInvalid = new Sudoku(invalidBoard);
        System.out.println(sudokuInvalid.checkSudoku());

        int[][] solvedBoard = {
                {1,9,3,4,2,7,5,6,8},
                {8,6,7,9,1,5,3,4,2},
                {5,2,4,6,8,3,1,9,7},
                {3,5,8,7,6,4,2,1,9},
                {6,1,2,5,9,8,7,3,4},
                {7,4,9,1,3,2,6,8,5},
                {4,3,5,8,7,1,9,2,6},
                {9,8,1,2,5,6,4,7,3},
                {2,7,6,3,4,9,8,5,1}
        };
        Sudoku sudokuSolved = new Sudoku(solvedBoard);
        System.out.println(sudokuSolved.checkSudoku());
    }
}
