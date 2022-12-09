package com.tetris.batch.model.tetromino;

import java.io.Serializable;

public abstract class Tetromino implements Serializable{
    // private int[][] dimensions;
    private final char type;
    private int startInd;

    protected Tetromino(final char type, int startInd){
        this.type = type;
        this.startInd = startInd;
    }

    public char getType(){ return type;}

    public void setStartInd(int startInd){ this.startInd = startInd;}

    public int getStartInd(){ return startInd; }
}
