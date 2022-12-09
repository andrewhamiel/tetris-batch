package com.tetris.batch.service;

import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.I;
import org.springframework.stereotype.Component;

@Component
public class TetrisPiecePlacerI implements TetrisPiecePlacer<I>{
    
    @Override
    public int placePiece(I tetromino){
        int startColumn = tetromino.getStartInd();
        int startRow = TetrisBoard.getIndexHeight()[startColumn];
        //Start at highest height of 4 horizontal columns
        for(int i = 1; i < 4; i++) startRow = Math.max(startRow, TetrisBoard.getIndexHeight()[startColumn+i]);
       
        TetrisBoard.updateHeight(startRow, startColumn);
        TetrisBoard.updateHeight(startRow, startColumn+1);
        TetrisBoard.updateHeight(startRow, startColumn+2);
        TetrisBoard.updateHeight(startRow, startColumn+3);

        TetrisBoard.validateRows();
        return TetrisBoard.getMaxHeight();
    }
}

