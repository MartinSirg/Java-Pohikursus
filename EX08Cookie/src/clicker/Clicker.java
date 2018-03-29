package clicker;

import clicker.view.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
            MainViewController controller = loader.getController();
            Scene currentScene = new Scene(root);

            primaryStage.setScene(currentScene);
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
