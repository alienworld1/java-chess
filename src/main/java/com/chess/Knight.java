package com.chess;

public class Knight extends AbstractPiece {
    public Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }
    
    // The L-shape move
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());
        
        return (rowDiff == 2 && colDiff == 1 || rowDiff == 1 && colDiff == 2) && 
               canCapture(to, board);
    }

    @Override
    public Knight copy() {
        return new Knight(getColor());
    }
}