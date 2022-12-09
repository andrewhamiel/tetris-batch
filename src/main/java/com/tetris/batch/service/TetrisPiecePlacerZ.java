package com.tetris.batch.service;

import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.Z;

import org.springframework.stereotype.Component;

@Component
public class TetrisPiecePlacerZ implements TetrisPiecePlacer<Z>{
    
    @Override
    public int placePiece(Z tetromino){
        int startColumn = tetromino.getStartInd();
        int startRow = findStartRow(TetrisBoard.getIndexHeight()[startColumn], startColumn);
        
        TetrisBoard.updateHeight(startRow, startColumn);
        TetrisBoard.updateHeight(startRow, startColumn+1);
        TetrisBoard.updateHeight(startRow-1, startColumn+1);
        TetrisBoard.updateHeight(startRow-1, startColumn+2);

        TetrisBoard.validateRows();
        return TetrisBoard.getMaxHeight();
    }

    /* Checks to see if any key vertices are filled, and increments row until insert conditions are met.
    * Assumes that for every unfilled vertex [i][j], any vertex[i+n][j] above it are also unfilled.
    *  [1*, 1,  0 ]
    *  [0,  1*, 1*]
    * Asterisked vertices are checked fields
    */ 
    private int findStartRow(int startRow, int startColumn){
        while(startRow <= 0 || TetrisBoard.getBoard()[startRow][startColumn] != 0
        || TetrisBoard.getBoard()[startRow][startColumn+1] != 0
        || TetrisBoard.getBoard()[startRow-1][startColumn+2] != 0) startRow++;
        return startRow;
    }
}
