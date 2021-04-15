package com.sarveshparab;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
public class SlidingPuzzle {

    private static final int DEFAULT_PUZZLE_SIZE = 15;
    private static final AStarHeuristicEnum DEFAULT_HEURISTIC = AStarHeuristicEnum.MANHATTAN_DEPTH;

    private final int gridWidth;
    private final int puzzleSize;
    private final int[][] initState;
    private final int[][] goalState;

    private boolean verbose = false;

    public SlidingPuzzle() {
        this(DEFAULT_PUZZLE_SIZE);
    }

    public SlidingPuzzle(int puzzleSize) {
        this(puzzleSize, randomizeInitState(puzzleSize));
    }

    public SlidingPuzzle(int[][] initState) {
        this(initState.length * initState[0].length - 1, initState);
    }

    public SlidingPuzzle(int puzzleSize, int[][] initState) {
        this.puzzleSize = puzzleSize;
        this.gridWidth = (int) Math.sqrt(this.puzzleSize + 1);
        this.initState = initState;
        this.goalState = generateDefaultGoalState();
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    private static int[][] randomizeInitState(int puzzleSize) {
        int gridSize = (int) Math.sqrt(puzzleSize + 1);
        int[][] randomInitState = new int[gridSize][gridSize];
        List<Integer> tilesList = IntStream.rangeClosed(0, puzzleSize).boxed().collect(Collectors.toList());
        Collections.shuffle(tilesList);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                randomInitState[i][j] = tilesList.get(i * gridSize + j);
            }
        }
        return randomInitState;
    }

    private int[][] generateDefaultGoalState() {
        int[][] defaultGoalState = new int[this.gridWidth][this.gridWidth];
        for (int i = 0; i < this.gridWidth; i++) {
            for (int j = 0; j < this.gridWidth; j++) {
                defaultGoalState[i][j] = (i * this.gridWidth + j + 1) % (this.puzzleSize + 1);
            }
        }
        return defaultGoalState;
    }

    public String solve() {
        return this.solve(DEFAULT_HEURISTIC);
    }

    public String solve(AStarHeuristicEnum heuristic){
        if (isSolvable(this.initState)) {
            GameStateNode initNode = new GameStateNode(this.initState, getEmptyPosition(this.initState), 0, "S", "");
            return runAStarAlgo(initNode, heuristic);
        } else {
            System.out.println("The initial state is not solvable");
            return null;
        }
    }

    private String runAStarAlgo(GameStateNode initNode, AStarHeuristicEnum heuristic) {
        PriorityQueue<GameStateNode> fringeList = new PriorityQueue<>(pickAStarHeuristic(heuristic));
        List<GameStateNode> expandedList = new ArrayList<>();
        boolean reachedGoal = false;
        GameStateNode currentNode;
        String path = "";

        fringeList.add(initNode);
        while(!reachedGoal && !fringeList.isEmpty()) {
            currentNode = fringeList.poll();

            if (this.verbose)
                currentNode.printState();

            if(goalReached(currentNode.puzzle)) {
                reachedGoal = true;
                path = currentNode.path;
            } else {
                expandedList.add(currentNode);
                traverseFurther(fringeList, expandedList, currentNode);
            }
        }

        return path;
    }

    private void traverseFurther(PriorityQueue<GameStateNode> fringeList, List<GameStateNode> expandedList, GameStateNode currentNode) {
        int emptyPosRow = currentNode.emptyPos / this.gridWidth;
        int emptyPosCol = currentNode.emptyPos % this.gridWidth;

        if (emptyPosRow > 0){
            GameStateNode newNode = makeMove(currentNode, currentNode.emptyPos-this.gridWidth, "U");
            if (!alreadyIncludes(fringeList, newNode) && !alreadyIncludes(expandedList, newNode)){
                fringeList.add(newNode);
            }
        }

        if (emptyPosRow < this.gridWidth - 1){
            GameStateNode newNode = makeMove(currentNode, currentNode.emptyPos+this.gridWidth, "D");
            if (!alreadyIncludes(fringeList, newNode) && !alreadyIncludes(expandedList, newNode)){
                fringeList.add(newNode);
            }
        }

        if (emptyPosCol > 0){
            GameStateNode newNode = makeMove(currentNode, currentNode.emptyPos-1, "L");
            if (!alreadyIncludes(fringeList, newNode) && !alreadyIncludes(expandedList, newNode)){
                fringeList.add(newNode);
            }
        }

        if (emptyPosCol < this.gridWidth - 1){
            GameStateNode newNode = makeMove(currentNode, currentNode.emptyPos+1, "R");
            if (!alreadyIncludes(fringeList, newNode) && !alreadyIncludes(expandedList, newNode)){
                fringeList.add(newNode);
            }
        }
    }

    private boolean alreadyIncludes(Collection<GameStateNode> collection, GameStateNode newNode) {
        for (GameStateNode node : collection){
            boolean misMatch = false;
            for(int i=0; !misMatch && i < this.gridWidth; i++) {
                for (int j = 0; !misMatch && j < this.gridWidth; j++) {
                    if (node.puzzle[i][j] != newNode.puzzle[i][j]) {
                        misMatch = true;
                    }
                }
            }

            if(!misMatch) {
                return true;
            }
        }
        return false;
    }

    private GameStateNode makeMove(GameStateNode currentNode, int newEmptyPos, String direction) {
        int oldEmptyPos = currentNode.emptyPos;
        int[][] newState = new int[this.gridWidth][this.gridWidth];
        for(int i=0; i < this.gridWidth; i++) {
            for (int j = 0; j < this.gridWidth; j++) {
                newState[i][j] = currentNode.puzzle[i][j];
            }
        }

        newState[newEmptyPos / this.gridWidth][newEmptyPos % this.gridWidth] = 0;
        newState[oldEmptyPos / this.gridWidth][oldEmptyPos % this.gridWidth] = currentNode.puzzle[newEmptyPos / this.gridWidth][newEmptyPos % this.gridWidth];

        return new GameStateNode(newState, newEmptyPos, currentNode.depth + 1, direction, currentNode.path + direction);
    }

    private boolean goalReached(int[][] puzzle) {
        for(int i=0; i < this.gridWidth; i++) {
            for (int j = 0; j < this.gridWidth; j++) {
                if(puzzle[i][j] != this.goalState[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSolvable(int[][] puzzle){
        int invCount = 0;
        int[] flatPuzzle = Stream.of(puzzle).flatMapToInt(IntStream::of).toArray();

        for (int i = 0; i < this.gridWidth * this.gridWidth - 1; i++) {
            for (int j = i + 1; j < this.gridWidth * this.gridWidth; j++) {
                if ((flatPuzzle[i] !=0 && flatPuzzle[j]!=0) && (flatPuzzle[i] > flatPuzzle[j]))
                    invCount++;
            }
        }

        if (this.gridWidth % 2 == 1)
            return invCount % 2 == 0;
        else {
            int emptyPos = getEmptyRowFromBottom(puzzle);
            if (emptyPos < 0)
                return false;
            if (emptyPos % 2 == 1)
                return invCount % 2 == 0;
            else
                return invCount % 2 == 1;
        }
    }

    private int getEmptyRowFromBottom(int[][] puzzle) {
        for (int i = this.gridWidth - 1; i >= 0; i--)
            for (int j = this.gridWidth - 1; j >= 0; j--)
                if (puzzle[i][j] == 0)
                    return this.gridWidth - i;
        return -1;
    }

    private int getEmptyPosition(int[][] puzzle) {
        for (int i = this.gridWidth - 1; i >= 0; i--)
            for (int j = this.gridWidth - 1; j >= 0; j--)
                if (puzzle[i][j] == 0)
                    return i * this.gridWidth + j;
        return -1;
    }

    private Comparator<GameStateNode> pickAStarHeuristic(AStarHeuristicEnum heuristic){
        Comparator<GameStateNode> comparator = null;
        switch(heuristic){
            case MANHATTAN:
                comparator = new Comparator<GameStateNode>() {
                    @Override
                    public int compare(GameStateNode o1, GameStateNode o2) {
                        return o1.distance - o2.distance;
                    }
                };
                break;
            case MANHATTAN_DEPTH:
                comparator = new Comparator<GameStateNode>() {
                    @Override
                    public int compare(GameStateNode o1, GameStateNode o2) {
                        return o1.depth + o1.distance - o2.depth - o2.distance;
                    }
                };
                break;
            case MISPLACEMENT:
                comparator = new Comparator<GameStateNode>() {
                    @Override
                    public int compare(GameStateNode o1, GameStateNode o2) {
                        return o1.misplacement - o2.misplacement;
                    }
                };
                break;
            case MISPLACEMENT_DEPTH:
                comparator = new Comparator<GameStateNode>() {
                    @Override
                    public int compare(GameStateNode o1, GameStateNode o2) {
                        return o1.depth + o1.misplacement - o2.depth - o2.misplacement;
                    }
                };
                break;
        }
        return comparator;
    }

    public void printGameState(int[][] puzzle){
        for(int i=0; i < this.gridWidth; i++){
            for(int j=0; j < this.gridWidth; j++){
                System.out.print(puzzle[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] getInitState() {
        return this.initState;
    }

    public int[][] getGoalState() {
        return this.goalState;
    }

    public void printPath(String path) {
        char[] pathArr = path.toCharArray();
        GameStateNode currentNode = new GameStateNode(this.initState, getEmptyPosition(this.initState), 0, "S", "");
        for (char p : pathArr) {
            int newEmptyPos = 0;
            switch (p){
                case 'U':
                    newEmptyPos = currentNode.emptyPos-this.gridWidth;
                    break;
                case 'D':
                    newEmptyPos = currentNode.emptyPos+this.gridWidth;
                    break;
                case 'L':
                    newEmptyPos = currentNode.emptyPos-1;
                    break;
                case 'R':
                    newEmptyPos = currentNode.emptyPos+1;
                    break;
            }
            currentNode = makeMove(currentNode, newEmptyPos, String.valueOf(p));
            currentNode.printState();
        }
    }

    enum AStarHeuristicEnum {
        MANHATTAN, MANHATTAN_DEPTH, MISPLACEMENT, MISPLACEMENT_DEPTH
    }

    class GameStateNode {
        int emptyPos;
        int netPos;
        int distance;
        int depth;
        int misplacement;
        String direction;
        String path;
        int[][] puzzle;

        GameStateNode(int[][] puzzle, int emptyPos, int depth, String direction, String path) {
            this.puzzle = puzzle;
            this.depth = depth;
            this.direction = direction;
            this.path = path;
            this.emptyPos = emptyPos;
            this.netPos = gridWidth * gridWidth;
            this.distance = calcManhattanDist();
            this.misplacement = calcMisplacement();
        }

        private int calcManhattanDist() {
            int dist = 0;
            for(int i=0; i < gridWidth; i++){
                for(int j=0; j < gridWidth; j++) {
                    int val = this.puzzle[i][j];
                    if (val != 0) {
                        dist += Math.abs((val-1)/gridWidth - i) + Math.abs((val-1)%gridWidth - j);
                    }
                }
            }
            return dist;
        }

        private int calcMisplacement() {
            int miss = 0;
            for(int i=0; i < gridWidth; i++) {
                for (int j = 0; j < gridWidth; j++) {
                    if (this.puzzle[i][j] != (i * gridWidth + j + 1) % this.netPos) {
                        miss++;
                    }
                }
            }
            return miss;
        }

        public void printState(){
            for(int i=0; i < gridWidth; i++){
                for(int j=0; j < gridWidth; j++){
                    System.out.print(puzzle[i][j] + "\t");
                }
                if (i == 0){
                    System.out.println("\t |Depth: " + this.depth + " |Manhattan: " + this.distance + " |Misplaced: " + this.misplacement);
                } else {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }
}
