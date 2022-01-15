package com.example.crosswordpuzzle.infrastructure;

import com.example.crosswordpuzzle.dataaccess.Level;
import com.example.crosswordpuzzle.core.Utility;
import com.example.crosswordpuzzle.Main;

import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.FontWeight;
import javafx.scene.input.KeyCode;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;
import java.util.Objects;
import java.util.List;

public class CrosswordPuzzle {
    private final Scene scene;
    private final TextField[][] textFields;
    private Button buttonCheck, buttonGoToMainMenu;

    public CrosswordPuzzle(@NotNull Level level) {
        textFields = new TextField[level.gridPaneGameRows][level.gridPaneGameColumns];

        scene = new Scene(
                CreateLayout(level),
                level.gridPaneGameColumns * Utility.GRID_PANE_LETTERS_CELL_SIZE + Utility.GAME_SENTENCES_WIDTH * 2,
                level.gridPaneGameRows * Utility.GRID_PANE_LETTERS_CELL_SIZE + Utility.GAME_GUI_HEIGHT);

        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.M, KeyCombination.CONTROL_DOWN), buttonGoToMainMenu::fire);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN), buttonCheck::fire);

        CreateContextMenu(level);
    }

    private @NotNull ScrollPane CreateScrollPane(int width, int height, @NotNull List<String> sentences, @NotNull Font font) {
        ScrollPane newScrollPane = new ScrollPane();

        newScrollPane.setMinSize(width, height);
        newScrollPane.setMaxSize(width, height);

        StringBuilder stringBuilder = new StringBuilder().append('\n');
        for (String sentence : sentences) {
            stringBuilder.append(sentence).append('\n');
        }

        Text newText = new Text(stringBuilder.toString());

        newText.setFont(font);
        newText.setTextAlignment(TextAlignment.CENTER);
        newText.setWrappingWidth(Utility.GAME_SENTENCES_WIDTH - 15);

        newScrollPane.setContent(newText);

        return newScrollPane;
    }

    private @NotNull BorderPane CreateLayout(@NotNull Level level) {
        BorderPane newBorderPane = new BorderPane();
        newBorderPane.setTop(CreateGUI(level));

        newBorderPane.setLeft(CreateScrollPane(
                Utility.GAME_SENTENCES_WIDTH,
                level.gridPaneGameRows * Utility.GRID_PANE_LETTERS_CELL_SIZE,
                level.gameSentencesHorizontal,
                Utility.fontHelveticaBTC
        ));

        newBorderPane.setCenter(CreateGridPaneWithLetters(level));

        newBorderPane.setRight(CreateScrollPane(
                Utility.GAME_SENTENCES_WIDTH,
                level.gridPaneGameRows * Utility.GRID_PANE_LETTERS_CELL_SIZE,
                level.gameSentencesVertical,
                Utility.fontHelveticaBTC
        ));

        return newBorderPane;
    }

    private @NotNull GridPane CreateGridPaneWithLetters(@NotNull Level level) {
        GridPane newGridPane = new GridPane();
        newGridPane.setBackground(Utility.backgroundBlack);

        for (int row = 0; row < level.gridPaneGameRows; row++) {
            for (int column = 0; column < level.gridPaneGameColumns; column++) {
                if (level.gameCP[row][column] == 0) {
                    continue;
                }

                TextField newTextField = new TextField();

                newTextField.setMinSize(Utility.GRID_PANE_LETTERS_CELL_SIZE, Utility.GRID_PANE_LETTERS_CELL_SIZE);
                newTextField.setMaxSize(Utility.GRID_PANE_LETTERS_CELL_SIZE, Utility.GRID_PANE_LETTERS_CELL_SIZE);

                newTextField.setFont(Font.font("Helvetica", FontWeight.BOLD, Utility.GRID_PANE_LETTERS_CELL_SIZE / 4 + Utility.GRID_PANE_LETTERS_CELL_SIZE / 8));
                newTextField.setAlignment(Pos.CENTER);

                newTextField.setTextFormatter(new TextFormatter<>(filter -> (Pattern.compile("[a-zA-Z]*").matcher(filter.getControlNewText()).matches()) ? filter : null));
                newTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (newTextField.getText().length() > 1) {
                        newTextField.setText((newValue.indexOf(oldValue) == 1) ? newTextField.getText().substring(0, 1) : newTextField.getText().substring(1, 2));
                    }

                    newTextField.setText(newTextField.getText().toUpperCase());
                });

                //TEST
                newTextField.setText(String.valueOf(level.gameCP[row][column]));
                //TEST

                textFields[row][column] = newTextField;
                newGridPane.add(newTextField, column, row);
            }
        }

        return newGridPane;
    }

    private void CreateContextMenu(@NotNull Level level) {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem menuItemCheck = new MenuItem("Check");
        MenuItem menuItemClearCells = new MenuItem("Clear Cells");

        menuItemCheck.setOnAction(actionEvent -> buttonCheck.fire());
        menuItemClearCells.setOnAction(actionEvent -> {
            for (int row = 0; row < level.gridPaneGameRows; row++) {
                for (int column = 0; column < level.gridPaneGameColumns; column++) {
                    if (level.gameCP[row][column] == 0) {
                        continue;
                    }

                    textFields[row][column].setText("");
                }
            }
        });

        contextMenu.getItems().add(menuItemCheck);
        contextMenu.getItems().add(menuItemClearCells);

        scene.setOnContextMenuRequested(contextMenuEvent -> contextMenu.show(scene.getWindow(), contextMenuEvent.getScreenX(), contextMenuEvent.getScreenY()));
    }

    private @NotNull GridPane CreateGUI(@NotNull Level level) {
        GridPane newGridPane = new GridPane();

        newGridPane.setMinSize(level.gridPaneGameColumns * Utility.GRID_PANE_LETTERS_CELL_SIZE + Utility.GAME_SENTENCES_WIDTH * 2, Utility.GAME_GUI_HEIGHT);
        newGridPane.setMaxSize(level.gridPaneGameColumns * Utility.GRID_PANE_LETTERS_CELL_SIZE + Utility.GAME_SENTENCES_WIDTH * 2, Utility.GAME_GUI_HEIGHT);

        newGridPane.setAlignment(Pos.CENTER);
        newGridPane.setHgap(25);

        buttonCheck = Utility.CreateButton("Check", Utility.fontHelveticaB20, 120, 40);
        buttonCheck.setOnAction(event -> {
            for (int row = 0; row < level.gridPaneGameRows; row++) {
                for (int column = 0; column < level.gridPaneGameColumns; column++) {
                    if (level.gameCP[row][column] == 0) {
                        continue;
                    }

                    if (Objects.equals(textFields[row][column].getText(), "") || textFields[row][column].getText().charAt(0) != level.gameCP[row][column]) {
                        buttonCheck.setText("Check Again");
                        buttonCheck.setMinSize(180, 40);
                        buttonCheck.setMaxSize(180, 40);
                        return;
                    }
                }
            }

            buttonCheck.setText("Done");
            buttonCheck.setMinSize(120, 40);
            buttonCheck.setMaxSize(120, 40);
        });

        buttonGoToMainMenu = Utility.CreateButton("Main Menu", Utility.fontHelveticaB20, 180, 40);
        buttonGoToMainMenu.setOnAction(actionEvent -> Main.sceneManager.SwitchToMainMenu(actionEvent));

        Button buttonChooseLevel = Utility.CreateButton("Choose Level Menu", Utility.fontHelveticaB20, 240, 40);
        buttonChooseLevel.setOnAction(actionEvent -> Main.sceneManager.SwitchToMenuLevelChooser(actionEvent));

        newGridPane.add(buttonGoToMainMenu, 0, 0);
        newGridPane.add(buttonCheck, 1, 0);
        newGridPane.add(buttonChooseLevel, 2, 0);

        return newGridPane;
    }

    public Scene GetScene() {
        return scene;
    }
}
