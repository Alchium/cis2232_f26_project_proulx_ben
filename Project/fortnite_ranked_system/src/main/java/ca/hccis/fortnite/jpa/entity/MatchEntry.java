package ca.hccis.fortnite.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "matchentry")
public class MatchEntry {

    //This is added to this jpa entity class as an example of @Transient.  If you add attributes to this
    //class that are not to be persisted to the database, they should be @Transient.
    @Transient
    private String skillsCode;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matchId", nullable = false)
    private Integer matchId;

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
    private double rankProgress;

    @Column(name = "eliminations")
    private Integer eliminations;

    @Column(name = "win")
    private Boolean win;

    @Size(max = 20)
    @NotNull
    @Column(name = "matchDate", nullable = false, length = 20)
    private String matchDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "matchTime", nullable = false, length = 20)
    private String matchTime;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
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

    public double getRankProgress() {
        return rankProgress;
    }

    public void setRankProgress(double rankProgress) {
        this.rankProgress = rankProgress;
    }

    public Integer getEliminations() {
        return eliminations;
    }

    public void setEliminations(Integer eliminations) {
        this.eliminations = eliminations;
    }

    public Boolean getWin() {
        return win;
    }

    public void setWin(Boolean win) {
        this.win = win;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

}