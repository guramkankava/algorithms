package com.github.guramkankava.gameoflife;

public class Cell {

    private final int id;
    private CellState state;

    public Cell(int id) {
        this.id = id;
        this.state = CellState.INACTIVE;
    }

    public Cell(int id, CellState state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return CellState.ACTIVE.equals(state);
    }

    public void setState(CellState state) {
        this.state = state;
    }
        
}