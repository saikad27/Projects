package com.modules;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;


public class Board {
   char id;
   String name;
   String color;
   int pieceCount = 0;
   boolean trailBox = false;
   boolean invalid = false;
   boolean firstMove = true;
   double opVal = 0.0;
   boolean isMarked = false;
   Circle circle = new Circle();
  
   boolean isBlank;
   boolean markedKill = false;
   ImageView imageView =new ImageView(new Image("images/b_king.png"));
   //PassNPlay passNPlay = new PassNPlay();
   public ImageView getImageView(){
      return null;
   }
   public Circle getCircle(){
     return circle;
   }

   Rectangle rectangle = new Rectangle(95,95);
   public Rectangle getRectangle(){
      Rectangle rectangle = new Rectangle(95,95);
      rectangle.setFill(Color.TRANSPARENT);
      return rectangle;
   }
  /*  void markKill(Board[][] board,int a,int b){
    
      System.out.println("Inside markKill"+a+b);
      
      board[a][b].markedKill = true;
      play.highlight();
        
   }*/
   
   void possibleMoves(Board[][] board,int p1,int p2){

   }
   String getColor(){
		return color;
	}
    
}

