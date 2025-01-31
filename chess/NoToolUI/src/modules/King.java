package modules;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class King extends Pieces{
    private ImageView imageView;
	private static final String BLACK_KING = "assets/images/b_king.png";
	private static final String WHITE_KING = "assets/images/w_king.png";
	String color;	//black or white
	//String name = "King";
	
	public King(String color){
		this.color = color;
		if(this.color=="black"){
			imageView = new ImageView(new Image(BLACK_KING));
		}
		else{
			imageView = new ImageView(new Image(WHITE_KING));
		}
		
	}

	void possibleMoves(Board[][] board,int p1,int p2){
		
		for(int i=p1-1;i<=p1+1;i++){
			if(i<8 && i>=0 )
			for(int j=p2-1;j<=p2+1;j++){
				if(j<8 && j>=0){
					if(board[i][j].isBlank){
						
						board[i][j].isMarked = true;
					}
					else if(board[i][j].color!=null && board[i][j].color!=color){
						board[i][j].markedKill = true;
					}
				}
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
