package cardsApp.cards;

import cardsApp.cards.view.BankCardsController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private ObservableList<Card> observableCards = FXCollections.observableArrayList(
            Card.cardFactory("1111111111111111", "Ago", Card.Type.DEBIT, ""),
            Card.cardFactory("8888888888888888", "Ingrid", Card.Type.CREDIT, "5000")
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/BankCards.fxml"));
            root = loader.load();
            BankCardsController controller = loader.getController();
            controller.setMain(this);
            controller.setTable(observableCards);

        } catch (IOException e) {
            root = new BorderPane();
            root.setCenter(new Label("VIGA CONTROLLERIS"));
            e.printStackTrace();
        }
        Scene currentScene = new Scene(root);
        primaryStage.setScene(currentScene);
        primaryStage.show();
    }

    public ObservableList<Card> getAllCards() {
        return observableCards;
    }


}
