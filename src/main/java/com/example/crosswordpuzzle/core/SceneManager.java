package com.example.crosswordpuzzle.core;

import com.example.crosswordpuzzle.Main;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import org.jetbrains.annotations.NotNull;

public class SceneManager {
    private Stage stage;

    public SceneManager(@NotNull Stage stage) {
        this.stage = stage;
    }

    public void SwitchToMainMenu(@NotNull ActionEvent actionEvent) {
        SwitchTo(actionEvent, Main.menuMain.GetScene());
    }

    public void SwitchToCP(@NotNull ActionEvent actionEvent) {
        SwitchTo(actionEvent, Main.crosswordPuzzle.GetScene());
    }

    public void SwitchToMenuLevelChooser(@NotNull ActionEvent actionEvent) { SwitchTo(actionEvent, Main.menuLevelChooser.GetScene()); }

    private void SwitchTo(@NotNull ActionEvent actionEvent, Scene scene) {
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.setX((Utility.screenSize.getWidth() - stage.getWidth()) / 2);
        stage.setY((Utility.screenSize.getHeight() - stage.getHeight()) / 2);

        stage.show();
    }

    public void Quit() {
        stage.close();
    }
}
