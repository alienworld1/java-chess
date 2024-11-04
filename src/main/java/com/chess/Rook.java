package com.chess;

public class Rook extends AbstractPiece {
    private boolean hasMoved = false;

    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }
    
    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        return (from.getRow() == to.getRow() || from.getColumn() == to.getColumn()) && 
               canCapture(to, board) && 
               isPathClear(from, to, board);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public Piece copy() {
        Rook copy = new Rook(getColor());
        copy.hasMoved = this.hasMoved;
        return copy;
    }
}