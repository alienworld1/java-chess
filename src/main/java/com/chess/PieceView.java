package com.chess;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceView extends ImageView {
    private final Piece piece;
    
    public PieceView(Piece piece) {
        this.piece = piece;
        setImage(getPieceImage());
        setFitWidth(60);
        setFitHeight(60);
    }
    
    private Image getPieceImage() {
        String color = piece.getColor() == PieceColor.WHITE ? "white" : "black";
        String type = piece.getType().toString().toLowerCase();
        String imagePath = "/pieces/" + color + "_" + type + ".png";
        return new Image(getClass().getResourceAsStream(imagePath));
    }
    
    public Piece getPiece() {
        return piece;
    }

}