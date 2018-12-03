package advanced.balik.application;

import advanced.balik.application.view.Controller;
import advanced.balik.application.view.RootLayoutController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Van Emde Boas Tree");

        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        initRootLayout();

        showMainWindow();
    }

    private void showMainWindow() {
        try {

            FXMLLoader loader = new FXMLLoader();
            AnchorPane mainWindow = loader.load(getClass().getClassLoader().getResource("MainWindow.fxml"));

            // Помещаем сведения об адресатах в центр корневого макета.
            rootLayout.setCenter(mainWindow);

            // Даём контроллеру доступ к главному приложению.
            Controller controller = loader.getController();
//            controller.setMainApp(this);

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

            // Даём контроллеру доступ к главному приложению.
            RootLayoutController controller = loader.getController();
            //controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();//TODO:LOG
        }
    }

}
