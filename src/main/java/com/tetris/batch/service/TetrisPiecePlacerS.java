package com.tetris.batch.service;

import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.S;
import org.springframework.stereotype.Component;

@Component
public class TetrisPiecePlacerS implements TetrisPiecePlacer<S>{
    
    @Override
    public int placePiece(S tetromino){
        int startColumn = tetromino.getStartInd();
        int startRow = findStartRow(TetrisBoard.getIndexHeight()[startColumn], startColumn);
        
        TetrisBoard.updateHeight(startRow, startColumn);
        TetrisBoard.updateHeight(startRow, startColumn+1);
        TetrisBoard.updateHeight(startRow+1, startColumn+1);
        TetrisBoard.updateHeight(startRow+1, startColumn+2);

        TetrisBoard.validateRows();
        return TetrisBoard.getMaxHeight();
    }

    /* Checks to see if any key vertices are filled, and increments row until insert conditions are met.
    * Assumes that for every unfilled vertex [i][j], any vertex[i+n][j] above it are also unfilled.
    *  [0,  1,  1*]
    *  [1*, 1*, 0 ]
    */ 
    private int findStartRow(int startRow, int startColumn){
        while(TetrisBoard.getBoard()[startRow][startColumn] != 0
        || TetrisBoard.getBoard()[startRow][startColumn+1] != 0
        || TetrisBoard.getBoard()[startRow+1][startColumn+2] != 0) startRow++;
        return startRow;
    }

}
