package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerProfile {

    @FXML
    public static Label huso;
    public Stage level;
    @FXML
    public VBox vBox;
    @FXML
    public Pane pane;
    @FXML
    public Button buttonHome;
    @FXML
    public Button buttonLevels;
    @FXML
    public Button buttonEdit;
    @FXML
    public Button buttonSucks;
    @FXML
    public Button buttonLogout;
    @FXML
    public Button buttonExit;
    @FXML
    public Label labelWelcomeBack;
    Pane root;
    Pane root1;
    @FXML
    private BorderPane borderPane;

    @FXML
    public void goHome(ActionEvent event) throws IOException {
        borderPane.setCenter(pane);
    }

    @FXML
    public void level(ActionEvent event) throws IOException {
        //FXMLLoader controllerLevel = ControllerLevel.setButtonBooleans();
        ControllerLevel.setUpCoinArrays();
        //root = controllerLevel.getRoot();
        root = ControllerLevel.drawCoins();
        borderPane.setCenter(root);
    }

    @FXML
    public void edit(ActionEvent event) throws IOException {
        root1 = FXMLLoader.load(ControllerProfile.class.getResource("/edit.fxml"));
        borderPane.setCenter(root1);
    }

    @FXML
    public void sucks(ActionEvent event) {
        level = (Stage) buttonSucks.getScene().getWindow();
        level.close();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        level = (Stage) buttonLogout.getScene().getWindow();
        level.close();
        ControllerLogin.profileLoaded = false;
        FXMLLoader loader = Login.loadScreen("login");
    }

    @FXML
    public void exit(ActionEvent event) {
        level = (Stage) buttonExit.getScene().getWindow();
        level.close();
    }
}
