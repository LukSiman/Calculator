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

public final class BottomButtons extends Buttons {

    /**
     * Constructor that creates the buttons and adds them to the passed grid
     *
     * @param numberGrid is the passed grid from ui.UI class
     */
    public BottomButtons(final GridPane numberGrid) {

        //array that holds the button values
        final String[] bottomRow = {"+/-", "0", "."};

        //code iterates through the array and creates buttons
        for (int column = 0; column < bottomRow.length; column++) {

            //skips 0 button
            if (bottomRow[column].equals("0")) {
                continue;
            }
            Button bottomButton = new Button(bottomRow[column]);

            //adds the buttons to the GridPane
            numberGrid.add(bottomButton, column, 3);
            bottomButton.setMinSize(BUTTON_SIZE, BUTTON_SIZE);

            //if button is activated then useButton method is run that holds all the logic
            bottomButton.setOnAction((event) -> useButton(bottomButton));

            //removes focus from the buttons
            bottomButton.setFocusTraversable(false);
        }
    }

    /**
     * Contains logic for clear and backspace buttons
     *
     * @param button is the button which functionality will be applied
     */
    @Override
    public final void useButton(final Button button) {

        //runs only if calculations haven't been done or at least have been reset
        if (!UI.mathFinished) {

            //checks whether the value is positive or negative
            if (button.getText().equals("+/-")) {
                if (UI.positiveNegative == 0) {
                    mainScreen.setText("-" + mainScreen.getText());
                    UI.positiveNegative = 1;
                } else {

                    //replaces the negative or positive value
                    mainScreen.setText((mainScreen.getText().replaceFirst("-", "")));
                    UI.positiveNegative = 0;
                }

                //logic for decimals
            } else if (button.getText().equals(".")) {

                //if result was decimal then updates the available decimal point value
                if (UI.decimalResult) {
                    UI.bottomCount = 2;
                    UI.decimalResult = false;
                }

                //adds the decimal point at the very beginning, calculations still work with number starting with a decimal
                if (UI.operation == null && UI.bottomCount == 1) {
                    UI.bottomCount++;
                    mainScreen.setText(mainScreen.getText() + button.getText());

                    //add decimal to the second number, also works if started with only decimal
                } else if (UI.operation != null && UI.bottomCount <= 2) {
                    mainScreen.setText(mainScreen.getText() + button.getText());
                    UI.bottomCount++;
                }
            }
        }
    }
}