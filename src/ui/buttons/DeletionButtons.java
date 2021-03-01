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

public final class DeletionButtons extends Buttons {

    /**
     * Constructor that creates the buttons and adds them to the passed grid
     *
     * @param deletionButtonsGrid is the passed grid from ui.UI class
     */
    public DeletionButtons(final GridPane deletionButtonsGrid) {
        //array that holds the button values
        final String[] deletionButtons = {"Clear", "Backspace"};

        //code iterates through the array and creates buttons
        for (int column = 0; column < deletionButtons.length; column++) {
            Button deletionButton = new Button(deletionButtons[column]);

            //adds the buttons to the GridPane
            deletionButtonsGrid.add(deletionButton, column, 0);
            deletionButton.setMinSize(BUTTON_SIZE * 2.55, BUTTON_SIZE * 0.5);

            //if button is activated then useButton method is run that holds all the logic
            deletionButton.setOnAction((event) -> useButton(deletionButton));
            deletionButton.getStyleClass().add("clear");

            //removes focus from the buttons
            deletionButton.setFocusTraversable(false);
        }
    }

    /**
     * Contains logic for clear and backspace buttons
     *
     * @param button is the button to which functionality will be applied
     */
    @Override
    public final void useButton(final Button button) {
        //checks the passed String value of the button and runs the code accordingly

        //clears the main screen and resets values to default
        if (button.getText().equals("Clear")) {
            mainScreen.setStyle("-fx-font-size: 30px");
            mainScreen.setText("");
            UI.operation = null;
            UI.numberCount = 0;
            UI.wasOperatorUsed = false;
            UI.mathFinished = false;
            UI.decimalResult = false;
            UI.bottomCount = 1;
            UI.positiveNegative = 0;

            //deletes numbers and operations 1 by 1
        } else if (button.getText().equals("Backspace")) {
            String tempScreen = mainScreen.getText();

            //runs only if main screen is not blank and calculations are not done
            if (!mainScreen.getText().isEmpty() && !UI.mathFinished) {

                //since operations have a space before and after this needs to delete 3 characters, sets math operator to null, changes number and operator statuses
                if (tempScreen.substring(tempScreen.length() - 1).isBlank()) {
                    mainScreen.setText(tempScreen.substring(0, tempScreen.length() - 3));
                    UI.wasOperatorUsed = false;
                    UI.wereNumbersEntered = false;
                    UI.operation = null;

                    //if decimal dot is deleted updates the decimal counter
                } else if (tempScreen.endsWith(".")) {
                    mainScreen.setText(tempScreen.substring(0, tempScreen.length() - 1));
                    UI.bottomCount--;
                } else {
                    //otherwise deletes everything 1 by 1, also reduces the number count because limit is 17
                    mainScreen.setText(tempScreen.substring(0, tempScreen.length() - 1));
                    UI.numberCount--;
                }
            }
        }
    }
}