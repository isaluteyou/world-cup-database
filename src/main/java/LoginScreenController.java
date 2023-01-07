import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class LoginScreenController {

    @FXML TextField usernameField;
    @FXML PasswordField passwordField;
    private String username, password;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent actionEvent) throws IOException {
        username = usernameField.getText();
        password = passwordField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuScreen.fxml"));
        root = loader.load();

        MenuSceneController menuSceneController = loader.getController();
        menuSceneController.setUsername(username);

        try {
            Connection conn = Database.getConnection(username, password);
            Database.initialize(username, password);
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();

            conn.close();
        }
        catch(SQLException sqlException) {
            JOptionPane.showMessageDialog(null, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exit() {
        Platform.exit();
        System.exit(0);
    }
}
