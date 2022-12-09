package com.tetris.batch.service;

import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.L;
import org.springframework.stereotype.Component;

@Component
public class TetrisPiecePlacerL implements TetrisPiecePlacer<L>{
    
    @Override
    public int placePiece(L tetromino){
        int startColumn = tetromino.getStartInd();
        int startRow = Math.max(TetrisBoard.getIndexHeight()[startColumn], TetrisBoard.getIndexHeight()[startColumn+1]);
        TetrisBoard.updateHeight(startRow, startColumn);
        TetrisBoard.updateHeight(startRow, startColumn+1);
        TetrisBoard.updateHeight(startRow+1, startColumn);
        TetrisBoard.updateHeight(startRow+2, startColumn);

        TetrisBoard.validateRows();
        return TetrisBoard.getMaxHeight();
    }
}
