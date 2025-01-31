package modules;

import javafx.application.Application;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Play extends Application {
    int bKing1 = 0, bKing2 = 0, wKing1 = 0, wKing2 = 0;
    boolean check = false;
    int pre_p1, pre_p2;
    int movNo = 0;
    boolean action = false;
    boolean trail_exists = false;
    int p1, p2;
    Board[][] board = new Board[8][8];
    Rectangle[][] sqr = new Rectangle[8][8];
    GridPane gridPane = new GridPane();

    @Override
    public void start(Stage stage) {

        stage.setTitle("Chess Board");
        stage.setHeight(790);
        stage.setWidth(761);
        stage.setResizable(true);

        // Initialize and add pieces to the board

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // Inserting Pawns
                if (i == 1 || i == 6) {

                    if (i == 1) {
                        board[i][j] = new Pawn("black");
                        board[i][j].color = "black";

                    }
                    if (i == 6) {
                        board[i][j] = new Pawn("white");
                        board[i][j].color = "white";

                    }

                }

                // Inserting Rooks
                else if ((i == 0 && (j == 0 || j == 7)) || (i == 7 && (j == 0 || j == 7))) {

                    if (i == 0) {
                        board[i][j] = new Rook("black");
                        board[i][j].color = "black";
                        board[i][j].name = "Rook";
                    } else if (i == 7) {
                        board[i][j] = new Rook("white");
                        board[i][j].color = "white";
                        board[i][j].name = "Rook";
                    }

                }

                // Inserting Bishops
                else if ((i == 0 && (j == 2 || j == 5)) || (i == 7 && (j == 2 || j == 5))) {

                    if (i == 0) {
                        board[i][j] = new Bishop("black");
                        board[i][j].color = "black";
                        board[i][j].name = "Bishop";
                    } else if (i == 7) {
                        board[i][j] = new Bishop("white");
                        board[i][j].color = "white";
                        board[i][j].name = "Bishop";
                    }

                }

                // Inserting Knight
                else if ((i == 0 && (j == 1 || j == 6)) || (i == 7 && (j == 1 || j == 6))) {
                    // Knight knight=null;
                    if (i == 0) {
                        board[i][j] = new Knight("black");
                        board[i][j].color = "black";

                    } else if (i == 7) {
                        board[i][j] = new Knight("white");
                        board[i][j].color = "white";
                    }
                    // board[i][j] = knight;
                }

                // Inserting King
                else if ((i == 0 && j == 4) || (i == 7 && j == 4)) {
                    // King king=null;
                    if (i == 0) {
                        board[i][j] = new King("black");
                        board[i][j].color = "black";
                        board[i][j].name = "King";

                    } else if (i == 7) {
                        board[i][j] = new King("white");
                        board[i][j].color = "white";
                        board[i][j].name = "King";
                    }
                    // board[i][j] = king;
                }

                // Inserting Queen
                else if ((i == 0 && j == 3) || (i == 7 && j == 3)) {
                    // Queen queen=null;
                    if (i == 0) {
                        board[i][j] = new Queen("black");
                        board[i][j].color = "black";
                        board[i][j].name = "Queen";
                    } else if (i == 7) {
                        board[i][j] = new Queen("white");
                        board[i][j].color = "white";
                        board[i][j].name = "Queen";
                    }
                    // board[i][j] = queen;
                }

                // Inserting Blank spaces
                else {
                    Marked marked = new Marked();
                    board[i][j] = marked;
                    board[i][j].isBlank = true;
                }

            }
        }
        // Board temp = board[1][2];
        // board[1][2] = board[2][2];
        // board[2][2] = temp;

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

                //System.out.println(action);
                // if(action ){
                if (check == true && board[p1][p2].name != "King") {
                    System.out.println("Try again ");
                } else if (check == true && board[p1][p2].name == "King") {
                    // resolveCheck();
                } else if (board[p1][p2].markedKill && action) {
                  //  System.out.println("Calling kill");
                    kill(p1, p2);
                    display();
                    clean();
                    movNo++;
                    //System.out.println(movNo);
                    action = false;
                } else if (board[p1][p2].isMarked && action) {
                    int a = p1, b = p2;
                    if (board[p1][p2].isBlank) {
                       // System.out.println(board[p1][p2]);
                        move(a, b);
                        if(board[p1][p2].name=="Queen" || board[p1][p2].name=="Rook" || board[p1][p2].name=="Bishop"){
                            System.out.println("Location : "+p1+" "+p2);
                            possibleTrail(p1,p2);
                        }
                       // possibleCheck();

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

        Group group = new Group(gridPane);
        Scene scene = new Scene(group);

        stage.setScene(scene);

        stage.show();

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
                sqr[i][j].setFill(((i + j) % 2 == 0) ? (Color.WHEAT) : (Color.CORAL));
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

        // Below line makes sure that the previous location of attacking piece to be
        // blank
        board[pre_p1][pre_p2].isBlank = true;

        // movNo++;
    }

    public void clean() {
        // System.out.println("Inside clean");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j].getCircle().setOpacity(0);
                board[i][j].invalid = false;
                board[i][j].trailBox = false;
                board[i][j].isMarked = false;

                if (board[i][j].markedKill) {
                    board[i][j].markedKill = false;
                    System.out.println("Inside clean");
                    sqr[i][j].setFill(((i + j) % 2 == 0) ? (Color.WHEAT) : (Color.CORAL));
                }
            }
        }
    }

    public void move(int a, int b) {
        movNo++;
        board[pre_p1][pre_p2].firstMove = false;
        System.out.println("Swaping");
        board[a][b].getCircle().setOpacity(0);
        clean();

        Board temp = board[a][b];
        board[a][b] = board[pre_p1][pre_p2];
        board[pre_p1][pre_p2] = temp;

        display();

    }

    public void move(int cP1, int cP2, int tP1, int tP2) {
        movNo++;
        board[cP1][cP2].firstMove = false;
        System.out.println("Swaping");
        board[tP1][tP2].getCircle().setOpacity(0);
        clean();

        Board temp = board[tP1][tP2];
        board[tP1][tP2] = board[cP1][cP2];
        board[cP1][cP2] = temp;

        display();
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
        //System.out.println("Inside search King");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].color == "black" && board[i][j].name == "King") {
                    bKing1 = i;
                    bKing2 = j;
                    //System.out.println("Black King : " + bKing1 + " " + bKing2);
                    break;
                }
                if (board[i][j].color == "white" && board[i][j].name == "King") {
                    wKing1 = i;
                    wKing2 = j;
                    //System.out.println("White King : " + wKing1 + " " + wKing2);
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
                    if(board[i][j].invalid){
                        if(board[i][j].trailBox){
                            board[i][j].getCircle().setOpacity(1.0);
                        }
                    }
                    else{
                        board[i][j].getCircle().setOpacity(1.0);
                    }
                    
                }
            }
        }
    }

    void possibleTrail(int c1,int c2) {
        System.out.println(c1+" "+c2);
        int row,column;
        int c1King = 0;
        int c2King = 0;
        searchKing();
        System.out.println("Inside possible trail");
        if (board[c1][c2].color == "black") {
            
            c1King = wKing1;
            c2King = wKing2;
            System.out.println("White king : "+c1King+" "+c2King);
        }

        else if(board[c1][c2].color=="white"){
            
            c1King = bKing1;
            c2King = bKing2;
            System.out.println("Black king : "+c1King+" "+c2King);
        }
        
        // Elephant path
        if (c1 == c1King || c2 == c2King) {

        }

        // North-west
        else if (c1 > c1King && c2 > c2King) {
            System.out.println("Inside north-west");
            row = c1 - c1King;
            column = c2 - c2King;
            if(row==column){
                System.out.println(row+" "+column);
                trail_exists = true;
            }
            else{
                trail_exists = false;
            }
            System.out.println("Hello");
            if(trail_exists){
                System.out.println(c1+" "+c2);
                int i=c1-1;
                int j=c2-1;
                while(i>=c1King){
                    System.out.println("Checking "+i+" "+j);
                    if(board[i][j].color==board[c1][c2].color){
                        trail_exists = false;
                        break;
                    }
                    else if(board[i][j].isBlank){
                        board[i][j].trailBox = true;
                    }
                    else if(board[i][j]==board[c1King][c2King]){
                        System.out.println("Marked Check");
                        check=true;
                    }
                    else if(board[i][j].color!=board[c1][c2].color){
                        board[i][j].invalid = true;
                        System.out.println("Marked invalid");
                    }
                    //trailCount++;
                    i--;
                    j--;
                }
                
            }
            if(!trail_exists){
               // clean();
            }
        }

        // North-east
        else if (c1 > c1King && c2 < c2King) {
            System.out.println("Inside north-east");
            row = c1 - c1King;
            column = c2King - c2;
            System.out.println(row+" "+column);
            if (row == column) {
                System.out.println("Trail exists");
                trail_exists = true;
            }
            else{
                trail_exists = false;
            }

            if(trail_exists){
                //int trailCount = 0;
                int i=c1-1;
                int j=c2+1;
                while(i<=c1King){
                    if(board[i][j].color==board[c1][c2].color){
                        trail_exists = false;
                        break;
                    }
                    else if(board[i][j].isBlank){
                        board[i][j].trailBox = true;
                    }
                    else if(board[i][j].color!=board[c1][c2].color){
                        board[i][j].invalid = true;
                    }
                    //trailCount++;
                    i--;
                    j++;
                }
                
            }
            if(!trail_exists){
               // clean();
            }
        }

        // South-west
        else if (c1 < c1King && c2 > c2King) {
            row = c1King-c1;
            column = c2-c2King;
            if (row == column) {
                trail_exists = true;
            }
            else{
                trail_exists = false;
            }

            if(trail_exists){
                //int trailCount = 0;
                int i=c1+1;
                int j=c2-1;
                while(i<=c1King){
                    if(board[i][j].color==board[c1][c2].color){
                        trail_exists = false;
                        break;
                    }
                    else if(board[i][j].isBlank){
                        board[i][j].trailBox = true;
                    }
                    else if(board[i][j].color!=board[c1][c2].color){
                        board[i][j].invalid = true;
                    }
                    //trailCount++;
                    i++;
                    j--;
                }
                
            }
            if(!trail_exists){
               // clean();
            }

        }

        // South-east
        else if (c1 < c1King && c2 < c2King) {

        }
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
    void inDe() {

    }
}
