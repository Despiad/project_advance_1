package advanced.balik.application;

import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Starter extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Van Emde Boas Tree");

        //this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        initRootLayout();

        showPersonOverview();
    }

}
