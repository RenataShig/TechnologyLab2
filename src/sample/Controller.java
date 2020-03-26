package sample;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Integer> listView;

    @FXML
    public Button addBtn;

    @FXML
    public Button removeBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        listView.getItems().addListener((ListChangeListener<Integer>) change -> listViewChanged());
    }

    private void loadData() {
        Data data = FileReaderWriter.read();
        this.listView.setItems(FXCollections.observableList(data.getListValue()));
    }

    private void listViewChanged() {
        saveChanges();
    }

    private void saveChanges() {
        List<Integer> values = new ArrayList<>(this.listView.getItems());
        Data data = new Data(values);
        FileReaderWriter.write(data);
    }

    public void addClick(ActionEvent actionEvent) {
        int countElement = this.listView.getItems().size();
        List<Integer> valueList = new ArrayList<>();
        for (int i = 0; i <= countElement; i++) {
            valueList.add(i);
        }
        ObservableList<Integer> comboBoxList = FXCollections.observableList(valueList);
        showDialog("Добавить значение в список", "Введите значение: ", comboBoxList);
        //result.ifPresent(value -> listView.getItems().add(Integer.parseInt(value)));
        listViewChanged();
    }

    public void removeClick(ActionEvent actionEvent) {
        int selectedElementIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedElementIndex == -1) {
            return;
        }
        listView.getItems().remove(selectedElementIndex);
        listViewChanged();
    }

    private void showDialog(String title, String content, ObservableList<Integer> dialogObservableList) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addValueView.fxml"));
            Parent parent = fxmlLoader.load();

            AddValueController dialogController = fxmlLoader.<AddValueController>getController();
            dialogController.setDialogObservableList(dialogObservableList);
            dialogController.setList(listView.getItems());
            Scene scene = new Scene(parent, 196, 95);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception ex) {

        }
    }
}
