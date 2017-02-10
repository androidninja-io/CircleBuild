package io.androidninja.circlebuild;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TempClassUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        TempClass tempClass = new TempClass();
        assertEquals(4, tempClass.add(2,2));
    }

    @Test
    public void subtraction_isCorrect() throws Exception {
        TempClass tempClass = new TempClass();
        assertEquals(2, tempClass.sub(4,2));
    }

}
