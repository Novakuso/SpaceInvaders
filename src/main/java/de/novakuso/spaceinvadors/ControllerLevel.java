package de.novakuso.spaceinvadors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControllerLevel {
    public static int level;
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
