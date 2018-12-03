package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class MainInterfaceController {

    private MainApp mainApp;

    private List<Integer> data;
    @FXML
    private Label dataLabel;


    public MainInterfaceController() {
        data = new ArrayList<Integer>();
    }

    @FXML
    private void initialize() {
        dataLabel.setText(data.toString());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }
}