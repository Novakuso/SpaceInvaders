package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ControllerLevel {
    public static int level;
    public static FXMLLoader loader;

    public static List<Boolean> coinBoolean;
    public static List<String> coinImage;
    public static List<String> coinURL;
    public static List<Double> coinX;
    public static List<Double> coinY;
    public static String coinNotActivated = "images/coinNotActivated.png";
    public static String coinActivated = "images/coin.png";

    @FXML
    public Pane pane;
    @FXML
    public Label labelLevel;
    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;

    public static FXMLLoader setButtonBooleans() throws IOException {
        loader = new FXMLLoader(ControllerLevel.class.getResource("/level.fxml"));
        loader.load();

        ControllerLevel controllerLevel = loader.getController();


        controllerLevel.button1.setDisable(true);
        controllerLevel.button2.setDisable(true);
        controllerLevel.button3.setDisable(true);
        controllerLevel.button4.setDisable(true);

        if (Login.levels.get(0)) {
            controllerLevel.button1.setDisable(false);
        }
        if (Login.levels.get(1)) {
            controllerLevel.button2.setDisable(false);
        }
        if (Login.levels.get(2)) {
            controllerLevel.button3.setDisable(false);
        }
        if (Login.levels.get(3)) {
            controllerLevel.button4.setDisable(false);
        }
        return loader;
    }

    public static void setUpCoinArrays() {

        coinBoolean = new ArrayList<>();
        coinImage = new ArrayList<>();
        coinURL = new ArrayList<>();
        coinX = new ArrayList<>();
        coinY = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            coinBoolean.add(i, null);
            coinImage.add(i, null);
            coinY.add(i, null);
            coinX.add(i, null);
        }
        coinURL.add(0, coinNotActivated);
        coinURL.add(1, coinActivated);

        System.out.println("0 " + Login.level1.get(0));
        System.out.println("1 " + Login.level1.get(1));
        System.out.println("3 " + Login.level1.get(2));
        System.out.println("4 " + Login.level2.get(0));
        System.out.println("5 " + Login.level2.get(1));
        System.out.println("6 " + Login.level2.get(2));
        System.out.println("7 " + Login.level3.get(0));
        System.out.println("8 " + Login.level3.get(1));
        System.out.println("9 " + Login.level3.get(2));
        System.out.println("10 " + Login.level4.get(0));
        System.out.println("11 " + Login.level4.get(1));
        System.out.println("12 " + Login.level4.get(2));

        coinBoolean.set(0, Login.level1.get(0));
        coinBoolean.set(1, Login.level1.get(1));
        coinBoolean.set(2, Login.level1.get(2));
        coinBoolean.set(3, Login.level2.get(0));
        coinBoolean.set(4, Login.level2.get(1));
        coinBoolean.set(5, Login.level2.get(2));
        coinBoolean.set(6, Login.level3.get(0));
        coinBoolean.set(7, Login.level3.get(1));
        coinBoolean.set(8, Login.level3.get(2));
        coinBoolean.set(9, Login.level4.get(0));
        coinBoolean.set(10, Login.level4.get(1));
        coinBoolean.set(11, Login.level4.get(2));

        for (int i = 0; i < coinBoolean.size(); i++) {
            if (i < 3) {
                coinX.set(i, i * 50.0 + 100);
            }
            if (i >= 3 && i < 6) {
                coinX.set(i, i * 50.0 - 50);
            }
            if (i >= 6 && i < 9) {
                coinX.set(i, i * 50 - 200.0);
            }
            if (i >= 9) {
                coinX.set(i, i * 50 - 350.0);
            }
        }
        for (int i = 0; i < coinBoolean.size(); i++) {
            if (i < 3) {
                coinY.set(i, 125.0);
            }
            if (i >= 3 && i < 6) {
                coinY.set(i, 200.0);
            }
            if (i >= 6 && i < 9) {
                coinY.set(i, 275.);
            }
            if (i >= 9) {
                coinY.set(i, 350.0);
            }
        }

        for (int i = 0; i < 12; i++) {
            if (coinBoolean.get(i)) {
                coinImage.set(i, coinURL.get(1));
                System.out.println("1");
            }
            if (!coinBoolean.get(i)) {
                coinImage.set(i, coinURL.get(0));
                System.out.println("0");
            }
        }

    }

    public static Pane drawCoins() throws IOException {
        FXMLLoader loader = new FXMLLoader(ControllerLevel.class.getResource("/level.fxml"));
        //Pane pane = loader.load();
        //ControllerLevel controllerLevel = loader.getController();

        loader = ControllerLevel.setButtonBooleans();

        ControllerLevel controllerLevel = loader.getController();

        for (int i = 0; i < coinBoolean.size(); i++) {
            Image image = new Image(coinImage.get(i), 50, 50, false, false);
            ImageView imageView = new ImageView(image);
            imageView.setTranslateX(coinX.get(i));
            imageView.setTranslateY(coinY.get(i));
            controllerLevel.pane.getChildren().add(imageView);
        }
        return controllerLevel.pane;
    }

    @FXML
    public void level1(ActionEvent event) throws Exception {
        level = 1;
        SpaceInvaders.loadGame();
        Stage levelStage = (Stage) labelLevel.getScene().getWindow();
        levelStage.close();
    }

    @FXML
    public void level2(ActionEvent event) throws Exception {
        level = 2;
        SpaceInvaders.loadGame();
        Stage levelStage = (Stage) labelLevel.getScene().getWindow();
        levelStage.close();
    }

    @FXML
    public void level3(ActionEvent event) throws Exception {
        level = 3;
        SpaceInvaders.loadGame();
        Stage levelStage = (Stage) labelLevel.getScene().getWindow();
        levelStage.close();
    }

    @FXML
    public void level4(ActionEvent event) throws Exception {
        level = 4;
        SpaceInvaders.loadGame();
        Stage levelStage = (Stage) labelLevel.getScene().getWindow();
        levelStage.close();
    }
}

