package com.chess;

public class ChessGame {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private GameState gameState;
    private MoveValidator moveValidator;
    
    public ChessGame(Player whitePlayer, Player blackPlayer) {
        this.board = new Board();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.moveValidator = new MoveValidator();
        this.gameState = GameState.ONGOING;
    }
    
    public void play() {
        Player currentPlayer = whitePlayer;
        
        while (gameState == GameState.ONGOING) {
            Move move = currentPlayer.getNextMove(board);
            
            if (moveValidator.isValidMove(move, board)) {
                board.movePiece(move);
                updateGameState();
                currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
            }
        }
    }
    
    private void updateGameState() {
        System.err.println("should do this stuff");
    }
}
