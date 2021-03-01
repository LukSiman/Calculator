package ui.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import logic.Logic;
import ui.UI;

/**
 * Calculator
 *
 * @author Lukas Simanavicius
 * @version 1.0
 */

public final class EqualsButton extends Buttons {

    private final Logic logic;

    /**
     * Constructor that creates the buttons and adds them to the passed grid
     *
     * @param numberGrid is the passed grid from ui.UI class
     */
    public EqualsButton(final GridPane numberGrid) {
        //creates logic object that does all the actual calculations
        logic = new Logic();
        Button equalsButton = new Button("=");
        equalsButton.setMinSize(BUTTON_SIZE * 2, BUTTON_SIZE);

        //adds the buttons to the GridPane
        numberGrid.add(equalsButton, 3, 3, 2, 1);

        //if button is activated then useButton method is run that holds all the logic
        equalsButton.setOnAction((event) -> useButton(equalsButton));

        //removes focus from the buttons
        equalsButton.setFocusTraversable(false);
    }

    /**
     * Contains logic for the equals button
     *
     * @param button is the button to which functionality will be applied
     */
    @Override
    public final void useButton(final Button button) {

        //runs if operation was already was input and valid
        if (UI.operation != null) {


            //if the only or the first value is the math operator then code clears the main screen
            if (mainScreen.getText().strip().equals(UI.operation)) {
                mainScreen.setText("");
            } else {
                //otherwise code splits the String of the main screen in parts and runs calculations based on the operator
                //splitting is necessary in order to get both left side and right side numbers (operator being the middle)
                String[] parts;
                String screenText = mainScreen.getText().replaceAll("\\s", "");
                //since + is a special java sign we need to exit out first
                if (UI.operation.equals("+")) {
                    parts = screenText.split("\\+");
                } else if (screenText.charAt(0) == '-' && UI.operation.equals("-")) {
                    screenText = screenText.substring(1);
                    parts = screenText.split(UI.operation);
                    parts[0] = "-" + parts[0];
                } else {
                    parts = screenText.split(UI.operation);
                }

                //numberTwoReal is same as numberOneReal in case user wants to apply operator to the first number immediately
                double numberOneReal = Double.parseDouble(parts[0]);
                double numberTwoReal = numberOneReal;

                //changes numberTwoReal to the actual right number value if it exists
                if (parts.length > 1) {
                    numberTwoReal = Double.parseDouble(parts[1]);
                }

                double result;

                //special instruction for dividing by zero
                //font change is needed since the phrase doesn't fit with the original size
                if (UI.operation.equals("/") && numberTwoReal == 0) {
                    mainScreen.setStyle("-fx-font-size: 22px");
                    mainScreen.setText("You tried dividing by zero!");
                } else {
                    result = logic.calculate(UI.operation, numberOneReal, numberTwoReal);

                    //displays result with decimals if needed
                    if (result % 1 == 0) {
                        int resultToInt = (int) result;
                        mainScreen.setText("" + resultToInt);
                        UI.decimalResult = false;
                        UI.numberCount = ("" + resultToInt).length();
                    } else {
                        mainScreen.setText("" + result);
                        UI.decimalResult = true;
                        UI.numberCount = ("" + result).length() - 1;
                    }
                }
                UI.mathFinished = true;

                //if there are more than 17 numbers in the answer then the bellow text is shown
                if (UI.numberCount > NumberButtons.MAX_NUMBERS) {
                    mainScreen.setStyle("-fx-font-size: 27px");
                    mainScreen.setText("Too many numbers!");
                }
            }
            UI.operation = null;
            UI.wasOperatorUsed = false;
            UI.bottomCount = 1;
        }
    }
}