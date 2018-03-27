package timeline;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import timeline.view.StopperController;

import java.io.IOException;

public class Main extends Application {

    private StringProperty currentTime;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root;
        StopperController controller = new StopperController();
        try {
            currentTime = new SimpleStringProperty("0");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/Stopper.fxml"));
            root = loader.load();
            controller = loader.getController();
            controller.setMain(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Äge stopper");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.bindTime();

    }

    public StringProperty getCurrentTimeProperty() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime.set(currentTime);
    }
}
