package com.chess;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ChessBoard extends GridPane {
    private final Square[][] squares = new Square[8][8];
    private final Board gameBoard;
    private Square selectedSquare = null;
    
    public ChessBoard() {
        gameBoard = new Board();
        initializeBoard();
    }
    
    private void initializeBoard() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Square square = new Square(row, col, (row + col) % 2 == 0 ? Color.WHITE : Color.LIGHTGRAY);
                squares[row][col] = square;
                add(square, col, row);
                
                // Add piece if exists
                Piece piece = gameBoard.getPiece(new Position(row, col));
                if (piece != null) {
                    PieceView pieceView = new PieceView(piece);
                    square.setPiece(pieceView);
                }
                
                setupSquareHandlers(square);
            }
        }
    }
    
    private void setupSquareHandlers(Square square) {
        square.setOnDragDetected(event -> {
            if (square.hasPiece()) {
                selectedSquare = square;
                square.startFullDrag();
            }
            event.consume();
        });
        
        square.setOnMouseDragEntered(event -> {
            if (selectedSquare != null && selectedSquare != square) {
                Position from = new Position(selectedSquare.getRow(), selectedSquare.getColumn());
                Position to = new Position(square.getRow(), square.getColumn());
                Piece piece = gameBoard.getPiece(from);
    
                System.out.println(from.toString());
                System.out.println(to.toString());
                System.out.println(piece.isValidMove(from, to, gameBoard));


                if (piece != null && piece.isValidMove(from, to, gameBoard)) {
                    square.setHighlighted(true);
                }
            }
            event.consume();
        });
        
        square.setOnMouseDragExited(event -> {
            square.setHighlighted(false);
            event.consume();
        });
        
        square.setOnMouseDragReleased(event -> {
            if (selectedSquare != null) {
                Position from = new Position(selectedSquare.getRow(), selectedSquare.getColumn());
                Position to = new Position(square.getRow(), square.getColumn());
                Piece piece = gameBoard.getPiece(from);
                
                if (piece != null && piece.isValidMove(from, to, gameBoard)) {
                    // Move the piece
                    Move move = new Move(from, to, piece, gameBoard.getPiece(to));
                    gameBoard.movePiece(move);
                    
                    // Update GUI
                    PieceView pieceView = selectedSquare.removePiece();
                    square.setPiece(pieceView);
                }
                
                square.setHighlighted(false);
                selectedSquare = null;
            }
            event.consume();
        });
    }
}