package com.github.guramkankava.gameoflife;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class Row {

    private final int id;

    private final Map<Integer, Cell> cells = new TreeMap<>();

    public Row (int id) {
        this.id = id;
    }

    public int getId () {
        return this.id;
    }

    public Cell cell (int cellId) {
        return cells.get(cellId);
    }

    public Collection<Cell> cells () {
        return cells.values();
    }

    public void addCell (int cellId) {
        if(!cells.containsKey(cellId)) {
            cells.put(cellId, new Cell(cellId, CellState.INACTIVE));
        }
    }

    public boolean hasCell (int cellId) {
        return cells.containsKey(cellId);
    }
}
