import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("loginScreen.fxml")));
            Parent menu = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menuScreen.fxml")));
            primaryStage.setTitle("World Cup 2022");
            String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();

            Scene loginScene = new Scene(root);
            loginScene.getStylesheets().add(css);

            Scene menuScene = new Scene(menu);
            menuScene.getStylesheets().add(css);

            primaryStage.setResizable(false);
            primaryStage.setScene(loginScene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String... args) {
        launch(args);
    }
}
