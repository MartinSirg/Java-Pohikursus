package clicker.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class MainViewController {

    @FXML private BorderPane root;

    @FXML private Label currentCookies;
    @FXML private Label currentShovels;
    @FXML private Label currentKolhoosid;
    @FXML private Label currentGulagTourists;

    @FXML private Button buyShovelbtn;
    @FXML private Button buyKolhoosbtn;
    @FXML private Button buyGulagbtn;

    @FXML private ImageView stalinTato;
    @FXML private ImageView gulagImg;
    @FXML private ImageView shovelImg;
    @FXML private ImageView kolhoosImg;
    private Timeline timeline1 = new Timeline();

    private IntegerProperty potatoes = new SimpleIntegerProperty(0);
    private IntegerProperty shovelsCount = new SimpleIntegerProperty(1);
    private IntegerProperty kolhooscount = new SimpleIntegerProperty(0);
    private IntegerProperty gulagCount = new SimpleIntegerProperty(0);
    private IntegerProperty shovelPrice = new SimpleIntegerProperty(10);
    private IntegerProperty kolhoosPrice = new SimpleIntegerProperty(50);
    private IntegerProperty gulagPrice = new SimpleIntegerProperty(1000);

    @FXML
    public void initialize() {
        stalinTato.setPickOnBounds(false);
        currentCookies.setWrapText(true);
        timeline1.setCycleCount(2);
        timeline1.setAutoReverse(true);

    }

    @FXML
    public void pressedStalin() {
        stalinTato.setCursor(Cursor.CLOSED_HAND);
        potatoes.set(potatoes.getValue() + shovelsCount.get());
        currentCookies.setText(String.format("Kardulaid: %d", potatoes.get()));
    }

    @FXML
    public void releasedStalin() {
        stalinTato.setCursor(Cursor.HAND);
    }

    @FXML
    public void buyShovelBtnPressed() {
        fadeImage(shovelImg);
    }

    @FXML
    public void buyKolhoosBtnPressed() {
        fadeImage(kolhoosImg);
    }

    @FXML
    public void buyGulagBtnPressed() {
        fadeImage(gulagImg);
    }

    private void fadeImage(ImageView image) {
        if (!(timeline1.getStatus() == Animation.Status.RUNNING)) {
            KeyValue opacity = new KeyValue(image.opacityProperty(), 1);
            KeyFrame frame = new KeyFrame(Duration.seconds(2), opacity);
            timeline1.getKeyFrames().add(frame);
            timeline1.play();
            timeline1.setOnFinished(event -> timeline1.getKeyFrames().remove(frame));
        }
    }
}
