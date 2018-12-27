package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import advanced.balik.application.graph.HeapGraph;
import advanced.balik.application.graph.Style;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.*;
import java.util.stream.Collectors;

public class MainInterfaceController {

    /**
     * Длительность задержки при автоматических действиях
     */
    private static final Duration DURATION = Duration.millis(500);

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

    @FXML
    private Label stepLabel;
    @FXML
    private Label logLabel;

    /* Animation controls */
    @FXML
    private TitledPane animationPane;
    @FXML
    private TextField inputValue;
    @FXML
    private FlowPane board;
    @FXML
    private BorderPane workSpace;
    @FXML
    private VBox sideBar;
    @FXML
    private ToggleButton sideBarToggle;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ToggleButton hideConsoleToggle;
    @FXML
    private ScrollPane consoleTab;
    @FXML
    private AnchorPane lowerTab;
    @FXML
    private AnchorPane rightControlGroup;

    private Integer step;

    private List<Integer> data;

    public MainInterfaceController() {
        step = 0;
        this.data = new ArrayList<>();
    }

    @FXML
    private void initialize() {
        Group content = heapGraph.getContent();
        board.getChildren().add(content);
        logAction(Action.EMPTY.getAction());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * HEAP OPERATIONS
     **/
    @FXML
    private void insert() {
        getInput().ifPresent(value -> {
            if (!data.contains(value)) {
                heapGraph.addNode(value);
                data.add(value);
                logAction(String.format(Action.INSERT.getAction(), value));
            }
        });
    }

    @FXML
    private void getMin() {
        if (!heapGraph.isEmpty()) {
            int min = heapGraph.getMin();
            logAction(String.format(Action.MIN.getAction(), min));
        } else {
            logAction(Action.EMPTY.getAction());
        }
    }

    /**
     * Очистить дерево.
     */
    @FXML
    public void clean() {
        data.clear();
        heapGraph.clear();
        inputValue.clear();
        logAction(Action.CLEAR.getAction());
    }

    /**
     * Добавить случайное значение как новый элемент кучи.
     */
    @FXML
    public void insertRandom() {
        RANDOM.setSeed(System.currentTimeMillis());
        int randomValue = RANDOM.nextInt(UPPER_BOUND_RANDOM * 2) + LOWER_BOUND_RANDOM;
        if (!data.contains(randomValue)) {
            heapGraph.addNode(randomValue);
            data.add(randomValue);
            logAction(String.format(Action.INSERT_RANDOM.getAction(), randomValue));
        }
    }

    /**
     * HIDE PANELS
     **/
    @FXML
    public void hideSideBar() {
        if (!sideBarToggle.isSelected()) {
            rightControlGroup.getChildren().remove(sideBar);
        } else {
            rightControlGroup.getChildren().add(sideBar);
        }
    }

    @FXML
    public void hideConsole() {
        if (hideConsoleToggle.isSelected()) {
            splitPane.setDividerPositions(1.0);
            lowerTab.getChildren().remove(consoleTab);
        } else {
            splitPane.setDividerPositions(0.75);
            lowerTab.getChildren().add(consoleTab);
        }
    }

    /**
     * CONSOLE PRINTER
     **/
    private void logAction(String action) {
        System.out.println(action);
        step++;
        stepLabel.setText(step.toString());
        logLabel.setText(action);
    }

    /**
     * Общий обработчик событий клавиатуры.
     * <br>По кнопке ENTER - принимает значение из поля ввода.
     *
     * @param keyEvent событие нажатия на кнопку клавиатуры.
     */
    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        KeyCode keyCode = keyEvent.getCode();
        if (keyCode.equals(KeyCode.ENTER)) {
            insert();
        }
        //navigateToSelected();
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
        }
        inputValue.clear();
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
     * Метод для отключения и включения кнопок боковой панели.
     * Используется при запуске и остановке анимации.
     *
     * @param disable если true, то отключить кнопки боковой панели управления.
     *                Если false - то включить их.
     */
    private void disableControls(boolean disable) {
        Set<Node> controls = sideBar.getChildren().stream()
                .filter(node -> !node.getStyleClass().contains(Style.ANIMATION_BUTTON.getStyleClass()))
                .filter(node -> !node.equals(animationPane))
                .collect(Collectors.toSet());
        controls.forEach(node -> node.setDisable(disable));
    }
}