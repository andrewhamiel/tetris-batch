package com.tetris.batch.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.tetris.batch.exception.TetrisOutputException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

public class TetrisWriterTest {

    BufferedWriter bufferedWriter;

    @InjectMocks
    private TetrisWriter tetrisWriter;

    @Test
    void writeSuccessTwoRowsSuccess()throws Exception{
        List<Queue<String>> list = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add("2\n");
        q.add("4\n");
        list.add(q);
        tetrisWriter = new TetrisWriter("src/test/resources/writer/test1Actual.txt");
        tetrisWriter.write(list);
        tetrisWriter.afterWrite();
        
        File f1 = new File("src/test/resources/writer/test1Actual.txt");
        File f2 = new File("src/test/resources/writer/test1Expected.txt");
        Assert.assertEquals(FileUtils.checksumCRC32(f1), FileUtils.checksumCRC32(f2));
    }

    @Test
    void writeSuccessTenRows()throws IOException{
        List<Queue<String>> list = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add("2\n");
        q.add("4\n");
        q.add("5\n");
        q.add("7\n");
        q.add("7\n");
        q.add("8\n");
        q.add("9\n");
        q.add("10\n");
        q.add("10\n");
        q.add("10\n");
        list.add(q);
        tetrisWriter = new TetrisWriter("src/test/resources/writer/test1Actual.txt");
        tetrisWriter.write(list);
        tetrisWriter.afterWrite();

        File f1 = new File("src/test/resources/writer/test1Actual.txt");
        File f2 = new File("src/test/resources/writer/test2Expected.txt");
        Assert.assertEquals(FileUtils.checksumCRC32(f1), FileUtils.checksumCRC32(f2));
    }

    

    @Test
    void writeSuccessTwoRowsIOException(){
        List<Queue<String>> list = new ArrayList<>();
        Queue<String> q = new LinkedList<>();
        q.add("2\n");
        q.add("4\n");
        list.add(q);
        //Tries to write with BufferedWriter that has already been closed
        Assert.assertThrows(TetrisOutputException.class, () -> {
            TetrisWriter t = new TetrisWriter("src/test/resources/writer/dummyFile.txt");
            t.afterWrite();
            t.write(list);
        });
    }
}
