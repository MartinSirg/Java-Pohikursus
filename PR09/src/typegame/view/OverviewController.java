package typegame.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class OverviewController {

    private static final String ALL_SYMBOLS = "abcdefghijklmnopqrstuvwxyz";
    private static final int ANIMATION_TIME = 200, TFIVE = 25, SFIVE = 75;
    private static final double MAX_OPACITY = 0.8;

    @FXML private Label symbol1;
    @FXML private Label symbol2;
    @FXML private Label symbol3;
    @FXML private Label scoreLabel;
    @FXML private ImageView redPop;
    @FXML private ImageView greenPop;

    private List<Label> symbols;
    private Random random = new Random();
    private Timeline timelineRed = new Timeline(), timelineGreen = new Timeline();

    private IntegerProperty score = new SimpleIntegerProperty(0);

    private Scene scene;

    @FXML
    public void initialize() {
        initTimelines();

        symbols = new ArrayList<>();
        symbols.add(symbol1);
        symbols.add(symbol2);
        symbols.add(symbol3);

        scoreLabel.textProperty().bind(score.asString("Score: %d"));
    }

    private void initTimelines() {
        timelineRed.setCycleCount(2);
        timelineRed.setAutoReverse(true);
        KeyValue redOpacity = new KeyValue(redPop.opacityProperty(), MAX_OPACITY);
        KeyFrame redFrame = new KeyFrame(Duration.millis(ANIMATION_TIME), redOpacity);
        timelineRed.getKeyFrames().add(redFrame);

        timelineGreen.setCycleCount(2);
        timelineGreen.setAutoReverse(true);
        KeyValue greenOpacity = new KeyValue(greenPop.opacityProperty(), MAX_OPACITY);
        KeyFrame greenFrame = new KeyFrame(Duration.millis(ANIMATION_TIME), greenOpacity);
        timelineGreen.getKeyFrames().add(greenFrame);
    }


    public void setScene(Scene scene, Pane pane) {
        this.scene = scene;
        scene.setOnKeyPressed(event -> {
            Map<Label, String> symbolMap = new HashMap<>();
            for (Label label: symbols) {
                symbolMap.put(label, label.getText());
            }
            boolean hit = false;
            for (Map.Entry entry: symbolMap.entrySet()) {
                if (entry.getValue().equals(event.getText())) {
                    if (timelineGreen.getStatus() != Animation.Status.RUNNING) {
                        timelineGreen.play();
                    }
                    System.out.println("HIT");
                    updateLabel((Label) entry.getKey());
                    hit = true;
                    score.setValue(score.add(1).intValue());
                    break;
                }
            }
            if (!hit) {
                if (timelineRed.getStatus() != Animation.Status.RUNNING) {
                    timelineRed.play();
                }
                score.setValue(score.subtract(2).intValue());
                System.out.println("MISS");
            }
        });

    }

    private void updateLabel(Label label) {
        int index = random.nextInt(ALL_SYMBOLS.length()); // [0-22] + 3 == [3-25]
        label.setText(ALL_SYMBOLS.substring(index, index + 1));
        List<Label> allLabels = new ArrayList<>();
        allLabels.add(symbol1);
        allLabels.add(symbol2);
        allLabels.add(symbol3);
        allLabels.add(scoreLabel);
        allLabels.remove(label);
        boolean loop = true;
        while (loop) {
            label.setLayoutX(random.nextInt((int) scene.getWidth() - SFIVE) + TFIVE);
            label.setLayoutY(random.nextInt((int) scene.getHeight() - SFIVE) + TFIVE);
            for (Label label1: allLabels) {
                if (!label.getBoundsInParent().intersects(label1.getBoundsInParent())) {
                    loop = false;
                }
            }
        }
    }
}


