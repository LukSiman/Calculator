package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ui.buttons.*;

/**
 * Calculator
 *
 * @author Lukas Simanavicius
 * @version 1.0
 */

public final class UI extends Application {

    //shared variables that buttons use for their logic
    public static String operation = null;
    public static int numberCount = 0;
    public static boolean wasOperatorUsed = false;
    public static boolean wereNumbersEntered = false;
    public static boolean mathFinished = false;
    public static boolean decimalResult = false;
    public static int bottomCount = 1;
    public static int positiveNegative = 0;

    /**
     * Calculator visual layout
     * Creates different button objects
     * Contains keyboard commands for the button objects
     */
    @Override
    public final void start(Stage window) {

        //Creating the main layout and mainScreen where calculations will be displayed
        BorderPane layout = new BorderPane();
        Label mainScreen = new Label();
        mainScreen.setMinSize(320, 120);
        mainScreen.setMaxSize(320, 120);
        mainScreen.getStyleClass().add("mainscreen");
        layout.setTop(mainScreen);
        layout.setPadding(new Insets(5, 0, 10, 0));
        BorderPane.setAlignment(mainScreen, Pos.CENTER);


        //Creating a GridPane that will hold all the buttons for numbers and operations
        GridPane numberGrid = new GridPane();
        numberGrid.getStyleClass().add("numbers");
        numberGrid.setPadding(new Insets(5, 10, 0, 10));
        numberGrid.setVgap(2);
        numberGrid.setHgap(2);
        layout.setBottom(numberGrid);
        BorderPane.setAlignment(numberGrid, Pos.CENTER);


        //Creating a GridPane that will hold clearing and backspace buttons
        GridPane deleteButtons = new GridPane();
        deleteButtons.setHgap(2);
        deleteButtons.setPadding(new Insets(1, 10, 0, 10));
        layout.setCenter(deleteButtons);
        BorderPane.setAlignment(deleteButtons, Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);

        window.setMinHeight(470);
        window.setMinWidth(344);
        window.setMaxHeight(470);
        window.setMaxWidth(344);

        //Creating the deletion buttons and keyboard command for backspace
        DeletionButtons deletionButtons = new DeletionButtons(deleteButtons);
        deletionButtons.setMainScreen(mainScreen);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.BACK_SPACE) {
                Button keyboardOperation = new Button("Backspace");
                deletionButtons.useButton(keyboardOperation);
            }
        });

        //Creating the number buttons and keyboard commands for them
        NumberButtons numberButtons = new NumberButtons(numberGrid);
        numberButtons.setMainScreen(mainScreen);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode().isDigitKey()) {
                Button keyboardNumber = new Button("" + key.getText());
                numberButtons.useButton(keyboardNumber);
            }
        });

        //Creating the operation buttons and keyboard commands for addition, subtraction, multiplication and division
        OperationButtons operationButtons = new OperationButtons(numberGrid);
        operationButtons.setMainScreen(mainScreen);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ADD) {
                Button keyboardOperation = new Button("+");
                operationButtons.useButton(keyboardOperation);
            } else if (key.getCode() == KeyCode.SUBTRACT) {
                Button keyboardOperation = new Button("-");
                operationButtons.useButton(keyboardOperation);
            } else if (key.getCode() == KeyCode.MULTIPLY) {
                Button keyboardOperation = new Button("x");
                operationButtons.useButton(keyboardOperation);
            } else if (key.getCode() == KeyCode.DIVIDE) {
                Button keyboardOperation = new Button("/");
                operationButtons.useButton(keyboardOperation);
            }
        });

        //Creating the decimal and change positivity buttons
        // Creating keyboard command for decimal button
        BottomButtons bottomButtons = new BottomButtons(numberGrid);
        bottomButtons.setMainScreen(mainScreen);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.DECIMAL) {
                Button keyboardOperation = new Button(".");
                bottomButtons.useButton(keyboardOperation);
            }
        });

        //Creating the equals button and keyboard commands for it
        EqualsButton equalsButton = new EqualsButton(numberGrid);
        equalsButton.setMainScreen(mainScreen);

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if (key.getCode() == KeyCode.ENTER) {
                Button keyboardEquals = new Button("=");
                equalsButton.useButton(keyboardEquals);
            } else if (key.getCode() == KeyCode.EQUALS) {
                Button keyboardEquals = new Button("=");
                equalsButton.useButton(keyboardEquals);
            }
        });

        window.show();
    }
}