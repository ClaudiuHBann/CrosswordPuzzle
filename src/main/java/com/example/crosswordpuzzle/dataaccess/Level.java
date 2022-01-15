package com.example.crosswordpuzzle.dataaccess;

import java.util.ArrayList;
import java.util.List;

public class Level {
    public int gridPaneGameRows;
    public int gridPaneGameColumns;

    public char[][] gameCP;
    public List<String> gameSentencesHorizontal;
    public List<String> gameSentencesVertical;

    public Level(int gridPaneGameRows, int gridPaneGameColumns)
    {
        this.gridPaneGameRows = gridPaneGameRows;
        this.gridPaneGameColumns = gridPaneGameColumns;

        gameCP = new char[gridPaneGameRows][gridPaneGameColumns];

        gameSentencesVertical = new ArrayList<>();
        gameSentencesHorizontal = new ArrayList<>();
    }
}
