package com.tetris.batch.service;

import com.tetris.batch.exception.TetrisInputException;
import com.tetris.batch.model.TetrisBoard;
import com.tetris.batch.model.tetromino.I;
import com.tetris.batch.model.tetromino.J;
import com.tetris.batch.model.tetromino.L;
import com.tetris.batch.model.tetromino.Q;
import com.tetris.batch.model.tetromino.S;
import com.tetris.batch.model.tetromino.T;
import com.tetris.batch.model.tetromino.Tetromino;
import com.tetris.batch.model.tetromino.Z;
import com.tetris.batch.util.TetrisTestUtils;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TetrisGameServiceTest {
    private TetrisPiecePlacerI tetrisPiecePlacerI;
    private TetrisPiecePlacerJ tetrisPiecePlacerJ;
    private TetrisPiecePlacerL tetrisPiecePlacerL;
    private TetrisPiecePlacerQ tetrisPiecePlacerQ;
    private TetrisPiecePlacerT tetrisPiecePlacerT;
    private TetrisPiecePlacerZ tetrisPiecePlacerZ;
    private TetrisPiecePlacerS tetrisPiecePlacerS;

    private TetrisGameService tetrisGameService;

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
    }

    @AfterEach
    void tearDown(){
        TetrisBoard.clear();
    }

    @Test
    void testISuccess()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        Assert.assertEquals(1, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidIPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testJSuccess()throws TetrisInputException{
        Tetromino tetromino = new J('J', 0);
        Assert.assertEquals(3, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidJPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testLSuccess()throws TetrisInputException{
        Tetromino tetromino = new L('L', 0);
        Assert.assertEquals(3, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidLPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testQSuccess()throws TetrisInputException{
        Tetromino tetromino = new Q('Q', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidQPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testTSuccess()throws TetrisInputException{
        Tetromino tetromino = new T('T', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidTPlacement(1, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testZSuccess()throws TetrisInputException{
        TetrisBoard.clear();
        Tetromino tetromino = new Z('Z', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidZPlacement(1, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testSSuccess()throws TetrisInputException{
        Tetromino tetromino = new S('S', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidSPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testQLength10ClearSuccess()throws TetrisInputException{
        Tetromino tetromino = new Q('Q', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 2);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 4);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 6);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 8);
        Assert.assertEquals(0, tetrisGameService.place(tetromino));
        Assert.assertFalse(TetrisTestUtils.isValidQPlacement(0, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testLOccupiedSecondColumnSuccess()throws TetrisInputException{
        Tetromino tetromino = new L('L', 0);
        Assert.assertEquals(3, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidLPlacement(0, 0, TetrisBoard.getBoard()));
        tetromino = new L('L', 0);
        Assert.assertEquals(6, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidLPlacement(3, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testLClearAndFillSuccessIs()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 4);
        tetrisGameService.place(tetromino);
        tetromino = new L('L', 8);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        int[][] board = TetrisBoard.getBoard();
        Assert.assertEquals(1, board[0][8]);
        Assert.assertEquals(0, board[0][9]);
        //Check for successful clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(0, board[0][i]);
        TetrisBoard.clear();
    }

    @Test
    void testLClearAndFillSuccessQs()throws TetrisInputException{
        Tetromino tetromino = new Q('Q', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 2);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 4);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 6);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        
        tetromino = new L('L', 8);
        int[][] board = TetrisBoard.getBoard();

        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertEquals(1, board[0][8]);
        Assert.assertEquals(1, board[1][8]);
        Assert.assertEquals(0, board[0][9]);
        //Check for successful fill after clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(1, board[0][i]);
        TetrisBoard.clear();
    }

    @Test
    void testJOccupiedFirstColumnSuccess()throws TetrisInputException{
        Tetromino tetromino = new J('J', 0);
        Assert.assertEquals(3, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidJPlacement(0, 0, TetrisBoard.getBoard()));
        tetromino = new J('J', 0);
        Assert.assertEquals(6, tetrisGameService.place(tetromino));
        Assert.assertTrue(TetrisTestUtils.isValidJPlacement(3, 0, TetrisBoard.getBoard()));
        TetrisBoard.clear();
    }

    @Test
    void testJClearAndFillSuccessIs()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 4);
        tetrisGameService.place(tetromino);
        tetromino = new J('J', 8);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        int[][] board = TetrisBoard.getBoard();
        Assert.assertEquals(0, board[0][8]);
        Assert.assertEquals(1, board[0][9]);
        Assert.assertEquals(1, board[1][9]);
        //Check for successful clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(0, board[0][i]);
        TetrisBoard.clear();
    }

    @Test
    void testJClearAndFillSuccessQs()throws TetrisInputException{
        Tetromino tetromino = new Q('Q', 0);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 2);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 4);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        tetromino = new Q('Q', 6);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));  
        tetromino = new J('J', 8);
        int[][] board = TetrisBoard.getBoard();

        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        Assert.assertEquals(1, board[0][9]);
        Assert.assertEquals(1, board[1][9]);
        Assert.assertEquals(0, board[0][8]);
        //Check for successful fill after clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(1, board[0][i]);
        TetrisBoard.clear();
    }

    @Test
    void testIClearAndFillSuccessQs()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 4);
        tetrisGameService.place(tetromino);
        tetromino = new Q('Q', 8);
        Assert.assertEquals(1, tetrisGameService.place(tetromino));
        int[][] board = TetrisBoard.getBoard();
        Assert.assertEquals(1, board[0][8]);
        Assert.assertEquals(1, board[0][9]);
        //Check for successful clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(0, board[0][i]);
        TetrisBoard.clear();
    }

    @Test
    void testIClearAndFillSuccess2ndRow()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 4);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 4);
        tetrisGameService.place(tetromino);
        tetromino = new J('J', 8);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        int[][] board = TetrisBoard.getBoard();

        Assert.assertEquals(1, board[0][9]);
        Assert.assertEquals(1, board[1][9]);
        Assert.assertEquals(0, board[0][8]);
        //Check for successful fill after clear
        for(int i = 0; i < 8; i++) Assert.assertEquals(1, board[0][i]);
        TetrisBoard.clear();
    }

    /* Testing a more complex fill to see if it behaves correctly.
    * Before: 
    * [0, 0, 0, 1, 1, 1, 0, 0, 0, 0]
    * [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    * [1, 1, 1, 1, 1, 1, 0, 0, 1, 0]
    * After: 
    * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    * [0, 0, 0, 1, 1, 1, 0, 0, 0, 0]
    * [1, 1, 1, 1, 1, 1, 0, 0, 1, 0]
    */
    @Test
    void testSAndTClearAndFillSuccess2ndRow()throws TetrisInputException{
        Tetromino tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new S('S', 4);
        tetrisGameService.place(tetromino);
        tetromino = new T('T', 7);
        tetrisGameService.place(tetromino);
        tetromino = new I('I', 0);
        tetrisGameService.place(tetromino);
        tetromino = new T('T', 3);
        Assert.assertEquals(2, tetrisGameService.place(tetromino));
        int[][] board = TetrisBoard.getBoard();

        Assert.assertEquals(1, board[1][3]);
        Assert.assertEquals(1, board[1][4]);
        Assert.assertEquals(0, board[1][2]);
        TetrisBoard.clear();
    }
}
