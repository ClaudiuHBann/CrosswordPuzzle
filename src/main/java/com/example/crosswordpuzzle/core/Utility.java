package com.example.crosswordpuzzle.core;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.FontWeight;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import org.jetbrains.annotations.NotNull;

import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * A class which contains static methods for creating styled buttons and exception dialogs
 * Also contains static fonts, info about GUI sizes etc...
 */
public class Utility {
    public static final int GRID_PANE_LETTERS_CELL_SIZE = 50;
    public static final int GAME_SENTENCES_WIDTH = 250;
    public static final int GAME_GUI_HEIGHT = 125;

    public static final Font fontHelveticaB20 = Font.font("Helvetica", FontWeight.BOLD, 20);
    public static final Font fontHelveticaBTC = Font.font("Helvetica", FontWeight.BOLD, GRID_PANE_LETTERS_CELL_SIZE / 3);

    public static final Background backgroundBlack = new Background(new BackgroundFill(Color.BLACK, null, null));

    public static final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    /**
     * Returns a button with the specified information
     *
     * @param text   the text of the button
     * @param font   the font of the text of the button
     * @param width  the width of the button
     * @param height the height of the button
     * @return a button with the specified text, font and size
     */
    public static @NotNull Button CreateButton(@NotNull String text, @NotNull Font font, int width, int height) {
        Button newButton = new Button(text);

        newButton.setMinSize(width, height);
        newButton.setMaxSize(width, height);

        newButton.setFont(font);

        return newButton;
    }

    /**
     * Creates and shows a dialog with an exception trace
     *
     * @param exception the exception to be showed
     */
    public static void ShowExceptionDialog(@NotNull Exception exception) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");

        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));

        TextArea textArea = new TextArea(stringWriter.toString());

        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(new Label("The exception stacktrace was:"), 0, 0);
        gridPane.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(gridPane);

        alert.showAndWait();
    }
}
