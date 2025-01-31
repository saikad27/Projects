package com.modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Knight extends Pieces{
    private ImageView imageView;
	private static final String BLACK = "images/b_knight.png";
	private static final String WHITE = "images/w_knight.png";
	String color;	//black or white
	String name = "Knight";
	
	public Knight(String color){
		this.color = color;
		if(this.color=="black"){
			imageView = new ImageView(new Image(BLACK));
		}
		else{
			imageView = new ImageView(new Image(WHITE));
		}
		
	}
	/*Method that gives possible moves by marking the possible moves 
	locations on the board to be true and making the mark visible*/

	void possibleMoves(Board[][] board,int p1,int p2){
		int a = p1, b = p2;
		System.out.println("inside Kinght Possible moves");
		if((a-2)>=0){
			if(b-1>=0 && board[a-2][b-1].isBlank){
			
				board[a-2][b-1].isMarked = true;
			}
			//Condition for possible kill
			else if(b-1>=0 && board[a-2][b-1].color!=color){
				board[a-2][b-1].markedKill = true; 
			}

			if(b+1<8 && board[a-2][b+1].isBlank){
			
				board[a-2][b+1].isMarked = true;
			}
			//Condition for possible kill
			else if(b+1<8 && board[a-2][b+1].color!=color){
				board[a-2][b+1].markedKill = true;
			}
			
		}
		if((b-2)>=0){
			if(a-1>=0 && board[a-1][b-2].isBlank){
				
			
				board[a-1][b-2].isMarked = true;
			}
			//Condition for possible kill
			else if(a-1>=0 && board[a-1][b-2].color!=color){
				board[a-1][b-2].markedKill = true;
			}

			if(a+1<8 && board[a+1][b-2].isBlank){
				
			
				board[a+1][b-2].isMarked = true;

			}
			//Condition for possible kill
			else if(a+1<8 && board[a+1][b-2].color!=color){
				board[a+1][b-2].markedKill = true;
			}
			
		}
		if((b+2)<8){
			if(a-1>=0 && board[a-1][b+2].isBlank){
				
			
				board[a-1][b+2].isMarked = true;
			}
			//Condition for possible kill
			else if(a-1>=0 && board[a-1][b+2].color!=color){
				board[a-1][b+2].markedKill = true;
			}
			if(a+1<8 && board[a+1][b+2].isBlank){
				
			
				board[a+1][b+2].isMarked = true;
			}
			//Condition for possible kill
			else if(a+1<8 && board[a+1][b+2].color!=color){
				board[a+1][b+2].markedKill = true;
			}
			
		}
		if((a+2)<8){
			if(b-1>=0 && board[a+2][b-1].isBlank){
				
			
				board[a+2][b-1].isMarked = true;
			}
			else if(b-1>=0 && board[a+2][b-1].color != color){
				board[a+2][b-1].markedKill = true;
			}
			if(b+1<8 && board[a+2][b+1].isBlank){
				
			
				board[a+2][b+1].isMarked = true;
			}
			else if(b+1<8 && board[a+2][b+1].color!=color){
				board[a+2][b+1].markedKill = true;
			}
			
		}	
    }
    public ImageView getImageView(){
		imageView.setScaleX(0.7);
		imageView.setScaleY(0.7);
		imageView.setFitWidth(85);
		imageView.setFitHeight(85);
        return imageView;
    }
}
