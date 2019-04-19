package Nqueens;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Nqueens.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hardik Mody
 */
public class GeneticAlgorithmTest {
    
    public GeneticAlgorithmTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    //******************************************************************************************
    //    Comment This while running main file
//    Uncomment rand.setSeed(50) in GeneticAlgorithm->GeneticRun while running this to check Mutation

//    @Test
//    public void testMutation1() {
//
//        RunNQueens tester = new RunNQueens();
//
//        tester.test(8, 0.001, 1000);
//        int result1 = 40;
//        int result2 = tester.galg.getMutations();
//
//        assertEquals(result1, result2);
//
//    }
////    
//
//    @Test
//    public void testMutation2() {
//
//        RunNQueens test = new RunNQueens();
//
//        test.test(8, 0.001, 2000);
//        int result1 = 40;
//        int result2 = test.galg.getMutations();
//
//        assertEquals(result1, result2);
//
//    }
//  
//
//    @Test
//    public void testMutation3() {
//
//        RunNQueens test = new RunNQueens();
//
//        test.test(8, 0.001, 3000);
//        int result1 = 40;
//        int result2 = test.galg.getMutations();
//
//        assertEquals(result1, result2);
//
//    }
//
//    @Test
//    public void testMutation4() {
//
//        RunNQueens test = new RunNQueens();
//
//        test.test(8, 0.001, 4000);
//        int result1 = 40;
//        int result2 = test.galg.getMutations();
//
//        assertEquals(result1, result2);
//
//    }
//
//    @Test
//    public void testMutation5() {
//
//        RunNQueens test = new RunNQueens();
//
//        test.test(8, 0.001, 5000);
//        int result1 = 40;
//        int result2 = test.galg.getMutations();
//
//        assertEquals(result1, result2);
//
//    }
////********************************************************************************
//
//    @Test
//    public void testChooseOffspring() {
//
//        RunNQueens test = new RunNQueens();
//
//        test.test(8, 0.001, 3000);
//        int result1 = 600;
//        int result2 = test.galg.getCount();
//
//        assertEquals(result1, result2);
//
//    }
//************************************************************************************
//
    @Test
    public void testRunGA1() {
        System.out.println("runGA");
        RunNQueens test = new RunNQueens();
        test.test(4, 0.1, 8);
        boolean result1 = true;
        boolean result2 = test.galg.GeneticRun();

        assertEquals(result1, result2);

    }

    @Test
    public void testRunGA2() {
        System.out.println("runGA");
        RunNQueens test = new RunNQueens();
        test.test(4, 0.15, 20);
        boolean expResult = true;
        boolean result = test.galg.GeneticRun();

        assertEquals(expResult, result);

    }

    @Test
    public void testRunGA3() {
        System.out.println("runGA");
        RunNQueens test = new RunNQueens();
        test.test(4, 0.02, 10);
        boolean expResult = true;
        boolean result = test.galg.GeneticRun();

        assertEquals(expResult, result);

    }

    @Test
    public void testRunGA4() {
        System.out.println("runGA");
        RunNQueens test = new RunNQueens();

        test.test(4, 0.001, 15);
        boolean expResult = true;
        boolean result = test.galg.GeneticRun();

        assertEquals(expResult, result);

    }

    @Test
    public void testRunGA5() {
        System.out.println("runGA");
        RunNQueens test = new RunNQueens();

        test.test(4, 0.1, 19);
        boolean expResult = true;
        boolean result = test.galg.GeneticRun();

        assertEquals(expResult, result);

    }
}
