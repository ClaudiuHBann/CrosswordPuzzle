package com.example.crosswordpuzzle;

import com.example.crosswordpuzzle.infrastructure.Menus.MenuLevelChooser;
import com.example.crosswordpuzzle.infrastructure.CrosswordPuzzle;
import com.example.crosswordpuzzle.infrastructure.Menus.MenuLogin;
import com.example.crosswordpuzzle.infrastructure.Menus.MenuMain;
import com.example.crosswordpuzzle.core.SceneManager;
import com.example.crosswordpuzzle.core.LevelLoader;
import com.example.crosswordpuzzle.core.Utility;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneManager sceneManager;
    public static MenuMain menuMain;
    public static MenuLogin menuLogin;
    public static MenuLevelChooser menuLevelChooser;
    public static LevelLoader levelLoader;
    public static CrosswordPuzzle crosswordPuzzle;

    @Override
    public void start(Stage stage) {
        sceneManager = new SceneManager(stage);
        menuMain = new MenuMain();
        menuLogin = new MenuLogin();
        levelLoader = new LevelLoader(null);
        menuLevelChooser = new MenuLevelChooser();

        stage.setTitle("Crossword Puzzle");
        stage.setScene(menuLogin.GetScene());
        stage.setResizable(false);
        stage.show();

        stage.setX((Utility.screenSize.getWidth() - stage.getWidth()) / 2);
        stage.setY((Utility.screenSize.getHeight() - stage.getHeight()) / 2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

// error handling



// check code for everything?! XD
