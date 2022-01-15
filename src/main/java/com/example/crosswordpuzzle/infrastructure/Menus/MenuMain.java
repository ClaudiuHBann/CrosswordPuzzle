package com.example.crosswordpuzzle.infrastructure.Menus;

import com.example.crosswordpuzzle.core.Utility;
import com.example.crosswordpuzzle.Main;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.util.Arrays;
import java.util.List;

public class MenuMain {
    private final Scene scene;

    public MenuMain() {
        List<Button> listOfButtons = Arrays.asList(
                Utility.CreateButton("Choose Level", Utility.fontHelveticaB20, 180, 40),
                Utility.CreateButton("Quit", Utility.fontHelveticaB20, 120, 40)
        );

        listOfButtons.get(0).setOnAction(actionEvent -> Main.sceneManager.SwitchToMenuLevelChooser(actionEvent));
        listOfButtons.get(1).setOnAction(actionEvent -> Main.sceneManager.Quit());

        VBox vBox = new VBox();
        vBox.setBackground(Utility.backgroundBlack);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(listOfButtons.get(0), listOfButtons.get(1));

        scene = new Scene(vBox, 320, 240);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN), listOfButtons.get(0)::fire);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN), listOfButtons.get(1)::fire);
    }

    public Scene GetScene() {
        return scene;
    }
}
