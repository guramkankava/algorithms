package com.github.guramkankava.gameoflife;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GridTest {

    @BeforeEach
    public void beforeEach () {

    }

    @Test
    public void testNextGeneration () {
        Grid grid = new Grid(10,  10);
        grid.setCellAsAlive(3,3);
        grid.setCellAsAlive(3,4);
        grid.setCellAsAlive(4,2);
        grid.setCellAsAlive(4,3);
        grid.setCellAsAlive(5,5);
        grid.setCellAsAlive(6,4);
        for (int i = 0; i < 5; i++) {
            grid.print();
            grid.nextGeneration();
        }
    }

    @DisplayName("Assert for valid next generation")
    @Test
    public void testNextGenerationV2 () {
        Grid grid = new Grid(25, 25);
        grid.setCellAsAlive(12,12);
        grid.setCellAsAlive(13,13);
        grid.setCellAsAlive(14,11);
        grid.setCellAsAlive(14,12);
        grid.setCellAsAlive(14,13);
        grid.print();
        byte[][] nextGeneration = grid.nextGeneration();
        grid.print();
        // Any live cell with fewer than two live neighbors dies
        assertEquals(0, nextGeneration[12][12]);
        // Any dead cell with exactly three live neighbors becomes a live cell
        assertEquals(1, nextGeneration[13][11]);
        //Any live cell with two or three live neighbors lives
        assertEquals(1, nextGeneration[13][13]);
        // Any live cell with fewer than two live neighbors dies
        assertEquals(0, nextGeneration[14][11]);
        //Any live cell with two or three live neighbors lives
        assertEquals(1, nextGeneration[14][12]);
        //Any live cell with two or three live neighbors lives
        assertEquals(1, nextGeneration[14][13]);
        // Any dead cell with exactly three live neighbors becomes a live cell
        assertEquals(1, nextGeneration[14][12]);
    }
}