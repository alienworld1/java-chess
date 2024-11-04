package com.chess;

public interface AIPlayer extends Player {
    int evaluatePosition(Board board);
    void setDifficulty(int depth);
}