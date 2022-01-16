package com.example.crosswordpuzzle.core;

import com.example.crosswordpuzzle.Main;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import org.jetbrains.annotations.NotNull;

/**
 * A manager for switching scenes
 */
public class SceneManager {
    private Stage stage;

    /**
     * Initializes the class
     */
    public SceneManager(@NotNull Stage stage) {
        this.stage = stage;
    }

    /**
     * Switches to the main menu scene
     *
     * @param actionEvent the action event
     */
    public void SwitchToMainMenu(@NotNull ActionEvent actionEvent) {
        SwitchTo(actionEvent, Main.menuMain.GetScene());
    }

    /**
     * Switches to the crossword puzzle scene
     *
     * @param actionEvent the action event
     */
    public void SwitchToCP(@NotNull ActionEvent actionEvent) {
        SwitchTo(actionEvent, Main.crosswordPuzzle.GetScene());
    }

    /**
     * Switches to the level chooser menu scene
     *
     * @param actionEvent the action event
     */
    public void SwitchToMenuLevelChooser(@NotNull ActionEvent actionEvent) {
        SwitchTo(actionEvent, Main.menuLevelChooser.GetScene());
    }

    /**
     * The method which is used for the wrappers to switch scenes
     * Moves the windows in the middle of the screen automatically
     *
     * @param actionEvent the action event
     * @param scene       the scene to be switched to
     */
    private void SwitchTo(@NotNull ActionEvent actionEvent, Scene scene) {
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(scene);

        stage.setX((Utility.screenSize.getWidth() - stage.getWidth()) / 2);
        stage.setY((Utility.screenSize.getHeight() - stage.getHeight()) / 2);

        stage.show();
    }

    /**
     * Quits the application
     */
    public void Quit() {
        stage.close();
    }
}
