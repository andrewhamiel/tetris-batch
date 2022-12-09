package com.tetris.batch.processor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.service.TetrisGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;


public class TetrisProcessor implements ItemProcessor<List<Tetromino>, Queue<String>>{
    private Logger logger = LoggerFactory.getLogger(TetrisProcessor.class);

    @Autowired
    private TetrisGameService tetrisGameService;
    
    @Override
    public Queue<String> process(List<Tetromino> tetrominos) throws Exception {
        int height = 0;
        Queue<String> outputQueue = new LinkedList<>();
        logger.info("processor log");
        for(Tetromino t : tetrominos){
            height = tetrisGameService.place(t);
        }
        outputQueue.add(Integer.toString(height) + "\n");
        return outputQueue;
    }

    @AfterStep
    public void afterProcessor(StepExecution stepExecution) {
        logger.debug("Processor step completed.");
    }
}
