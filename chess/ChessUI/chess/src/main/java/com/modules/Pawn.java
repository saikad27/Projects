package com.modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pawn extends Pieces {
	private ImageView imageView;
	private static final String BLACK = "images/b_pawn.png";
	private static final String WHITE = "images/w_pawn.png";
	String color; // black or white
	String name = "Pawn";

	public Pawn(String color) {
		this.color = color;
		if (color == "black") {
			imageView = new ImageView(new Image(BLACK));
		} else {
			imageView = new ImageView(new Image(WHITE));
		}

	}

	void possibleMoves(Board[][] board, int p1, int p2) {
		int a = p1;
		int b = p2;
		System.out.println("Inside possible moves");
		if (board[p1][p2].firstMove) {
			for (int i = 0; i < 2; i++) {
				a = inDe(a);
				if (a >= 0 && a < 8) {
					if (board[a][b].isBlank) {
						board[a][b].isMarked = true;
					}
					else{
						break;
					}
				}
			}
		} else {
			a = inDe(a);
			if (a >= 0 && a < 8) {
				if (board[a][b].isBlank) {
					
					board[a][b].isMarked = true;
				}
			}
		}
		possibleKill(board,p1,p2);
	}

	public void possibleKill(Board[][] board, int p1, int p2) {
		int a = p1;
		int b = p2;
		System.out.println("Inside possibleKill method");
		a = inDe(a);
			//System.out.println(board[a][b-1].color);
			if(b-1>=0 && board[a][b-1].color!=null && color!=board[a][b-1].color){
				board[a][b-1].markedKill=true;
			}
			if(b+1<8 && board[a][b+1].color!=null && color!=board[a][b+1].color){
				board[a][b+1].markedKill=true;
			}
	}

	public ImageView getImageView() {
		imageView.setScaleX(0.7);
		imageView.setScaleY(0.7);
		imageView.setFitWidth(70);
		imageView.setFitHeight(70);
		return imageView;
	}

	int inDe(int a) {
		if (color == "black") {
			a++;
		} else if (color == "white") {
			a--;
		}
		return a;
	}
}
