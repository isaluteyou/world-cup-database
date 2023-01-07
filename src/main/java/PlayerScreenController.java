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
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PlayerScreenController implements Initializable {

    HashMap<String, Integer> teamsById = new HashMap<>();
    List<String> countries = List.of(
            "Ecuador", "Netherlands", "Qatar", "Senegal",
            "England", "Iran", "United States", "Wales",
            "Argentina", "Mexico", "Poland", "Saudi Arabia",
            "Australia", "Denmark", "France", "Tunisia",
            "Costa Rica", "Germany", "Japan", "Spain",
            "Belgium", "Canada", "Croatia", "Morocco",
            "Brazil", "Cameroon", "Serbia", "Switzerland",
            "Ghana", "Portugal", "South Korea", "Uruguay"
    );
    @FXML public TableView<Players> tableView;
    @FXML public TableColumn<Players, Integer> idColumn;
    @FXML public TableColumn<Players, Integer> numberColumn;
    @FXML public TableColumn<Players, String> posColumn;
    @FXML public TableColumn<Players, String> firstNameColumn;
    @FXML public TableColumn<Players, String> lastNameColumn;

    @FXML public TableColumn<Players, Integer> goalsColumn;
    @FXML public TableColumn<Players, String> dobColumn;
    @FXML public TableColumn<Players, String> clubColumn;
    @FXML public TableColumn<Players, String> teamColumn;
    @FXML public ComboBox<Integer> deleteField;
    @FXML public ComboBox<String> teamField;
    @FXML public TextField numberField, posField, goalsField, clubField, firstNameField, lastNameField, dobField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("number"));
        posColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("pos"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("lastName"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<Players, Integer>("goals"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("dob"));
        clubColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("club"));
        teamColumn.setCellValueFactory(new PropertyValueFactory<Players, String>("team"));

        ObservableList<String> playerTeams = null;
        try {
            playerTeams = getPlayerTeams();
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        teamField.setItems(playerTeams);

        ObservableList<Integer> playerIds = null;
        try {
            playerIds = getPlayerIds();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        deleteField.setItems(playerIds);

        for(int i = 0; i < 32; i++)
        {
            teamsById.put(countries.get(i), i); // Ecuador = 0, Netherlands = 1... etc - order from here https://en.wikipedia.org/wiki/2022_FIFA_World_Cup_squads
        }

        try {
            tableView.setItems(getPlayers());
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Players> getPlayers() throws IOException, SQLException {
        LinkedList<LinkedList<Object>> tableValues = Database.getData("players");
        ObservableList<Players> players = FXCollections.observableArrayList();
        for (int i = 0; i < tableValues.size(); i++) {
            Integer id = (Integer) tableValues.get(i).get(0);
            if (id == null) {
                id = 0;
            }
            Integer number = (Integer) tableValues.get(i).get(1);
            if (number == null) {
                number = 0;
            }
            String pos = (String) tableValues.get(i).get(2);
            if (pos == null) {
                pos = "";
            }
            String firstName = (String) tableValues.get(i).get(3);
            if (firstName == null) {
                firstName = "";
            }
            String lastName = (String) tableValues.get(i).get(4);
            if (lastName == null) {
                lastName = "";
            }
            Integer goals = (Integer) tableValues.get(i).get(5);
            if (goals == null) {
                goals = 0;
            }
            Timestamp dob = (Timestamp) tableValues.get(i).get(6);
            String dobString;
            if (dob == null) {
                dobString = "";
            }
            else {
                dobString = dob.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            String club = (String) tableValues.get(i).get(7);
            if (club == null) {
                club = "";
            }
            String team = (String) tableValues.get(i).get(8);
            if (team == null) {
                team = "";
            }
            players.add(new Players(id, number, pos, firstName, lastName, goals, dobString, club, team));
        }
        return players;
    }

    public void deleteButton() throws SQLException, IOException {
        String deleteText = deleteField.getValue().toString();
        if (deleteText.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the id of the row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.deleteData("players", "player_id=" + deleteText); // delete player stats by id
            tableView.setItems(getPlayers()); // update gui table
            updateGUI(); // update other gui stuff
        }
    }

    public void modifyButton() throws SQLException, IOException {
        Integer idAsInt = deleteField.getValue();
        String id = (idAsInt != null) ? idAsInt.toString() : null;
        String number = numberField.getText();
        String pos = posField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String goals = goalsField.getText();
        String dob = dobField.getText();
        String club = clubField.getText();
        String team = teamField.getValue();

        if (id == null || id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the id of the row you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.modifyData("players", "player_id=" + id, "player_id=" + id);
            if (!number.isEmpty())
                Database.modifyData("players", "player_number=" + number, "player_id=" + id);
            if (!pos.isEmpty())
                Database.modifyData("players", "fifa_rank=" + "'" + pos + "'", "player_id=" + id);
            if (!firstName.isEmpty())
                Database.modifyData("players", "player_first_name=" + "'" + firstName + "'", "player_id=" + id);
            if (!lastName.isEmpty())
                Database.modifyData("players", "player_last_name=" + "'" + lastName + "'", "player_id=" + id);
            if (!goals.isEmpty())
                Database.modifyData("players", "player_goals=" + goals, "player_id=" + id);
            if (!dob.isEmpty())
                Database.modifyData("players", "date_of_birth=" + "'" + dob + "'", "player_id=" + id);
            if (!club.isEmpty())
                Database.modifyData("players", "club=" + "'" + club + "'", "player_id=" + id);
            if (team != null && !team.isEmpty())
                Database.modifyData("players", "team=" + "'" + team + "'", "player_id=" + id);
            tableView.setItems(getPlayers()); // update gui table
            updateGUI(); // update other gui stuff
        }
    }

    public void newTeamButton() throws SQLException, IOException {
        String number = numberField.getText();
        String pos = posField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String goals = goalsField.getText();
        String dob = dobField.getText();
        String club = clubField.getText();
        String team = teamField.getValue();

        int id = teamsById.get(team) * 26 + Integer.parseInt(number);

        if (number.isEmpty() || pos.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || goals.isEmpty() || dob.isEmpty() || club.isEmpty() || team.isEmpty())
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.addData("players", Integer.toString(id), number, pos, firstName, lastName, goals, dob, club, team); // add values to database table
            tableView.setItems(getPlayers()); // update gui table
            updateGUI(); // update other gui stuff
        }
    }

    public void backButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/menuScreen.fxml"));
        Parent root = loader.load();

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

    private ObservableList<Integer> getPlayerIds() throws SQLException {
        ObservableList<Integer> playerIds = FXCollections.observableArrayList();
        LinkedList<LinkedList<Object>> data = Database.getData("players");
        for (int i = 0; i < data.size(); i++) {
            playerIds.add((Integer) data.get(i).get(0));
        }
        return playerIds;
    }

    private void updateGUI() throws SQLException, IOException {
        teamField.setItems(getPlayerTeams());
        deleteField.setItems(getPlayerIds());
    }
}
