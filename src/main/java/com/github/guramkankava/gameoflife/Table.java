package com.github.guramkankava.gameoflife;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Table {

    public static final String TOP_LEFT = "TopLeft";
    public static final String TOP_MIDDLE = "TopMiddle";
    public static final String TOP_RIGHT = "TopRight";

    public static final String LEFT = "Left";
    public static final String RIGHT = "Right";

    public static final String BOTTOM_LEFT = "BottomLeft";
    public static final String BOTTOM_MIDDLE = "BottomMiddle";
    public static final String BOTTOM_RIGHT = "BottomRight";

    private Map<Integer, Row> rows = new TreeMap<> ();

    public void addRow(int rowId) {
        if (!rows.containsKey(rowId)) {
            rows.put(rowId, new Row(rowId));
        }
    }

    public Map<Integer, Row> getRows()
    {
        return rows;
    }

    public Row getRow(int rowId)
    {
        return rows.get(rowId);
    }

    public boolean hasRow(int rowId) {
        return rows.containsKey(rowId);
    }

    public void markCellAs (CellIndex cellIndex, CellState cellState) {
        if (!this.hasRow(cellIndex.rowId()))
        {
            this.addRow(cellIndex.rowId());
        }
        Row row = this.getRow(cellIndex.rowId());
        if (!row.hasCell(cellIndex.cellId())) {
            row.addCell(cellIndex.cellId());
        }
        Cell cell = row.cell(cellIndex.cellId());
        cell.setState(cellState);
        addMissingCells(cellIndex);
    }

    public void addMissingCells(CellIndex cellIndex) {
        for (Map.Entry<String, CellIndex> entry : getCellsNeighboursDimensions(cellIndex).entrySet()) {
            int rowId = entry.getValue().rowId();
            if (!this.hasRow(rowId)) {
                this.addRow(rowId);
            }
            Row row = this.getRow(rowId);
            int cellId = entry.getValue().cellId();
            if (!row.hasCell(cellId)) {
                row.addCell(cellId);
            }
        }
    }

    public int getActiveNeighboursCount(CellIndex cellIndex) {
        Map<String, CellIndex> neighboursIndexes = getCellsNeighboursDimensions(cellIndex);
        int liveNeighboursCount = 0;
        for (Map.Entry<String, CellIndex> entry : neighboursIndexes.entrySet()) {
            int rowId = entry.getValue().rowId();
            if (!this.hasRow(rowId)) {
                continue;
            }
            Row row = this.getRow(rowId);
            int cellId = entry.getValue().cellId();
            if (!row.hasCell(cellId)) {
                continue;
            }
            Cell cell = row.cell(cellId);
            if (cell.isActive()) {
                liveNeighboursCount ++;
            }
        }
        return liveNeighboursCount;
    }

    public Map<String, CellIndex> getCellsNeighboursDimensions(CellIndex cellIndex) {
        Map<String, CellIndex> neighbours = new HashMap<>(8);
        int rowId = cellIndex.rowId();
        int cellId = cellIndex.cellId();
        // get top left
        neighbours.put(TOP_LEFT, new CellIndex(rowId - 1, cellId - 1));
        // get top middle
        neighbours.put(TOP_MIDDLE, new CellIndex(rowId - 1, cellId));
        // get top right
        neighbours.put(TOP_RIGHT, new CellIndex(rowId - 1, cellId + 1));
        // get left
        neighbours.put(LEFT, new CellIndex(rowId, cellId - 1));
        // get right
        neighbours.put(RIGHT, new CellIndex(rowId, cellId + 1));
        // get bottom left
        neighbours.put(BOTTOM_LEFT, new CellIndex(rowId + 1, cellId - 1));
        // get bottom middle
        neighbours.put(BOTTOM_MIDDLE, new CellIndex(rowId + 1, cellId));
        // get bottom right
        neighbours.put(BOTTOM_RIGHT, new CellIndex(rowId + 1, cellId + 1));
        return neighbours;
    }

    public void shuffle() {
        Map<Integer, Row> rows = new TreeMap<> ();
        for (Map.Entry<Integer, Row> entry : this.rows.entrySet()) {
            int rowId = entry.getKey();
            rows.put(rowId, new Row(rowId));
            for (Cell cell : entry.getValue().cells()) {
                int cellId = cell.getId();
                rows.get(rowId).addCell(cellId);
                CellIndex cellIndex = new CellIndex(rowId, cellId);
                int liveNeighboursCount = getActiveNeighboursCount(cellIndex);
                if (liveNeighboursCount == 3 || (liveNeighboursCount == 2 && cell.isActive())) {
                    rows.get(rowId).cell(cellId).setState(CellState.ACTIVE);
                } else {
                    rows.get(rowId).cell(cellId).setState(CellState.INACTIVE);
                }
            }
        }
        this.rows = rows;
    }

    public void print () {
        for(Map.Entry<Integer, Row> entry : getRows().entrySet()) {
            for (Cell cell : entry.getValue().cells()) {
                System.out.printf("%s ", cell.isActive() ? "*" : ".");
            }
            System.out.println();
        }
        System.out.println();
    }
}