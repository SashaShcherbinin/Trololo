package com.sprinklebit.stepa;

import com.sprinklebit.input.InputData;
import com.sprinklebit.input.pojo.Ride;
import com.sprinklebit.vlad.DistanceUtils;
import com.sprinklebit.vlad.Utils;
import javafx.print.Collation;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ScoreTest extends TestCase {

    private final String input;
    private final String output;

    private static final String EXAMPLE_A_PATH = "a_example.in";
    private static final String EXAMPLE_B_PATH = "b_should_be_easy.in";
    private static final String EXAMPLE_C_PATH = "c_no_hurry.in";
    private static final String EXAMPLE_D_PATH = "d_metropolis.in";
    private static final String EXAMPLE_E_PATH = "e_high_bonus.in";
    private static final String OUTPUT_A_PATH = "outResult_a.txt";
    private static final String OUTPUT_A1_PATH = "outResult_a1.txt";
    private static final String OUTPUT_A2_PATH = "outResult_a2.txt";
    private static final String OUTPUT_A3_PATH = "outResult_a3.txt";
    private static final String OUTPUT_A4_PATH = "outResult_a4.txt";
    private static final String OUTPUT_B_PATH = "outResult_b.txt";
    private static final String OUTPUT_C_PATH = "outResult_c.txt";
    private static final String OUTPUT_D_PATH = "outResult_d.txt";
    private static final String OUTPUT_E_PATH = "outResult_e.txt";
    private final int expectedScore;

    public ScoreTest(String input, String output, int  expectedScore) {
        this.input = input;
        this.output = output;
        this.expectedScore = expectedScore;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList(new Object[][] {
                { EXAMPLE_A_PATH, OUTPUT_A_PATH, 10},
                { EXAMPLE_A_PATH, OUTPUT_A1_PATH, 6},
                { EXAMPLE_A_PATH, OUTPUT_A2_PATH, 8},
                { EXAMPLE_A_PATH, OUTPUT_A3_PATH, 4},
                { EXAMPLE_A_PATH, OUTPUT_A4_PATH, 2},
                { EXAMPLE_E_PATH, OUTPUT_E_PATH, 3985634},
        });
    }

    @Test
    public void testRidesQtyInRow() {
        ResultReader output = new ResultReader(this.output);

        for (StepaRoute route : output.getStepaRoutes()) {
            assertEquals(route.getQty(), route.getRideIndexes().length);
        }
    }

    @Test
    public void testScore() {
        ResultReader output = new ResultReader(this.output);
        InputData input = new InputData(this.input, new Utils());

        Score sc = new Score(input, output);

        assertEquals(this.expectedScore, sc.calculateScore());
    }
}