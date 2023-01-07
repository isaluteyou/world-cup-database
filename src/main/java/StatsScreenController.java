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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StatsScreenController implements Initializable {

    @FXML public TableView<Stats> tableView;
    @FXML public TextField goalsField, assistsField, shotsField,
            yellowCardsField, gamesPlayedField, minutesPlayedField, redCardsField;
    @FXML public ComboBox<Integer> deleteField;
    @FXML public ComboBox<Integer> idField;
    @FXML public TableColumn<Stats, Integer> idColumn;
    @FXML public TableColumn<Stats, Integer> gamesPlayedColumn;
    @FXML public TableColumn<Stats, Integer> minutesPlayedColumn;
    @FXML public TableColumn<Stats, Integer> goalsColumn;
    @FXML public TableColumn<Stats, Integer> assistsColumn;
    @FXML public TableColumn<Stats, Integer> shotsColumn;
    @FXML public TableColumn<Stats, Integer> yellowCardsColumn;
    @FXML public TableColumn<Stats, Integer> redCardsColumn;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("id"));
        gamesPlayedColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("gamesPlayed"));
        minutesPlayedColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("minutesPlayed"));
        goalsColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("goals"));
        assistsColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("assists"));
        shotsColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("shots"));
        yellowCardsColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("yellowCards"));
        redCardsColumn.setCellValueFactory(new PropertyValueFactory<Stats, Integer>("redCards"));

        ObservableList<Integer> playerIds = null;
        try {
            playerIds = getPlayerIds("players");
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        idField.setItems(playerIds);

        ObservableList<Integer> playerStatsIds = null;
        try {
            playerStatsIds = getPlayerIds("player_stats");
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

        deleteField.setItems(playerStatsIds);

        try {
            tableView.setItems(getStats());
        }
        catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Stats> getStats() throws IOException, SQLException {
        LinkedList<LinkedList<Object>> tableValues = Database.getData("player_stats");
        ObservableList<Stats> stats = FXCollections.observableArrayList();
        for (int i = 0; i < tableValues.size(); i++) {
            Integer id = (Integer) tableValues.get(i).get(0);
            if (id == null) {
                id = 0;
            }
            Integer gamesPlayed = (Integer) tableValues.get(i).get(1);
            if (gamesPlayed == null) {
                gamesPlayed = 0;
            }
            Integer minutesPlayed = (Integer) tableValues.get(i).get(2);
            if (minutesPlayed == null) {
                minutesPlayed = 0;
            }
            Integer goals = (Integer) tableValues.get(i).get(3);
            if (goals == null) {
                goals = 0;
            }
            Integer assists = (Integer) tableValues.get(i).get(4);
            if (assists == null) {
                assists = 0;
            }
            Integer shots = (Integer) tableValues.get(i).get(5);
            if (shots == null) {
                shots = 0;
            }
            Integer yellowCards = (Integer) tableValues.get(i).get(6);
            if (yellowCards == null) {
                yellowCards = 0;
            }
            Integer redCards = (Integer) tableValues.get(i).get(7);
            if (redCards == null) {
                redCards = 0;
            }
            stats.add(new Stats(id, gamesPlayed, minutesPlayed, goals, assists, shots, yellowCards, redCards));
        }
        return stats;
    }

    public void newStatsButton() throws SQLException, IOException {
        Integer id = idField.getValue();
        String gamesPlayed = gamesPlayedField.getText();
        String minutesPlayed = minutesPlayedField.getText();
        String goals = goalsField.getText();
        String assists = assistsField.getText();
        String shots = shotsField.getText();
        String yellowCards = yellowCardsField.getText();
        String redCards = redCardsField.getText();

        if (gamesPlayed.isEmpty() || minutesPlayed.isEmpty() || goals.isEmpty() || assists.isEmpty()
                || shots.isEmpty() || yellowCards.isEmpty() || redCards.isEmpty())
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.addData("player_stats", id.toString(), gamesPlayed, minutesPlayed, goals, assists, shots, yellowCards, redCards); // add values to database table
            tableView.setItems(getStats()); // update gui table
        }
    }

    public void deleteButton() throws SQLException, IOException {
        String deleteText = deleteField.getValue().toString();
        if (deleteText.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the id of the row you want to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.deleteData("player_stats", "player_id=" + deleteText); // delete player stats by id
            tableView.setItems(getStats()); // update gui table
        }
    }

    public void modifyButton() throws SQLException, IOException {
        String id = idField.getValue().toString();
        String gamesPlayed = gamesPlayedField.getText();
        String minutesPlayed = minutesPlayedField.getText();
        String goals = goalsField.getText();
        String assists = assistsField.getText();
        String shots = shotsField.getText();
        String yellowCards = yellowCardsField.getText();
        String redCards = redCardsField.getText();

        if (id.isEmpty())
            JOptionPane.showMessageDialog(null, "Please provide the id of the row you want to modify!", "Error", JOptionPane.ERROR_MESSAGE);
        else {
            Database.modifyData("player_stats", "player_id=" + id, "player_id=" + id);
            if (!gamesPlayed.isEmpty())
                Database.modifyData("player_stats", "games_played=" + gamesPlayed, "player_id=" + id);
            if (!minutesPlayed.isEmpty())
                Database.modifyData("player_stats", "minutes_played" + minutesPlayed, "player_id=" + id);
            if (!goals.isEmpty())
                Database.modifyData("player_stats", "goals=" + goals, "player_id=" + id);
            if (!assists.isEmpty())
                Database.modifyData("player_stats", "assists=" + assists, "player_id=" + id);
            if (!shots.isEmpty())
                Database.modifyData("player_stats", "shots=" + shots, "player_id=" + id);
            if (!yellowCards.isEmpty())
                Database.modifyData("player_stats", "yellow_cards=" + yellowCards, "player_id=" + id);
            if (!redCards.isEmpty())
                Database.modifyData("player_stats", "red_cards=" + redCards, "player_id=" + id);
            tableView.setItems(getStats()); // update gui table
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

    private ObservableList<Integer> getPlayerIds(String table) throws IOException, SQLException {
        ObservableList<Integer> playerIds = FXCollections.observableArrayList();
        if (table.equals("players")) {
            LinkedList<LinkedList<Object>> data = Database.getData("players");
            for (int i = 0; i < data.size(); i++) {
                playerIds.add((Integer) data.get(i).get(0));
            }

            LinkedList<LinkedList<Object>> statsData = Database.getData("player_stats");
            for (int i = 0; i < statsData.size(); i++) {
                Integer id = (Integer) statsData.get(i).get(0);
                playerIds.remove(id);
            }
        }
        else {
            LinkedList<LinkedList<Object>> data = Database.getData("player_stats");
            for (int i = 0; i < data.size(); i++) {
                playerIds.add((Integer) data.get(i).get(0));
            }
        }
        return playerIds;
    }
}
