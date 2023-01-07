import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Team {

    private final SimpleStringProperty name, association, group;
    private final SimpleIntegerProperty rank, pot;

    public Team(String name, String association, int fifa_rank, int pot, String group) {
        this.name = new SimpleStringProperty(name);
        this.association = new SimpleStringProperty(association);
        this.rank = new SimpleIntegerProperty(fifa_rank);
        this.pot = new SimpleIntegerProperty(pot);
        this.group = new SimpleStringProperty(group);
    }

    // Getters
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getAssociation() {
        return association.get();
    }

    public SimpleStringProperty associationProperty() {
        return association;
    }

    public String getGroup() {
        return group.get();
    }

    public SimpleStringProperty groupProperty() {
        return group;
    }

    public int getRank() {
        return rank.get();
    }

    public SimpleIntegerProperty rankProperty() {
        return rank;
    }

    public int getPot() {
        return pot.get();
    }

    public SimpleIntegerProperty potProperty() {
        return pot;
    }

    // Setters
    public void setName(String name) {
        this.name.set(name);
    }

    public void setAssociation(String association) {
        this.association.set(association);
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public void setRank(int fifa_rank) {
        this.rank.set(fifa_rank);
    }

    public void setPot(int pot) {
        this.pot.set(pot);
    }
}
