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

    @FXML
    private Label dataLabel;
    @FXML
    private TextField inputValue;

    @FXML
    private void insert() {
        if (isInputValid()) {
            data.add(Integer.parseInt(inputValue.getText()));
            refreshData();
        }
        inputValue.clear();
    }

    public MainInterfaceController() {
        data = new ArrayList<Integer>();
    }

    @FXML
    private void initialize() {
        refreshData();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void refreshData() {
        dataLabel.setText(data.toString());
    }

    private boolean notNumeral(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        int commaCount = 0;
        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '.') {
                commaCount++;
            }
            if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != '-')
                    || commaCount > 1) {
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
    private boolean isInputValid() {
        String errorMessage = "";

        if (notNumeral(inputValue.getText())) {
            errorMessage = "No valid input!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }

    }

}