package com.sarveshparab;

import java.util.ArrayList;
import java.util.List;

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
public class KnightsTour {
    private static final int DEFAULT_BOARD_SIZE = 5;

    private final int boardSize;

    public KnightsTour() {
        this(DEFAULT_BOARD_SIZE);
    }

    public KnightsTour(int boardSize) {
        this.boardSize = boardSize;
    }

    public int[][] computeTour() {
        int[][] board = new int[this.boardSize][this.boardSize];
        boolean tourFound = false;

        for (int i = 0; !tourFound && i < this.boardSize; i++) {
            for (int j = 0; !tourFound && j < this.boardSize; j++) {
                board[i][j] = 1;
                tourFound = _computeTour(board, i, j, 1);
            }
        }
        if (tourFound)
            return board;
        else
            return null;
    }

    private boolean _computeTour(int[][] board, int placedRow, int placedCol, int movesMade) {
        if (movesMade == this.boardSize * this.boardSize) {
            return true;
        }

        List<int[]>possibleMoves =  getPossibleMoves(board, placedRow, placedCol);
        for (int[] move : possibleMoves) {
            board[move[0]][move[1]] = movesMade + 1;
            if (_computeTour(board, move[0], move[1], movesMade + 1)) {
                return true;
            } else {
                board[move[0]][move[1]] = 0;
            }
        }

        return false;
    }

    private List<int[]> getPossibleMoves(int[][] board, int row, int col) {
        List<int[]> moves = new ArrayList<>();

        if (isMoveValid(board, row + 2, col + 1)) {
            moves.add(new int[]{row + 2, col + 1});
        }
        if (isMoveValid(board, row + 2, col - 1)) {
            moves.add(new int[]{row + 2, col - 1});
        }
        if (isMoveValid(board, row - 2, col + 1)) {
            moves.add(new int[]{row - 2, col + 1});
        }
        if (isMoveValid(board, row - 2, col - 1)) {
            moves.add(new int[]{row - 2, col - 1});
        }

        if (isMoveValid(board, row + 1, col + 2)) {
            moves.add(new int[]{row + 1, col + 2});
        }
        if (isMoveValid(board, row - 1, col + 2)) {
            moves.add(new int[]{row - 1, col + 2});
        }
        if (isMoveValid(board, row + 1, col - 2)) {
            moves.add(new int[]{row + 1, col - 2});
        }
        if (isMoveValid(board, row - 1, col - 2)) {
            moves.add(new int[]{row - 1, col - 2});
        }

        return moves;
    }

    private boolean isMoveValid(int[][] board, int row, int col) {
        return (row >= 0 && col >= 0 && row < this.boardSize && col < this.boardSize && board[row][col] == 0);
    }

    public void showBoard(int[][] board){
        for(int i=0; i<this.boardSize; i++){
            for(int j=0; j<this.boardSize; j++){
                System.out.print(board[i][j]+ "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
