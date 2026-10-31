package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.entity.PlayerStats;
import ca.hccis.fortnite.entity.ReportInput;
import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;

/**
 * Class to hold business logic needed for the report.
 *
 * @author BP
 * @since 20261030
 */
public class ReportBO {

    public static void processPlayerScoreReport(ReportInput reportInput) {

        //Get all of the matches
        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<MatchEntry> theList = matchEntryBO.processSelectAllByPlayerName(reportInput.getName());

        //Process through the matches and get score summaries.
        ArrayList<PlayerStats> playerStatsList = new ArrayList<>();

        for (int i = 0; i < theList.size(); i++ ) {

            //filter out if match is before the start date
            if (reportInput.getStartDate() != null && !reportInput.getStartDate().isEmpty()) {
                if (theList.get(i).getMatchDate().compareTo(reportInput.getStartDate()) < 0) {
                    System.out.println("Skipping match " + theList.get(i).getMatchDate() + " because it is before the start date");
                    continue;
                }
            }
            //filter out if match is after the date
            if (reportInput.getEndDate() != null && !reportInput.getEndDate().isEmpty()) {
                if (theList.get(i).getMatchDate().compareTo(reportInput.getEndDate()) > 0) {
                    System.out.println("Skipping match " + theList.get(i).getMatchDate() + " because it is after the end date");
                    continue;
                }
            }

            PlayerStats playerStats = new PlayerStats(theList.get(i));

            int matchesPlayed = 0;
            int totalEliminations = 0;
            int totalPlacement = 0;
            int totalWins = 0;
            double rankDifference = 0.0;
            double eliminationAverage = 0.0;
            double placementAverage = 0.0;
            double winAverage = 0.0;

            if (theList.get(i).getPlayerName().equalsIgnoreCase(reportInput.getName())) {
                totalEliminations += theList.get(i).getEliminations();
                totalPlacement += theList.get(i).getPlacement();
                matchesPlayed += 1;

                if (theList.get(i).getWin()) {
                    totalWins += 1;
                }
            }

            rankDifference = theList.get(i).getRankProgress()-theList.get(i+1).getRankProgress();

            placementAverage = totalPlacement / matchesPlayed * 100.00;
            winAverage = totalWins / matchesPlayed * 100.00;
            eliminationAverage = totalEliminations / matchesPlayed * 100.00;

            playerStats.setEliminations(totalEliminations);
            playerStats.setMatchesPlayed(matchesPlayed);
            playerStats.setWins(totalWins);
            playerStats.setEliminationAverage(eliminationAverage);
            playerStats.setPlacementAverage(placementAverage);
            playerStats.setWinAverage(winAverage);
            playerStats.setPlayerName(reportInput.getName());
            playerStatsList.add(playerStats);
        }
        reportInput.setPlayerStatsList(playerStatsList);
    }
}