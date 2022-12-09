package com.tetris.batch.model.tetromino;

public class Q extends Tetromino{
    private char type;
    private int startInd;

    public Q(char type, int startInd){
        super(type, startInd);
        this.type = type;
        this.startInd = startInd;
    }

    @Override
    public char getType(){ return type;}

    @Override
    public int getStartInd(){ return startInd;}
}
