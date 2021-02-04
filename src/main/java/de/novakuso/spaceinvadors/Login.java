package de.novakuso.spaceinvadors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Login extends Application {

    public static Pane pane;
    public static Scene scene;
    public static Connection databaseConnection;
    public static FXMLLoader loader;

    public static boolean checkCredentials(String username, String password) {
        try {
            System.out.println("try");
            PreparedStatement preparedStatement = databaseConnection.prepareStatement("SELECT username, password FROM users WHERE username=? AND password=?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Logged in");
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static FXMLLoader loadScreen(String name) throws IOException {

        loader = new FXMLLoader(ControllerNewAccount.class.getResource("/" + name + ".fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        return loader;
    }

    public static void main(String[] args) {
        try {
            databaseConnection = DriverManager.getConnection("jdbc:mysql://" + Configuration.MYSQL_HOST + ":" + Configuration.MYSQL_PORT + "/" + Configuration.MYSQL_DATABASE + "?serverTimezone=UTC", Configuration.MYSQL_USERNAME, Configuration.MYSQL_PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("/login.fxml"));
        scene = new Scene(pane, SpaceInvaders.WIDTH, SpaceInvaders.HEIGHT);
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
