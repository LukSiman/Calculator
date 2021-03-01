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

public final class NumberButtons extends Buttons {

    final static int MAX_NUMBERS = 17;

    /**
     * Constructor that creates the buttons and adds them to the passed grid
     *
     * @param numberGrid is the passed grid from ui.UI class
     */
    public NumberButtons(final GridPane numberGrid) {
        //array that holds the button values
        final String[][] numbers = {{"7", "8", "9"}, {"4", "5", "6"}, {"1", "2", "3"}, {"", "0"}};

        //code iterates through the array and creates buttons
        for (int row = 0; row < numbers.length; row++) {
            for (int column = 0; column < numbers[row].length; column++) {
                Button numberButton = new Button("" + numbers[row][column]);

                //adds the buttons to the GridPane
                numberGrid.add(numberButton, column, row);
                numberButton.setMinSize(BUTTON_SIZE, BUTTON_SIZE);

                //if button is activated then useButton method is run that holds all the logic
                numberButton.setOnAction((event) -> useButton(numberButton));

                //removes focus from the buttons
                numberButton.setFocusTraversable(false);
            }
        }
    }

    /**
     * Contains logic for clear and backspace buttons
     *
     * @param button is the button which functionality will be applied
     */
    @Override
    public final void useButton(final Button button) {
        UI.wereNumbersEntered = true;
        if (!UI.mathFinished) {
            //maximum amount of numbers is 17, an arbitrary amount, increments with every new number
            if (UI.numberCount < MAX_NUMBERS) {
                UI.numberCount++;
                mainScreen.setText(mainScreen.getText() + button.getText());
            }
        } else {
            //default number font size
            mainScreen.setStyle("-fx-font-size: 30px");

            //if after the result a number is used then code clears the screen, sets variables to default and enters the number immediately
            mainScreen.setText("");
            UI.mathFinished = false;
            UI.bottomCount = 1;
            UI.numberCount = 0;
            UI.operation = null;
            UI.decimalResult = false;
            button.fire();
        }
    }
}