package org.mm.testprojectjetbrainsdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * @author lelay
 * @since 06.09.19
 */
@RunWith(Parameterized.class)
public class AppTest {

    private App app = new App();

    private String firstSequence;
    private String secondSequence;
    private boolean correctAnswer;

    public AppTest(String firstSequence, String secondSequence, boolean correctAnswer) {
        this.firstSequence = firstSequence;
        this.secondSequence = secondSequence;
        this.correctAnswer = correctAnswer;
    }

    @Test
    public void resultWillBeEqualTest() {
        assertEquals(correctAnswer, app.resultWillBeEqual(firstSequence, secondSequence));
    }

    @Parameterized.Parameters
    public static Collection appParams() {
        return Arrays.asList(new Object[][] {
                {"+2+10+20", "+20+10+2", true},
                {"+10*2", "*2+10", false},
                {"+10*2*3", "+10*3*2", true},

                {"+9+10*2*3*5+11+15", "+10+9*5*2*3+15+11",true},
                {"+0+1*3", "+1*3+0", true}, // 0 addition case
                {"*1+2*3", "+2*1*3", true}, // 1 multiplication case
                {"+2+3*1*1*1+2+3", "+2*1+3*1+2+3*1", true},
                {"*1*1*1", "*1*1*1", true},
                {"*1*0+1", "*0+1*1", true}
        });
    }
}
