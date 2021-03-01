package ui.buttons;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ui.UI;

/**
 * Calculator
 *
 * @author Lukas Simanavicius
 * @version 1.0
 */

public final class OperationButtons extends Buttons {

    private final EqualsButton equalsButton;
    private final Button equal = new Button("=");

    /**
     * Constructor that creates the buttons and adds them to the passed grid
     *
     * @param numberGrid is the passed grid from ui.UI class
     */
    public OperationButtons(final GridPane numberGrid) {
        //array that holds the button values
        final String[][] operations = {{"+", "-"}, {"x", "/"}, {"x" + "\u00B2", "\u221A"}};

        //code iterates through the array and creates buttons
        for (int row = 0; row < operations.length; row++) {
            for (int column = 0; column < operations[row].length; column++) {
                Button operationButton = new Button(operations[row][column]);

                //adds the buttons to the GridPane
                numberGrid.add(operationButton, column + 3, row);
                operationButton.setMinSize(BUTTON_SIZE, BUTTON_SIZE);

                //if button is activated then useButton method is run that holds all the logic
                operationButton.setOnAction((event) -> useButton(operationButton));

                //removes focus from the buttons
                operationButton.setFocusTraversable(false);
            }
        }
        equalsButton = new EqualsButton(numberGrid);
    }

    /**
     * Contains logic for clear and backspace buttons
     *
     * @param button is the button which functionality will be applied
     */
    @Override
    public final void useButton(final Button button) {

        //runs only if last operation was not a division by 0 or there were too many numbers in the result (above 17)
        if (!mainScreen.getText().equals("You tried dividing by zero!") && !mainScreen.getText().equals("Too many numbers!")) {

            //if the last or the only character before operator was a decimal point then code removes it
            if (UI.bottomCount == 2 && UI.operation == null) {
                String[] parts = mainScreen.getText().split("\\.");
                if (parts.length < 1) {
                    mainScreen.setText("");
                } else if (parts.length < 2) {
                    mainScreen.setText(parts[0]);
                }
            }

            if (UI.mathFinished) {
                UI.mathFinished = false;
            }

            String originalOperation = UI.operation;
            UI.operation = button.getText();


            equalsButton.setMainScreen(mainScreen);

            //adds the operator to the screen and sets it's status
            //uses boolean values to determine whether to add, replace or sum + add operators and numbers
            if (!UI.wasOperatorUsed) {
                UI.wasOperatorUsed = true;
                UI.wereNumbersEntered = false;
                mainScreen.setText(mainScreen.getText() + " " + button.getText() + " ");

                //replaces the original operator with new operator
            } else if (!UI.wereNumbersEntered) {
                mainScreen.setText(mainScreen.getText().replace(originalOperation, UI.operation));

                //allows you to sum numbers and add and operator immediately
            } else {
                UI.operation = originalOperation;
                equalsButton.useButton(equal);
                mainScreen.setText(mainScreen.getText() + " " + button.getText() + " ");
                UI.mathFinished = false;
                UI.operation = button.getText();
                UI.wasOperatorUsed = true;
                UI.wereNumbersEntered = false;
            }

            //power and square root buttons do the calculations number immediately
            if (button.getText().equals("x" + "\u00B2") || button.getText().equals("\u221A")) {
                equalsButton.useButton(equal);
            }
        }
    }
}
