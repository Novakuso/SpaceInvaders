package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControllerGameOver {

    @FXML
    public static Pane pane;

    @FXML
    public static VBox vbox;
    @FXML
    public static Label labelScore;
    @FXML
    public static Label labelHighscore;
    @FXML
    public static Label labelGameOver;
    @FXML
    public static Button buttonPlayAgain;
    @FXML
    public static Button buttonChangeLevel;
    @FXML
    public static Button buttonFuckThisGame;
    @FXML
    public static Button buttonLogout;
    @FXML
    public static Button buttonExit;
    @FXML
    public Label score;
    @FXML
    public Label highscore;

    public void playAgain(ActionEvent event) {
        Stage gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();

        SpaceInvaders.gameIsOver = false;
        SpaceInvaders.gameOverScreenLoaded = false;
        SpaceInvaders.score = 0;
        SpaceInvaders.setArrayData();
        SpaceInvaders.lifeToRemove = SpaceInvaders.maxLives - 1;
        SpaceInvaders.playerX = SpaceInvaders.WIDTH / 2 - SpaceInvaders.player.getRequestedWidth() / 2;
        SpaceInvaders.playerY = SpaceInvaders.HEIGHT - 50 - SpaceInvaders.player.getRequestedHeight();

        SpaceInvaders.shotSpeed = 5.5;
        SpaceInvaders.objectSpeed = 2.5;
        SpaceInvaders.shotReloadSpeed = 20;
        SpaceInvaders.objectReloadSpeed = 50;
    }

    @FXML
    public void changeLevel(ActionEvent event) {
        //change level
    }

    @FXML
    public void fuckThisGame(ActionEvent event) {
        Stage spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        Stage gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
    }

    @FXML
    public void logout(ActionEvent event) {
        //logout of your account
    }

    @FXML
    public void exit(ActionEvent event) {
        Stage spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        Stage gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
    }
}
