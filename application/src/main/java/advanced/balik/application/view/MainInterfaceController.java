package advanced.balik.application.view;

import advanced.balik.application.MainApp;
import advanced.balik.application.graph.HeapGraph;
import advanced.balik.application.graph.Style;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class MainInterfaceController {

    private static final Logger log = Logger.getLogger(MainInterfaceController.class);

    /**
     * Длительность задержки при автоматических действиях
     */
    private static final Duration DURATION = Duration.millis(1000);

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
    private TextField turnValue;
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

    private List<Integer> turns;

    private final Animation animation = new Timeline(new KeyFrame(
            DURATION,
            actionEvent -> {
                if (!turns.isEmpty()) {
                    if (turns.get(0) == 1) {
                        insertRandom();
                    } else {
                        getMin();
                    }
                    turns.remove(0);
                } else {
                    stop();
                }
            }));

    public MainInterfaceController() {
        step = 0;
        this.data = new ArrayList<>();
        this.turns = new ArrayList<>();
    }

    @FXML
    private void initialize() {
        Group content = heapGraph.getContent();
        board.getChildren().add(content);
        animation.setCycleCount(Animation.INDEFINITE);
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
        getInput(inputValue).ifPresent(value -> {
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
        log.info(action);
        ++step;
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
    }

    /**
     * Проверить наличие целого числа в поле ввода и вернуть его внутри контейнера Optional.
     *
     * @return Optional, возможно содержащий целое число.
     */
    private Optional<Integer> getInput(TextField inputField) {
        String input = inputField.getText();

        Optional<Integer> optional;
        if (input.matches("^(-?)\\d+")) {
            optional = Optional.of(Integer.parseInt(input));
        } else {
            optional = Optional.empty();
        }
        inputField.clear();
        return optional;
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

    /**
     * Запустить или продолжить анимацию.
     */
    @FXML
    public void play() {
        animation.play();
        disableControls(true);
    }

    /**
     * Приостановить анимацию.
     */
    @FXML
    public void pause() {
        animation.pause();
        disableControls(false);
    }

    /**
     * Остановить анимацию.
     */
    @FXML
    private void stop() {
        animation.stop();
        turns.clear();
        disableControls(false);
    }

    /**
     * AUTO MODE
     **/

    @FXML
    private RadioButton insertButton;
    @FXML
    private RadioButton minButton;
    @FXML
    private RadioButton randomButton;

    private boolean onlyInsert;
    private boolean onlyMin;

    @FXML
    private void autoMode() {
        getInput(turnValue).ifPresent(value -> {
            RANDOM.setSeed(System.currentTimeMillis());
            for (int i = 0; i < value; ++i) {
                if (!onlyMin && !onlyInsert) {
                    turns.add(RANDOM.nextInt(2));
                } else if (onlyInsert) {
                    turns.add(1);
                } else {
                    turns.add(0);
                }
            }
            play();
        });
    }

    @FXML
    public void changeMode(ActionEvent event) {
        insertButton.setSelected(false);
        minButton.setSelected(false);
        randomButton.setSelected(false);

        RadioButton currButton = (RadioButton) event.getSource();
        currButton.setSelected(true);

        final String text = currButton.getText();
        onlyInsert = false;
        onlyMin = false;

        if (text.equals("Only insert")) {
            onlyInsert = true;
        }

        if (text.equals("Only min")) {
            onlyMin = true;
        }
    }
}