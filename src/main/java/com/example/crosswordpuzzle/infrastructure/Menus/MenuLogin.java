package com.example.crosswordpuzzle.infrastructure.Menus;

import com.example.crosswordpuzzle.core.Utility;
import com.example.crosswordpuzzle.Main;

import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import java.util.Objects;

public class MenuLogin {
    public final Scene scene;

    public MenuLogin() {
        GridPane grid = new GridPane();

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        Text sceneTitle = new Text("Welcome to Crossword Puzzle");
        sceneTitle.setFont(Utility.fontHelveticaB20);
        grid.add(sceneTitle, 0, 0, 2, 1);


        Label usernameLabel = new Label("Username:");
        TextField username = new TextField();

        grid.add(usernameLabel, 0, 1);
        grid.add(username, 1, 1);


        Label passwordLabel = new Label("Password:");
        PasswordField password = new PasswordField();

        grid.add(passwordLabel, 0, 2);
        grid.add(password, 1, 2);


        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);

        Button buttonLogIn = new Button("Log in");
        hBox.getChildren().add(buttonLogIn);
        Text infoIfWrong = new Text();

        grid.add(hBox, 1, 4);
        grid.add(infoIfWrong, 1, 6);

        buttonLogIn.setOnAction(actionEvent -> {
            if (Objects.equals(username.getText(), "username") && Objects.equals(password.getText(), "password")) {
                Main.sceneManager.SwitchToMainMenu(actionEvent);
            } else {
                infoIfWrong.setFill(Color.FIREBRICK);
                infoIfWrong.setText("Invalid username or password...");
            }
        });

        scene = new Scene(grid, 400, 250);
        scene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), buttonLogIn::fire);
    }

    public Scene GetScene() {
        return scene;
    }
}
