package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin extends SpaceInvaders {

    public static ControllerProfile currentProfile;
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
    public static String username;
    public static boolean profileLoaded = false;
    public static FXMLLoader loader;


    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) labelLogin.getScene().getWindow();
        stage.close();
    }

    public static void loadProfile(String username) throws IOException {

        if (!profileLoaded) {
            loader = Login.loadScreen("profile");
            currentProfile = loader.getController();
            currentProfile.labelWelcomeBack.setText(String.format("Welcome back %s!", username));
            profileLoaded = true;
        } else {
            currentProfile = loader.getController();
            currentProfile.labelWelcomeBack.setText(String.format("Welcome back %s!", username));
        }
    }

    @FXML
    void login(ActionEvent event) throws Exception {

        System.out.println();

        if (Login.checkCredentials(textFieldUsername.getText(), passwordFieldPassword.getText())) {
            System.out.println("checked credentials");

            Stage primaryStage = (Stage) wrongCredentials.getScene().getWindow();
            primaryStage.close();

            username = textFieldUsername.getText();

            loadProfile(textFieldUsername.getText());

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
        textFieldUsername.setText(null);
        passwordFieldPassword.setText(null);
    }
}

