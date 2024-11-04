package com.chess;

public class Pawn extends AbstractPiece {
    private boolean hasMoved = false;
    
    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int direction = (color == PieceColor.WHITE) ? -1 : 1;
        int rowDiff = to.getRow() - from.getRow();
        int colDiff = Math.abs(to.getColumn() - from.getColumn());
        
        // Basic forward move
        if (colDiff == 0 && rowDiff == direction && board.getPiece(to) == null) {
            return true;
        }
        
        // Initial two-square move
        if (!hasMoved && colDiff == 0 && rowDiff == 2 * direction) {
            Position intermediate = new Position(from.getRow() + direction, from.getColumn());
            return board.getPiece(to) == null && board.getPiece(intermediate) == null;
        }
        
        // Capture move
        if (colDiff == 1 && rowDiff == direction) {
            Piece targetPiece = board.getPiece(to);
            return targetPiece != null && targetPiece.getColor() != this.color;
        }
        
        return false;
    }

    @Override
    public Pawn copy() {
        Pawn copy = new Pawn(getColor());
        copy.hasMoved = this.hasMoved;
        return copy;
    }
}