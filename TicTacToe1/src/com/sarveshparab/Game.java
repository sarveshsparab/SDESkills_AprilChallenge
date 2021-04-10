package com.sarveshparab;

import static com.sarveshparab.Game.BoardPlayerEnum.*;

public class Game {

    private static final int DEFAULT_BOARD_SIZE = 3;

    private final int boardSize;
    private final BoardPlayerEnum[][] boardState;
    private final int[] verticalSum;
    private final int[] horizontalSum;
    private int equiDiagonal;
    private int nonEquiDiagonal;
    private int xMoves;
    private int oMoves;

    public Game() {
        this(DEFAULT_BOARD_SIZE);
    }

    public Game(int boardSize) {
        this.boardSize = boardSize;

        this.equiDiagonal = EMPTY.getVal();
        this.nonEquiDiagonal = EMPTY.getVal();

        this.verticalSum = new int[this.boardSize];
        this.horizontalSum = new int[this.boardSize];
        this.boardState = new BoardPlayerEnum[this.boardSize][this.boardSize];
        for (int i=0; i<this.boardSize; i++) {
            this.verticalSum[i] = EMPTY.getVal();
            this.horizontalSum[i] = EMPTY.getVal();
            for (int j = 0; j < this.boardSize; j++) {
                this.boardState[i][j] = EMPTY;
            }
        }

        this.xMoves = 0;
        this.oMoves = 0;
    }

    public String play(char player, int x, int y){
        if(player == X.getSymbol()){
            if((this.xMoves == this.oMoves) && (this.xMoves + this.oMoves != this.boardSize * this.boardSize)){
                if(this.boardState[x][y] == EMPTY){
                    this.boardState[x][y] = X;
                    this.xMoves++;
                    this.horizontalSum[x] += X.getVal();
                    this.verticalSum[y] += X.getVal();
                    if(x == y)
                        this.equiDiagonal += X.getVal();
                    if(this.boardSize - 1 == x + y)
                        this.nonEquiDiagonal += X.getVal();

                    return "OK";
                } else {
                    return "BAD";
                }
            } else {
                return "BAD";
            }
        } else if(player == O.getSymbol()){
            if((this.oMoves == this.xMoves - 1) && (this.xMoves + this.oMoves != this.boardSize * this.boardSize)){
                if(this.boardState[x][y] == EMPTY){
                    this.boardState[x][y] = O;
                    this.oMoves++;
                    this.horizontalSum[x] += O.getVal();
                    this.verticalSum[y] += O.getVal();
                    if(x == y)
                        this.equiDiagonal += O.getVal();
                    if(this.boardSize - 1 == x + y)
                        this.nonEquiDiagonal += O.getVal();

                    return "OK";
                } else {
                    return "BAD";
                }
            } else {
                return "BAD";
            }
        } else {
            return "BAD";
        }
    }

    public String checkWin(){
        if(this.equiDiagonal == 3 || this.nonEquiDiagonal == 3){
            return "X WON";
        } else if(this.equiDiagonal == -3 || this.nonEquiDiagonal == -3){
            return "O WON";
        }

        for (int i=0; i<this.boardSize; i++) {
            if(this.verticalSum[i] == 3 || this.horizontalSum[i] == 3){
                return "X WON";
            } else if(this.verticalSum[i] == -3 || this.horizontalSum[i] == -3){
                return "O WON";
            }
        }

        if (Math.abs(this.xMoves - this.oMoves) > 1){
            return "BAD BOARD";
        }

        if (this.xMoves + this.oMoves == this.boardSize * this.boardSize){
            return "DRAW";
        } else {
            return "INPROGRESS";
        }
    }

    public void showBoard(){
        for(int i=0; i<this.boardSize; i++){
            for(int j=0; j<this.boardSize; j++){
                System.out.print(this.boardState[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getBoardSize(){
        return this.boardSize;
    }

    public enum BoardPlayerEnum {
        X(1, 'x'),O(-1, 'o'),EMPTY(0, '-');

        private final int value;
        private final char symbol;

        BoardPlayerEnum(int state, char symbol) {
            this.value = state;
            this.symbol = symbol;
        }

        public int getVal(){
            return value;
        }

        public char getSymbol(){
            return symbol;
        }
    }
}
