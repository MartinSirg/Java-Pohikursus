package clicker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Clicker extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Clicker.class.getResource("view/mainview.fxml"));
            System.out.println(loader.getLocation());
            root = loader.load();
            Scene currentScene = new Scene(root);

            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(currentScene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
