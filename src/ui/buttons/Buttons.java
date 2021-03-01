package ui.buttons;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Calculator
 *
 * @author Lukas Simanavicius
 * @version 1.0
 */


public abstract class Buttons {

    public Label mainScreen;
    public final static int BUTTON_SIZE = 60;

    /**
     * Method for setting the main screen that shows all the calculations
     *
     * @param mainScreen the label for the main screen
     */
    public void setMainScreen(final Label mainScreen) {
        mainScreen.setAlignment(Pos.CENTER_RIGHT);
        this.mainScreen = mainScreen;
    }


    @SuppressWarnings({"unused", "DanglingJavadoc"})
    /**
     * abstract method that will hold logic for all different buttons once children implement it
     * @param button the passed button
     */
    public abstract void useButton(final Button button);
}
