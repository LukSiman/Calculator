package logic;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class LogicTest {

    final Logic logic = new Logic();

    @Test
    void addition() {
        assertThat(2 + 3, equalTo(5));
        assertEquals(5, logic.addition(2, 3), 0.1);
        assertEquals(14.8, logic.addition(5.5, 9.3), 0.1);
        assertEquals(-5, logic.addition(-2, -3), 0.1);
        assertEquals(-14.8, logic.addition(-5.5, -9.3), 0.1);
        assertEquals(1, logic.addition(-2, 3), 0.1);
        assertEquals(3.8, logic.addition(-5.5, 9.3), 0.1);
        assertEquals(0, logic.addition(0, 0), 0.1);
        assertEquals(2, logic.addition(0, 2), 0.1);
        assertEquals(3, logic.addition(3, 0), 0.1);
    }

    @Test
    void subtraction() {
        assertEquals(3, logic.subtraction(9, 6), 0.1);
        assertEquals(5.5, logic.subtraction(25.3, 19.8), 0.1);
        assertEquals(-3, logic.subtraction(-9, -6), 0.1);
        assertEquals(-5.5, logic.subtraction(-25.3, -19.8), 0.1);
        assertEquals(-15, logic.subtraction(-9, 6), 0.1);
        assertEquals(-45.1, logic.subtraction(-25.3, 19.8), 0.1);
        assertEquals(0, logic.subtraction(0, 0), 0.1);
        assertEquals(19.8, logic.subtraction(0, -19.8), 0.1);
        assertEquals(2, logic.subtraction(2, 0), 0.1);
    }

    @Test
    void multiplication() {
        assertEquals(6, logic.multiplication(2, 3), 0.1);
        assertEquals(14.52, logic.multiplication(3.3, 4.4), 0.1);
        assertEquals(6, logic.multiplication(-2, -3), 0.1);
        assertEquals(14.52, logic.multiplication(-3.3, -4.4), 0.1);
        assertEquals(-6, logic.multiplication(-2, 3), 0.1);
        assertEquals(-14.52, logic.multiplication(-3.3, 4.4), 0.1);
        assertEquals(0, logic.multiplication(0, 0), 0.1);
        assertEquals(0, logic.multiplication(0, 4.4), 0.1);
        assertEquals(0, logic.multiplication(3.3, 0), 0.1);
    }

    @Test
    void division() {
        assertEquals(4, logic.division(8, 2), 0.1);
        assertEquals(5.3, logic.division(21.73, 4.1), 0.1);
        assertEquals(4, logic.division(-8, -2), 0.1);
        assertEquals(5.3, logic.division(-21.73, -4.1), 0.1);
        assertEquals(-4, logic.division(-8, 2), 0.1);
        assertEquals(-5.3, logic.division(-21.73, 4.1), 0.1);
    }

    @Test
    void squareRoot() {
        assertEquals(5, logic.squareRoot(25), 0.1);
        assertEquals(10, logic.squareRoot(100.20), 0.1);
        assertEquals(0, logic.squareRoot(0), 0.1);
    }
}