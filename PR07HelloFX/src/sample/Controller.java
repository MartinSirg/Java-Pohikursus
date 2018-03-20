package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Label textLabel;

    @FXML
    private Button buttonShowLabel;

    @FXML
    private TextField textField;

    public void showLabelClick() {
        textLabel.setVisible(true);
    }

    public void changeLabel() {
        textLabel.setText(textField.getText());
    }
}
