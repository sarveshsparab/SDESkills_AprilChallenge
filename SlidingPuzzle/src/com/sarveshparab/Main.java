package com.sarveshparab;

/**
 * Problem: 15 Puzzle (Sliding Puzzle)
 *   -- https://beta.sdeskills.com/30day-challenge/day7
 *
 * Author Details:
 *   -- Sarvesh Parab
 *   -- sarveshsparab@gmail.com
 *   -- http://www.sarveshparab.com/
 *   -- https://www.linkedin.com/in/sarveshsparab/
 */
public class Main {

    public static void main(String[] args) {

        int [][] initState4x4 = {
                {1, 6, 7, 4},
                {5, 2, 3, 8},
                {9, 10, 15, 11},
                {13, 0, 14, 12}
        };

        int [][] initState3x3 = {
                {2, 3, 0},
                {4, 5, 8},
                {6, 1, 7}
        };

        SlidingPuzzle puzzle = new SlidingPuzzle(initState4x4);
        puzzle.setVerbose(false);

        System.out.println("----------------------------------------------");
        System.out.println("INITIAL STATE");
        puzzle.printGameState(puzzle.getInitState());
        System.out.println("GOAL STATE");
        puzzle.printGameState(puzzle.getGoalState());
        System.out.println("----------------------------------------------");

        String path = puzzle.solve(SlidingPuzzle.AStarHeuristicEnum.MANHATTAN_DEPTH);
        if (path != null){
            System.out.println("Goal reached in " + path.length() + " moves | Solution path: " + path);
            System.out.println("Traversing the path");
            System.out.println("----------------------------------------------");
            puzzle.printPath(path);
        }
    }

}
