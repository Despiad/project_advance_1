package advanced.balik.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    private static Stage primaryStage;
    private static BorderPane rootLayout;


    /**
     * Возвращает главную сцену.
     *
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    //TODO: ARROW BUTTON IMG and icon

    /**
     * Значок приложения.
     */
    private static final Image ICON = new Image(Starter.class.getResourceAsStream("/icon.png"));

    /**
     * Заголовок окна.
     */
    private static final String TITLE = "Van Emde Boas Tree";


    @Override
    public void start(Stage primaryStage) {
        Starter.primaryStage = primaryStage;
        Starter.primaryStage.setTitle(TITLE);
        Starter.primaryStage.getIcons().add(ICON);

        initRootLayout();

        showMainWindow();
    }

    private void showMainWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            AnchorPane mainWindow = loader.load(getClass().getClassLoader().getResource("MainWindow.fxml"));

            rootLayout.setCenter(mainWindow);
        } catch (IOException e) {
            e.printStackTrace();//TODO:LOG
        }
    }

    private void initRootLayout() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();

            rootLayout = loader.load(getClass().getClassLoader().getResource("RootLayout.fxml"));

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();//TODO:LOG
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
