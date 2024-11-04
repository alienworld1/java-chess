package com.chess;

public interface Player {
    Move getNextMove(Board board);
    PieceColor getColor();
}