package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin extends SpaceInvaders {

    @FXML
    public Label labelLogin;
    @FXML
    public Label labelUsername;
    @FXML
    public Label labelPassword;
    @FXML
    public Label wrongCredentials;
    @FXML
    public PasswordField passwordFieldPassword;
    @FXML
    public TextField textFieldUsername;
    @FXML
    public Button buttonLogin;
    @FXML
    public Button buttonSignUp;
    @FXML
    public Button buttonCancel;


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) labelLogin.getScene().getWindow();
        stage.close();
    }

    @FXML
    void login(ActionEvent event) throws Exception {

        System.out.println();

        if (Login.checkCredentials(textFieldUsername.getText(), passwordFieldPassword.getText())) {
            System.out.println("checked credentials");

            Stage primaryStage = (Stage) wrongCredentials.getScene().getWindow();
            primaryStage.close();

            SpaceInvaders.loadGame();

        } else {
            System.out.println("Wrong Password or Username");
            wrongCredentials.setVisible(true);
        }
        textFieldUsername.setText(null);
        passwordFieldPassword.setText(null);
    }


    @FXML
    void signUp(ActionEvent event) throws IOException {
        wrongCredentials.setVisible(false);
        Login.loadScreen("newAccount");
    }
}

