package logic;

/**
 * Calculator
 * @author Lukas Simanavicius
 * @version 1.0
 */

public final class Logic {

    /**
     * Method that invokes the correct calculation method according to the passed operation
     * @param operation the operator to which the correct operation will be invoked
     * @param numberOne the first number to be used
     * @param numberTwo the second number to be used
     * @return the calculation result
     */
    public final double calculate(final String operation, final double numberOne, final double numberTwo) {
        double result = 0;

        switch (operation) {
            case "+":
                result = addition(numberOne, numberTwo);
                break;
            case "-":
                result = subtraction(numberOne, numberTwo);
                break;
            case "x":
                result = multiplication(numberOne, numberTwo);
                break;
            case "/":
                result = division(numberOne, numberTwo);
                break;
            case "x"+"\u00B2":
                result = power(numberOne);
                break;
            case "\u221A":
                result = squareRoot(numberOne);
                break;
        }
        return result;
    }

    /**
     * @param numberOne first number for the function
     * @param numberTwo second number for the function
     * @return addition between numbers
     */
    public final double addition(final double numberOne, final double numberTwo) {
        final double result = numberOne + numberTwo;
        return Math.round(result * 10000.0) / 10000.0;
    }

    /**
     * @param numberOne first number for the function
     * @param numberTwo second number for the function
     * @return subtraction between two numbers
     */
    public final double subtraction(final double numberOne, final double numberTwo) {
        final double result = numberOne - numberTwo;
        return Math.round(result * 10000.0) / 10000.0;
    }

    /**
     * @param numberOne first number for the function
     * @param numberTwo second number for the function
     * @return multiplication between two numbers
     */
    public final double multiplication(final double numberOne, final double numberTwo) {
        final double result = numberOne * numberTwo;
        return Math.round(result * 10000.0) / 10000.0;
    }

    /**
     * @param numberOne first number for the function
     * @param numberTwo second number for the function
     * @return division between two numbers
     */
    public final double division(final double numberOne, final double numberTwo) {
        final double result = numberOne / numberTwo;
        return Math.round(result * 10000.0) / 10000.0;
    }

    /**
     * @param numberOne first number for the function
     * @return power of two numbers
     */
    public final double power(final double numberOne) {
        final double result = Math.pow(numberOne, 2);
        return Math.round(result * 10000.0) / 10000.0;
    }

    /**
     * @param number number for the function
     * @return squareRoot of the number
     */
    public final double squareRoot(final double number) {
        final double result = Math.sqrt(number);
        return Math.round(result * 10000.0) / 10000.0;
    }
}