package com.sarveshparab;

import java.util.ArrayList;
import java.util.List;

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
public class NQueens {

    private static final int DEFAULT_BOARD_SIZE = 8;

    private int boardSize;
    private final List<BoardPieceEnum[][]> resultBoards;

    public NQueens() {
        this(DEFAULT_BOARD_SIZE);
    }

    public NQueens(int boardSize) {
        this.boardSize = boardSize;
        this.resultBoards = new ArrayList<>();
    }

    public int computeAllConfigurations() {
        BoardPieceEnum[][] initBoard = new BoardPieceEnum[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                initBoard[i][j] = BoardPieceEnum.EMPTY;
            }
        }
        backtrack(initBoard, this.boardSize, 0);
        return this.resultBoards.size();
    }

    public void showAllConfigurations() {
        System.out.println();
        for (int i = 0; i < this.resultBoards.size(); i++){
            System.out.println("----------------------- [" + (i+1) + "] -----------------------");
            showBoard(this.resultBoards.get(i));
        }
    }

    private void backtrack(BoardPieceEnum[][] board, int queens, int row) {
        if (queens == 0) {
            this.resultBoards.add(deepCopy(board));
        }

        for (int i = row; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (isAllowedPosition(board, i, j)) {
                    board[i][j] = BoardPieceEnum.QUEEN;
                    backtrack(board, queens - 1, i + 1);
                    board[i][j] = BoardPieceEnum.EMPTY;
                }
            }
        }
    }

    private boolean isAllowedPosition(BoardPieceEnum[][] board, int row, int col) {
        for (int i = 0; i < this.boardSize; i++) {
            if (board[i][col] == BoardPieceEnum.QUEEN) {
                return false;
            }
            if (board[row][i] == BoardPieceEnum.QUEEN) {
                return false;
            }
            if ((row+i<this.boardSize && col+i<this.boardSize) && board[row+i][col+i] == BoardPieceEnum.QUEEN) {
                return false;
            }
            if ((row+i<this.boardSize && col-i>=0) && board[row+i][col-i] == BoardPieceEnum.QUEEN) {
                return false;
            }
            if ((row-i>=0 && col+i<this.boardSize) && board[row-i][col+i] == BoardPieceEnum.QUEEN) {
                return false;
            }
            if ((row-i>=0 && col-i>=0) && board[row-i][col-i] == BoardPieceEnum.QUEEN) {
                return false;
            }
        }

        return true;
    }

    private void showBoard(BoardPieceEnum[][] board){
        for(int i=0; i<this.boardSize; i++){
            for(int j=0; j<this.boardSize; j++){
                System.out.print(board[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private BoardPieceEnum[][] deepCopy(BoardPieceEnum[][] oldBoard) {
        BoardPieceEnum[][] copyBoard = new BoardPieceEnum[this.boardSize][this.boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                copyBoard[i][j] = oldBoard[i][j];
            }
        }
        return copyBoard;
    }

    public enum BoardPieceEnum {
        QUEEN('Q'),EMPTY('-');

        private final char symbol;

        BoardPieceEnum(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol(){
            return symbol;
        }
    }
}
