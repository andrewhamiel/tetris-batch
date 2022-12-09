package com.tetris.batch.factory;

import com.tetris.batch.model.tetromino.I;
import com.tetris.batch.model.tetromino.J;
import com.tetris.batch.model.tetromino.L;
import com.tetris.batch.model.tetromino.Q;
import com.tetris.batch.model.tetromino.S;
import com.tetris.batch.model.tetromino.T;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.model.tetromino.Z;

public class TetrominoFactory {
    private TetrominoFactory(){}
    
    public static Tetromino getTetromino(char type, int startInd){
       switch(type){
           case 'Q':
                return new Q(type, startInd);
            case 'Z':
                return new Z(type, startInd);
            case 'S':
                return new S(type, startInd);
            case 'T':
                return new T(type, startInd);
            case 'I':
                return new I(type, startInd);
            case 'L':
                return new L(type, startInd);
            case 'J':
                return new J(type, startInd);
            default:
                return null;
       }
   } 
}
