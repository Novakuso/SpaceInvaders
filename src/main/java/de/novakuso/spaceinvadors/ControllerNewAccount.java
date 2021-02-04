package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;

public class ControllerNewAccount {

    @FXML
    public Label labelCreateNewAccount;
    @FXML
    public Label labelUsername;
    @FXML
    public Label labelPassword;
    @FXML
    public Label labelPasswordRepeat;
    @FXML
    public Label passwordsWrong;
    @FXML
    public TextField username;
    @FXML
    public PasswordField password;
    @FXML
    public PasswordField passwordRepeat;
    @FXML
    public Button buttonCreate;
    @FXML
    public Button buttonCancel;

    public static void createNewAccount(String username, String password) {
        try {
            Statement st = Login.databaseConnection.createStatement();
            String sql = "INSERT INTO  `users` (username, password) VALUES ('" + username + "', \"" + password + "\");";
            st.executeUpdate(sql);
            System.out.println("Account created successfully");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    void create(ActionEvent event) {
        if (password.getText().equals(passwordRepeat.getText())) {
            createNewAccount(username.getText(), password.getText());
        } else passwordsWrong.setVisible(true);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage newAccount = (Stage) labelCreateNewAccount.getScene().getWindow();
        newAccount.close();
    }
}
