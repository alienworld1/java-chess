package com.chess;

public class Queen extends AbstractPiece {
    public Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());

        boolean isDiagonal = rowDiff == colDiff;
        boolean isStraight = from.getRow() == to.getRow() || from.getColumn() == to.getColumn();

        return (isDiagonal || isStraight) && 
               canCapture(to, board) && 
               isPathClear(from, to, board);
    }

    @Override
    public Piece copy() {
        return new Queen(getColor());
    }
}