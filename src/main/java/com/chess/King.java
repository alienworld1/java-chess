package com.chess;

public class King extends AbstractPiece {
    private boolean hasMoved = false;

    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean isValidMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getColumn() - from.getColumn());

        // Normal move
        if (rowDiff <= 1 && colDiff <= 1) {
            return canCapture(to, board) && !board.isPositionUnderAttack(to, getColor());
        }

        // Castling
        if (!hasMoved && rowDiff == 0 && colDiff == 2) {
            return isValidCastling(from, to, board);
        }

        return false;
    }

    private boolean isValidCastling(Position from, Position to, Board board) {
        int direction = to.getColumn() > from.getColumn() ? 1 : -1;
        Position rookPosition = new Position(from.getRow(), direction > 0 ? 7 : 0);
        Piece rook = board.getPiece(rookPosition);

        if (!(rook instanceof Rook) || ((Rook)rook).hasMoved()) {
            return false;
        }

        // Check if path is clear and not under attack
        return isPathClear(from, rookPosition, board) && 
               !isPathUnderAttack(from, to, board);
    }

    private boolean isPathUnderAttack(Position from, Position to, Board board) {
        int rowStep = Integer.compare(to.getRow() - from.getRow(), 0);
        int colStep = Integer.compare(to.getColumn() - from.getColumn(), 0);

        Position current = new Position(from.getRow() + rowStep, from.getColumn() + colStep);
        while (!current.equals(to)) {
            if (board.isPositionUnderAttack(current, getColor())) {
                return true;
            }
            current = new Position(current.getRow() + rowStep, current.getColumn() + colStep);
        }

        return board.isPositionUnderAttack(to, getColor());
    }

    @Override
    public Piece copy() {
        King copy = new King(getColor());
        copy.hasMoved = this.hasMoved;
        return copy;
    }
}