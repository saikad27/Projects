package com.modules;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AboutUs {
    Stage primaryStage;
    Scene aboutUsScene;

    Label label = new Label("We have implemented the chess game in which their are two options.\nFirst one is 'Pass And Play' and second one is 'Play With Computer'.\nIn pass and play we have written the code for playing with another player and also we have another option of playing with computer.\n\nIn playing with computer we have used Stockfish Chess Engine which is freely available, this chess engine is capable of finding the \nbest possible move according to the current state of the chess board.\nTo represent the state of the chess board we have used FEN string.\n\nFEN is the abbreviation of Forsyth-Edwards Notation, and it is the standard notation to describe positions \nof a chess game. Steven J. Edwards, a computer programmer, created this notation system based on \nanother system designed by the journalist David Forsyth.\n\nMENTOR : SUBODH SIR\n\nContributers :\n\n1.Sairaj Vishnu Kad\n2.Vighnesh Gangadhar Belkar\n3.Bhavesh Pritesh Patil\n\n");
    
    public void setUserPage2(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
        // Method to set the user page scene
    public void setUserPage2Scene(Scene scene){
        this.aboutUsScene = scene;
    }
    public VBox createAboutUsScene(Runnable backHandler){
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color:Gold");

        backButton.setScaleX(1.5);
        backButton.setScaleY(1.5);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                backHandler.run();
            }
        });
        label.setFont(new Font(25));
        label.setTextFill(Color.WHITESMOKE);
        VBox vbox = new VBox(30,label,backButton);
        vbox.setPrefHeight(900);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color:Black");
        return vbox;

    }
}
