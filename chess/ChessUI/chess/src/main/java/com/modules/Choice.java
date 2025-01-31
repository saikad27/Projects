package com.modules;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Choice extends Application {

    Stage primaryStage;
    Scene choiceScene;

    private Scene getChoiceScene() {
        return choiceScene;
    }

    private void getChoicePage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showAlert();
    }

    private void showAlert() {
        Label msg = new Label("");
        msg.setPrefWidth(175);
        msg.setFont(new Font(55));
        msg.setAlignment(Pos.TOP_CENTER);

        Button withhumanButton = new Button("1 VS 1");
        withhumanButton.setFont(new Font(25));
        withhumanButton.setStyle("-fx-background-color:Gold");

        withhumanButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initPassNPlayScene();
            }
        });

        Button withcomputerButton = new Button("Computer");
        withcomputerButton.setFont(new Font(25));
        withcomputerButton.setStyle("-fx-background-color:Gold");

        withcomputerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initPlaywithComputerScene();
            }
        });

        Button aboutUsButton = new Button("About Us");
        aboutUsButton.setFont(new Font(25));
        aboutUsButton.setStyle("-fx-background-color:Gold");

        aboutUsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                initAboutUsScene();
            }
        });

        HBox hb = new HBox(300, withhumanButton, withcomputerButton, aboutUsButton);
        hb.setAlignment(Pos.CENTER);

        VBox vb = new VBox(475, msg, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-image:url('https://m.economictimes.com/thumb/msid-103321811,width-1440,height-900,resizemode-4,imgsize-101550/chess.jpg');-fx-background-size : cover");

        choiceScene = new Scene(vb, 1500, 950);
    }

    @Override
    public void start(Stage primaryStage) {
        Choice loginController = new Choice();

        loginController.getChoicePage(primaryStage);
        primaryStage.setScene(loginController.getChoiceScene());

        primaryStage.setTitle("JChess");
        primaryStage.setHeight(1050);
        primaryStage.setWidth(1680);
        primaryStage.setFullScreen(true);

        primaryStage.show();
    }

    private void initPassNPlayScene() {
        PassNPlay c2w_pi_userPage = new PassNPlay();
        c2w_pi_userPage.setUserPage(primaryStage);

        Scene passNPlayScene = new Scene(c2w_pi_userPage.createPassNPlayScene(this::handleBack), 1680, 1050);

        c2w_pi_userPage.setUserPageScene(passNPlayScene);
        switchToScene(passNPlayScene);
    }

    private void initPlaywithComputerScene() {
        PlayWithComp chess_computer = new PlayWithComp();
        chess_computer.setUserPage1(primaryStage);

        Scene playwithComputerScene = new Scene(chess_computer.createPlayWithCompScene(this::handleBack), 1680, 1050);

        chess_computer.setUserPage1Scene(playwithComputerScene);
        switchToScene(playwithComputerScene);
    }

    private void initAboutUsScene() {
        AboutUs chess_aboutus = new AboutUs();
        chess_aboutus.setUserPage2(primaryStage);

        Scene aboutUsScene = new Scene(chess_aboutus.createAboutUsScene(this::handleBack), 1680, 1050);

        chess_aboutus.setUserPage2Scene(aboutUsScene);
        switchToScene(aboutUsScene);
    }

    private void handleBack() {
        switchToScene(choiceScene);
    }

    private void switchToScene(Scene newScene) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), primaryStage.getScene().getRoot());
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        fadeOut.setOnFinished(event -> {
            primaryStage.setScene(newScene);
            primaryStage.setFullScreen(true); // Ensures full screen is retained
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newScene.getRoot());
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


