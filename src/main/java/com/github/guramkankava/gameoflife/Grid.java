package com.github.guramkankava.gameoflife;

import java.util.Arrays;

public class Grid {
    private final int rowCount, columnCount;

    private byte[][] table;

    public Grid(int rowCount, int columnCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        table = new byte[rowCount][columnCount];
    }

    public void setCellAsAlive(int row, int cell) {
        setCellAsAlive(this.table, row, cell);
    }

    private void setCellAsAlive(byte[][] table, int row, int cell) {
        table[row][cell] = 1;
    }

    public void print() {
        for (int rowId = 0; rowId < table.length; rowId++) {
            String rowToString = Arrays.toString(table[rowId])
                    .replace("0", ".")
                    .replace("1", "*")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "");
            System.out.printf("%d %s \n", rowId + 1, rowToString);
        }
        System.out.print("\n");
    }

    public byte[][] nextGeneration() {
        byte[][] nextGeneration = new byte[rowCount][columnCount];
        for (int rowId = 0; rowId < rowCount; rowId++) {
            for (int columnId = 0; columnId < columnCount; columnId++) {
                byte liveNeighboursCount = getColumnLiveNeighboursCount(rowId, columnId);
                if ((isColumnAlive(rowId, columnId) && liveNeighboursCount == 2) || liveNeighboursCount == 3) {
                    setCellAsAlive(nextGeneration, rowId, columnId);
                }
            }
        }
        table = nextGeneration;
        return nextGeneration;
    }

    private byte getColumnLiveNeighboursCount(int rowId, int columnId) {
        // get top left
        byte liveNeighboursCount = getColumn(rowId - 1, columnId - 1);

        // get top middle
        liveNeighboursCount += getColumn(rowId - 1, columnId);

        // get top right
        liveNeighboursCount += getColumn(rowId - 1, columnId + 1);

        // get left
        liveNeighboursCount += getColumn(rowId, columnId - 1);

        // get right
        liveNeighboursCount += getColumn(rowId, columnId + 1);

        // get bottom left
        liveNeighboursCount += getColumn(rowId + 1, columnId - 1);

        // get bottom middle
        liveNeighboursCount += getColumn(rowId + 1, columnId);

        // get bottom right
        liveNeighboursCount += getColumn(rowId + 1, columnId + 1);

        return liveNeighboursCount;
    }

    private byte getColumn(int rowId, int columnId) {
        try {
            return table[rowId][columnId];
        } catch (IndexOutOfBoundsException e) {
            return 0;
        }
    }

    private boolean isColumnAlive(int rowId, int columnId) {
        return getColumn(rowId, columnId) == 1;
    }

}
