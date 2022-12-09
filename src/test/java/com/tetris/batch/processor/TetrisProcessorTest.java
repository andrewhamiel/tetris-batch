package com.tetris.batch.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.I;
import com.tetris.batch.model.tetromino.J;
import com.tetris.batch.model.tetromino.Q;
import com.tetris.batch.model.tetromino.S;
import com.tetris.batch.model.tetromino.T;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.model.tetromino.Z;
import com.tetris.batch.service.TetrisGameService;
import com.tetris.batch.service.TetrisPiecePlacerI;
import com.tetris.batch.service.TetrisPiecePlacerJ;
import com.tetris.batch.service.TetrisPiecePlacerL;
import com.tetris.batch.service.TetrisPiecePlacerQ;
import com.tetris.batch.service.TetrisPiecePlacerS;
import com.tetris.batch.service.TetrisPiecePlacerT;
import com.tetris.batch.service.TetrisPiecePlacerZ;
import com.tetris.batch.util.TetrisTestUtils;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
public class TetrisProcessorTest {

    private TetrisPiecePlacerI tetrisPiecePlacerI;
    private TetrisPiecePlacerJ tetrisPiecePlacerJ;
    private TetrisPiecePlacerL tetrisPiecePlacerL;
    private TetrisPiecePlacerQ tetrisPiecePlacerQ;
    private TetrisPiecePlacerT tetrisPiecePlacerT;
    private TetrisPiecePlacerZ tetrisPiecePlacerZ;
    private TetrisPiecePlacerS tetrisPiecePlacerS;

    private TetrisGameService tetrisGameService;

    @InjectMocks
    private TetrisProcessor tetrisProcessor;

    @BeforeEach
    void setup(){
        tetrisPiecePlacerI = new TetrisPiecePlacerI();
        tetrisPiecePlacerJ = new TetrisPiecePlacerJ();
        tetrisPiecePlacerL = new TetrisPiecePlacerL();
        tetrisPiecePlacerQ = new TetrisPiecePlacerQ();
        tetrisPiecePlacerT = new TetrisPiecePlacerT();
        tetrisPiecePlacerZ = new TetrisPiecePlacerZ();
        tetrisPiecePlacerS = new TetrisPiecePlacerS();

        tetrisGameService = new TetrisGameService(tetrisPiecePlacerI, tetrisPiecePlacerJ, 
        tetrisPiecePlacerL, tetrisPiecePlacerQ, tetrisPiecePlacerT, tetrisPiecePlacerZ, tetrisPiecePlacerS);
        tetrisProcessor = new TetrisProcessor();
        ReflectionTestUtils.setField(tetrisProcessor, "tetrisGameService", tetrisGameService);
    }

    @AfterEach
    void tearDown(){
        TetrisBoard.clear();
    }

    @Test
    void testProvidedInputDataSuccess(){
        List<Tetromino> tetrominos = List.of(new Q('Q', 0));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("2\n", results.poll());
            int[][] board = TetrisBoard.getBoard();
            //Check that Q shape filled board
            Assert.assertTrue(TetrisTestUtils.isValidQPlacement(0, 0, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
        TetrisBoard.clear();

    }

    @Test
    void testTwoDifferentShapesSuccess(){
        List<Tetromino> tetrominos = List.of(new Q('Q', 0), new T('T', 2));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("2\n", results.poll());
            int[][] board = TetrisBoard.getBoard();
            //Check that Q shape filled board
            Assert.assertTrue(TetrisTestUtils.isValidQPlacement(0, 0, board));
            //T-shape
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 2, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
        TetrisBoard.clear();

    }

    @Test
    void testLength10Success(){
        List<Tetromino> tetrominos = List.of(new Q('Q', 0), new T('T', 2), new T('T', 5), new J('J', 8));
        try{           
            int[][] board = TetrisBoard.getBoard();
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("3\n", results.poll());
            //Q-shape
            Assert.assertTrue(TetrisTestUtils.isValidQPlacement(0, 0, board));
            //T-shape
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 2, board));
            //T-shape
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 5, board));
            //J shape
            Assert.assertTrue(TetrisTestUtils.isValidJPlacement(0, 8, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading valid input.");
        }
        TetrisBoard.clear();

    }

    @Test
    void testMultipleLinesSuccess(){
        List<Tetromino> tetrominos = List.of(new Q('Q', 0), new T('T', 2), new T('T', 5), new J('J', 8));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("3\n", results.poll());
            int[][] board = TetrisBoard.getBoard();
            //Q, T, T, J
            Assert.assertTrue(TetrisTestUtils.isValidQPlacement(0, 0, board));
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 2, board));
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 5, board));
            Assert.assertTrue(TetrisTestUtils.isValidJPlacement(0, 8, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading first valid line.");
        }
        //Check next row
        tetrominos = new ArrayList<>(List.of(new T('T', 0), new J('J', 2), new I('I', 4), new Q('Q', 8)));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("7\n", results.poll());
            int[][] board = TetrisBoard.getBoard();
            //T, J, I, Q
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(3, 0, board));
            Assert.assertTrue(TetrisTestUtils.isValidJPlacement(4, 2, board));
            Assert.assertTrue(TetrisTestUtils.isValidIPlacement(2, 4, board));
            Assert.assertTrue(TetrisTestUtils.isValidQPlacement(3, 8, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading second valid line.");
        }
        TetrisBoard.clear();

    }
    /*
    * First: 
    * [1, 1, 1, 1, 0, 1, 1, 1, 1, 0]
    *
    * Second:
    * [1, 1, 1, 1, 1, 1, 0, 0, 1, 1]
    * [0, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    * [1, 1, 1, 1, 0, 1, 1, 1, 1, 0]
    *
    */

    @Test
    void testMultipleLinesWithZAndSSuccess(){
        List<Tetromino> tetrominos = List.of(new I('I', 0), new Z('Z', 4), new S('S', 7));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos); 
            int[][] board = TetrisBoard.getBoard();   
            Assert.assertEquals("2\n", results.poll());
            Assert.assertTrue(TetrisTestUtils.isValidIPlacement(0, 0, board));
            Assert.assertTrue(TetrisTestUtils.isValidZPlacement(1, 4, board));
            Assert.assertTrue(TetrisTestUtils.isValidSPlacement(0, 7, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading first valid line.");
        }
        //Check next row
        tetrominos = new ArrayList<>(List.of(new Z('Z', 0), new S('S', 2), new Z('Z', 4), new T('T', 6)));
        try{           
            Queue<String> results = tetrisProcessor.process(tetrominos);    
            Assert.assertEquals("5\n", results.poll());
            int[][] board = TetrisBoard.getBoard();
            //Z
            Assert.assertTrue(TetrisTestUtils.isValidZPlacement(2, 0, board));
            //S
            Assert.assertTrue(TetrisTestUtils.isValidSPlacement(2, 2, board));
            //Z that is on top of S
            Assert.assertTrue(TetrisTestUtils.isValidZPlacement(4, 4, board));
            //T that locks into Z
            Assert.assertTrue(TetrisTestUtils.isValidTPlacement(4, 6, board));
        }catch(Exception e){
            Assert.fail("Exception thrown when reading second valid line.");
        }
        TetrisBoard.clear();

    }
}

