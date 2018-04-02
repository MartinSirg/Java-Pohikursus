package clicker.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class MainViewController {

    private static final int TIME = 5100, MAX_KOLHOOS = 50, FIFTY = 50, GULAG_PRICE = 200, GULAG_POWER = 10,
            MAX_WIDTH = 200;

    @FXML private BorderPane root;

    @FXML private TextField textField;

    @FXML private Label currentPotatoesLabel;
    @FXML private Label currentShovels;
    @FXML private Label currentKolhoosid;
    @FXML private Label currentGulagTourists;
    @FXML private Label shovelPriceLabel;
    @FXML private Label kolhoosPriceLabel;
    @FXML private Label gulagPriceLabel;

    @FXML private Button buyShovelbtn;
    @FXML private Button buyKolhoosbtn;
    @FXML private Button buyGulagbtn;
    @FXML private Button hackButton;
    @FXML private Button closeBtn;
    @FXML private Button infoBtn;

    @FXML private ImageView stalinTato;
    @FXML private ImageView gulagImg;
    @FXML private ImageView shovelImg;
    @FXML private ImageView kolhoosImg;

    private Timeline imageTimeline = new Timeline(), kolhoosTimeline = new Timeline(), gulagTimeline = new Timeline();

    private IntegerProperty potatoes = new SimpleIntegerProperty(0);
    private IntegerProperty shovelsCount = new SimpleIntegerProperty(1);
    private IntegerProperty kolhoosCount = new SimpleIntegerProperty(0);
    private IntegerProperty gulagCount = new SimpleIntegerProperty(0);
    private IntegerProperty shovelPrice = new SimpleIntegerProperty(10);
    private IntegerProperty kolhoosPrice = new SimpleIntegerProperty(FIFTY);
    private IntegerProperty gulagPrice = new SimpleIntegerProperty(GULAG_PRICE);


    @FXML
    public void initialize() {
        stalinTato.setPickOnBounds(false);

        // initial timeline configs
        imageTimeline.setCycleCount(2);
        imageTimeline.setAutoReverse(true);
        kolhoosTimeline.setCycleCount(Animation.INDEFINITE);
        gulagTimeline.setCycleCount(Animation.INDEFINITE);


        //bind price labels to price properties
        shovelPriceLabel.textProperty().bind(shovelPrice.asString());
        kolhoosPriceLabel.textProperty().bind(kolhoosPrice.asString());
        gulagPriceLabel.textProperty().bind(gulagPrice.asString());


        // potato count && shovel count
        currentPotatoesLabel.textProperty().bind(potatoes.asString("Kardulaid: %d"));
        currentShovels.textProperty().bind(shovelsCount.asString("Labidad: %d"));


        potatoes.addListener((observable, oldValue, newValue) -> changeBtnStates(newValue.intValue()));
        //kolhoos count listener
        kolhoosCount.addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() == MAX_KOLHOOS) {
                currentKolhoosid.setText(String.format("Vabatahtlikke: MAX %d", newValue.intValue()));
            } else {
                currentKolhoosid.setText(String.format("Vabatahtlikke: %d", newValue.intValue()));
            }
            updateKolhoosTimeline();
        });

        //gulag count listener
        gulagCount.addListener((observable, oldValue, newValue) -> {
            currentGulagTourists.setText(String.format("Gulagi kylalisi: %d", newValue.intValue()));
            updateGulagTimeline();
        });

        textField.textProperty().addListener(observable -> {
            if (textField.getText().equals("pepe")) {
                hackButton.setVisible(true);
            }
        });
    }

    private void changeBtnStates(Integer potatoCount) {
        // validate shovel button
        if (potatoCount < shovelPrice.get()) {
            buyShovelbtn.setDisable(true);
        } else {
            buyShovelbtn.setDisable(false);
        }
        // validate kolhoos button
        if (potatoCount < kolhoosPrice.get()) {
            buyKolhoosbtn.setDisable(true);
        } else {
            buyKolhoosbtn.setDisable(false);
        }
        // validate gulag button
        if (potatoCount < gulagPrice.get()) {
            buyGulagbtn.setDisable(true);
        } else {
            buyGulagbtn.setDisable(false);
        }
    }

    private void updateGulagTimeline() {
        if (gulagTimeline.getStatus() == Animation.Status.RUNNING) { // timeline running
            gulagTimeline.stop();
            gulagTimeline.getKeyFrames().remove(0);
        }
        gulagTimeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    potatoes.setValue(potatoes.add(GULAG_POWER * gulagCount.get()).intValue());
                    System.out.println("Got " + GULAG_POWER * gulagCount.get() + " potatoes from gulag");
                }
        ));
        gulagTimeline.play();
    }

    private void updateKolhoosTimeline() {
        if (kolhoosTimeline.getStatus() == Animation.Status.RUNNING) { // timeline running
            kolhoosTimeline.stop();
            kolhoosTimeline.getKeyFrames().remove(0);
        }
        kolhoosTimeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(TIME - 100 * kolhoosCount.get()),
                event -> {
                    potatoes.setValue(potatoes.add(shovelsCount).intValue());
                    System.out.println(String.format("Got %d potatoes from kolhoos. Currently outputs every %d ms.",
                            shovelsCount.get(), TIME - (100 * kolhoosCount.get())));
                }
        ));
        kolhoosTimeline.play();
    }

    @FXML
    public void pressedStalin() {
        stalinTato.setCursor(Cursor.CLOSED_HAND);
        potatoes.set(potatoes.add(shovelsCount).intValue());
        System.out.println(String.format("Got %d potatos by clicking on StalinTato.", shovelsCount.get()));
    }

    @FXML
    public void releasedStalin() {
        stalinTato.setCursor(Cursor.HAND);
    }

    @FXML
    public void buyShovelBtnPressed() {
        if (potatoes.get() >= shovelPrice.get()) {
            potatoes.set(potatoes.subtract(shovelPrice).intValue());
            shovelsCount.set(shovelsCount.add(1).intValue());
            shovelPrice.set(shovelPrice.add(10).intValue());
            fadeImage(shovelImg);
        }
    }

    @FXML
    public void buyKolhoosBtnPressed() {
        if (potatoes.get() >= kolhoosPrice.get() && kolhoosCount.get() < MAX_KOLHOOS) {
            potatoes.set(potatoes.subtract(kolhoosPrice).intValue());
            kolhoosCount.set(kolhoosCount.add(1).intValue());
            kolhoosPrice.set(kolhoosPrice.add(FIFTY).intValue());
            fadeImage(kolhoosImg);
        }
    }

    @FXML
    public void buyGulagBtnPressed() {
        if (potatoes.get() >= gulagPrice.get()) {
            potatoes.set(potatoes.subtract(gulagPrice).intValue());
            gulagCount.set(gulagCount.add(1).intValue());
            gulagPrice.set(gulagPrice.add(GULAG_PRICE).intValue());
            fadeImage(gulagImg);
        }
    }

    private void fadeImage(ImageView image) {
        if (!(imageTimeline.getStatus() == Animation.Status.RUNNING)) {
            KeyValue opacity = new KeyValue(image.opacityProperty(), 1);
            KeyFrame frame = new KeyFrame(Duration.seconds(2), opacity);
            imageTimeline.getKeyFrames().add(frame);
            imageTimeline.play();
            imageTimeline.setOnFinished(event -> imageTimeline.getKeyFrames().remove(frame));
        }
    }

    @FXML
    public void hackBtnPressed() {
        potatoes.setValue(potatoes.add(1000).intValue());
    }

    @FXML
    private void closeBtnPressed() {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void infoBtnPressed() {
        BorderPane info = new BorderPane();
        info.setCenter(new Label("Made by Martin Sirg. 2. April 2017"));
        Scene scene = new Scene(info, MAX_WIDTH, 100);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.show();
    }
}
