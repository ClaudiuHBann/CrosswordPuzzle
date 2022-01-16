package com.example.crosswordpuzzle.infrastructure.Menus;

import com.example.crosswordpuzzle.infrastructure.CrosswordPuzzle;
import com.example.crosswordpuzzle.core.Utility;
import com.example.crosswordpuzzle.Main;

import javafx.collections.FXCollections;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.util.List;

/**
 * Level Chooser Menu GUI
 */
public class MenuLevelChooser {
    private final Scene scene;

    /**
     * Initializes class and also creates the gui of the level chooser menu
     */
    public MenuLevelChooser() {
        GridPane gridpane = new GridPane();

        gridpane.setMinSize(600, 300);
        gridpane.setMaxSize(600, 300);

        List<String> listOfLevels = Main.levelLoader.ListAllFiles();
        List<Pair<Integer, Integer>> listOfSizes = Main.levelLoader.GetSizeOfAll();

        for (int i = 0; i < listOfLevels.size(); i++) {
            if (listOfLevels.get(i).length() > 20) {
                listOfLevels.set(i, listOfLevels.get(i).substring(0, 17) + "...   ");
            } else if (listOfLevels.get(i).length() < 20) {
                listOfLevels.set(i, String.format("%-" + 20 + "s", listOfLevels.get(i)) + "   ");
            } else {
                listOfLevels.set(i, listOfLevels.get(i) + "   ");
            }

            listOfLevels.set(i, listOfLevels.get(i) + "Size: " + listOfSizes.get(i).getKey() + " X " + listOfSizes.get(i).getValue());
        }

        ListView<String> listView = new ListView<>(FXCollections.observableArrayList(listOfLevels));

        listView.setCellFactory(cell -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null) {
                    setText(item);

                    setFont(Utility.fontHelveticaB20);
                }
            }
        });

        Button buttonPlay = Utility.CreateButton("Play", Utility.fontHelveticaB20, 120, 40);

        listView.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                buttonPlay.fire();
            }
        });
        listView.setOnMouseClicked(click -> {
            if (click.getClickCount() == 2 && click.getButton().equals(MouseButton.PRIMARY)) {
                buttonPlay.fire();
            }
        });

        listView.setMinSize(600, 225);
        listView.setMaxSize(600, 225);

        Label levelNameOrNullInfo = new Label("Select a level to load...");

        levelNameOrNullInfo.setFont(Utility.fontHelveticaB20);

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> levelNameOrNullInfo.setText("Selected level is: " + Main.levelLoader.ListAllFiles().get(listView.getSelectionModel().getSelectedIndex())));

        HBox hBox = new HBox();

        hBox.setMinSize(600, 75);
        hBox.setMaxSize(600, 75);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(25);
        hBox.setPadding(new Insets(0, 25, 0, 25));

        buttonPlay.setOnAction(actionEvent -> {
            if (listView.getSelectionModel().getSelectedIndices().size() > 0) {
                Main.crosswordPuzzle = new CrosswordPuzzle(Main.levelLoader.Load(Main.levelLoader.ListAllFiles().get(listView.getSelectionModel().getSelectedIndex())));
                Main.sceneManager.SwitchToCP(actionEvent);
            } else {
                levelNameOrNullInfo.setText("Selected level is: None");
            }
        });

        Button buttonBack = Utility.CreateButton("Back", Utility.fontHelveticaB20, 120, 40);

        buttonBack.setOnAction(actionEvent -> Main.sceneManager.SwitchToMainMenu(actionEvent));

        hBox.getChildren().addAll(buttonBack, levelNameOrNullInfo, buttonPlay);

        gridpane.add(listView, 0, 0);
        gridpane.add(hBox, 0, 1);

        scene = new Scene(gridpane, 600, 300);
    }

    /**
     * Returns the level chooser menu scene
     */
    public Scene GetScene() {
        return scene;
    }
}