package com.tetris.batch.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import com.tetris.batch.exception.TetrisOutputException;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemWriter;

public class TetrisWriter implements ItemWriter<Queue<String>>{
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TetrisWriter.class);

    private BufferedWriter bufferedWriter;

    //This exception can never be thrown as no read on a deleted file can be performed in scope of 
    //constructor after instantiation. This is why custom exception handling is intentionally left out. 
    public TetrisWriter(String outputFileName) throws IOException{
        bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFileName)));
    }
    
    @Override
    public void write(List<? extends Queue<String>> outputQueue) throws TetrisOutputException{
        for(int i = 0; i < outputQueue.size(); i++){
            logger.info("writer log");

            Queue<String> q = outputQueue.get(i);
            while(!q.isEmpty()){
                try{
                    String str = q.poll();
                    bufferedWriter.append(str);
                }catch(IOException e){
                    logger.error("Error writing to output file.");
                    throw new TetrisOutputException(e);
                }
            }       
        }
    }

    @AfterStep
    public ExitStatus afterWrite() {
        try{
            bufferedWriter.close();
        }catch(IOException e){
            logger.error("Error closing buffer reader.");
        }
        logger.info("Writer step successfully completed, meaning all tasklets have successfully completed. Job will end successfully.");
        return ExitStatus.COMPLETED;
    }
}
