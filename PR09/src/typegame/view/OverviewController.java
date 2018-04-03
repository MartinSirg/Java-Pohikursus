package typegame.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import typegame.Main;

import java.util.ArrayList;
import java.util.List;

public class OverviewController {

    @FXML private BorderPane window;
    @FXML private Label symbol1;
    @FXML private Label symbol2;
    @FXML private Label symbol3;
    private List<String> symbols = new ArrayList();
    private Main main;

    @FXML
    public void initialize() {
        Stage stage = (Stage) window.getScene().getWindow();
        System.out.println(scene);
        /*scene.setOnKeyPressed(event -> {
            System.out.println("kekee");
        });*/

    }

    @FXML
    public void inWindowKeyPressed() {
        System.out.println("kek");
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
