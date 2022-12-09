package com.tetris.batch.util;

public class TetrisTestUtils {
    public static boolean isValidQPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow+1][startColumn] == 1 && board[startRow+1][startColumn+1] == 1);
    }

    public static boolean isValidTPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow-1][startColumn+1] == 1 && board[startRow][startColumn+2] == 1);
    }

    public static boolean isValidJPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow+1][startColumn+1] == 1 && board[startRow+2][startColumn+1] == 1);
    }

    public static boolean isValidLPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow+1][startColumn] == 1 && board[startRow+2][startColumn] == 1);
    }

    public static boolean isValidIPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow][startColumn+2] == 1 && board[startRow][startColumn+1] == 1);
    }

    public static boolean isValidSPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow+1][startColumn+1] == 1 && board[startRow+1][startColumn+2] == 1);
    }

    public static boolean isValidZPlacement(int startRow, int startColumn, int[][] board){
        return (board[startRow][startColumn] == 1 && board[startRow][startColumn+1] == 1 
        && board[startRow-1][startColumn+1] == 1 && board[startRow-1][startColumn+2] == 1);
    }
}