package com.modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rook extends Pieces{
    private ImageView imageView;
	private static final String BLACK = "images/b_rook.png";
	private static final String WHITE = "images/w_rook.png";
	String color;	//black or white
	String name = "Rook";
	
	
	public Rook(String color){
		this.color = color;
		if(this.color=="black"){
			imageView = new ImageView(new Image(BLACK));
		}
		else{
			imageView = new ImageView(new Image(WHITE));
			
		}
		
	}
	void possibleMoves(Board[][] board,int p1,int p2){
		int east,west,north,south,i;
		east = p2+1;
		west = p2-1;
		north = p1-1;
		south = p1+1;
		i = 1;
		
		System.out.println("Inside Rook possible moves");
		while(i<8){
			//east
			if(east<8 && board[p1][east].isBlank){
			//	board[p1][east].getCircle().setOpacity(1);
				board[p1][east].isMarked = true;
				east++;
			}else if(east<8 && board[p1][east].color!=color){
				board[p1][east].markedKill = true;
			}
			//west
			if(west>=0 && board[p1][west].isBlank){
			//	board[p1][west].getCircle().setOpacity(1);
				board[p1][west].isMarked = true;
				west--;
			}else if(west>=0 && board[p1][west].color!=color){
				board[p1][west].markedKill = true;
			}
			//north
			if(north>=0 && board[north][p2].isBlank){
			//	board[north][p2].getCircle().setOpacity(1);
				board[north][p2].isMarked = true;
				north--;
			}else if(north>=0 && board[north][p2].color!=color){
				board[north][p2].markedKill = true;
			}
			//south
			if(south<8 && board[south][p2].isBlank){
			//	board[south][p2].getCircle().setOpacity(1);
				board[south][p2].isMarked = true;
				south++;
			}else if(south<8 && board[south][p2].color!=color){
				board[south][p2].markedKill = true;
			}
			i++;
		}
    }
    public ImageView getImageView(){
		imageView.setScaleX(0.7);
		imageView.setScaleY(0.7);
		imageView.setFitWidth(75);
		imageView.setFitHeight(75);
        return imageView;
    }
}
