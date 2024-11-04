package com.chess;

public interface Piece {
    boolean isValidMove(Position from, Position to, Board board);
    PieceColor getColor();
    PieceType getType();
    Piece copy();
}
