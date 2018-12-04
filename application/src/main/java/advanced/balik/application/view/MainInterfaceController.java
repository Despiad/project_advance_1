package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

//TODO:BETTER DATA VISUALIZATION
public class MainInterfaceController {

    private MainApp mainApp;

    private List<Integer> data;
    private Integer step;

    @FXML
    private Label dataLabel;
    @FXML
    private Label stepLabel;
    @FXML
    private TextField inputValue;

    @FXML
    private void insert() {
        if (isInputValid(true)) {
            data.add(Integer.parseInt(inputValue.getText()));
            step++;
            refreshData();
        }
        inputValue.clear();
    }

    @FXML
    private void delete() {
        if (isInputValid(false)) {
            data.remove(data.indexOf(Integer.parseInt(inputValue.getText())));
            step++;
            refreshData();
        }
        inputValue.clear();
    }

    public MainInterfaceController() {
        data = new ArrayList<>();
        step = 0;
    }

    @FXML
    private void initialize() {
        refreshData();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void refreshData() {
        data.sort(Integer::compareTo);
        dataLabel.setText(data.toString());
        stepLabel.setText(step.toString());
    }

    private boolean notNumeral(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    /**
     * Проверяет пользовательский ввод в текстовых полях.
     *
     * @return true, если пользовательский ввод корректен
     */
    private boolean isInputValid(boolean isInsert) {
        final String input = inputValue.getText();
        String errorMessage = "";

        if (notNumeral(input)) {
            errorMessage = "No valid input!\n" +
                    "Please enter only integer number";
        } else {
            Integer element = Integer.parseInt(input);
            if (data.contains(element) && isInsert) {
                errorMessage = "This item is already in the tree.";
            }

            if (!data.contains(element) && !isInsert) {
                errorMessage = "This item isn't in the tree.";
            }

        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid field");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }

}