import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Players {
    private final SimpleStringProperty pos, firstName, lastName, dob, club, team;
    private final SimpleIntegerProperty id, number, goals;

    public Players(int id, int number, String pos, String firstName, String lastName, int goals, String dob, String club, String team) {
        this.id = new SimpleIntegerProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.pos = new SimpleStringProperty(pos);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.goals = new SimpleIntegerProperty(goals);
        this.dob = new SimpleStringProperty(dob);
        this.club = new SimpleStringProperty(club);
        this.team = new SimpleStringProperty(team);
    }

    public String getPos() {
        return pos.get();
    }

    public SimpleStringProperty posProperty() {
        return pos;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getDob() {
        return dob.get();
    }

    public SimpleStringProperty dobProperty() {
        return dob;
    }

    public String getClub() {
        return club.get();
    }

    public SimpleStringProperty clubProperty() {
        return club;
    }

    public String getTeam() {
        return team.get();
    }

    public SimpleStringProperty teamProperty() {
        return team;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public int getGoals() {
        return goals.get();
    }

    public SimpleIntegerProperty goalsProperty() {
        return goals;
    }

    public void setPos(String pos) {
        this.pos.set(pos);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public void setClub(String club) {
        this.club.set(club);
    }

    public void setTeam(String team) {
        this.team.set(team);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public void setGoals(int goals) {
        this.goals.set(goals);
    }
}
