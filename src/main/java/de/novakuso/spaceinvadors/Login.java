package de.novakuso.spaceinvadors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Login extends Application {

    public static Pane pane;
    public static Scene scene;
    public static Connection databaseConnection;
    public static FXMLLoader loader;
    public static List<Boolean> levels = new ArrayList<>();
    public static boolean levelIsFree;
    public static List<Boolean> level1 = new ArrayList<>();
    public static List<Boolean> level2 = new ArrayList<>();
    public static List<Boolean> level3 = new ArrayList<>();
    public static List<Boolean> level4 = new ArrayList<>();

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

    static int id;

    public static int loadLevelsFromDatabase(String username, String password) {

        try {
            //System.out.println("try");
            PreparedStatement preparedStatement = databaseConnection.prepareStatement("SELECT id FROM users WHERE username=? AND password=?");

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                System.out.println(id);
            }
            //get levels
            String levelsString;
            ResultSet rsLevels;
            String coinsString;
            ResultSet rsCoins;

            for (int i = 1; i < 5; i++) {
                levelsString = "SELECT level" + i + " FROM levels WHERE id=" + id;
                System.out.println(levelsString);
                PreparedStatement psLevels = databaseConnection.prepareStatement(levelsString);
                rsLevels = psLevels.executeQuery();
                if (rsLevels.next()) {
                    levelIsFree = rsLevels.getBoolean("level" + i);
                }
                levels.add(i - 1, levelIsFree);
                System.out.println(i + " levels " + levels.get(i - 1));

                for (int j = 1; j < 4; j++) {
                    coinsString = "SELECT coin" + j + " FROM level" + i + " WHERE id=" + id;
                    PreparedStatement psCoins = databaseConnection.prepareStatement(coinsString);
                    rsCoins = psCoins.executeQuery();
                    if (rsCoins.next()) {
                        if (i == 1) {
                            level1.add(j - 1, rsCoins.getBoolean("coin" + j));
                            System.out.println(j - 1 + " level1 " + level1.get(j - 1));
                        }
                        if (i == 2) {
                            level2.add(j - 1, rsCoins.getBoolean("coin" + j));
                            System.out.println(j - 1 + " level2 " + level2.get(j - 1));
                        }
                        if (i == 3) {
                            level3.add(j - 1, rsCoins.getBoolean("coin" + j));
                            System.out.println(j - 1 + " level3 " + level3.get(j - 1));
                        }
                        if (i == 4) {
                            level4.add(j - 1, rsCoins.getBoolean("coin" + j));
                            System.out.println(j - 1 + " level4 " + level4.get(j - 1));
                        }

                    }
                }
                System.out.println();
                //ControllerLevel.setLevels(Login.levels);
            }


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
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
