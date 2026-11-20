package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.entity.PlayerStats;
import ca.hccis.fortnite.entity.ReportInput;
import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class to hold business logic needed for the report.
 *
 * @author BP
 * @since 20261030
 */
public class ReportBO {

    public static void processPlayerScoreReport(ReportInput reportInput) {

        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<MatchEntry> theList = matchEntryBO.processSelectAllByPlayerName(reportInput.getName());

        ArrayList<String> chartLabels = new ArrayList<>();
        ArrayList<Double> chartScores = new ArrayList<>();

        // Chronological order so "first" and "last" in the range are correct
        theList.sort(Comparator.comparing(MatchEntry::getMatchDate));

        ArrayList<PlayerStats> playerStatsList = new ArrayList<>();
        PlayerStats playerStats = new PlayerStats();

        int matchesPlayed = 0;
        int totalEliminations = 0;
        int totalPlacement = 0;
        int totalWins = 0;
        String currentRank = "";
        double rankProgress = 0.0;
        double rankDifference = 0.0;
        double eliminationAverage = 0.0;
        double placementAverage = 0.0;
        double winAverage = 0.0;

        // First and last matches inside the selected range
        MatchEntry firstInRange = null;
        MatchEntry lastInRange = null;

        for (MatchEntry match : theList) {

            if (reportInput.getStartDate() != null && !reportInput.getStartDate().isEmpty()
                    && match.getMatchDate().compareTo(reportInput.getStartDate()) < 0) {
                continue;
            }
            if (reportInput.getEndDate() != null && !reportInput.getEndDate().isEmpty()
                    && match.getMatchDate().compareTo(reportInput.getEndDate()) > 0) {
                continue;
            }

            if (match.getPlayerName().equalsIgnoreCase(reportInput.getName())) {
                totalEliminations += match.getEliminations();
                totalPlacement += match.getPlacement();
                matchesPlayed += 1;
                if (match.getWin()) {
                    totalWins += 1;
                }

                if (firstInRange == null) {
                    firstInRange = match;
                }
                lastInRange = match;
            }
            
            // inside the valid-match block:
            chartLabels.add(match.getMatchDate().toString());
            chartScores.add(getAbsoluteRankScore(match.getCurrentRank(), match.getRankProgress()));
        }

        if (lastInRange != null) {
            currentRank = lastInRange.getCurrentRank();
            rankProgress = lastInRange.getRankProgress();

            // Rank difference = last match in range - first match in range
            if (firstInRange != lastInRange) {
                double startScore = getAbsoluteRankScore(firstInRange.getCurrentRank(), firstInRange.getRankProgress());
                double endScore = getAbsoluteRankScore(lastInRange.getCurrentRank(), lastInRange.getRankProgress());

                if (startScore >= 0 && endScore >= 0) {
                    rankDifference = endScore - startScore;
                }
            }
        }

        if (matchesPlayed > 0) {
            placementAverage = (double) totalPlacement / matchesPlayed;
            winAverage = (double) totalWins / matchesPlayed * 100.00;
            eliminationAverage = (double) totalEliminations / matchesPlayed;
        }

        playerStats.setCurrentRank(currentRank);
        playerStats.setRankProgress(rankProgress);
        playerStats.setRankDifference(rankDifference);
        playerStats.setEliminations(totalEliminations);
        playerStats.setMatchesPlayed(matchesPlayed);
        playerStats.setWins(totalWins);
        playerStats.setEliminationAverage(eliminationAverage);
        playerStats.setPlacementAverage(placementAverage);
        playerStats.setWinAverage(winAverage);
        playerStats.setPlayerName(reportInput.getName());
        playerStatsList.add(playerStats);

        reportInput.setPlayerStatsList(playerStatsList);


    }

    // Ranks ordered lowest to highest. Position in this list = rank index.
    private static final List<String> RANK_ORDER = Arrays.asList(
            "bronze i", "bronze ii", "bronze iii",
            "silver i", "silver ii", "silver iii",
            "gold i", "gold ii", "gold iii",
            "platinum i", "platinum ii", "platinum iii",
            "diamond i", "diamond ii", "diamond iii",
            "elite", "champion", "unreal"
    );

    /**
     * Converts a rank name and progress % into a single comparable score.
     * Returns -1 if the rank name isn't recognized.
     */
    private static double getAbsoluteRankScore(String rankName, double rankProgress) {
        if (rankName == null) {
            return -1;
        }
        // Normalize "Gold 2" / "gold  II" style input to "gold ii"
        String normalized = rankName.trim().toLowerCase()
                .replaceAll("\\s+", " ")
                .replaceAll(" 1$", " i")
                .replaceAll(" 2$", " ii")
                .replaceAll(" 3$", " iii");

        int index = RANK_ORDER.indexOf(normalized);
        if (index < 0) {
            return -1;
        }
        return (index * 100.0) + rankProgress;
    }

}