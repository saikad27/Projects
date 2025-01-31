package modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bishop extends Pieces{
    private ImageView imageView;
	private static final String BLACK_BISHOP = "assets/images/b_bishop.png";
	private static final String WHITE_BISHOP = "assets/images/w_bishop.png";
	String color;	//black or white
	String name = "Bishop";
	
	public Bishop(String color){
		this.color = color;
		if(this.color=="black"){
			imageView = new ImageView(new Image(BLACK_BISHOP));
		}
		else{
			imageView = new ImageView(new Image(WHITE_BISHOP));
		}
	}
	void possibleMoves(Board[][] board,int p1,int p2){
		

		boolean northWest = true;
		boolean northEast = true;
		boolean southWest = true;
		boolean southEast = true;

		int nW1 = p1;
		int nW2 = p2;

		int nE1 = p1;
		int nE2	= p2;

		int sW1 = p1;
		int sW2 = p2;

		int sE1 = p1; 
		int sE2 = p2;
		
		//northWest
		while(northWest){
			if(nW1-1>=0 && nW2-1>=0){
				if(board[nW1-1][nW2-1].isBlank){
					
					board[nW1-1][nW2-1].isMarked = true;
					System.out.println("North-West");
					nW1--;
					nW2--;
					
				}
				else if(board[nW1-1][nW2-1].color!=color){
					
					board[nW1-1][nW2-1].markedKill = true;
					northWest = false;
				}
			 	else{
					
					northWest = false;
				}
			}
			else{
				System.out.println("Infinite");
				northWest = false;
			}
		}
		
		//northEast
		while(northEast){
			if(nE1-1>=0 && nE2+1<8){
				if(board[nE1-1][nE2+1].isBlank){
					
					board[nE1-1][nE2+1].isMarked = true;
					
					nE1--;
					nE2++;
				}
				else if(board[nE1-1][nE2+1].color!=color){
					
					board[nE1-1][nE2+1].markedKill = true;
					northEast = false;
				}
			 	else{
					
					northEast = false;
				}	
			}
			else{
				System.out.println("Infinite");
				northEast = false;
			}
		}

		//southWest
		while(southWest){
			if(sW1+1<8 && sW2-1>=0){
				if(board[sW1+1][sW2-1].isBlank){
					
					board[sW1+1][sW2-1].isMarked = true;
				
					sW1++;
					sW2--;
				}
				else if(board[sW1+1][sW2-1].color!=color){
					
					board[sW1+1][sW2-1].markedKill = true;
					southWest = false;
				}
				else{
					southWest = false;
				}
			}
			else{
				System.out.println("Infinite");
				southWest = false;
			}
		}

		//southEast
		while(southEast){
			System.out.println("South-East");
			if(sE1+1<8 && sE2+1<8){
				System.out.println(sE1+" "+sE2);
				if(board[sE1+1][sE2+1].isBlank){
					
					board[sE1+1][sE2+1].isMarked = true;
					
					sE1++;
					sE2++;
				}
				else if(board[sE1+1][sE2+1].color!=color){
					
					board[sE1+1][sE2+1].markedKill = true;
					southEast = false;
				}
				else{
					southEast = false;
				}	
			}
			else{
				
				southEast = false;
			}
		}	
    }
    public ImageView getImageView(){
		imageView.setScaleX(0.5);
		imageView.setScaleY(0.5);
		imageView.setFitWidth(95);
		imageView.setFitHeight(95);
        return imageView;
    }
}
