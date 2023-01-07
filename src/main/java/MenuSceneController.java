import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuSceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }
    public void teamsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/teamsTable.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void playersButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/playersScreen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void playerStatsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/statsScreen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void stadiumsButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/stadiumsScreen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButton(ActionEvent actionEvent) throws IOException, SQLException {
        username = "";
        Database.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/loginScreen.fxml"));
        root = loader.load();
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);

        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        JOptionPane.showMessageDialog(null, "Successfully logged out!", "World Cup 2022", JOptionPane.INFORMATION_MESSAGE);
    }

    public void exitButton() throws SQLException {
        Platform.exit();
        Database.close();
        System.exit(0);
    }

    public String getUsername() {
        return username;
    }
}
