package com.tetris.batch.config;

import java.io.IOException;
import java.util.List;
import java.util.Queue;
import com.tetris.batch.exception.TetrisInputException;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.processor.TetrisProcessor;
import com.tetris.batch.reader.TetrisInputReader;
import com.tetris.batch.writer.TetrisWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class TetrisConfig {

    @Autowired 
    private JobBuilderFactory jobs;

    @Autowired 
    private StepBuilderFactory steps;

    @Value("${input.fileName}")
    private String inputFileName;

    @Value("${output.fileName}")
    private String outputFileName;

    @Bean
    @StepScope
    public TetrisInputReader tetrisInputReader() throws TetrisInputException{
        return new TetrisInputReader(inputFileName);
    }

    @Bean
    @StepScope
    public TetrisProcessor tetrisProcessor() {
        return new TetrisProcessor();
    }

    @Bean
    @StepScope
    public TetrisWriter itemWriter() throws IOException {
        return new TetrisWriter(outputFileName);
    }

    @Bean
    protected Step processLines(ItemReader<List<Tetromino>> reader,
      ItemProcessor<List<Tetromino>, Queue<String>> processor, ItemWriter<Queue<String>> writer) {
        return steps.get("processLines").<List<Tetromino>, Queue<String>> chunk(1)
          .reader(reader)
          .processor(processor)
          .writer(writer)
          .build();
    }

    @Bean
    public Job job() throws IOException{
        return jobs
          .get("chunksJob")
          .start(processLines(tetrisInputReader(), tetrisProcessor(), itemWriter()))
          .build();
    }
}