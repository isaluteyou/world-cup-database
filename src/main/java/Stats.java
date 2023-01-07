import javafx.beans.property.SimpleIntegerProperty;

public class Stats {
    private final SimpleIntegerProperty id, gamesPlayed, minutesPlayed, goals, assists, shots, yellowCards, redCards;

    public Stats(int id, int gamesPlayed, int minutesPlayed, int goals, int assists, int shots, int yellowCards, int redCards) {
        this.id = new SimpleIntegerProperty(id);
        this.gamesPlayed = new SimpleIntegerProperty(gamesPlayed);
        this.minutesPlayed = new SimpleIntegerProperty(minutesPlayed);
        this.goals = new SimpleIntegerProperty(goals);
        this.assists = new SimpleIntegerProperty(assists);
        this.shots = new SimpleIntegerProperty(shots);
        this.yellowCards = new SimpleIntegerProperty(yellowCards);
        this.redCards = new SimpleIntegerProperty(redCards);
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

    public int getGamesPlayed() {
        return gamesPlayed.get();
    }

    public SimpleIntegerProperty gamesPlayedProperty() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed.set(gamesPlayed);
    }

    public int getMinutesPlayed() {
        return minutesPlayed.get();
    }

    public SimpleIntegerProperty minutesPlayedProperty() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed.set(minutesPlayed);
    }

    public int getGoals() {
        return goals.get();
    }

    public SimpleIntegerProperty goalsProperty() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals.set(goals);
    }

    public int getAssists() {
        return assists.get();
    }

    public SimpleIntegerProperty assistsProperty() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists.set(assists);
    }

    public int getShots() {
        return shots.get();
    }

    public SimpleIntegerProperty shotsProperty() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots.set(shots);
    }

    public int getYellowCards() {
        return yellowCards.get();
    }

    public SimpleIntegerProperty yellowCardsProperty() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards.set(yellowCards);
    }

    public int getRedCards() {
        return redCards.get();
    }

    public SimpleIntegerProperty redCardsProperty() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards.set(redCards);
    }
}
