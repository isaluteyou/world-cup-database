import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StadiumScreenController implements Initializable {
    @FXML public TableView<Stadium> tableView;
    @FXML public TableColumn<Stadium, Integer> idColumn;
    @FXML public TableColumn<Stadium, String> nameColumn;
    @FXML public TableColumn<Stadium, String> locationColumn;
    @FXML public TableColumn<Stadium, Integer> capacityColumn;
    @FXML public TableColumn<Stadium, Integer> gamesColumn;
    @FXML public TextField nameField, locationField, capacityField, gamesField;
    @FXML public ComboBox<Integer> idSelector;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int stadiumMaxId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Stadium, String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Stadium, String>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>("capacity"));
        gamesColumn.setCellValueFactory(new PropertyValueFactory<Stadium, Integer>("games"));

        // Updating the id selector
        ObservableList<Integer> stadiumIds;
        try {
            stadiumIds = getStadiumIds();
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        idSelector.setItems(stadiumIds);
        stadiumMaxId = stadiumIds.size(); // Max ID

        try {
            tableView.setItems(getStadiums());
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Stadium> getStadiums() throws IOException, SQLException {
        LinkedList<LinkedList<Object>> tableValues = Database.getData("stadiums");
        ObservableList<Stadium> stadiums = FXCollections.observableArrayList();
        for(int i = 0; i < tableValues.size(); i ++) {
            stadiums.add(new Stadium((Integer) tableValues.get(i).get(0),
                    (String)  tableValues.get(i).get(1),
                    (String) tableValues.get(i).get(2),
                    (Integer) tableValues.get(i).get(3),
                    (Integer)  tableValues.get(i).get(4)));
        }
        return stadiums;
    }

    public void newStadiumButton() throws SQLException, IOException {
        String name = nameField.getText();
        String location = locationField.getText();
        String capacity = capacityField.getText();
        String games = gamesField.getText();

        if(name.isEmpty() || location.isEmpty() || capacity.isEmpty() || games.isEmpty())
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            stadiumMaxId ++;
            String id = Integer.toString(stadiumMaxId); // convert max id to string
            Database.addData("stadiums", id, name, location, capacity, games); // add values to database table
            tableView.setItems(getStadiums()); // update gui table
            idSelector.setItems(getStadiumIds()); // update id selector
        }
    }

    public void deleteButton() throws SQLException, IOException {
        String id = idSelector.getValue().toString();
        if(id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the id of the stadium you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.deleteData("stadiums", "stadium_id=" + id);
            stadiumMaxId --;
            tableView.setItems(getStadiums()); // update gui table
            idSelector.setItems(getStadiumIds()); // update the id selector
        }
    }

    public void modifyButton() throws SQLException, IOException {
        String id = idSelector.getValue().toString();
        String name = nameField.getText();
        String location = locationField.getText();
        String capacity = capacityField.getText();
        String games = gamesField.getText();

        if(id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please select the id of the stadium you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            if(!name.isEmpty())
                Database.modifyData("stadiums",  "stadium_name=" + "'" + name + "'", "stadium_id=" + id);
            if(!location.isEmpty())
                Database.modifyData("stadiums",  "location=" + "'" + location + "'", "stadium_id=" + id);
            if(!capacity.isEmpty())
                Database.modifyData("stadiums",  "capacity=" + capacity, "stadium_id=" + id);
            if(!games.isEmpty())
                Database.modifyData("stadiums",  "games=" + games, "stadium_id=" + id);
            tableView.setItems(getStadiums()); // update gui table
            idSelector.setItems(getStadiumIds()); // update the id selector
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuScreen.fxml"));
        root = loader.load();

        MenuSceneController menuSceneController = loader.getController();
        menuSceneController.setUsername(menuSceneController.getUsername());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Integer> getStadiumIds() throws IOException, SQLException {
        ObservableList<Integer> stadiumIds = FXCollections.observableArrayList();
        LinkedList<LinkedList<Object>> data = Database.getData("stadiums");
        for (int i = 0; i < data.size(); i++) {
            stadiumIds.add((Integer) data.get(i).get(0));
        }
        return stadiumIds;
    }
}
