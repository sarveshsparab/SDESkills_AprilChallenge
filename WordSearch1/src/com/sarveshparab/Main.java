package com.sarveshparab;

import java.util.List;

/**
 * Problem: Word Search I
 *   -- https://beta.sdeskills.com/30day-challenge/day9
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    public static void main(String[] args) {
        char[][] wordGrid = new char[][]{
                {'W', 'V', 'E', 'R', 'T', 'I', 'C', 'A', 'L', 'L'},
                {'R', 'O', 'O', 'A', 'F', 'F', 'L', 'S', 'A', 'B'},
                {'A', 'C', 'R', 'I', 'L', 'I', 'A', 'T', 'O', 'A'},
                {'N', 'D', 'O', 'D', 'K', 'O', 'N', 'W', 'D', 'C'},
                {'D', 'R', 'K', 'E', 'S', 'O', 'O', 'D', 'D', 'K'},
                {'O', 'E', 'E', 'P', 'Z', 'E', 'G', 'L', 'I', 'W'},
                {'M', 'S', 'I', 'I', 'H', 'O', 'A', 'E', 'R', 'A'},
                {'A', 'L', 'R', 'K', 'R', 'R', 'I', 'R', 'E', 'R'},
                {'K', 'O', 'D', 'I', 'D', 'E', 'D', 'R', 'C', 'D'},
                {'H', 'E', 'L', 'W', 'S', 'L', 'E', 'U', 'T', 'H'}
        };

        WordSearch wordSearch = new WordSearch(wordGrid);
        wordSearch.showGrid();
        System.out.println("----------------------------------------------");
        wordSearch.search("SEEK");
        wordSearch.search("FIND");
        wordSearch.search("RANDOM");
        wordSearch.search("SLEUTH");
        wordSearch.search("BACKWARD");
        wordSearch.search("VERTICAL");
        wordSearch.search("DIAGONAL");
        wordSearch.search("WIKIPEDIA");
        wordSearch.search("HORIZONTAL");
        wordSearch.search("WORDSEARCH");
    }
}
