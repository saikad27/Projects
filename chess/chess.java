import java.util.*;
class chess{
    chess(Pieces[][] board){
			
			//Piece count such as R1,R2,B1,B2
            int count;
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(j<4){
                        count = 1;
                    }
                    else{
                        count = 2;
                    }
                    //Inserting Pawns
                    if(i==1 ||i==6){
                        board[i][j] = new Pawn();
                        if(i==1){
                            //board[i][j] = new Pawn();
                            board[i][j].name = "[  0P"+(j+1)+"  ]";
                            board[i][j].color = "black";
                        }
                        if(i==6){
                            //board[i][j] = new Pawn();
                            board[i][j].name = "[  1P"+(j+1)+"  ]";
                            board[i][j].color = "white";
                        }
                    }
    
                    //Inserting Rooks
                    else if((i==0&&(j==0||j==7))||(i==7&&(j==0||j==7))){
                        board[i][j] = new Rook();
                        if(i==0){
                            board[i][j].name ="[  0R"+ count +"  ]"; 
                            board[i][j].color = "black";
                        }
                        else if(i==7){
                            board[i][j].name ="[  1R"+ count +"  ]";
                            board[i][j].color = "white";
                        }
                        
                    }
                    
                    //Inserting Bishops
                    else if((i==0&&(j==2||j==5))||(i==7&&(j==2||j==5))){
                        board[i][j] = new Bishop();
                                            if(i==0){
                                                    
                                                    board[i][j].name ="[  0B"+ count +"  ]";
                                                    board[i][j].color = "black";
                                            }
                                            else if(i==7){
                                                    board[i][j].name ="[  1B"+ count +"  ]";
                                                    board[i][j].color = "white";
                                            }
                    }
                    //Inserting Knight
                                    else if((i==0&&(j==1||j==6))||(i==7&&(j==1||j==6))){
                                            board[i][j] = new Knight();
                                            if(i==0){
                                                    board[i][j].name ="[  0N"+ count +"  ]";
                                                    board[i][j].color = "black";
                                            }
                                            else if(i==7){
                                                    board[i][j].name ="[  1N"+ count +"  ]";
                                                    board[i][j].color = "white";
                                            }
                                    }
                    //Inserting King
                                    else if((i==0&&j==4)||(i==7&&j==4)){
                                            board[i][j] = new King();
                                            if(i==0){
                                                    board[i][j].name ="[  0K   ]";
                                                    board[i][j].color = "black";
                                            }
                                            else if(i==7){
                                                    board[i][j].name ="[  1K   ]";
                                                    board[i][j].color = "white";
                                            }
                                    }
                    //Inserting Queen
                                    else if((i==0&&j==3)||(i==7&&j==3)){
                                            board[i][j] = new Queen();
                                            if(i==0){
                                                    board[i][j].name ="[  0Q   ]";
                                                    board[i][j].color = "black";
                                            }
                                            else if(i==7){
                                                    board[i][j].name ="[  1Q   ]";
                                                    board[i][j].color = "white";
                                            }
                                    }
                    //Inserting Blank spaces
                    else{
                        board[i][j] = new Blank();
                        board[i][j].name = "[  ---  ]";
                    }
                }
    
            }
    
        
    }
	void display(Pieces arr[][]){
		System.out.println();	
		for(Pieces[] row : arr){
			for(Pieces element : row){
				System.out.print(element.name+" ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	
	public static void main(String[] args){

		Pieces[][] board = new Pieces[8][8];
		chess chessObj = new chess(board);		//Assembles the board
		Pieces obj = new Pieces();
		while(true){		
			String str;
			chessObj.display(board);
			System.out.print("Enter a piece : ");
			Scanner sc = new Scanner(System.in);
			str = sc.next();
			obj.fetch(str,board);
			board[obj.p1][obj.p2].possibleMoves(board,obj.p1,obj.p2);
			System.out.println(obj.p1+","+obj.p2);				
			chessObj.display(board);
			board[obj.p1][obj.p2].move(board,obj.p1,obj.p2);
			obj.clean(board);
			chessObj.display(board);
				
		}
	}
}
class Pieces{
	Scanner sc = new Scanner(System.in);
	int p1,p2;
	String hash = "[   #   ]";
	String blank = "[  ---  ]";
	Pieces temp;
	boolean firstMove = true;
	private int movNum = 0;
	String target;
	String color;	//black=0 & white=1
	String name;
	void move(Pieces[][] board,int p1,int p2){
		System.out.print("Enter your move(Ex: #1#,#2#,#3#,#4#) : ");
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
				if(board[i][j].name.contains("#")){
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
class Rook extends Pieces{
	void possibleMoves(Pieces[][] board,int p1,int p2){
		int east,west,north,south,i;
		east = p2+1;
		west = p2-1;
		north = p1-1;
		south = p1+1;
		i = 1;
		int pnum = 1;
		System.out.println("Inside Rook possible moves");
		while(i<8){
			//east
			if(east<8 && board[p1][east].name == blank){
				board[p1][east].name = "[  #"+pnum++ +"#  ]";
				east++;
			}
			//west
			if(west>=0 && board[p1][west].name == blank){
				board[p1][west].name = "[  #"+pnum++ +"#  ]";
				west--;
			}
			//north
			if(north>=0 && board[north][p2].name == blank){
				board[north][p2].name = "[  #"+pnum++ +"#  ]";
				north--;
			}
			//south
			if(south<8 && board[south][p2].name == blank){
				board[south][p2].name = "[  #"+pnum++ +"#  ]";
				south++;
			}
			i++;
		}
    }

}
class Knight extends Pieces{
	void possibleMoves(Pieces[][] board,int p1,int p2){
		int a= p1, b = p2;
		int pnum = 1;
		if((a-2)>=0){
			if(b-1>=0 && board[a-2][b-1].name==blank){
				board[a-2][b-1].name = "[  #"+pnum++ +"#  ]";
			}
			if(b+1<8 && board[a-2][b+1].name==blank){
				board[a-2][b+1].name = "[  #"+pnum++ +"#  ]";
			}
			
		}
		if((b-2)>=0){
			if(a-1>=0 && board[a-1][b-2].name==blank){
				board[a-1][b-2].name = "[  #"+pnum++ +"#  ]";
			}
			if(a+1<8 && board[a+1][b-2].name==blank){
				board[a+1][b-2].name = "[  #"+pnum++ +"#  ]";
			}
			
		}
		if((b+2)<8){
			if(a-1>=0 && board[a-1][b+2].name==blank){
				board[a-1][b+2].name = "[  #"+pnum++ +"#  ]";
			}
			if(a+1<8 && board[a+1][b+2].name==blank){
				board[a+1][b+2].name = "[  #"+pnum++ +"#  ]";
			}
			
		}
		if((a+2)<8){
			if(b-1>=0 && board[a+2][b-1].name==blank){
				board[a+2][b-1].name = "[  #"+pnum++ +"#  ]";
			}
			if(b+1<8 && board[a+2][b+1].name==blank){
				board[a+2][b+1].name = "[  #"+pnum++ +"#  ]";
			}
			
		}	
    }
}
class Bishop extends Pieces{
	void possibleMoves(Pieces[][] board,int p1,int p2){
		int pnum = 1;

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
				if(board[nW1-1][nW2-1].name == blank){
					board[nW1-1][nW2-1].name = "[  #"+pnum++ +"#  ]";
					System.out.println("North-West");
					nW1--;
					nW2--;
				}
				else{
					northWest = false;
				}	
			}
			else{
				northWest = false;
			}
		}
		
		//northEast
		while(northEast){
			if(nE1-1>=0 && nE2+1<8){
				if(board[nE1-1][nE2+1].name == blank){
					board[nE1-1][nE2+1].name = "[  #"+pnum++ +"#  ]";
					System.out.println("North-East");
					nE1--;
					nE2++;
				}
				else{
					northEast = false;
				}	
			}
			else{
				northEast = false;
			}
		}

		//southWest
		while(southWest){
			if(sW1+1<8 && sW2-1>=0){
				if(board[sW1+1][sW2-1].name == blank){
					board[sW1+1][sW2-1].name = "[  #"+pnum++ +"#  ]";
					System.out.println("South-West");
					sW1++;
					sW2--;
				}
				else{
					southWest = false;
				}	
			}
			else{
				southWest = false;
			}
		}

		//southEast
		while(southEast){
			if(sE1+1<8 && sE2+1<8){
				if(board[sE1+1][sE2+1].name == blank){
					board[sE1+1][sE2+1].name = "[  #"+pnum++ +"#  ]";
					System.out.println("South-East");
					sE1++;
					sE2++;
				}
				else{
					southEast = false;
				}	
			}
			else{
				southEast = false;
			}
		}


		incrementMove();	
    }
}
class Queen extends Pieces{
	
	void possibleMoves(Pieces[][] board,int p1,int p2){
		
		//NorthWest
		{
			int pnum = 1;
			int a = p1-1;
			int b = p2-1;
			while(a>=0 && b>=0){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #1"+pnum++ +"  ]";
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
			int pnum = 1;
			int a = p1-1;
			int b = p2;
			while(a>=0){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #2"+pnum++ +"  ]";
				}
				else{
					break;
				}
				a--;
			}
		}

		//NorthEast
		{
			int pnum = 1;
			int a = p1-1;
			int b = p2+1;
			while(a>=0 && b<8){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #3"+pnum++ +"  ]";
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
			int pnum = 1;
			int a = p1;
			int b = p2-1;
			while(b>=0){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #4"+pnum++ +"  ]";
				}
				else{
					break;
				}
				b--;
			}
		}

		//East
		{	
			int pnum = 1;
			int a = p1;
			int b = p2+1;
			while(b<8){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #5"+pnum++ +"  ]";
				}
				else{
					break;
				}
				b++;
			}
		}

		//SouthWest
		{
			int pnum = 1;
			int a = p1+1;
			int b = p2-1;
			while(a<8 && b>=0){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #6"+pnum++ +"  ]";
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
			int pnum = 1;
			int a = p1+1;
			int b = p2;
			while(a<8){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #7"+pnum++ +"  ]";
				}
				else{
					break;
				}
				a++;
			}
		}
		//SouthEast
		{
			int pnum = 1;
			int a = p1+1;
			int b = p2+1;
			while(a<8 && b<8){
				if(board[a][b].name==blank){
					board[a][b].name = "[  #8"+pnum++ +"  ]";
				}
				else{
					break;
				}
				a++;
				b++;
			}
		}
		
    }
}
class King extends Pieces{
	
	void possibleMoves(Pieces[][] board,int p1,int p2){
		int pnum = 1;
		for(int i=p1-1;i<=p1+1;i++){
			if(i<8 && i>=0 )
			for(int j=p2-1;j<=p2+1;j++){
				if(j<8 && j>=0){
					if(board[i][j].name == blank){
						board[i][j].name = "[  #"+pnum++ +"#  ]";
					}
				}
			}
		}
    }
}
class Pawn extends Pieces{
	
	//Possible Moves
	void possibleMoves(Pieces[][] board,int p1,int p2){
		int a=p1,b=p2;
		if(board[p1][p2].firstMove){
				
			for(int i =1; i<=2; i++){
				if(board[p1][p2].color == "black"){
					// next line increases 'a' to select next box in black pawn peices	
					a++;    
					if(a>=0 && a<8){
						if(board[a][b].name == blank){
									
							board[a][b].name = hash;
						} 
					}
				}
				else if(board[p1][p2].color =="white"){
                    // next line increases 'a' to select next box in white pawn peices
					a--;
					if(a>=0 && a<8){
								
						if(board[a][b].name == blank){
								
							board[a][b].name = hash;
						} 
					}
				}
			}
				
		}
        else{
            if(board[p1][p2].color == "black"){
                // next line increases 'a' to select next box in black pawn peices	
                a++;    
                if(a>=0 && a<8){
                    if(board[a][b].name == blank){
                                
                        board[a][b].name = hash;
                    } 
                }
            }
            else if(board[p1][p2].color =="white"){
                    // next line increases 'a' to select next box in white pawn peices
					a--;
					if(a>=0 && a<8){
								
						if(board[a][b].name == blank){
								
							board[a][b].name = hash;
						} 
					}
				}
        }	
	}

	//Move
	void move(Pieces[][] board,int p1,int p2){
		System.out.print("Enter your move : ");
		int unit = sc.nextInt();

		if(board[p1][p2].color == "black" && (p1+unit)<8){
			if(board[p1+unit][p2].name == hash){
				temp = board[p1+unit][p2];
				board[p1+unit][p2] = board[p1][p2];
				board[p1][p2] = temp;
			}
		}
		else if(board[p1][p2].color == "white" && (p1-unit)>=0){
			if(board[p1-unit][p2].name == hash){
				temp = board[p1-unit][p2];
				board[p1-unit][p2] = board[p1][p2];
				board[p1][p2] = temp;
			}
		}
        //Below line causes for next possible move of this pawn to be 1 
        firstMove = false;
		incrementMove();
	}
}

class Blank extends Pieces{
	
}
