import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeamsScreenController implements Initializable {

    @FXML public TableView<Team> tableView;
    @FXML public TableColumn<Team, String> nameColumn;
    @FXML public TableColumn<Team, String> associationColumn;
    @FXML public TableColumn<Team, Integer> rankColumn;
    @FXML public TableColumn<Team, Integer> potColumn;
    @FXML public TableColumn<Team, String> groupColumn;
    @FXML public TextField nameField, associationField, rankField, potField, groupField;
    @FXML public ComboBox<String> deleteField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
        associationColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("association"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("rank"));
        potColumn.setCellValueFactory(new PropertyValueFactory<Team, Integer>("pot"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<Team, String>("group"));

        ObservableList<String> playerTeams;
        try {
            playerTeams = getPlayerTeams();
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        deleteField.setItems(playerTeams);

        try {
            tableView.setItems(getTeams());
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Team> getTeams() throws IOException, SQLException {
        LinkedList<LinkedList<Object>> tableValues = Database.getData("teams");
        ObservableList<Team> teams = FXCollections.observableArrayList();
        for(int i = 0; i < tableValues.size(); i ++) {
            teams.add(new Team((String) tableValues.get(i).get(0),
                    (String)  tableValues.get(i).get(1),
                    (Integer) tableValues.get(i).get(2),
                    (Integer) tableValues.get(i).get(3),
                    (String)  tableValues.get(i).get(4)));
        }
        return teams;
    }

    public void newTeamButton() throws SQLException, IOException {
        String name = nameField.getText();
        String association = associationField.getText();
        String rank = rankField.getText();
        String pot = potField.getText();
        String group = groupField.getText();
        if(name.isEmpty() || association.isEmpty() || rank.isEmpty() || pot.isEmpty() || group.isEmpty())
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.addData("teams", name, association, rank, pot, group); // add values to database table
            tableView.setItems(getTeams()); // update gui table
            deleteField.setItems(getPlayerTeams());
        }
    }

    public void deleteButton() throws SQLException, IOException {
        String deleteText = deleteField.getValue();
        if(deleteText.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the name of the row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.deleteData("teams", "team_name=" + "'" + deleteText + "'");
            tableView.setItems(getTeams()); // update gui table
            deleteField.setItems(getPlayerTeams());
        }
    }

    public void modifyButton() throws SQLException, IOException {
        String name = nameField.getText();
        String association = associationField.getText();
        String rank = rankField.getText();
        String pot = potField.getText();
        String group = groupField.getText();

        if(name.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the name of the row you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            if(!association.isEmpty())
                Database.modifyData("teams",  "association=" + "'" + association + "'", "team_name=" + "'" + name + "'");
            if(!rank.isEmpty())
                Database.modifyData("teams",  "fifa_rank=" + rank, "team_name=" + "'" + name + "'");
            if(!pot.isEmpty())
                Database.modifyData("teams",  "pot=" + pot, "team_name=" + "'" + name + "'");
            if(!group.isEmpty())
                Database.modifyData("teams",  "team_group=" + "'" + group + "'", "team_name=" + "'" + name + "'");
            tableView.setItems(getTeams()); // update gui table
            deleteField.setItems(getPlayerTeams());
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

    private ObservableList<String> getPlayerTeams() throws IOException, SQLException {
        ObservableList<String> playerTeams = FXCollections.observableArrayList();
        LinkedList<LinkedList<Object>> data = Database.getData("teams");
        for (int i = 0; i < data.size(); i++) {
            playerTeams.add((String) data.get(i).get(0));
        }
        return playerTeams;
    }
}
