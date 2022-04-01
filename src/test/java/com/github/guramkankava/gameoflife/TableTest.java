package com.github.guramkankava.gameoflife;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class TableTest {

    @Test
    public void testShuffle () {
        Table table = new Table();
        table.markCellAs(new CellIndex(2, 4), CellState.ACTIVE);
        table.markCellAs(new CellIndex(2, 5), CellState.ACTIVE);
        table.markCellAs(new CellIndex(3, 5), CellState.ACTIVE);
        table.markCellAs(new CellIndex(6, 4), CellState.ACTIVE);
        table.markCellAs(new CellIndex(6, 5), CellState.ACTIVE);
        table.markCellAs(new CellIndex(7, 3), CellState.ACTIVE);
        table.markCellAs(new CellIndex(7, 4), CellState.ACTIVE);
        table.markCellAs(new CellIndex(8, 6), CellState.ACTIVE);
        table.markCellAs(new CellIndex(9, 5), CellState.ACTIVE);
        table.print();
        table.shuffle();
        table.print();
        //Any live cell with two or three live neighbors lives
        assertTrue(table.getRow(2).cell(4).isActive());
        //Any live cell with two or three live neighbors lives
        assertTrue(table.getRow(2).cell(5).isActive());
        // Any dead cell with exactly three live neighbors becomes a live cell
        assertTrue(table.getRow(3).cell(4).isActive());
        //Any live cell with two or three live neighbors lives
        assertTrue(table.getRow(3).cell(5).isActive());
        // Any dead cell with exactly three live neighbors becomes a live cell
        assertTrue(table.getRow(6).cell(3).isActive());
        //Any live cell with two or three live neighbors lives
        assertTrue(table.getRow(6).cell(4).isActive());
        //Any live cell with two or three live neighbors lives
        assertTrue(table.getRow(6).cell(5).isActive());
        // Any dead cell with exactly three live neighbors becomes a live cell
        assertTrue(table.getRow(8).cell(5).isActive());
        // Any live cell with fewer than two live neighbors dies
        assertFalse(table.getRow(8).cell(6).isActive());
        // Any live cell with fewer than two live neighbors dies
        assertFalse(table.getRow(9).cell(5).isActive());
    }

    @Test
    public void testGetCellsNeighboursDimensions() {
        Table table = new Table();
        Map<String, CellIndex> cellsNeighboursDimensions = table.getCellsNeighboursDimensions(new CellIndex(1, 1));
        //Cell is surrounded by 8 cells, 3 top, 2 side, 3 bottom
        assertEquals(8, cellsNeighboursDimensions.size());
        CellIndex topLeft = cellsNeighboursDimensions.get("TopLeft");
        assertEquals(0, topLeft.rowId());
        assertEquals(0, topLeft.cellId());
        CellIndex topMiddle = cellsNeighboursDimensions.get("TopMiddle");
        assertEquals(0, topMiddle.rowId());
        assertEquals(1, topMiddle.cellId());
        CellIndex topRight = cellsNeighboursDimensions.get("TopRight");
        assertEquals(0, topRight.rowId());
        assertEquals(2, topRight.cellId());
        CellIndex left = cellsNeighboursDimensions.get("Left");
        assertEquals(1, left.rowId());
        assertEquals(0, left.cellId());
        CellIndex right = cellsNeighboursDimensions.get("Right");
        assertEquals(1, right.rowId());
        assertEquals(2, right.cellId());
        CellIndex bottomLeft = cellsNeighboursDimensions.get("BottomLeft");
        assertEquals(2, bottomLeft.rowId());
        assertEquals(0, bottomLeft.cellId());
        CellIndex bottomMiddle = cellsNeighboursDimensions.get("BottomMiddle");
        assertEquals(2, bottomMiddle.rowId());
        assertEquals(1, bottomMiddle.cellId());
        CellIndex bottomRight = cellsNeighboursDimensions.get("BottomRight");
        assertEquals(2, bottomRight.rowId());
        assertEquals(2, bottomRight.cellId());
    }

    @Test
    public void testAddMissingCells() {
        Table table = new Table();
        table.addRow(1);
        assertTrue(table.hasRow(1));
        Row middleRow = table.getRow(1);
        middleRow.addCell(1);
        assertTrue(middleRow.hasCell(1));
        assertFalse(middleRow.hasCell(0));
        CellIndex cellIndex = new CellIndex(1,1);
        table.addMissingCells(cellIndex);
        assertTrue(middleRow.hasCell(0));
        assertTrue(middleRow.hasCell(2));
        assertTrue(table.hasRow(0));
        Row topRow = table.getRow(0);
        assertTrue(topRow.hasCell(0));
        assertTrue(topRow.hasCell(1));
        assertTrue(topRow.hasCell(2));
        assertTrue(table.hasRow(2));
        Row bottomRow = table.getRow(2);
        assertTrue(bottomRow.hasCell(0));
        assertTrue(bottomRow.hasCell(1));
        assertTrue(bottomRow.hasCell(2));
    }

    @Test
    public void testGetActiveNeighboursCount () {
        CellIndex middleCellIndex = new CellIndex(2, 2);
        Table table = new Table();
        table.markCellAs(middleCellIndex, CellState.ACTIVE);
        int activeNeighboursCount = table.getActiveNeighboursCount(middleCellIndex);
        assertEquals(0, activeNeighboursCount);
        CellIndex leftCellIndex = new CellIndex(2, 1);
        table.markCellAs(leftCellIndex, CellState.ACTIVE);
        activeNeighboursCount = table.getActiveNeighboursCount(middleCellIndex);
        assertEquals(1, activeNeighboursCount);
        CellIndex rightCellIndex = new CellIndex(2, 3);
        table.markCellAs(rightCellIndex, CellState.ACTIVE);
        activeNeighboursCount = table.getActiveNeighboursCount(middleCellIndex);
        assertEquals(2, activeNeighboursCount);

        int leftCellActiveNeighboursCount = table.getActiveNeighboursCount(leftCellIndex);
        assertEquals(1, leftCellActiveNeighboursCount);
        int rightCellActiveNeighboursCount = table.getActiveNeighboursCount(rightCellIndex);
        assertEquals(1, rightCellActiveNeighboursCount);
        CellIndex topMiddleCellIndex = new CellIndex(1, 2);
        int topMiddleCellActiveNeighboursCount = table.getActiveNeighboursCount(topMiddleCellIndex);
        assertEquals(3, topMiddleCellActiveNeighboursCount);
        CellIndex topLeftCellIndex = new CellIndex(1, 1);
        int topLeftCellActiveNeighboursCount = table.getActiveNeighboursCount(topLeftCellIndex);
        assertEquals(2, topLeftCellActiveNeighboursCount);
        CellIndex topRightCellIndex = new CellIndex(1, 3);
        int topRightCellActiveNeighboursCount = table.getActiveNeighboursCount(topRightCellIndex);
        assertEquals(2, topRightCellActiveNeighboursCount);
        CellIndex bottomLeftCellIndex = new CellIndex(3, 1);
        int bottomLeftCellActiveNeighboursCount = table.getActiveNeighboursCount(bottomLeftCellIndex);
        assertEquals(2, bottomLeftCellActiveNeighboursCount);
        CellIndex bottomMiddleCellIndex = new CellIndex(3, 2);
        int bottomMiddleCellActiveNeighboursCount = table.getActiveNeighboursCount(bottomMiddleCellIndex);
        assertEquals(3, bottomMiddleCellActiveNeighboursCount);
        CellIndex bottomRightCellIndex = new CellIndex(3, 3);
        int bottomRightCellActiveNeighboursCount = table.getActiveNeighboursCount(bottomRightCellIndex);
        assertEquals(2, bottomRightCellActiveNeighboursCount);
    }
}