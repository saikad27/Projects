package com.modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Queen extends Pieces{
    private ImageView imageView;
	private static final String BLACK = "images/b_queen.png";
	private static final String WHITE = "images/w_queen.png";
	String color;	//black or white
	String name = "Queen";
	
	public Queen(String color){
		this.color = color;
		if(this.color=="black"){
			imageView = new ImageView(new Image(BLACK));
		}
		else{
			imageView = new ImageView(new Image(WHITE));
		}
		
	}
	void possibleMoves(Board[][] board,int p1,int p2){
		
		//NorthWest
		{
			
			int a = p1-1;
			int b = p2-1;
			while(a>=0 && b>=0){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}
				else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a--;
				b--;
			}
		}

		//North
		{	
			
			int a = p1-1;
			int b = p2;
			while(a>=0){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}
				else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a--;
			}
		}

		//NorthEast
		{
			
			int a = p1-1;
			int b = p2+1;
			while(a>=0 && b<8){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}
				else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a--;
				b++;
			}
		}
		//West
		{
			
			int a = p1;
			int b = p2-1;
			while(b>=0){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				b--;
			}
		}

		//East
		{	
			
			int a = p1;
			int b = p2+1;
			while(b<8){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				b++;
			}
		}

		//SouthWest
		{
			
			int a = p1+1;
			int b = p2-1;
			while(a<8 && b>=0){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a++;
				b--;
			}
		}

		//South
		{
			
			int a = p1+1;
			int b = p2;
			while(a<8){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a++;
			}
		}
		//SouthEast
		{
			
			int a = p1+1;
			int b = p2+1;
			while(a<8 && b<8){
				if(board[a][b].isBlank){
				
					board[a][b].isMarked = true;
				}else if(board[a][b].color!=color){
					board[a][b].markedKill = true;
					break;
				}
				else{
					break;
				}
				a++;
				b++;
			}
		}
		
    }
    public ImageView getImageView(){
		imageView.setScaleX(0.7);
		imageView.setScaleY(0.7);
		imageView.setFitWidth(95);
		imageView.setFitHeight(95);
        return imageView;
    }
}
