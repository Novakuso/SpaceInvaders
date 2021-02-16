package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ControllerEdit {
    public static String currentMode = "Edit";
    public static boolean mode;
    @FXML
    public Pane pane;
    @FXML
    public Label labelEditProfile;
    @FXML
    public Label labelUsername;
    @FXML
    public Label labelOldPassword;
    @FXML
    public Label labelNewPassword;
    @FXML
    public TextField textFieldUsername;
    @FXML
    public PasswordField passwordFieldPasswordOld;
    @FXML
    public PasswordField passwordFieldPasswordNew;
    @FXML
    public Button buttonApply;
    @FXML
    public Button buttonMode;
    public Stage level;

    public static void editAccount(String oldUsername, String newUsername, String oldPassword, String newPassword) {
        if (Login.checkCredentials(ControllerLogin.username, oldPassword)) {
            try {
                Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://" + Configuration.MYSQL_HOST + ":" + Configuration.MYSQL_PORT + "/" + Configuration.MYSQL_DATABASE + "?serverTimezone=UTC", Configuration.MYSQL_USERNAME, Configuration.MYSQL_PASSWORD);

                PreparedStatement preparedStatement = databaseConnection.prepareStatement("UPDATE users SET username=?, password=? WHERE username=? AND password=?");

                preparedStatement.setString(1, String.valueOf(newUsername));
                preparedStatement.setString(2, String.valueOf(newPassword));
                preparedStatement.setString(3, String.valueOf(oldUsername));
                preparedStatement.setString(4, String.valueOf(oldPassword));
                preparedStatement.executeUpdate();
                System.out.println("Account updated successfully");

                ControllerLogin.loadProfile(newUsername);

            } catch (SQLException | IOException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void deleteAccount(String username, String password) {
        if (Login.checkCredentials(ControllerLogin.username, password)) {
            try {
                Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://" + Configuration.MYSQL_HOST + ":" + Configuration.MYSQL_PORT + "/" + Configuration.MYSQL_DATABASE + "?serverTimezone=UTC", Configuration.MYSQL_USERNAME, Configuration.MYSQL_PASSWORD);

                PreparedStatement preparedStatement = databaseConnection.prepareStatement("DELETE FROM users WHERE username=? AND password=?");

                preparedStatement.setString(1, String.valueOf(username));
                preparedStatement.setString(2, String.valueOf(password));
                preparedStatement.executeUpdate();
                System.out.println("Account deleted successfully");

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void checkMode() {
        if (currentMode.equals("Edit")) {
            mode = false;
            currentMode = "Delete";
        } else if (currentMode.equals("Delete")) {
            mode = true;
            currentMode = "Edit";
        }
    }

    @FXML
    public void apply(ActionEvent event) throws IOException {
        if (currentMode.equals("Edit")) {
            System.out.println("Edit");
            editAccount(ControllerLogin.username, textFieldUsername.getText(), passwordFieldPasswordOld.getText(), passwordFieldPasswordNew.getText());
        } else if (currentMode.equals("Delete")) {
            deleteAccount(ControllerLogin.username, passwordFieldPasswordOld.getText());
            System.out.println("Delete");

            //logout
            level = (Stage) buttonApply.getScene().getWindow();
            level.close();
            ControllerLogin.profileLoaded = false;
            FXMLLoader loader = Login.loadScreen("login");
        }
    }

    @FXML
    public void changeMode(ActionEvent event) {
        buttonMode.setText(currentMode);
        checkMode();
        labelEditProfile.setText(currentMode + " Account");
        labelUsername.setVisible(mode);
        labelNewPassword.setVisible(mode);
        textFieldUsername.setVisible(mode);
        passwordFieldPasswordNew.setVisible(mode);
    }
}
