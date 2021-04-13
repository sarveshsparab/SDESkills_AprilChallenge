package com.sarveshparab;

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
public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("----------------------------------------------");
        System.out.print("Choose your side (X or O): ");
        String mySide = in.nextLine();
        System.out.print("Computer plays Random(R) or Smart(S): ");
        String compStrategy = in.nextLine();
        System.out.println("----------------------------------------------");
        System.out.println("Lets Begin, the computer will play " + (compStrategy.equalsIgnoreCase("S")?"Smartly":"Randomly")
                + ", " + (mySide.equalsIgnoreCase("X")?"You":"Computer") +" will play first");

        TicTacToe game = new TicTacToe(mySide.equalsIgnoreCase("X")? TicTacToe.BoardPlayerEnum.X:TicTacToe.BoardPlayerEnum.O,
                mySide.equalsIgnoreCase("X")? TicTacToe.BoardPlayerEnum.O:TicTacToe.BoardPlayerEnum.X,
                compStrategy.toUpperCase());

        game.start();
    }
}
