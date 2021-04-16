package com.sarveshparab;

import java.util.*;

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
public class WordSearch {

    private final char[][] wordGrid;
    private final Map<Character, List<int[]>> charMap;

    public WordSearch(char[][] wordGrid) {
        this.wordGrid = wordGrid;
        this.charMap = preProcessGrid();
    }

    private Map<Character, List<int[]>> preProcessGrid() {
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < this.wordGrid.length; i++){
            for (int j = 0; j < this.wordGrid[i].length; j++) {
                this.wordGrid[i][j] = Character.toUpperCase(this.wordGrid[i][j]);
                if (map.containsKey(this.wordGrid[i][j])) {
                    List<int[]> posList = map.get(this.wordGrid[i][j]);
                    posList.add(new int[]{i, j});
                } else {
                    List<int[]> posList = new ArrayList<>();
                    posList.add(new int[]{i, j});
                    map.put(this.wordGrid[i][j], posList);
                }
            }
        }
        return map;
    }

    public void search(String word) {
        List<SearchResult> searchResults = new ArrayList<>();
        word = word.toUpperCase();
        List<int[]> startPosList = this.charMap.get(word.charAt(0));
        for (int[] pos : startPosList) {
            Direction dir = _search(pos[0], pos[1], word);
            if (dir != null) {
                searchResults.add(new SearchResult(pos[0], pos[1], dir));
            }
        }

        if (searchResults.isEmpty()) {
            System.out.println("Word not found!!");
        } else {
            System.out.println("Word [" + word + "] found at: ");
            for (WordSearch.SearchResult searchResult : searchResults) {
                System.out.println("Row: " + searchResult.getRow() + " Col: " + searchResult.getCol()
                        + " | Direction: " + searchResult.getDirection().name());
            }
        }
        System.out.println("----------------------------------------------");
    }

    private Direction _search(int row, int col, String word) {
        int len = word.length();
        int charsCovered;
        for (Direction direction : Direction.values()) {
            int[] posDelta = direction.getDirDelta();
            int newRow = row + posDelta[0];
            int newCol = col + posDelta[1];

            for (charsCovered = 1; charsCovered < len; charsCovered++) {
                if (newRow < 0 || newCol < 0 || newRow >= this.wordGrid.length || newCol >= this.wordGrid[0].length) {
                    break;
                }

                if (word.charAt(charsCovered) != this.wordGrid[newRow][newCol]) {
                    break;
                }

                newRow = newRow + posDelta[0];
                newCol = newCol + posDelta[1];
            }

            if (charsCovered == len) {
                return direction;
            }
        }
        return null;
    }

    public void showGrid(){
        System.out.print("+ ");
        for(int i=0; i<this.wordGrid[0].length; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < this.wordGrid.length; i++){
            System.out.print(i + " ");
            for (int j = 0; j < this.wordGrid[i].length; j++) {
                System.out.print(this.wordGrid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    enum Direction {
        NORTH(-1, 0),
        SOUTH(1, 0),
        EAST(0, 1),
        WEST(0, -1),
        NORTH_EAST(-1, 1),
        NORTH_WEST(-1, -1),
        SOUTH_EAST(1, 1),
        SOUTH_WEST(1, -1);

        private int rowChange;
        private int colChange;

        Direction(int rowChange, int colChange) {
            this.rowChange = rowChange;
            this.colChange = colChange;
        }

        public int[] getDirDelta(){
            return new int[]{rowChange, colChange};
        }
    }

    class SearchResult {
        private final int row;
        private final int col;
        private final Direction direction;

        SearchResult(int row, int col, Direction direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Direction getDirection() {
            return direction;
        }
    }
}
