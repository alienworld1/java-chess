package com.chess;

public abstract class AbstractPiece implements Piece {
    protected final PieceColor color;
    protected final PieceType type;
    
    public AbstractPiece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }
    
    @Override
    public PieceColor getColor() {
        return color;
    }
    
    @Override
    public PieceType getType() {
        return type;
    }
    
    protected boolean isPathClear(Position from, Position to, Board board) {
        if (from.equals(to)) {
            return true;
        }
        System.out.println("from = " + from.toString());
        System.out.println("to = " + to.toString());
        
        int rowStep = Integer.compare(to.getRow() - from.getRow(), 0);
        int colStep = Integer.compare(to.getColumn() - from.getColumn(), 0);

        Position current = new Position(from.getRow() + rowStep, from.getColumn() + colStep);
        while (!current.equals(to)) {
            System.out.println("current pos = " + current.toString());
            if (!current.isValid() || board.getPiece(current) != null) {
                return false;
            }
            current = new Position(current.getRow() + rowStep, current.getColumn() + colStep);
        }
        
        // Check the final position
        if (!to.isValid() || board.getPiece(to) != null) {
            return false;
        }

        return true;
    }
    
    protected boolean canCapture(Position to, Board board) {
        Piece targetPiece = board.getPiece(to);
        return targetPiece == null || targetPiece.getColor() != this.color;
    }

    @Override
    public abstract Piece copy();
}