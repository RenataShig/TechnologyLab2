package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class AddValueController implements Initializable {
    @FXML
    public TextField inputTextField;

    @FXML
    public ComboBox positionComboBox;

    @FXML
    public Pane dialogPane;

    @FXML
    public Button applyBtn;

    @FXML
    public Button cancelBtn;

    private ObservableList<Integer> dialogObservableList;

    private ObservableList<Integer> itemsList;



    public ObservableList<Integer> getDialogObservableList() {
        return dialogObservableList;
    }

    public void setDialogObservableList(ObservableList<Integer> dialogObservableList) {
        this.dialogObservableList = dialogObservableList;
        this.positionComboBox.setItems(dialogObservableList);
        this.positionComboBox.setValue(dialogObservableList.get(0));
    }

    public Pane getDialogPane() {
        return dialogPane;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        inputTextField.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
    }

    public void applyClick(ActionEvent actionEvent) {
        int value = Integer.parseInt(inputTextField.getText());
        int position = (int) positionComboBox.getValue();
        InsertValue insertValue = new InsertValue(value, position);
        itemsList.add(insertValue.getPosition(), insertValue.getValue());
        closeStage(actionEvent);
    }

    public void cancelClick(ActionEvent actionEvent) {
        closeStage(actionEvent);
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setList(ObservableList<Integer> items) {
        this.itemsList = items;
    }
}
