package com.tetris.batch.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.tetris.batch.exception.TetrisInputException;
import com.tetris.batch.factory.TetrominoFactory;
import com.tetris.batch.model.tetromino.Tetromino;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemReader;

public class TetrisInputReader implements ItemReader<List<Tetromino>>{
    private BufferedReader bufferedReader;

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TetrisInputReader.class);

    public TetrisInputReader(String fileName) throws TetrisInputException{
        
        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
        }catch(IOException e){
            logger.debug("Error reading file.");
            e.printStackTrace();
            throw new TetrisInputException(e); 
        }
    }

    @Override
     public List<Tetromino> read() throws TetrisInputException{
        List<Tetromino> tetrominos = new ArrayList<>();
        logger.info("reader log");

        String line = ""; 
        try{
            if((line = bufferedReader.readLine()) != null){
                if(!line.isEmpty()){
                    String[] tetros = line.split(",");
                    for(String tetro : tetros) 
                        tetrominos.add(TetrominoFactory.getTetromino(tetro.charAt(0), Integer.parseInt(tetro.substring(1).trim())));  
                        }
                      
            }else return null;
            return tetrominos;
         }catch(IOException | NumberFormatException e){
            logger.error("Failure with BufferedReader parsing invalid input.");
            throw new TetrisInputException(e);
         }
     }

    @AfterStep
    public void afterRead() throws TetrisInputException{
        try{
            bufferedReader.close();
            logger.debug("BufferedReader successfully closed.");

        }catch(IOException e){
            logger.error("Error closing BufferedReader.");
            throw new TetrisInputException(e);
        }
    }
}
