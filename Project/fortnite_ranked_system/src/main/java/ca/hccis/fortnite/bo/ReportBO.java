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

    private static final double PERCENT = 100.0;

    /**
     * Main calculation: the player's win rate as a percentage.
     *
     * @param stats the player's stats
     * @return win rate (0-100), or 0 if stats are null, no matches have been
     * played, or the wins/matches values are invalid (negative or wins > matches)
     */
    public static double calculate(PlayerStats stats) {
        if (stats == null || stats.getMatchesPlayed() <= 0) {
            return 0;
        }
        if (stats.getWins() < 0 || stats.getWins() > stats.getMatchesPlayed()) {
            return 0;
        }
        return (double) stats.getWins() / stats.getMatchesPlayed() * PERCENT;
    }

    /**
     * Average eliminations per match.
     *
     * @return eliminations / matches played, or 0 if invalid or no matches
     */
    public static double calculateAverageEliminations(PlayerStats stats) {
        if (stats == null || stats.getMatchesPlayed() <= 0 || stats.getEliminations() < 0) {
            return 0;
        }
        return (double) stats.getEliminations() / stats.getMatchesPlayed();
    }

    /**
     * Average finishing placement across all matches.
     *
     * @return total placement / matches played, or 0 if invalid or no matches
     */
//    public static double calculateAveragePlacement(PlayerStats stats) {
//        if (stats == null || stats.getMatchesPlayed() <= 0 || stats.getTotalPlacement() < 0) {
//            return 0;
//        }
//        return (double) stats.getTotalPlacement() / stats.getMatchesPlayed();
//    }

    /**
     * Rank progress gained (positive) or lost (negative) since the previous update.
     *
     * @return new rank progress - previous rank progress, or 0 if stats are null
     */
    public static double calculateRankChange(PlayerStats stats) {
        if (stats == null) {
            return 0;
        }
        return stats.getRankProgress() - stats.getPreviousRankProgress();
    }




    public static void processPlayerScoreReport(ReportInput reportInput) {

        //Get all of the matches
        MatchEntryBO matchEntryBO = new MatchEntryBO();
        ArrayList<MatchEntry> theList = matchEntryBO.processSelectAllByPlayerName(reportInput.getName());

        //Process through the matches and get score summaries.
        ArrayList<PlayerStats> playerStatsList = new ArrayList<>();
        for (MatchEntry current : theList) {

            //filter out if match is before the start date
            if (reportInput.getStartDate() != null && !reportInput.getStartDate().isEmpty()) {
                if (current.getMatchDate().compareTo(reportInput.getStartDate()) < 0) {
                    System.out.println("Skipping match " + current.getMatchDate() + " because it is before the start date");
                    continue;
                }
            }
            //filter out if match is after the date
            if (reportInput.getEndDate() != null && !reportInput.getEndDate().isEmpty()) {
                if (current.getMatchDate().compareTo(reportInput.getEndDate()) > 0) {
                    System.out.println("Skipping match " + current.getMatchDate() + " because it is after the end date");
                    continue;
                }
            }

            PlayerStats playerStats = new PlayerStats(current);

            int totalEliminations = 0;
            int totalWins = 0;

            if (current.getPlayerName().equalsIgnoreCase(reportInput.getName())) {
                totalEliminations += current.getEliminations();

                if (current.getWin()) {
                    totalWins += 1;
                }
            }

            playerStats.setEliminations(totalEliminations);
            playerStats.setWins(totalWins);
            playerStats.setPlayerName(reportInput.getName());
            playerStatsList.add(playerStats);
        }
        reportInput.setPlayerStatsList(playerStatsList);
    }
}