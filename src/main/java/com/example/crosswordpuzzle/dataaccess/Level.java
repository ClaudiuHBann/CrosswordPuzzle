package com.example.crosswordpuzzle.dataaccess;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for storing information about a level
 */
public class Level {
    public int gridPaneGameRows;
    public int gridPaneGameColumns;

    public char[][] gameCP;

    public List<String> gameSentencesHorizontal;
    public List<String> gameSentencesVertical;

    /**
     * Initializes the object and makes it ready to use
     * Creates 2 lists for sentences and a matrix for the letters
     *
     * @param gridPaneGameRows    number of rows of the level
     * @param gridPaneGameColumns number of rows of the level
     */
    public Level(int gridPaneGameRows, int gridPaneGameColumns) {
        this.gridPaneGameRows = gridPaneGameRows;
        this.gridPaneGameColumns = gridPaneGameColumns;

        gameCP = new char[gridPaneGameRows][gridPaneGameColumns];

        gameSentencesVertical = new ArrayList<>();
        gameSentencesHorizontal = new ArrayList<>();
    }
}
