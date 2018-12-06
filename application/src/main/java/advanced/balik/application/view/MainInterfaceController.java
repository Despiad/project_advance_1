package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import advanced.balik.application.graph.HeapGraph;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

import java.util.Optional;
import java.util.Random;

public class MainInterfaceController {

    /**
     * Генератор случайных чисел.
     */
    private static final Random RANDOM = new Random();

    /**
     * Верхняя граница (не включительно) генерации случайных чисел.
     */
    private static final int UPPER_BOUND_RANDOM = 1000;

    /**
     * Нижняя граница (включительно) генерации случайных чисел.
     */
    private static final int LOWER_BOUND_RANDOM = -999;

    /**
     * Визуальное представление кучи
     */
    private final HeapGraph heapGraph = new HeapGraph();

    private MainApp mainApp;

    private Integer step;

    @FXML
    private Label stepLabel;
    @FXML
    private TextField inputValue;
    @FXML
    private FlowPane board;
//todo: FIX SPACE
    @FXML
    private void insert() {
        //todo:check input
        heapGraph.addNode(Integer.parseInt(inputValue.getText()));
    }

    /**
     * Добавить случайное значение как новый элемент кучи.
     */
    @FXML
    public void insertRandom() {
        RANDOM.setSeed(System.currentTimeMillis());
        int randomValue = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
        heapGraph.addNode(randomValue);
    }

    @FXML
    private void logAction() {

    }

    @FXML
    private void getMin() {

    }

    public MainInterfaceController() {
        step = 0;
    }

    @FXML
    private void initialize() {
        Group content = heapGraph.getContent();
        board.getChildren().add(content);
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Проверить наличие целого числа в поле ввода и вернуть его внутри контейнера Optional.
     *
     * @return Optional, возможно содержащий целое число.
     */
    private Optional<Integer> getInput() {
        String input = inputValue.getText();

        Optional<Integer> optional;
        if (input.matches("^(-?)\\d+")) {
            optional = Optional.of(Integer.parseInt(input));
        } else {
            optional = Optional.empty();
            inputValue.clear();
        }

        return optional;
    }

    /**
     * Завершить работу программы.
     */
    @FXML
    public void close() {
        Platform.exit();
    }

    /**
     * LEGACY
     **/
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
    private boolean isInputValid() {
        final String input = inputValue.getText();
        String errorMessage = "";

        if (notNumeral(input)) {
            errorMessage = "No valid input!\n" +
                    "Please enter only integer number";
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