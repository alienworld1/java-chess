package com.chess;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends StackPane {
    private final int row;
    private final int column;
    private final Rectangle background;
    private PieceView pieceView;
    
    public Square(int row, int column, Color color) {
        this.row = row;
        this.column = column;
        
        background = new Rectangle(80, 80, color);
        getChildren().add(background);
        
        setOnMouseEntered(e -> {
            if (hasPiece()) {
                setHighlighted(true);
            }
        });
        
        setOnMouseExited(e -> setHighlighted(false));
    }
    
    public void setPiece(PieceView pieceView) {
        this.pieceView = pieceView;
        getChildren().add(pieceView);
    }
    
    public PieceView removePiece() {
        if (pieceView != null) {
            getChildren().remove(pieceView);
            PieceView removed = pieceView;
            pieceView = null;
            return removed;
        }
        return null;
    }
    
    public boolean hasPiece() {
        return pieceView != null;
    }

    public PieceView getPiece() {
        return pieceView;
    }
    
    public void setHighlighted(boolean highlighted) {
        background.setStroke(highlighted ? Color.YELLOW : Color.TRANSPARENT);
        background.setStrokeWidth(highlighted ? 2 : 0);
    }
    
    public int getRow() { return row; }
    public int getColumn() { return column; }
}