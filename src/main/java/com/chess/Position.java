package com.chess;

import javafx.geometry.Pos;

public class Position {
    private final int row;
    private final int column;
    
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() { return row; }
    public int getColumn() { return column; }
    
    public boolean isValid() {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }

    public String toString() {
        return String.format("(%d, %d)", row, column);
    }

    public boolean equals(Position other) {
        return this.row == other.row && this.column == other.column;
    }
}