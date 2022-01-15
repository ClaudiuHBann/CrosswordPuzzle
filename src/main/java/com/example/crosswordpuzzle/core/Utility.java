package com.example.crosswordpuzzle.core;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.text.FontWeight;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;

import org.jetbrains.annotations.NotNull;

public class Utility {
    public static final int GRID_PANE_LETTERS_CELL_SIZE = 50;
    public static final int GAME_SENTENCES_WIDTH = 250;
    public static final int GAME_GUI_HEIGHT = 125;

    public static final Font fontHelveticaB20 = Font.font("Helvetica", FontWeight.BOLD, 20);
    public static final Font fontHelveticaBTC = Font.font("Helvetica", FontWeight.BOLD, GRID_PANE_LETTERS_CELL_SIZE / 3);
    public static final Background backgroundBlack = new Background(new BackgroundFill(Color.BLACK, null, null));
    public static final Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    public static @NotNull Button CreateButton(@NotNull String text, @NotNull Font font, int width, int height) {
        Button newButton = new Button(text);

        newButton.setMinSize(width, height);
        newButton.setMaxSize(width, height);

        newButton.setFont(font);

        return newButton;
    }
}
