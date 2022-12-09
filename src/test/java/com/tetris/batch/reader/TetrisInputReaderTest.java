package com.tetris.batch.reader;

import java.io.File;
import java.util.List;
import com.tetris.batch.exception.TetrisInputException;
import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.I;
import com.tetris.batch.model.tetromino.J;
import com.tetris.batch.model.tetromino.Q;
import com.tetris.batch.model.tetromino.S;
import com.tetris.batch.model.tetromino.T;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.model.tetromino.Z;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class TetrisInputReaderTest {

    private TetrisInputReader tetrisInputReader;

    @AfterEach
    void tearDown(){
        TetrisBoard.clear();
    }

    @Test
    void testProvidedInputDataSuccess(){
        String fileName = "src/test/resources/providedInput.txt";
        Tetromino tetromino = new Q('Q', 0);
        try{
            tetrisInputReader = new TetrisInputReader(new File(fileName).getAbsolutePath());
            List<Tetromino> results = tetrisInputReader.read();
            Assert.assertEquals(tetromino.getType(), results.get(0).getType());
            Assert.assertEquals(tetromino.getStartInd(), results.get(0).getStartInd());
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
    }

    @Test
    void testProvidedInputDataNonExistentFile(){
        String fileName = "src/test/resources/providedInputNotReal.txt";
        Assert.assertThrows(TetrisInputException.class, 
        () -> {
            new TetrisInputReader(new File(fileName).getAbsolutePath());
        });     
    }

    @Test
    void testMultipleTetronimosOnLineSuccess(){
        String fileName = "src/test/resources/reader/MultipleTetronimos10LengthValid.txt";
        List<Tetromino> expected = List.of(new Q('Q', 0), new S('S', 2), new S('S', 5), new Q('Q', 8));
        try{
            tetrisInputReader = new TetrisInputReader(new File(fileName).getAbsolutePath());
            List<Tetromino> actual = tetrisInputReader.read();
            for(int i = 0; i < actual.size(); i++){
                Assert.assertEquals(expected.get(i).getType(), actual.get(i).getType());
                Assert.assertEquals(expected.get(i).getStartInd(), actual.get(i).getStartInd());
            }
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
  
    }

    @Test
    void testProvidedInvalidOrderOfTypeAndIndexException(){
        String fileName = "src/test/resources/reader/SingleTetronimoInvalidOrderOfTypeAndIndex.txt";
        Assert.assertThrows(TetrisInputException.class, () -> {
            TetrisInputReader t = new TetrisInputReader(new File(fileName).getAbsolutePath());
            t.read();
        });
    }

    //Note: for this test it should not fail in the reader, as this is a business logic break that should be caught in processor
    //Behavior before: parsing only 2nd character would /10 the index: i.e. 10 became ind 1
    @Test
    void testMultipleTetronimosLength20InvalidSuccess(){
        String fileName = "src/test/resources/reader/MultipleTetronimosLength20Invalid.txt";
        List<Tetromino> expected = List.of(new S('S', 0), new Z('Z', 3), new I('I', 6),
        new I('I', 10), new T('T', 14), new J('J', 18));
        try{
            tetrisInputReader = new TetrisInputReader(new File(fileName).getAbsolutePath());
            List<Tetromino> actual = tetrisInputReader.read();
            for(int i = 0; i < actual.size(); i++){
                Assert.assertEquals(expected.get(i).getType(), actual.get(i).getType());
                Assert.assertEquals(expected.get(i).getStartInd(), actual.get(i).getStartInd());
            }
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
    }

    //Note: for this test it should not fail in the reader, as this is a business logic break that should be caught in processor
    @Test
    void testSingleTetronimoNegativeIndexInvalidSuccess(){
        String fileName = "src/test/resources/reader/SingleTetronimoNegativeIndexInvalid.txt";
        List<Tetromino> expected = List.of(new Q('Q', -1));
        try{
            tetrisInputReader = new TetrisInputReader(new File(fileName).getAbsolutePath());
            List<Tetromino> actual = tetrisInputReader.read();
            Assert.assertEquals(expected.get(0).getType(), actual.get(0).getType());
            Assert.assertEquals(expected.get(0).getStartInd(), actual.get(0).getStartInd());
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        } 
    }
}
