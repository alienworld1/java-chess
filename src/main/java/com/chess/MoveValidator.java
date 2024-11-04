package com.chess;

public class MoveValidator {
    public boolean isValidMove(Move move, Board board) {
        return move.getPiece().isValidMove(move.getFrom(), move.getTo(), board) &&
               !wouldResultInCheck(move, board);
    }
    
    private boolean wouldResultInCheck(Move move, Board board) {
        // TODO: Implement check validation
        return false;
    }
}