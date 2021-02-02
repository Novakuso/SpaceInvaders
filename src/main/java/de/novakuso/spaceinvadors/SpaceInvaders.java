package de.novakuso.spaceinvadors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpaceInvaders extends Application {

    public static final int FRAMES = 144;
    public static final double WIDTH = 400;
    public static final double HEIGHT = 550;

    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    public static Pane root;
    public static Canvas canvas;
    public static GraphicsContext gc;
    public static Scene scene;
    public static Image player = new Image("images/player.png", 75, 50, false, false);
    public static Image lifeImage;

    public static boolean gameOverScreenLoaded = false;
    public static boolean gameIsOver = false;

    public static boolean shotIsAvailable = true;
    public static boolean isShot = false;
    public static boolean playerCanShoot = true;
    public static int currentShotImage = 0;
    public static int countShot = 0;

    public static int randomShotImage;
    public static double shotReloadSpeed;
    public static double shotSpeed;
    public static int maxShots;

    public static boolean objectCanSpawn = true;
    public static int currentObjectImage = 0;
    public static boolean isSpawned = false;
    public static boolean objectIsAvailable = true;
    public static int countObject = 0;

    public static int randomObjectImage;
    public static double objectSpeed;
    public static int maxObjects;
    public static double objectReloadSpeed;

    public static int removeSpeed = 3;
    public static int countRemove = 0;
    public static int counterObjectsUsed = 0;
    public static int counterShotsUsed = 0;
    public static int score = 0;
    public static int highscore = 0;
    public static int maxLives = 5;
    public static int lifeToRemove = maxLives - 1;

    public static double playerX = WIDTH / 2 - player.getRequestedWidth() / 2;
    public static double playerY = HEIGHT - 50 - player.getRequestedHeight();

    static Label labelScore = new Label();
    static List<Boolean> eventInputs;
    static List<Boolean> playerCanMoveDir;

    static List<Image> shotImages;
    static List<Double> shotX;
    static List<Double> shotY;
    static List<Integer> currentShot;
    static List<Boolean> shotIsUsed;

    static List<Image> objectImages;
    static List<Double> objectX;
    static List<Double> objectY;
    static List<Integer> currentObject;
    static List<Boolean> objectIsUsed;

    static List<Double> lifeX;
    static List<Double> lifeY;
    static List<Boolean> lifeIsUsed;

    //setup arrays
    public static void setupArrays() {
        eventInputs = new ArrayList<>();
        playerCanMoveDir = new ArrayList<>();

        shotImages = new ArrayList<>();
        shotX = new ArrayList<>();
        shotY = new ArrayList<>();
        currentShot = new ArrayList<>();
        shotIsUsed = new ArrayList<>();

        objectImages = new ArrayList<>();
        objectX = new ArrayList<>();
        objectY = new ArrayList<>();
        currentObject = new ArrayList<>();
        objectIsUsed = new ArrayList<>();

        lifeX = new ArrayList<>();
        lifeY = new ArrayList<>();
        lifeIsUsed = new ArrayList<>();
    }

    //add null to all arrays
    public static void addArrayIndexes() {
        for (int i = 0; i < 4; i++) {
            playerCanMoveDir.add(i, null);
        }
        for (int i = 0; i < 7; i++) {
            eventInputs.add(i, null);
        }
        for (int i = 0; i < maxShots; i++) {
            shotX.add(i, null);
            shotY.add(i, null);
            currentShot.add(i, null);
            shotIsUsed.add(i, null);
        }
        for (int i = 0; i < maxObjects; i++) {
            objectX.add(i, null);
            objectY.add(i, null);
            currentObject.add(i, null);
            objectIsUsed.add(i, null);
        }
        for (int i = 0; i < maxLives; i++) {
            lifeX.add(i, null);
            lifeY.add(i, null);
            lifeIsUsed.add(i, null);
        }
    }

    //set array data
    public static void setArrayData() {
        for (int i = 0; i < 4; i++) {
            playerCanMoveDir.set(i, true);
        }
        for (int i = 0; i < 7; i++) {
            eventInputs.set(i, false);
        }
        for (int i = 0; i < maxShots; i++) {
            shotX.set(i, WIDTH / 2);
            shotY.set(i, HEIGHT + 50);
            currentShot.set(i, 0);
            shotIsUsed.set(i, false);
        }
        for (int i = 0; i < maxObjects; i++) {
            objectX.set(i, RANDOM.nextDouble(WIDTH - objectImages.get(0).getRequestedWidth()));
            objectY.set(i, -100.0);
            currentObject.set(i, 0);
            objectIsUsed.set(i, false);
        }
        for (int i = 0; i < maxLives; i++) {
            lifeX.set(i, i * lifeImage.getRequestedWidth());
            lifeY.set(i, HEIGHT - lifeImage.getRequestedHeight());
            lifeIsUsed.set(i, true);
        }
    }

    //load images
    public static void loadImages() {
        shotImages.add(new Image("/images/green.png", 10, 30, false, false));
        shotImages.add(new Image("/images/cyan.png", 10, 30, false, false));
        shotImages.add(new Image("/images/red.png", 10, 30, false, false));
        shotImages.add(new Image("/images/black.png", 10, 30, false, false));
        shotImages.add(new Image("/images/purple.png", 10, 30, false, false));
        shotImages.add(new Image("/images/magenta.png", 10, 30, false, false));

        objectImages.add(new Image("/images/ballPurple.png", 50, 50, false, false));
        objectImages.add(new Image("/images/ballGreen.png", 50, 50, false, false));
        objectImages.add(new Image("/images/starPurple.png", 50, 50, false, false));
        objectImages.add(new Image("/images/starYellow.png", 50, 50, false, false));

        lifeImage = new Image("/images/heart.png", 25, 25, false, false);
    }

    //setup images
    public static void setupImages() {
        gc.drawImage(player, playerX, playerY);
    }

    //setup variables
    public static void setupVars() {
        shotReloadSpeed = 20;
        shotSpeed = 5.5;
        maxShots = 20;

        objectSpeed = 2.5;
        objectReloadSpeed = 50;
        maxObjects = 10;
    }

    //handle user inputs
    public static void setInputs() {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case A:
                    eventInputs.set(0, true);
                    break;
                case D:
                    eventInputs.set(1, true);
                    break;
                case W:
                    eventInputs.set(2, true);
                    break;
                case S:
                    eventInputs.set(3, true);
                    break;
                case SPACE:
                    eventInputs.set(4, true);
                    break;
                case P:
                    if (!eventInputs.get(6)) {
                        eventInputs.set(6, true);
                    } else if (eventInputs.get(6)) {
                        eventInputs.set(6, false);
                    }
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case A:
                    eventInputs.set(0, false);
                    break;
                case D:
                    eventInputs.set(1, false);
                    break;
                case W:
                    eventInputs.set(2, false);
                    break;
                case S:
                    eventInputs.set(3, false);
                    break;
                case SPACE:
                    eventInputs.set(4, false);
                    break;
            }
        });

        scene.setOnMousePressed(event -> {
            eventInputs.set(5, true);
        });
        scene.setOnMouseReleased(event -> {
            eventInputs.set(5, false);
        });
    }

    //main clock
    public static void tick(GraphicsContext gc) throws InterruptedException, IOException {
        if (!gameIsPaused() && !gameIsOver) {
            checkPlayerIfBorder();
            movePlayer();
            drawImages(gc);
        }
    }

    public static boolean gameIsPaused() {
        return eventInputs.get(6);
    }

    //move Player
    public static void movePlayer() {
        if (eventInputs.get(0) && playerCanMoveDir.get(0) && eventInputs.get(2) && playerCanMoveDir.get(2)) {
            playerX = playerX - 4;
            playerY = playerY - 4;
        } else if (eventInputs.get(0) && playerCanMoveDir.get(0) && eventInputs.get(3) && playerCanMoveDir.get(3)) {
            playerX = playerX - 4;
            playerY = playerY + 4;
        } else if (eventInputs.get(1) && playerCanMoveDir.get(1) && eventInputs.get(2) && playerCanMoveDir.get(2)) {
            playerX = playerX + 4;
            playerY = playerY - 4;
        } else if (eventInputs.get(1) && playerCanMoveDir.get(1) && eventInputs.get(3) && playerCanMoveDir.get(3)) {
            playerX = playerX + 4;
            playerY = playerY + 4;
        } else if (eventInputs.get(0) && playerCanMoveDir.get(0) && !eventInputs.get(1) && !eventInputs.get(2) && !eventInputs.get(3)) {
            playerX = playerX - 5;
        } else if (eventInputs.get(1) && playerCanMoveDir.get(1) && !eventInputs.get(0) && !eventInputs.get(2) && !eventInputs.get(3)) {
            playerX = playerX + 5;
        } else if (eventInputs.get(2) && playerCanMoveDir.get(2) && !eventInputs.get(0) && !eventInputs.get(1) && !eventInputs.get(3)) {
            playerY = playerY - 5;
        } else if (eventInputs.get(3) && playerCanMoveDir.get(3) && !eventInputs.get(0) && !eventInputs.get(1) && !eventInputs.get(2)) {
            playerY = playerY + 5;
        }
    }

    //check if player hits the border
    public static void checkPlayerIfBorder() {
        if (playerX < 5) {
            playerCanMoveDir.set(0, false);
        } else playerCanMoveDir.set(0, true);
        if (playerX > WIDTH - player.getRequestedWidth()) {
            playerCanMoveDir.set(1, false);
        } else playerCanMoveDir.set(1, true);
        if (playerY < 5) {
            playerCanMoveDir.set(2, false);
        } else playerCanMoveDir.set(2, true);
        if (playerY > HEIGHT - player.getRequestedHeight()) {
            playerCanMoveDir.set(3, false);
        } else playerCanMoveDir.set(3, true);
    }

    //delay next shot
    public static boolean countShots() {
        if (countShot >= shotReloadSpeed) {
            countShot = 0;
            return true;
        } else {
            countShot++;
            return false;
        }
    }

    //delay next object
    public static boolean countObjects() {
        if (countObject >= objectReloadSpeed) {
            countObject = 0;
            return true;
        } else {
            countObject++;
            return false;
        }
    }

    //delay to remove next object
    public static boolean countToRemove() {
        if (countRemove == removeSpeed) {
            countRemove = 0;
            return true;
        } else {
            countRemove++;
            return false;
        }
    }

    //player shoots
    public static boolean shoot() {
        if ((eventInputs.get(4) || eventInputs.get(5)) && shotIsAvailable && playerCanShoot) {
            randomShotImage = RANDOM.nextInt(shotImages.size());

            shotX.set(currentShotImage, playerX + player.getRequestedWidth() / 2 - 5);
            shotY.set(currentShotImage, playerY - player.getRequestedHeight() / 2 + 2);

            currentShot.set(currentShotImage, randomShotImage);
            shotIsUsed.set(currentShotImage, true);

            if (currentShotImage == maxShots - 1) {
                currentShotImage = 0;
            } else if (shotIsUsed.get(currentShotImage) && currentShotImage < maxShots - 1) {
                currentShotImage++;
            }
            playerCanShoot = false;
            shotIsAvailable = false;
            isShot = true;
            return true;
        } else return false;
    }

    //move shots
    public static void moveShots() {
        for (int i = 0; i < maxShots; i++) {
            if (shotY.get(i) <= HEIGHT && shotY.get(i) >= -shotImages.get(0).getRequestedHeight()) {
                shotY.set(i, shotY.get(i) - shotSpeed);
            }
        }
    }

    //check shots
    public static void checkShot() {
        if (countShots()) {
            playerCanShoot = true;
        }
        for (int i = 0; i < maxShots; i++) {
            if (shotY.get(i) < 0 - shotImages.get(0).getRequestedHeight()) {
                shotIsUsed.set(i, false);
                shotY.set(i, HEIGHT + 50);
            }
            if (shotIsUsed.get(i)) {
                counterShotsUsed++;
                shotIsAvailable = counterShotsUsed != maxShots;
            }
        }
    }

    //create new object
    public static boolean spawnObject() {
        if (objectCanSpawn && objectIsAvailable && !objectIsUsed.get(currentObjectImage)) {
            randomObjectImage = RANDOM.nextInt(objectImages.size());

            objectX.set(currentObjectImage, RANDOM.nextDouble(WIDTH - objectImages.get(0).getRequestedWidth()));
            objectY.set(currentObjectImage, 1.0);

            currentObject.set(currentObjectImage, randomObjectImage);
            objectIsUsed.set(currentObjectImage, true);

            if (currentObjectImage == maxObjects - 1) {
                currentObjectImage = 0;
            } else if (objectIsUsed.get(currentObjectImage) && currentObjectImage < maxObjects - 1) {
                currentObjectImage++;
            }
            objectCanSpawn = false;
            objectIsAvailable = false;
            isSpawned = true;
            return true;
        } else return false;
    }

    //move objects
    public static void moveObjects() {
        for (int i = 0; i < maxObjects; i++) {
            if (objectY.get(i) <= HEIGHT && objectY.get(i) >= -30) {
                objectY.set(i, objectY.get(i) + objectSpeed);
            }
        }
    }

    //check objects
    public static void checkObjects() throws IOException {
        if (countObjects()) {
            objectCanSpawn = true;
        }
        for (int i = 0; i < maxObjects; i++) {
            if (objectY.get(i) >= HEIGHT) {
                objectIsUsed.set(i, false);
                objectY.set(i, -100.0);
                removeLife();
            }
            for (int j = 0; j < maxShots; j++) {
                if (shotX.get(j) > objectX.get(i) && shotX.get(j) < objectX.get(i) + objectImages.get(0).getRequestedWidth() && shotY.get(j) < objectY.get(i) + objectImages.get(0).getRequestedHeight() && shotY.get(j) > objectY.get(i)
                        && shotX.get(j) + shotImages.get(0).getRequestedWidth() > objectX.get(i) && shotX.get(j) + shotImages.get(0).getRequestedWidth() < objectX.get(i) + objectImages.get(0).getRequestedWidth()) {
                    if (countToRemove()) {
                        objectY.set(i, -100.0);
                        objectIsUsed.set(i, false);
                        shotIsUsed.set(j, false);
                        shotY.set(j, HEIGHT + 50);

                        score++;

                        if (score % 15 == 0 && score <= 100) {
                            objectReloadSpeed = objectReloadSpeed / 2 + objectReloadSpeed / 3.75;
                            shotReloadSpeed = shotReloadSpeed / 2 + shotReloadSpeed / 2.75;
                        }
                        if (score >= highscore) {
                            highscore = score;
                        }
                        labelScore.setText("Score: " + score);
                    }
                }
            }
            if (objectIsUsed.get(i)) {
                counterObjectsUsed++;
                objectIsAvailable = counterObjectsUsed != maxObjects;
            }
        }
    }

    //remove lives
    public static void removeLife() throws IOException {
        if (lifeToRemove <= 0) {
            lifeIsUsed.set(0, false);
            drawImages(gc);
            if (!gameOverScreenLoaded) {
                gameOver();
                gameIsOver = true;
            }
        } else {
            lifeIsUsed.set(lifeToRemove, false);
            lifeToRemove--;
        }
    }

    //load game over screen
    public static void gameOver() throws IOException {
        System.out.println("game over");
        labelScore.setText("Score: 0");

        FXMLLoader loader = new FXMLLoader(ControllerGameOver.class.getResource("/gameOver.fxml"));
        Pane pane = loader.load();
        ControllerGameOver gameOver = loader.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        gameOver.score.setText("" + score);
        gameOver.highscore.setText("" + highscore);

        gameOverScreenLoaded = true;
    }

    //draw all images
    public static void drawImages(GraphicsContext gc) throws IOException {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.drawImage(player, playerX, playerY);


        for (int i = 0; i < maxLives; i++) {
            if (lifeIsUsed.get(i)) {
                gc.drawImage(lifeImage, lifeX.get(i), lifeY.get(i));
            }
        }

        if (shoot() || isShot) {
            for (int i = 0; i < maxShots; i++) {
                if (shotIsUsed.get(i)) {
                    gc.drawImage(shotImages.get(currentShot.get(i)), shotX.get(i), shotY.get(i));
                }
            }
            checkShot();
            moveShots();
        }

        if (spawnObject() || isSpawned) {
            for (int i = 0; i < maxObjects; i++) {
                if (objectIsUsed.get(i)) {
                    gc.drawImage(objectImages.get(currentObject.get(i)), objectX.get(i), objectY.get(i));
                }
            }
            checkObjects();
            moveObjects();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/game.fxml"));
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        root.setStyle("-fx-background-color: grey");
        scene = new Scene(root, WIDTH, HEIGHT);

        labelScore = ((Label) root.lookup("#labelScore"));

        setup();
        setInputs();

        //game clock
        new AnimationTimer() {
            long last_tick = 0;

            @Override
            public void handle(long now) {
                if (last_tick == 0) {
                    last_tick = now;
                    try {
                        tick(gc);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                if (now - last_tick > 1000L * 1000L * 1000L / FRAMES) {
                    last_tick = now;
                    try {
                        tick(gc);
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        Stage.setTitle("Space Invaders");
        Stage.setScene(scene);
        Stage.show();
    }

    //setup
    public void setup() {
        setupArrays();
        setupVars();
        loadImages();
        addArrayIndexes();
        setArrayData();
        setupImages();
        spawnObject();
    }
}
