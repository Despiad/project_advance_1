package advanced.balik.application;

import advanced.balik.application.view.MainInterfaceController;
import advanced.balik.application.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;


    /**
     * Возвращает главную сцену.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    //TODO: ARROW BUTTON IMG and icon

    private final static String ROOT_PATH = "/fxml/RootLayout.fxml";

    private final static String MAIN_PATH = "/fxml/MainWindow.fxml";

    private final static String ICON_PATH = "/img/icon.png";

    /**
     * Минимальная высота окна в пикселях.
     */
    private static final int MIN_HEIGHT = 660;

    /**
     * Минимальная ширина окна в пикселях.
     */
    private static final int MIN_WIDTH = 700;

    /**
     * Значок приложения.
     */
    private static final Image ICON = new Image(MainApp.class.getResourceAsStream(ICON_PATH));

    /**
     * Заголовок окна.
     */
    private static final String TITLE = "Leftist Heap";


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.getIcons().add(ICON);
        this.primaryStage.setMinHeight(MIN_HEIGHT);
        this.primaryStage.setMinWidth(MIN_WIDTH);

        initRootLayout();

        showMainWindow();
    }

    private void showMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_PATH));
            AnchorPane mainWindow = loader.load();

            MainInterfaceController controller = loader.getController();
            controller.setMainApp(this);

            rootLayout.setCenter(mainWindow);
        } catch (IOException e) {
            e.printStackTrace();//TODO:LOG
        }
    }

    private void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ROOT_PATH));

            rootLayout = loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();//TODO:LOG
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
