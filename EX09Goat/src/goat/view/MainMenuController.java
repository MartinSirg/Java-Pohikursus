package goat.view;

import goat.GoatMain;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML private Button startButton;
    private GoatMain main;


    public void setMain(GoatMain main) {
        this.main = main;
    }

    @FXML public void startButtonClicked() throws Exception {
        main.switchView();
    }
}
