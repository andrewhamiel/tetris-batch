package com.tetris.batch.model;

public class TetrisBoard {
    private static int[][] board = new int[100][10];
    private static int height;
    private static int[] indexHeight = new int[10];
    private static int[] numPopulatedPerRow = new int[100];
    private static int score;

    private TetrisBoard(){}

    public static int[][] getBoard(){
        return board;
    }

    public static int[] getIndexHeight(){
        return indexHeight;
    }

    /*
    * This method keeps track of the max height for each row that is printed to the output file.
    * TODO: Improve efficiency of this algorithm. It currently runs in O(length) time
    */
    public static int getMaxHeight(){
        int height = 0;
        for(int i = 0; i < indexHeight.length; i++) 
            height = Math.max(height, indexHeight[i]);
        return height;
    }

    /*
    * Because this is maintained as a static variable, state is an issue but particularly impactful
    * in unit tests. Because of that, this method is available to clear the board after unit test runs
    * to avoid any data quality issues. I could not modify the method visibility from public since this
    * is used across all unit test classes.
    */
    public static void clear(){
        board = new int[100][10];
        indexHeight = new int[10];
        height = 0;
        numPopulatedPerRow = new int[100];
    }

    /*
    * This method swaps the contents of a full row being deleted with the contents
    * of the max index at that particular column. If the row being deleted is at that 
    * column's max index, then 0 is swapped with itself and behaves normally. 
    * Runtime complexity is O(length) time
    */
    private static void clearAndFillRow(int row){
        height = 0;
        for(int i = 0; i < indexHeight.length; i++){
            board[row][i] = 0;
            swap(row, i, indexHeight[i]-1, i, board);
            indexHeight[i]--;
            numPopulatedPerRow[indexHeight[i]] --;
            
        }
    }

    private static void swap(int iRow, int iCol, int jRow, int jCol, int[][] board){
        int temp = board[iRow][iCol];
        board[iRow][iCol] = board[jRow][jCol];
        board[jRow][jCol] = temp;
    }

    /*
    * This method keeps track of heights at each column index, and populates individual vertices
    * with their new values. The runtime complexity of this method is O(1)
    */
    public static int updateHeight(int row, int column){
        board[row][column] = 1;
        indexHeight[column] = Math.max(indexHeight[column], row+1);
        height = Math.max(height, indexHeight[column]);

        numPopulatedPerRow[row]++;
        return 0;
    }

    /*
    * This method checks populated rows to see if they are full. If so, that row 
    * is deleted/the remaining populated vertices above are shifted down only 1 position.
    */
    public static int validateRows(){
        for(int i = height - 1; i >= 0; i--){
            if(numPopulatedPerRow[i] == 10) {
                clearAndFillRow(i);
                score++;
            }
        }

        return score;
    }
}
