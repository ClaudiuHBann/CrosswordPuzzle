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

/**
 * Class for loading levels and information about them
 */
public record LevelLoader(String rootFolder) {
    /**
     * Sets the root folder where levels are
     *
     * @param rootFolder the full path to the folder where levels are
     */
    public LevelLoader(String rootFolder) {
        this.rootFolder = (rootFolder == null) ? System.getProperty("user.dir") + "\\levels\\" : rootFolder;
    }

    /**
     * Returns a list of strings representing the levels names
     */
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

    /**
     * Returns a list of pairs of ints representing the levels number of rows and columns
     */
    public @NotNull List<Pair<Integer, Integer>> GetSizeOfAll() {
        List<Pair<Integer, Integer>> listOfSizes = new ArrayList<>();

        ListAllFiles().forEach(fileName -> {
            try {
                String rowsAndColumns = new Scanner(new File(rootFolder + fileName + ".cpl"), StandardCharsets.UTF_8).nextLine();
                listOfSizes.add(new Pair<>(Integer.parseInt(rowsAndColumns.split(" ")[0]), Integer.parseInt(rowsAndColumns.split(" ")[1])));
            } catch (IOException e) {
                Utility.ShowExceptionDialog(e);
            }
        });

        return listOfSizes;
    }

    /**
     * Loads a level with the specified name (and extension)
     *
     * @param levelName the name of the level with or without the extension
     * @return level object containing all the information about a level
     */
    public Level Load(String levelName) {
        if (levelName == null) {
            return null;
        } else if (levelName.indexOf('.') == -1) {
            levelName += ".cpl";
        }

        Scanner scanner;
        try {
            scanner = new Scanner(new File(rootFolder + levelName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Utility.ShowExceptionDialog(e);
            return null;
        }

        String rowsAndColumns = Objects.requireNonNull(scanner).nextLine();
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
