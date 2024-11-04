package com.chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Piece[][] squares;
    private List<Move> moveHistory;
    private PieceColor currentTurn;
    
    public Board() {
        squares = new Piece[8][8];
        moveHistory = new ArrayList<>();
        initializeBoard();
    }
    
    public Piece getPiece(Position position) {
        return squares[position.getRow()][position.getColumn()];
    }
        
    private void initializeBoard() {
            // Place pawns
            for (int i = 0; i < 8; i++) {
                squares[1][i] = new Pawn(PieceColor.BLACK);
                squares[6][i] = new Pawn(PieceColor.WHITE);
            }
            
            // Place other pieces
            squares[0][0] = new Rook(PieceColor.BLACK);
            squares[0][7] = new Rook(PieceColor.BLACK);
            squares[7][0] = new Rook(PieceColor.WHITE);
            squares[7][7] = new Rook(PieceColor.WHITE);
            
            squares[0][1] = new Knight(PieceColor.BLACK);
            squares[0][6] = new Knight(PieceColor.BLACK);
            squares[7][1] = new Knight(PieceColor.WHITE);
            squares[7][6] = new Knight(PieceColor.WHITE);
            
            squares[0][2] = new Bishop(PieceColor.BLACK);
            squares[0][5] = new Bishop(PieceColor.BLACK);
            squares[7][2] = new Bishop(PieceColor.WHITE);
            squares[7][5] = new Bishop(PieceColor.WHITE);
            
            squares[0][3] = new Queen(PieceColor.BLACK);
            squares[7][3] = new Queen(PieceColor.WHITE);
            
            squares[0][4] = new King(PieceColor.BLACK);
            squares[7][4] = new King(PieceColor.WHITE);
    }

    public void movePiece(Move move) {
        Piece movedPiece = squares[move.getFrom().getRow()][move.getFrom().getColumn()];
        squares[move.getTo().getRow()][move.getTo().getColumn()] = movedPiece;
        squares[move.getFrom().getRow()][move.getFrom().getColumn()] = null;
        moveHistory.add(move);
        currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public boolean isInCheck(PieceColor color) {
        Position kingPosition = findKingPosition(color);
        return isPositionUnderAttack(kingPosition, color);
    }

    public boolean isCheckmate(PieceColor color) {
        if (!isInCheck(color)) {
            return false;
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece != null && piece.getColor() == color) {
                    Position from = new Position(row, col);
                    for (int toRow = 0; toRow < 8; toRow++) {
                        for (int toCol = 0; toCol < 8; toCol++) {
                            Position to = new Position(toRow, toCol);
                            if (piece.isValidMove(from, to, this)) {
                                Move move = new Move(from, to, piece, squares[toRow][toCol]);
                                Board tempBoard = copyBoard();
                                tempBoard.movePiece(move);
                                if (!tempBoard.isInCheck(color)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean isPositionUnderAttack(Position position, PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece != null && piece.getColor() != color && piece.isValidMove(new Position(row, col), position, this)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Position findKingPosition(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("King not found on the board.");
    }

    private Board copyBoard() {
        Board copy = new Board();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = squares[row][col];
                if (piece != null) {
                    copy.squares[row][col] = piece.copy();
                }
            }
        }
        copy.moveHistory.addAll(moveHistory);
        copy.currentTurn = this.currentTurn;
        return copy;
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

}
