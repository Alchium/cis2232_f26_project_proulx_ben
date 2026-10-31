package ca.hccis.fortnite.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "matchentry")
public class MatchEntry {

    //This is added to this jpa entity class as an example of @Transient.  If you add attributes to this
    //class that are not to be persisted to the database, they should be @Transient.
    @Transient
    private String skillsCode;

    public MatchEntry() {
        this.eliminations = 0;
        this.wins = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(min=1, max = 10)
    @NotNull
    @Column(name = "playerName", nullable = false, length = 100)
    private String playerName;

    @Size(max = 20)
    @NotNull
    @Column(name = "currentRank", nullable = false, length = 20)
    private String currentRank;

    @Size( max = 5)
    @NotNull
    @Column(name = "rankProgress", nullable = false, length = 5)
    private double athleteName;

    @Column(name = "eliminations")
    private Integer eliminations;

    @Column(name = "wins")
    private Integer wins;

    @Size(max = 20)
    @NotNull
    @Column(name = "lastUpdated", nullable = false, length = 20)
    private String lastUpdated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCurrentRank() {
        return currentRank;
    }

    public void setCurrentRank(String currentRank) {
        this.currentRank = currentRank;
    }

    public double getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(double athleteName) {
        this.athleteName = athleteName;
    }

    public Integer getEliminations() {
        return eliminations;
    }

    public void setEliminations(Integer eliminations) {
        this.eliminations = eliminations;
    }

    public Integer getWins() {
        return wins;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}