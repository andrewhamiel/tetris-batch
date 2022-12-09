package com.tetris.batch.service;

import com.tetris.batch.exception.TetrisInputException;
import com.tetris.batch.model.tetromino.I;
import com.tetris.batch.model.tetromino.J;
import com.tetris.batch.model.tetromino.L;
import com.tetris.batch.model.tetromino.Q;
import com.tetris.batch.model.tetromino.S;
import com.tetris.batch.model.tetromino.T;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.model.tetromino.Z;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TetrisGameService{
    private TetrisPiecePlacer<I> tetrisPiecePlacerI;
    TetrisPiecePlacer<J> tetrisPiecePlacerJ; 
    TetrisPiecePlacer<L> tetrisPiecePlacerL;
    TetrisPiecePlacer<Q> tetrisPiecePlacerQ;
    TetrisPiecePlacer<T> tetrisPiecePlacerT;
    TetrisPiecePlacer<Z> tetrisPiecePlacerZ;
    TetrisPiecePlacer<S> tetrisPiecePlacerS;

    @Autowired
    public TetrisGameService(TetrisPiecePlacer<I> tetrisPiecePlacerI, TetrisPiecePlacer<J> tetrisPiecePlacerJ, 
    TetrisPiecePlacer<L> tetrisPiecePlacerL, TetrisPiecePlacer<Q> tetrisPiecePlacerQ, 
    TetrisPiecePlacer<T> tetrisPiecePlacerT, TetrisPiecePlacer<Z> tetrisPiecePlacerZ, TetrisPiecePlacer<S> tetrisPiecePlacerS){
        this.tetrisPiecePlacerI = tetrisPiecePlacerI;
        this.tetrisPiecePlacerJ = tetrisPiecePlacerJ;
        this.tetrisPiecePlacerL = tetrisPiecePlacerL;
        this.tetrisPiecePlacerQ = tetrisPiecePlacerQ;
        this.tetrisPiecePlacerT = tetrisPiecePlacerT;
        this.tetrisPiecePlacerZ = tetrisPiecePlacerZ;
        this.tetrisPiecePlacerS = tetrisPiecePlacerS;
    }

    public int place(Tetromino tetromino) throws TetrisInputException{
        switch(tetromino.getType()){
            case 'I':
                return tetrisPiecePlacerI.placePiece((I)tetromino);
            case 'J':
                return tetrisPiecePlacerJ.placePiece((J)tetromino);
            case 'L':
                return tetrisPiecePlacerL.placePiece((L)tetromino);
            case 'Q':
                return tetrisPiecePlacerQ.placePiece((Q)tetromino);
            case 'T':
                return tetrisPiecePlacerT.placePiece((T)tetromino);
            case 'Z':
                return tetrisPiecePlacerZ.placePiece((Z)tetromino);
            case 'S':
                return tetrisPiecePlacerS.placePiece((S)tetromino);
            default: throw new TetrisInputException(new RuntimeException());
        }
    }

    
  
}

