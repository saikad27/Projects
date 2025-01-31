package com.modules;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PlayWithComp  {
    int bKing1 = 0, bKing2 = 0, wKing1 = 0, wKing2 = 0;
    static int c1, c2, t1, t2;
    boolean check = false;
    int pre_p1, pre_p2;
    int movNo = 0;
    boolean action = false;
    boolean trail_exists = false;
    int p1, p2;
    Board[][] board = new Board[8][8];
    Rectangle[][] sqr = new Rectangle[8][8];
    GridPane gridPane = new GridPane();
    
    Stage primaryStage;
    
    Scene  playwithComputerScene;

    public void setUserPage1(Stage primaryStage ){

        this.primaryStage = primaryStage;
    }
    public void setUserPage1Scene(Scene scene ){

        this.playwithComputerScene = scene;
    }
    
    public HBox createPlayWithCompScene (Runnable backHandler) {
        StockfishEngine stockFish = new StockfishEngine();
       
        primaryStage.setHeight(900);
        primaryStage.setWidth(1440);
        
        assemble();

        fillGrid();

        gridPane.setOnMouseClicked(event -> {

            System.out.println(movNo + " click");
            Node clickedNode = event.getPickResult().getIntersectedNode();
            while (clickedNode != null && clickedNode != gridPane && GridPane.getRowIndex(clickedNode) == null) {
                clickedNode = clickedNode.getParent();
            }
            if (GridPane.getRowIndex(clickedNode) != null) {
                Integer row = GridPane.getRowIndex(clickedNode);
                Integer column = GridPane.getColumnIndex(clickedNode);
                p1 = row;
                p2 = column;

                if (board[p1][p2].markedKill && action) {

                    kill(p1, p2);
                    stockFish.generateFEN(board);
                    move();

                    display();
                    clean();
                    
                    movNo+=2;

                    action = false;
                } else if (board[p1][p2].isMarked && action) {
                    int a = p1, b = p2;
                    if (board[p1][p2].isBlank) {

                        move(a, b);
                        stockFish.generateFEN(board);
                        
                        move();
                        movNo+=2;
                        System.out.println(movNo);
                        action = false;
                    }
                }

                else if ((movNo % 2 == 0 && board[p1][p2].color == "white")
                        || (movNo % 2 == 1 && board[p1][p2].color == "black")) {
                    System.out.println(board[p1][p2].color + " " + movNo);
                    System.out.println(board[p1][p2].color);
                    clean();
                    System.out.println(board[p1][p2]);
                    board[p1][p2].possibleMoves(board, p1, p2);
                    
                    markMoves();
                    highlight();
                    pre_p1 = p1;
                    pre_p2 = p2;
                    action = true;
                } else {
                    clean();
                }
            }
        });
        Button backButton = new Button("Home");
        backButton.setStyle("-fx-background-color:Gold;-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius:5px");
        
        backButton.setFont(new Font(15));
        backButton.setPrefHeight(35);
        backButton.setPrefWidth(200);
        backButton.setScaleX(1.5);
        backButton.setScaleY(1.5);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                backHandler.run();
            }
        });
        Button exitButton = new Button("Quit Game");
        exitButton.setFont(new Font(15));
        exitButton.setPrefHeight(35);
        exitButton.setPrefWidth(200);
        exitButton.setStyle("-fx-background-color:Gold;-fx-border-color: black; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-background-radius:5px");
        exitButton.setScaleX(1.5);
        exitButton.setScaleY(1.5);
        exitButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }

        });
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(backButton,exitButton);
        vbox.setAlignment(Pos.CENTER);
        HBox vb = new HBox(80);
        gridPane.setAlignment(Pos.CENTER);
        vb.getChildren().add(gridPane);
        vb.getChildren().add(vbox);
        
        vb.setStyle("-fx-background-image:url('https://images.chesscomfiles.com/uploads/v1/images_users/tiny_mce/PedroPinhata/phpH1xFPj.png');-fx-background-size : cover");
        return vb;
       

        // Music:
        /*
         * try{
         * File midiFile1 = new File("src/assets/sound/build_level.mid");
         * Sequence sequence = MidiSystem.getSequence(midiFile1);
         * Sequencer sequencer = MidiSystem.getSequencer();
         * sequencer.open();
         * sequencer.setSequence(sequence);
         * 
         * sequencer.start();
         * 
         * 
         * }catch(Exception e){
         * e.printStackTrace();
         * }
         * try {
         * // Replace with the path to your MIDI file
         * File midiFile2 = new File("src/assets/sound/theme.mid");
         * Sequence sequence = MidiSystem.getSequence(midiFile2);
         * Sequencer sequencer = MidiSystem.getSequencer();
         * 
         * sequencer.open();
         * sequencer.setSequence(sequence);
         * sequencer.setLoopCount(1);
         * Thread.sleep(4000);
         * sequencer.start();
         * } catch (Exception e) {
         * e.printStackTrace();
         * }
         */

    }

    void display() {
        gridPane.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].isMarked = false;
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(sqr[i][j]);
                if (board[i][j].getImageView() != null) {
                    stackPane.getChildren().add(board[i][j].getImageView());
                }
                stackPane.getChildren().add(board[i][j].getCircle());
                gridPane.add(stackPane, j, i);
            }
        }
    }

    void fillGrid() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sqr[i][j] = new Rectangle(95, 95);
                sqr[i][j].setFill(((i + j) % 2 == 0) ? (Color.IVORY) : (Color.OLIVEDRAB));
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(sqr[i][j]);

                if (board[i][j].getImageView() != null) {

                    stackPane.getChildren().add(board[i][j].getImageView());

                }
                stackPane.getChildren().add(board[i][j].getCircle());

                gridPane.add(stackPane, j, i);
            }
        }

    }

    void kill(int a, int b) {
        board[a][b].getCircle().setOpacity(0);
        board[a][b].isMarked = false;
        Board temp = new Marked();
        board[a][b] = board[pre_p1][pre_p2];
        board[a][b].markedKill = true;
        board[pre_p1][pre_p2] = temp;
        board[pre_p1][pre_p2].isBlank = true;
    }

    public void clean() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].getCircle().setOpacity(0);
                board[i][j].invalid = false;
                board[i][j].trailBox = false;
                board[i][j].isMarked = false;

                if (board[i][j].markedKill) {
                    board[i][j].markedKill = false;
                    System.out.println("Inside clean");
                    sqr[i][j].setFill(((i + j) % 2 == 0) ? (Color.IVORY) : (Color.OLIVEDRAB));
                }
            }
        }
    }

    public void move(int a, int b) {
        
        board[pre_p1][pre_p2].firstMove = false;
        System.out.println("Swaping");
        board[a][b].getCircle().setOpacity(0);
        clean();

        Board temp = board[a][b];
        board[a][b] = board[pre_p1][pre_p2];
        board[pre_p1][pre_p2] = temp;

        display();

    }

    public void move() {
        // System.out.println(c1+" "+c2);
        board[c1][c2].firstMove = false;
        board[t1][t2].getCircle().setOpacity(0);
        clean();
        Board temp;
        
        if(board[t1][t2].isBlank){
            temp = board[t1][t2];
            board[t1][t2] = board[c1][c2];
            board[c1][c2] = temp;
            

        }
        else{
            kill(c1,c2,t1,t2);
        }
        

        
        display();
    }
    void kill(int a,int b,int c,int d){
        board[c][d].getCircle().setOpacity(0);
        board[c][d].isMarked = false;
        Board temp = new Marked();
        board[c][d] = board[a][b];
        board[c][d].markedKill = true;
        board[a][b] = temp;
        board[a][b].isBlank = true;
    }

    /*
     * Method responsible for highlighting possible kill pieces
     */
    public void highlight() {
        System.out.println("inside marked kill");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (board[i][j].markedKill) {
                    sqr[i][j].setFill(Color.SKYBLUE);
                }
            }
        }

    }

    void searchKing() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].color == "black" && board[i][j].name == "King") {
                    bKing1 = i;
                    bKing2 = j;

                    break;
                }
                if (board[i][j].color == "white" && board[i][j].name == "King") {
                    wKing1 = i;
                    wKing2 = j;

                    break;
                }
            }
        }
    }

    /*
     * Method responsible for marking possible moves
     */
    void markMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].isMarked) {
                    if (board[i][j].invalid) {
                        if (board[i][j].trailBox) {
                            board[i][j].getCircle().setOpacity(1.0);
                        }
                    } else {
                        board[i][j].getCircle().setOpacity(1.0);
                    }

                }
            }
        }
    }

    void possibleTrail(int c1, int c2) {
        System.out.println(c1 + " " + c2);
        int row, column;
        int c1King = 0;
        int c2King = 0;
        searchKing();
        System.out.println("Inside possible trail");
        if (board[c1][c2].color == "black") {

            c1King = wKing1;
            c2King = wKing2;
            System.out.println("White king : " + c1King + " " + c2King);
        }

        else if (board[c1][c2].color == "white") {

            c1King = bKing1;
            c2King = bKing2;
            System.out.println("Black king : " + c1King + " " + c2King);
        }

        // Elephant path
        if (c1 == c1King || c2 == c2King) {

        }

        // North-west
        else if (c1 > c1King && c2 > c2King) {
            System.out.println("Inside north-west");
            row = c1 - c1King;
            column = c2 - c2King;
            if (row == column) {
                System.out.println(row + " " + column);
                trail_exists = true;
            } else {
                trail_exists = false;
            }
            System.out.println("Hello");
            if (trail_exists) {
                System.out.println(c1 + " " + c2);
                int i = c1 - 1;
                int j = c2 - 1;
                while (i >= c1King) {
                    System.out.println("Checking " + i + " " + j);
                    if (board[i][j].color == board[c1][c2].color) {
                        trail_exists = false;
                        break;
                    } else if (board[i][j].isBlank) {
                        board[i][j].trailBox = true;
                    } else if (board[i][j] == board[c1King][c2King]) {
                        System.out.println("Marked Check");
                        check = true;
                    } else if (board[i][j].color != board[c1][c2].color) {
                        board[i][j].invalid = true;
                        System.out.println("Marked invalid");
                    }
                    
                    i--;
                    j--;
                }

            }
            if (!trail_exists) {
                
            }
        }

        // North-east
        else if (c1 > c1King && c2 < c2King) {
            System.out.println("Inside north-east");
            row = c1 - c1King;
            column = c2King - c2;
            System.out.println(row + " " + column);
            if (row == column) {
                System.out.println("Trail exists");
                trail_exists = true;
            } else {
                trail_exists = false;
            }

            if (trail_exists) {
                
                int i = c1 - 1;
                int j = c2 + 1;
                while (i <= c1King) {
                    if (board[i][j].color == board[c1][c2].color) {
                        trail_exists = false;
                        break;
                    } else if (board[i][j].isBlank) {
                        board[i][j].trailBox = true;
                    } else if (board[i][j].color != board[c1][c2].color) {
                        board[i][j].invalid = true;
                    }
                    
                    i--;
                    j++;
                }

            }
            if (!trail_exists) {
                
            }
        }

        // South-west
        else if (c1 < c1King && c2 > c2King) {
            row = c1King - c1;
            column = c2 - c2King;
            if (row == column) {
                trail_exists = true;
            } else {
                trail_exists = false;
            }

            if (trail_exists) {
                // int trailCount = 0;
                int i = c1 + 1;
                int j = c2 - 1;
                while (i <= c1King) {
                    if (board[i][j].color == board[c1][c2].color) {
                        trail_exists = false;
                        break;
                    } else if (board[i][j].isBlank) {
                        board[i][j].trailBox = true;
                    } else if (board[i][j].color != board[c1][c2].color) {
                        board[i][j].invalid = true;
                    }
                    // trailCount++;
                    i++;
                    j--;
                }

            }
            if (!trail_exists) {
                
            }

        }

        // South-east
        else if (c1 < c1King && c2 < c2King) {

        }
    }
    void checkTrail(){

    }
    void possibleCheck() {
        searchKing();
        System.out.println("Inside possible check");
        // if (movNo % 2 == 1) { // Black to move
        System.out.println("Inside black to move");
        board[p1][p2].possibleMoves(board, p1, p2);
        if (board[bKing1][bKing2].markedKill) {
            System.out.println("Inside King markedKill");
            board[bKing1][bKing2].markedKill = false;
            sqr[bKing1][bKing2].setFill(Color.RED);
            sqr[p1][p2].setFill(Color.RED);
            check = true;
        }
        display();
        // clean();

    }

    /*
     * void resolveCheck(){
     * if(p1>bKing1){
     * for(int a=bKing1+1;a<p1; ){
     * inDe();
     * }
     * }
     * else{
     * for(int a=bKing)
     * }
     * }
     */

    void assemble() {
        // Initialize and add pieces to the board
        
        for (int i = 0; i < 8; i++) {
            int pawnCount = 0;
            int bishopCount = 0;
            int knightCount = 0;
            int rookCount = 0;
            for (int j = 0; j < 8; j++) {

                // Inserting Pawns
                if (i == 1 || i == 6) {
                    pawnCount++;
                    if (i == 1) {
                        board[i][j] = new Pawn("black");
                        board[i][j].id = 'p';
                        board[i][j].color = "black";   
                    }
                    else if (i == 6) {
                        board[i][j] = new Pawn("white");
                        board[i][j].id = 'P';
                        board[i][j].color = "white";   
                    }
                    board[i][j].pieceCount = pawnCount;
                    System.out.println("Piece no. : "+board[i][j].pieceCount);
                }

                // Inserting Rooks
                else if ((i == 0 && (j == 0 || j == 7)) || (i == 7 && (j == 0 || j == 7))) {
                    rookCount++;
                    if (i == 0) {
                        board[i][j] = new Rook("black");
                        board[i][j].color = "black";
                        board[i][j].id = 'r';
                        board[i][j].name = "Rook";
                    } else if (i == 7) {
                        board[i][j] = new Rook("white");
                        board[i][j].color = "white";
                        board[i][j].id = 'R';
                        board[i][j].name = "Rook";
                    }
                    board[i][j].pieceCount = rookCount;
                }

                // Inserting Bishops
                else if ((i == 0 && (j == 2 || j == 5)) || (i == 7 && (j == 2 || j == 5))) {
                    bishopCount++;
                    if (i == 0) {
                        board[i][j] = new Bishop("black");
                        board[i][j].color = "black";
                        board[i][j].id = 'b';
                        board[i][j].name = "Bishop";
                    } else if (i == 7) {
                        board[i][j] = new Bishop("white");
                        board[i][j].color = "white";
                        board[i][j].id = 'B';
                        board[i][j].name = "Bishop";
                    }
                    board[i][j].pieceCount = bishopCount;
                }

                // Inserting Knight
                else if ((i == 0 && (j == 1 || j == 6)) || (i == 7 && (j == 1 || j == 6))) {
                    // Knight knight=null;
                    if (i == 0) {
                        board[i][j] = new Knight("black");
                        board[i][j].id = 'n';
                        board[i][j].color = "black";

                    } else if (i == 7) {
                        board[i][j] = new Knight("white");
                        board[i][j].id = 'N';
                        board[i][j].color = "white";
                    }
                    board[i][j].pieceCount = knightCount;
                }

                // Inserting King
                else if ((i == 0 && j == 4) || (i == 7 && j == 4)) {
                    // King king=null;
                    if (i == 0) {
                        board[i][j] = new King("black");
                        board[i][j].color = "black";
                        board[i][j].id = 'k';
                        board[i][j].name = "King";

                    } else if (i == 7) {
                        board[i][j] = new King("white");
                        board[i][j].color = "white";
                        board[i][j].id = 'K';
                        board[i][j].name = "King";
                    }
                    board[i][j].pieceCount = 1;
                }

                // Inserting Queen
                else if ((i == 0 && j == 3) || (i == 7 && j == 3)) {
                    // Queen queen=null;
                    if (i == 0) {
                        board[i][j] = new Queen("black");
                        board[i][j].color = "black";
                        board[i][j].id = 'q';
                        board[i][j].name = "Queen";
                    } else if (i == 7) {
                        board[i][j] = new Queen("white");
                        board[i][j].color = "white";
                        board[i][j].id = 'Q';
                        board[i][j].name = "Queen";
                    }
                    board[i][j].pieceCount = 1;
                }

                // Inserting Blank spaces
                else {
                    Marked marked = new Marked();
                    board[i][j] = marked;
                    board[i][j].isBlank = true;
                }

            }
        }
    }
}
