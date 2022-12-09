package com.tetris.batch.service;

import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.J;

import org.springframework.stereotype.Component;

@Component
public class TetrisPiecePlacerJ implements TetrisPiecePlacer<J>{
    
    @Override
    public int placePiece(J tetromino){
        int startColumn = tetromino.getStartInd();
        int startRow = Math.max(TetrisBoard.getIndexHeight()[startColumn], TetrisBoard.getIndexHeight()[startColumn+1]);
        
        TetrisBoard.updateHeight(startRow, startColumn);
        TetrisBoard.updateHeight(startRow, startColumn+1);
        TetrisBoard.updateHeight(startRow+1, startColumn+1);
        TetrisBoard.updateHeight(startRow+2, startColumn+1);

        TetrisBoard.validateRows();
        return TetrisBoard.getMaxHeight();
    }
}
