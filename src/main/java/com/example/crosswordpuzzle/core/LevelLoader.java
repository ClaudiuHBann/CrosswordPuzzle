package com.example.crosswordpuzzle.core;

import com.example.crosswordpuzzle.dataaccess.Level;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.io.File;

import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

public record LevelLoader(String rootFolder) {
    public static List<String> loadedLevels;

    public LevelLoader(String rootFolder) {
        this.rootFolder = (rootFolder == null) ? System.getProperty("user.dir") + "\\levels\\" : rootFolder;

        loadedLevels = new ArrayList<>();
    }

    public @NotNull List<String> ListAllFiles() {
        List<String> levels = new ArrayList<>();

        for (File file : Objects.requireNonNull(new File(this.rootFolder).listFiles())) {
            if (file.isFile()) {
                String[] splitedLevel = file.getName().split("\\.");
                if (splitedLevel[1].contains("cpl")) {
                    levels.add(splitedLevel[0]);
                }
            }
        }

        return levels;
    }

    public @NotNull List<Pair<Integer, Integer>> GetSizeOfAll() {
        List<Pair<Integer, Integer>> listOfSizes = new ArrayList<>();

        ListAllFiles().forEach(levelName -> {
            File file = new File(rootFolder + levelName + ".cpl");
            Scanner scanner = null;
            try {
                scanner = new Scanner(file, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String rowsAndColumns = scanner.nextLine();

            listOfSizes.add(new Pair<>(Integer.parseInt(rowsAndColumns.split(" ")[0]), Integer.parseInt(rowsAndColumns.split(" ")[1])));
        });

        return listOfSizes;
    }

    public Level Load(String levelName) throws IOException {
        if (levelName == null || loadedLevels.contains(levelName)) {
            return null;
        } else if (levelName.indexOf('.') == -1) {
            levelName += ".cpl";
        }

        loadedLevels.add(levelName);

        File file = new File(rootFolder + levelName);
        Scanner scanner = new Scanner(file, StandardCharsets.UTF_8);
        String rowsAndColumns = scanner.nextLine();
        Level level = new Level(Integer.parseInt(rowsAndColumns.split(" ")[0]), Integer.parseInt(rowsAndColumns.split(" ")[1]));

        for (int row = 0; row < level.gridPaneGameRows; row++) {
            String nextLine = scanner.nextLine();
            for (int column = 0; column < level.gridPaneGameColumns; column++) {
                level.gameCP[row][column] = (nextLine.charAt(column) == '.') ? 0 : nextLine.charAt(column);
            }
        }

        level.gameSentencesHorizontal.add(scanner.nextLine().split("\\.")[1] + '\n');

        String nextLine = scanner.nextLine();
        while (nextLine.charAt(0) != '.') {
            level.gameSentencesHorizontal.add(nextLine);
            nextLine = scanner.nextLine();
        }

        level.gameSentencesVertical.add(nextLine.split("\\.")[1] + '\n');

        while (scanner.hasNextLine()) {
            nextLine = scanner.nextLine();
            level.gameSentencesVertical.add(nextLine);
        }

        scanner.close();

        return level;
    }
}
