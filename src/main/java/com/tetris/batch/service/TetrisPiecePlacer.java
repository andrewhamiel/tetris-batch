package com.tetris.batch.service;

@FunctionalInterface
public interface TetrisPiecePlacer<T> {
    /*
    * This method will take the input piece and place it on the board. 
    * @param Tetromino the input puzzle piece
    * @returns int the height of the board after piece placement
    */
    int placePiece(T tetromino);
}
