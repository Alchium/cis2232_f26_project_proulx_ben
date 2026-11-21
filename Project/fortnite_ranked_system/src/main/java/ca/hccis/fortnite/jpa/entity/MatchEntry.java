package ca.hccis.fortnite.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    
    @NotNull(message = "Player Name is required")
    @Size(min=1, max = 100)
    @Column(name = "playerName", nullable = false, length = 100)
    private String playerName;

    @NotNull(message = "Current Rank is required")
    @Pattern(
            regexp = "^(Bronze|Silver|Gold|Platinum|Diamond) (I|II|III)$|^(Elite|Champion|Unreal)$",
            message = "Rank must be Bronze, Silver, Gold, Platinum or Diamond (I, II or III), or Elite, Champion or Unreal"
    )
    @Size(max = 20)
    @Column(name = "currentRank", nullable = false, length = 20)
    private String currentRank;

    @NotNull(message = "Rank Progress is required")
    @DecimalMin(value = "0.0", message = "Rank progress must be at least 0")
    @DecimalMax(value = "100.0", message = "Rank progress cannot exceed 100")
    @Column(name = "rankProgress", nullable = false)
    private double rankProgress;

    @NotNull(message = "Eliminations is required")
    @Min(value = 0, message = "Eliminations must be at least 0")
    @Max(value = 100, message = "Eliminations cannot exceed 100")
    @Column(name = "eliminations")
    private Integer eliminations;

    @NotNull(message = "Placement is required")
    @Min(value = 1, message = "Placement must be at least 1")
    @Max(value = 100, message = "Placement cannot exceed 100")
    @Column(name = "placement")
    private Integer placement;

    @NotNull(message = "Win is required")
    @Column(name = "win")
    private Boolean win;

    @Size(max = 20)
    @NotNull
    @Column(name = "matchDate", nullable = false, length = 20)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be yyyy-mm-dd")
    private String matchDate;

    @Size(max = 20)
    @NotNull
    @Column(name = "matchTime", nullable = false, length = 20)
    @Pattern(regexp = "\\d{2}:\\d{2}:\\d{2}", message = "Time must be HH:MM:SS")
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

    public Integer getPlacement() {
        return placement;
    }

    public void setPlacement(Integer placement) {
        this.placement = placement;
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

    @Override
    public String toString() {
        return  "matchId = " + matchId +
                " | playerName = '" + playerName + '\'' +
                " | currentRank = '" + currentRank + '\'' +
                " | rankProgress = " + rankProgress +
                " | eliminations = " + eliminations +
                " | placement = " + placement +
                " | win = " + win +
                " | matchDate = '" + matchDate + '\'' +
                " | matchTime = '" + matchTime + '\'';
    }
}