package com.sarveshparab;

import java.util.Random;
import java.util.Scanner;

/**
 * Problem: Tic-Tac-Toe II
 *   -- https://beta.sdeskills.com/30day-challenge/day4
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class TicTacToe {
    private static final int DEFAULT_BOARD_SIZE = 3;
    private static final Random rand = new Random();

    private final Scanner userInput;
    private final int boardSize;
    private final BoardPlayerEnum humanPlayer;
    private final BoardPlayerEnum compPlayer;
    private final String compStrategy;
    private final BoardPlayerEnum[][] boardState;
    private final int[] verticalSum;
    private final int[] horizontalSum;
    private int equiDiagonal;
    private int nonEquiDiagonal;
    private int xMoves;
    private int oMoves;

    public TicTacToe(BoardPlayerEnum humanPlayer, BoardPlayerEnum compPlayer, String compStrategy) {
        this(humanPlayer, compPlayer, compStrategy, DEFAULT_BOARD_SIZE);
    }

    public TicTacToe(BoardPlayerEnum humanPlayer, BoardPlayerEnum compPlayer, String compStrategy, int boardSize) {
        this.userInput = new Scanner(System.in);
        this.boardSize = boardSize;
        this.humanPlayer = humanPlayer;
        this.compPlayer = compPlayer;
        this.compStrategy = compStrategy;

        this.boardState = new BoardPlayerEnum[this.boardSize][this.boardSize];
        this.verticalSum = new int[this.boardSize];
        this.horizontalSum = new int[this.boardSize];

        for (int i=0; i<this.boardSize; i++) {
            this.verticalSum[i] = BoardPlayerEnum.EMPTY.getVal();
            this.horizontalSum[i] = BoardPlayerEnum.EMPTY.getVal();
            for (int j = 0; j < this.boardSize; j++) {
                this.boardState[i][j] = BoardPlayerEnum.EMPTY;
            }
        }

        this.equiDiagonal = BoardPlayerEnum.EMPTY.getVal();
        this.nonEquiDiagonal = BoardPlayerEnum.EMPTY.getVal();
    }

    public void start() {
        while(getGameState() == GameStateEnum.IN_PLAY){
            if(this.compPlayer == BoardPlayerEnum.X){
                int compPos = this.compStrategy.equalsIgnoreCase("S") ? getSmartPositionOptimized() : getRandomPosition();
                makeMove(this.compPlayer, compPos/this.boardSize, compPos%this.boardSize);
                if (getGameState() != GameStateEnum.IN_PLAY){
                    break;
                }
                int humanPos = getUserInput();
                makeMove(this.humanPlayer, humanPos/this.boardSize, humanPos%this.boardSize);
            } else {
                int humanPos = getUserInput();
                makeMove(this.humanPlayer, humanPos/this.boardSize, humanPos%this.boardSize);
                if (getGameState() != GameStateEnum.IN_PLAY){
                    break;
                }
                int compPos = this.compStrategy.equalsIgnoreCase("S") ? getSmartPositionOptimized() : getRandomPosition();
                makeMove(this.compPlayer, compPos/this.boardSize, compPos%this.boardSize);
            }
        }

        System.out.println("----------------------------------------------");
        System.out.println("Game Finished!!, " + (getGameState().getVal() == this.humanPlayer.getVal() ? "You WON" : getGameState().getVal() == 0?"Its a DRAW":"You LOST"));
        showBoard();
        System.out.println("----------------------------------------------");
    }

    private int getRandomPosition() {
        int row = rand.nextInt(this.boardSize);
        int col = rand.nextInt(this.boardSize);

        while(!positionValid(row, col)){
            row = rand.nextInt(this.boardSize);
            col = rand.nextInt(this.boardSize);
        }

        return row * this.boardSize + col;
    }

    private int getSmartPosition() {
        int val = this.compPlayer == BoardPlayerEnum.X ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int pos = 0;

        for (int i = 0; i < this.boardSize ; i++){
            for (int j = 0; j < this.boardSize ; j++) {
                if (this.boardState[i][j] == BoardPlayerEnum.EMPTY){
                    makeMove(this.compPlayer, i, j);
                    int moveVal = minimax(0, this.compPlayer == BoardPlayerEnum.X ? BoardPlayerEnum.O : BoardPlayerEnum.X);
                    undoMove(this.compPlayer, i, j);

                    if (this.compPlayer == BoardPlayerEnum.X){
                        if (moveVal > val){
                            val = moveVal;
                            pos = i * this.boardSize + j;
                        }
                    } else {
                        if (moveVal < val){
                            val = moveVal;
                            pos = i * this.boardSize + j;
                        }
                    }
                }
            }
        }

        return pos;
    }

    private int getSmartPositionOptimized() {
        int[] ans = minimaxOptimized(0, this.compPlayer);
        return ans[1] * this.boardSize + ans[2];
    }

    private int minimax(int depth, BoardPlayerEnum player){
        GameStateEnum gameState = getGameState();
        if (Math.abs(gameState.getVal()) == 1){
            return gameState.getVal();
        }

        if (gameState == GameStateEnum.DRAW){
            return 0;
        }

        int val;
        if (player == BoardPlayerEnum.X){
            val = Integer.MIN_VALUE;

            for (int i = 0; i < this.boardSize ; i++){
                for (int j = 0; j < this.boardSize ; j++){
                    if (this.boardState[i][j] == BoardPlayerEnum.EMPTY){
                        this.boardState[i][j] = BoardPlayerEnum.X;
                        makeMove(BoardPlayerEnum.X, i, j);
                        val = Math.max(val, minimax(depth + 1, BoardPlayerEnum.O));
                        undoMove(BoardPlayerEnum.X, i, j);
                    }
                }
            }

        } else {
            val = Integer.MAX_VALUE;

            for (int i = 0; i < this.boardSize ; i++){
                for (int j = 0; j < this.boardSize ; j++){
                    if (this.boardState[i][j] == BoardPlayerEnum.EMPTY){
                        makeMove(BoardPlayerEnum.O, i, j);
                        val = Math.min(val, minimax(depth + 1, BoardPlayerEnum.X));
                        undoMove(BoardPlayerEnum.O, i, j);
                    }
                }
            }
        }
        return val;
    }

    private int[] minimaxOptimized(int depth, BoardPlayerEnum player){
        GameStateEnum gameState = getGameState();
        if (Math.abs(gameState.getVal()) == 1){
            return new int[]{gameState.getVal(), -1, -1};
        }

        if (gameState == GameStateEnum.DRAW){
            return new int[]{0, -1, -1};
        }

        int val, x = -1, y = -1;
        if (player == BoardPlayerEnum.X){
            val = Integer.MIN_VALUE;

            for (int i = 0; i < this.boardSize ; i++){
                for (int j = 0; j < this.boardSize ; j++){
                    if (this.boardState[i][j] == BoardPlayerEnum.EMPTY){
                        this.boardState[i][j] = BoardPlayerEnum.X;
                        makeMove(BoardPlayerEnum.X, i, j);

                        int[] moveValArr = minimaxOptimized(depth + 1, BoardPlayerEnum.O);
                        if (moveValArr[0] > val){
                            val = moveValArr[0];
                            x = i;
                            y = j;
                        }

                        undoMove(BoardPlayerEnum.X, i, j);
                    }
                }
            }

        } else {
            val = Integer.MAX_VALUE;

            for (int i = 0; i < this.boardSize ; i++){
                for (int j = 0; j < this.boardSize ; j++){
                    if (this.boardState[i][j] == BoardPlayerEnum.EMPTY){
                        makeMove(BoardPlayerEnum.O, i, j);

                        int[] moveValArr = minimaxOptimized(depth + 1, BoardPlayerEnum.X);
                        if (moveValArr[0] < val){
                            val = moveValArr[0];
                            x = i;
                            y = j;
                        }

                        undoMove(BoardPlayerEnum.O, i, j);
                    }
                }
            }
        }
        return new int[]{val, x, y};
    }

    private void makeMove(BoardPlayerEnum player, int row, int col) {
        if(player == BoardPlayerEnum.X){
            this.xMoves++;
        } else if (player == BoardPlayerEnum.O){
            this.oMoves++;
        }
        this.boardState[row][col] = player;
        this.horizontalSum[row] += player.getVal();
        this.verticalSum[col] += player.getVal();
        if(row == col)
            this.equiDiagonal += player.getVal();
        if(this.boardSize - 1 == row + col)
            this.nonEquiDiagonal += player.getVal();
    }

    private void undoMove(BoardPlayerEnum player, int row, int col) {
        if(player == BoardPlayerEnum.X){
            this.xMoves--;
        } else if(player == BoardPlayerEnum.O){
            this.oMoves--;
        }
        this.boardState[row][col] = BoardPlayerEnum.EMPTY;
        this.horizontalSum[row] -= player.getVal();
        this.verticalSum[col] -= player.getVal();
        if(row == col)
            this.equiDiagonal -= player.getVal();
        if(this.boardSize - 1 == row + col)
            this.nonEquiDiagonal -= player.getVal();
    }

    private GameStateEnum getGameState() {
        if(this.equiDiagonal == 3 || this.nonEquiDiagonal == 3){
            return GameStateEnum.X_WON;
        } else if(this.equiDiagonal == -3 || this.nonEquiDiagonal == -3){
            return GameStateEnum.O_WON;
        }

        for (int i=0; i<this.boardSize; i++) {
            if(this.verticalSum[i] == 3 || this.horizontalSum[i] == 3){
                return GameStateEnum.X_WON;
            } else if(this.verticalSum[i] == -3 || this.horizontalSum[i] == -3){
                return GameStateEnum.O_WON;
            }
        }

        if (Math.abs(this.xMoves - this.oMoves) > 1){
            return GameStateEnum.BAD;
        }

        if (this.xMoves + this.oMoves == this.boardSize * this.boardSize){
            return GameStateEnum.DRAW;
        } else {
            return GameStateEnum.IN_PLAY;
        }
    }

    private int getUserInput(){
        showBoard();
        System.out.print("Choose a position (x,y): ");
        String pos = userInput.nextLine();
        while(!positionValid(Integer.parseInt(pos.split(",")[0]), Integer.parseInt(pos.split(",")[1]))){
            System.out.print("Not a valid position, Choose again (x,y): ");
            pos = userInput.nextLine();
        }
        return Integer.parseInt(pos.split(",")[0]) * this.boardSize + Integer.parseInt(pos.split(",")[1]);
    }

    private boolean positionValid(int row, int col) {
        if (row >= this.boardSize || col >= this.boardSize){
            return false;
        }
        if (this.boardState[row][col] != BoardPlayerEnum.EMPTY){
            return false;
        }
        return true;
    }

    public void showBoard(){
        System.out.print("+ ");
        for(int i=0; i<this.boardSize; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i=0; i < this.boardSize; i++){
            System.out.print(i + " ");
            for(int j=0; j < this.boardSize; j++){
                System.out.print(this.boardState[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.println();
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

    public enum GameStateEnum {
        IN_PLAY(0), X_WON(1), O_WON(-1), DRAW(0), BAD(0);

        private final int val;

        GameStateEnum(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}
