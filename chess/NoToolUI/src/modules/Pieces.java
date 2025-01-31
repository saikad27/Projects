package modules;
import java.util.Scanner;


public class Pieces extends Board{

	String name;
	String color;
	String blank = "[  ---  ]";
    Scanner sc = new Scanner(System.in);
	int p1,p2;
	Pieces temp;
	boolean firstMove = true;
	private int movNum = 0;
	String target;
	String star;

	void move(Pieces[][] board,int p1,int p2){
		System.out.print("Enter your move(Ex: *1*,*2*,*3*,*4*) : ");
		String unit = sc.next();

		for(int i=0; i<board.length;i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j].name.contains(unit)){
					temp = board[i][j];
					board[i][j] = board[p1][p2];
					board[p1][p2] = temp;
				}
			}
		}
	}
	void incrementMove(){
		movNum++;
	}
	void clean(Pieces[][] board){
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				if(board[i][j].name.contains("*")){
					board[i][j].name = blank;
				}
			}
		}
	}
	
	void possibleMoves(Pieces[][] board,int p1,int p2){
	
	}
	void fetch(String str,Pieces[][] board){
		String piece;
		target = str;
		boolean pfound = false;
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				piece = board[i][j].name;
				if(piece.contains(target)){
					p1 = i;
					p2 = j;
					System.out.println("i="+p1+" && j="+p2);
					pfound = true;
					break;
        	    }
			}
			if(pfound){
				break;
			}
		}
		
	}
	void kill(){
	
	}
    
}
