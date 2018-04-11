package goat;

import goat.view.GameController;
import goat.view.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GoatMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GoatMain.class.getResource("view/MainMenu.fxml"));
        BorderPane root = loader.load();
        Scene currentScene = new Scene(root);

        MainMenuController controller= loader.getController();
        controller.setMain(this);

        primaryStage.setScene(currentScene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    public void switchView() throws Exception{
        System.out.println("Kek");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(GoatMain.class.getResource("view/Game.fxml"));
        Pane root = loader.load();
        Scene currentScene = new Scene(root);

        GameController controller= loader.getController();
        controller.setScene(currentScene);
        controller.setRoot(root);

        mainStage.setScene(currentScene);
        mainStage.show();
    }
}
