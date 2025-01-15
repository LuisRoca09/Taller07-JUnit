

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void testMakeFormula() {
        String formula = Operations.MakeFormula();
        System.out.println("Formula generada: " + formula);
        assertTrue(formula.matches("^[0-9]+([\\+\\-\\*/][0-9]+)+$"));
        String[] tokens = formula.split("[\\+\\-\\*/]");
        assertTrue(tokens.length >= 2 && tokens.length <= 4);
    }

    @Test
    void testSolveSimpleAddition() {
        String formula = "5+3";
        String result = Operations.Solve(formula);
        assertEquals("5+3=8", result);
    }

    @Test
    void testSolveComplexExpression() {
        String formula = "2+3*4-5";
        String result = Operations.Solve(formula);
        assertEquals("2+3*4-5=9", result);
    }

    @Test
    void testSolveDivision() {
        String formula = "10/2";
        String result = Operations.Solve(formula);
        assertEquals("10/2=5", result);
    }

    @Test
    void testSolveDivisionByZero() {
        String formula = "10/0";
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            Operations.Solve(formula);
        });
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void testSolveNegativeNumbers() {
        String formula = "-5+3";
        String result = Operations.Solve(formula);
        assertEquals("-5+3=-2", result);
    }

    @Test
    void testSolveWithMultipleOperators() {
        String formula = "5+3*2-8/4";
        String result = Operations.Solve(formula);
        assertEquals("5+3*2-8/4=10", result);
    }
}
