package typegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import typegame.view.OverviewController;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene;
        OverviewController controller;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Overview.fxml"));
        Pane root = loader.load();
        controller = loader.getController();
        scene = new Scene(root, 1200, 750);
        primaryStage.setScene(scene);
        primaryStage.show();
        controller.setScene(scene, root);

    }
}
