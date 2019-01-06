package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class RootLayoutController {

    private MainApp mainApp;

    @FXML
    private void initialize() {
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp=mainApp;
    }

    /**
     * Завершить работу программы.
     */
    @FXML
    public void close() {
        Platform.exit();
    }
}
