package cardsApp.cards.view;

import cardsApp.cards.Card;
import cardsApp.cards.CreditCard;
import cardsApp.cards.Main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;


public class BankCardsController extends TextFieldTableCell{
    @FXML private TableView tableView;

    @FXML private TableColumn cardColumn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn limitColumn;
    @FXML private Button newButton;
    @FXML private RadioButton  debitButton;
    @FXML private RadioButton creditButton;
    @FXML private TextField numberTextField;
    @FXML private TextField ownerTextField;
    @FXML private TextField limitTextField;
    @FXML private ToggleGroup radioButtonsGroup;
    private Card.Type selected;
    private Main main;

    @Override
    public void startEdit() {
        TableRow<Card> tableRow = getTableRow();
        Card card = tableRow.getItem();
        if (card instanceof CreditCard) {
            super.startEdit();
        }
    }
    @FXML public void initialize() {
        creditButton.setSelected(true);
        tableView.setPlaceholder(new Label("No data yet."));
        //defineerin ära veergude tüübid
        cardColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("cardNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("owner"));
        limitColumn.setCellValueFactory(new PropertyValueFactory<Card, String>("limit"));
        //luban editida nime
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit( //vajab selgitust
                (EventHandler<TableColumn.CellEditEvent<Card, String>>) t -> (t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setOwner(t.getNewValue())
        );
    }

    public void setTable(ObservableList<Card> o) {
        tableView.setItems(o);
    }


    @FXML public void newButtonClicked() {
        main.getAllCards().add(Card.cardFactory(
                numberTextField.getText(),
                ownerTextField.getText(),
                selected,
                limitTextField.getText()
        ));
        System.out.println(main.getAllCards());

    }

    @FXML public void creditButtonClicked() {
        selected = Card.Type.CREDIT;
        limitTextField.setDisable(false);
    }

    @FXML public void debitButtonClicked() {
        selected = Card.Type.DEBIT;
        limitTextField.setText("");
        limitTextField.setDisable(true);
    }

    @FXML public void setMain(Main main) {
        this.main = main;
    }

}
