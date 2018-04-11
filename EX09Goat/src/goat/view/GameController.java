package goat.view;


import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GameController {

    private static final int MIN_HEIGHT = -325, MAX_HEIGHT = 325, MIN_WIDTH = -550, MAX_WIDTH = 550;

    private Scene scene;
    private Pane root;

    private Image goatDown = new Image(GameController.class.getResourceAsStream("images/kitsDown.png"));
    private Image goatUp = new Image(GameController.class.getResourceAsStream("images/kitsUp.png"));
    private Image goatLeft = new Image(GameController.class.getResourceAsStream("images/kitsLeft.png"));
    private Image goatRight = new Image(GameController.class.getResourceAsStream("images/kitsRight.png"));
    private Image goatDownRight = new Image(GameController.class.getResourceAsStream("images/kitsDownRight.png"));
    private Image goatDownLeft = new Image(GameController.class.getResourceAsStream("images/kitsDownLeft.png"));
    private Image goatUpRight = new Image(GameController.class.getResourceAsStream("images/kitsUpRight.png"));
    private Image goatUpLeft = new Image(GameController.class.getResourceAsStream("images/kitsUpLeft.png"));
    private Image mine = new Image(GameController.class.getResourceAsStream("images/miin.png"));

    private ObservableList<String> move = FXCollections.observableArrayList();
    private List<ImageView> mines = new ArrayList<>();
    private IntegerProperty score = new SimpleIntegerProperty(0), speed = new SimpleIntegerProperty(2);
    private Random random = new Random();

    @FXML private Label scoreLabel;
    @FXML private Label speedLabel;
    @FXML private Button speedUpBtn;
    @FXML private Button speedDownBtn;
    @FXML private ImageView goat;

    @FXML
    public void initialize() {
        scoreLabel.textProperty().bind(score.asString("Score: %d"));
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(
                        Duration.millis(5000),
                        event -> spawnMine()
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        speedLabel.textProperty().bind(speed.asString("Speed: %d"));
    }

    public void setRoot(Pane pane) {
        root = pane;
        spawnMine();
    }


    public void setScene(Scene scene) {
        this.scene = scene;
        start();

    }

    private void changePic(Image goatPic) {
        if (!goat.getImage().equals(goatPic)) {
            goat.setImage(goatPic);
        }
    }

    private void start() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveGoat(speed.get());
                for (ImageView img: mines) {
                    if (goat.getBoundsInParent().intersects(img.getBoundsInParent())) {
                        mines.remove(img);
                        root.getChildren().remove(img);
                        score.setValue(score.add(1).intValue());
                        break;
                    }
                }
            }
        }.start();

        scene.setOnKeyPressed(event -> {
            if (!move.contains(event.getText())) {
                move.add(event.getText());
            }
        });

        scene.setOnKeyReleased(event -> move.remove(event.getText()));
    }

    private void moveGoat() {
        if (move.size() == 0) {
            return;
        }
        //set right image
        if (move.size() == 2 && move.contains("a") && move.contains("w")) changePic(goatUpLeft);
        if (move.size() == 2 && move.contains("a") && move.contains("s")) changePic(goatDownLeft);
        if (move.size() == 2 && move.contains("s") && move.contains("d")) changePic(goatDownRight);
        if (move.size() == 2 && move.contains("d") && move.contains("w")) changePic(goatUpRight);
        if (move.size() == 1 && move.contains("w")) changePic(goatUp);
        if (move.size() == 1 && move.contains("s")) changePic(goatDown);
        if (move.size() == 1 && move.contains("d")) changePic(goatRight);
        if (move.size() == 1 && move.contains("a")) changePic(goatLeft);

        //move goat
        if (move.contains("s") && goat.getTranslateY() + 1 < MAX_HEIGHT) {
            goat.setTranslateY(goat.getTranslateY() + 1);
        }
        if (move.contains("w") && goat.getTranslateY() - 1 > MIN_HEIGHT) {
            goat.setTranslateY(goat.getTranslateY() - 1);
        }
        if (move.contains("a") && goat.getTranslateX() - 1 > MIN_WIDTH) {
            goat.setTranslateX(goat.getTranslateX() - 1);
        }
        if (move.contains("d") && goat.getTranslateX() + 1 < MAX_WIDTH) {
            goat.setTranslateX(goat.getTranslateX() + 1);
        }

        //System.out.println("X: " + goat.getTranslateX() + "; Y: " + goat.getTranslateY());
    }

    private void spawnMine() {
        ImageView newMineImgView = new ImageView(mine);
        int posX = random.nextInt(MAX_WIDTH * 2 + 20);
        int posY = random.nextInt(MAX_HEIGHT * 2 + 20);
        newMineImgView.setLayoutX(posX);
        newMineImgView.setLayoutY(posY);

        while (scoreLabel.getBoundsInParent().intersects(newMineImgView.getBoundsInParent())
                || goat.getBoundsInParent().intersects(newMineImgView.getBoundsInParent())) {
            int newPosX = random.nextInt(MAX_WIDTH * 2 + 20);
            int newPosY = random.nextInt(MAX_HEIGHT * 2 + 20);
            newMineImgView.setLayoutX(newPosX);
            newMineImgView.setLayoutY(newPosY);
        }

        root.getChildren().add(newMineImgView);
        mines.add(newMineImgView);
    }

    private void moveGoat(int speed) {
        for (int i = 0; i < speed; i++) {
            moveGoat();
        }
    }

    public void speedUpBtnClicked() {
        speed.setValue(speed.add(1).intValue());
    }

    public void speedDownBtnClicked() {
        if (speed.get() != 1) {
            speed.setValue(speed.subtract(1).intValue());
        }
    }
}
