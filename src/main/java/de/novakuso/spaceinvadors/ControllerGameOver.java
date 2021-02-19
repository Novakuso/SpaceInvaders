package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerGameOver {

    Stage spaceInvader;
    Stage gameOver;

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

    public static void resetGame() throws IOException {
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
        SpaceInvaders.currentCoin = -1;
        SpaceInvaders.coinY = -60;
        SpaceInvaders.coinIsSpawned = false;
        for (int i = 0; i < 3; i++) {
            SpaceInvaders.coinIsCollected.set(i, false);
        }
    }

    @FXML
    public void changeLevel(ActionEvent event) throws IOException {
        spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
        FXMLLoader loader = Login.loadScreen("profile");
        ControllerProfile currentProfile = loader.getController();
        currentProfile.labelWelcomeBack.setText(String.format("Welcome back %s!", ControllerLogin.username));
        resetGame();
    }

    public void playAgain(ActionEvent event) throws Exception {
        gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
        spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        resetGame();
        SpaceInvaders.loadGame();
    }

    @FXML
    public void fuckThisGame(ActionEvent event) {
        spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
        resetGame();
        ControllerLogin.profileLoaded = false;
        Login.loadScreen("login");
    }

    @FXML
    public void exit(ActionEvent event) {
        spaceInvader = (Stage) SpaceInvaders.labelScore.getScene().getWindow();
        spaceInvader.close();
        gameOver = (Stage) highscore.getScene().getWindow();
        gameOver.close();
    }
}
