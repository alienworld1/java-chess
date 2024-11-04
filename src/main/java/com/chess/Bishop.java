package com.chess;

public class Bishop extends AbstractPiece {
    public Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());

        System.out.println("rowDiff: " + rowDiff);
        System.out.println("colDiff: " + colDiff);
        System.out.println("canCapture(to, board): " + canCapture(to, board));
        System.out.println("isPathClear(from, to, board): " + isPathClear(from, to, board));

        // Check if the move is diagonal
        return rowDiff == colDiff && 
               canCapture(to, board) && 
               isPathClear(from, to, board);
    }

    @Override
    public Piece copy() {
        return new Bishop(getColor());
    }
}