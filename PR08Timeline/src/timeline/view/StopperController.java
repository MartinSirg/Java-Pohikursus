package timeline.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import timeline.Main;

public class StopperController {

    private static final double TIME_ACCURACY = 0.1;
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Label currentTimeLabel;
    private Main main;
    private double count = 0.0;
    private Timeline timeline = new Timeline();

    @FXML public void startButtonClicked() {
        timeline.play();
    }

    @FXML
    public void stopButtonClicked() {
        timeline.stop();
    }
    @FXML public void initialize() {
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            count += TIME_ACCURACY;
            main.setCurrentTime(String.format("%.1f", count));
            System.out.println("KEK");
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void bindTime() {
        currentTimeLabel.textProperty().bind(main.getCurrentTimeProperty());
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
