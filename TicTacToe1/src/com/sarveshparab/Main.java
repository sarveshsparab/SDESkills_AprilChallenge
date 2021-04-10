package com.sarveshparab;

import java.util.Random;

import static com.sarveshparab.Game.BoardPlayerEnum.O;
import static com.sarveshparab.Game.BoardPlayerEnum.X;

public class Main {

    private static final Random rand = new Random();

    public static void main(String[] args){

        Game ticTacToe = new Game();
        int moves = 0;
        int maxMoves = ticTacToe.getBoardSize() * ticTacToe.getBoardSize();

        ticTacToe.showBoard();

        while (moves < maxMoves){
            if(moves % 2 == 0){
                if(ticTacToe.play(X.getSymbol(), rand.nextInt(ticTacToe.getBoardSize()), rand.nextInt(ticTacToe.getBoardSize())).equals("OK")){
                    if(ticTacToe.checkWin().equals("X WON")){
                        moves = maxMoves;
                    }
                    moves++;
                    ticTacToe.showBoard();
                }
            } else {
                if(ticTacToe.play(O.getSymbol(), rand.nextInt(ticTacToe.getBoardSize()), rand.nextInt(ticTacToe.getBoardSize())).equals("OK")){
                    if(ticTacToe.checkWin().equals("O WON")){
                        moves = maxMoves;
                    }
                    moves++;
                    ticTacToe.showBoard();
                }
            }
        }

        System.out.println(ticTacToe.checkWin());

    }

}
