package com.sarveshparab;

import java.util.Random;

/**
 * Problem: Tic-Tac-Toe I
 *   -- https://beta.sdeskills.com/30day-challenge/day1
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    private static final Random rand = new Random();

    public static void main(String[] args){

        Game ticTacToe = new Game();
        int moves = 0;
        int maxMoves = ticTacToe.getBoardSize() * ticTacToe.getBoardSize();

        ticTacToe.showBoard();

        while (moves < maxMoves){
            if(moves % 2 == 0){
                if(ticTacToe.play(Game.BoardPlayerEnum.X.getSymbol(), rand.nextInt(ticTacToe.getBoardSize()), rand.nextInt(ticTacToe.getBoardSize())).equals("OK")){
                    if(ticTacToe.checkWin().equals("X WON")){
                        moves = maxMoves;
                    }
                    moves++;
                    ticTacToe.showBoard();
                }
            } else {
                if(ticTacToe.play(Game.BoardPlayerEnum.O.getSymbol(), rand.nextInt(ticTacToe.getBoardSize()), rand.nextInt(ticTacToe.getBoardSize())).equals("OK")){
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
