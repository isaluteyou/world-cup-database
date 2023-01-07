import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stadium {
    private final SimpleStringProperty name, location;
    private final SimpleIntegerProperty id, capacity, games;

    public Stadium(int id, String name, String location, int capacity, int games) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.games = new SimpleIntegerProperty(games);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public int getGames() {
        return games.get();
    }

    public SimpleIntegerProperty gamesProperty() {
        return games;
    }

    public void setGames(int games) {
        this.games.set(games);
    }
}
