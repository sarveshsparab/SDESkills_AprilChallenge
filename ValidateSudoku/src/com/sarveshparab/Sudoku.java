package com.sarveshparab;

import java.util.HashSet;
import java.util.Set;

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
public class Sudoku {
    private int[][] board;

    public Sudoku() {
        this.board = new int[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                this.board[i][j] = -1;
            }
        }
    }

    public Sudoku(int[][] board){
        this.board = board;
    }

    public String checkSudoku(){
        if(isEmpty()){
            return "EMPTY";
        }

        for (int k = 0; k < 9; k++){
            if (!isRowValid(k) || !isColumnValid(k)) {
                return "INVALID";
            }
        }
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (!isBoxValid(3 * i, 3 * j)){
                    return "INVALID";
                }
            }
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(this.board[i][j] == -1){
                    return "VALID";
                }
            }
        }

        return "SOLVED";
    }

    private boolean isEmpty(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (this.board[i][j] != -1){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isColumnValid(int col){
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < 9; i++){
            if (visit.contains(this.board[i][col])){
                return false;
            }

            if (this.board[i][col] != -1){
                visit.add(this.board[i][col]);
            }
        }
        return true;
    }

    private boolean isRowValid(int row){
        Set<Integer> visit = new HashSet<>();
        for (int j = 0; j < 9; j++){
            if (visit.contains(this.board[row][j])){
                return false;
            }

            if (this.board[row][j] != -1){
                visit.add(this.board[row][j]);
            }
        }
        return true;
    }

    private boolean isBoxValid(int row, int col){
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (visit.contains(this.board[i + row][j + col])){
                    return false;
                }

                if (this.board[i + row][j + col] != -1){
                    visit.add(this.board[i + row][j + col]);
                }
            }
        }
        return true;
    }

    public void showBoard(){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
