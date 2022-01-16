package com.example.crosswordpuzzle;

import com.example.crosswordpuzzle.infrastructure.Menus.*;
import com.example.crosswordpuzzle.infrastructure.*;
import com.example.crosswordpuzzle.core.*;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Initializes everything for the app and has all the instances of the application
 */
@SuppressWarnings("ClassEscapesDefinedScope")
public class Main extends Application {
    public static SceneManager sceneManager;
    public static LevelLoader levelLoader;

    public static MenuLogin menuLogin;
    public static MenuMain menuMain;
    public static MenuLevelChooser menuLevelChooser;
    public static CrosswordPuzzle crosswordPuzzle;

    /**
     * Overridden method which starts the app
     *
     * @param stage the stage of the app
     */
    @Override
    public void start(Stage stage) {
        sceneManager = new SceneManager(stage);
        levelLoader = new LevelLoader(null);

        menuLogin = new MenuLogin();
        menuMain = new MenuMain();
        menuLevelChooser = new MenuLevelChooser();

        stage.setTitle("Crossword Puzzle");
        stage.setScene(menuLogin.GetScene());
        stage.setResizable(false);
        stage.show();

        stage.setX((Utility.screenSize.getWidth() - stage.getWidth()) / 2);
        stage.setY((Utility.screenSize.getHeight() - stage.getHeight()) / 2);
    }

    /**
     * The entry point of the app
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}