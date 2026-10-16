package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.entity.PlayerStats;

/**
 * Business object containing the ranked statistic calculations for the
 * Fortnite Ranked Tracker.
 *
 * Calculations (from the project README):
 * <ul>
 *   <li>Win Rate = (Wins / Matches Played) x 100</li>
 *   <li>Average Eliminations = Total Eliminations / Matches Played</li>
 *   <li>Average Placement = Total Placement / Matches Played</li>
 *   <li>Rank Change = New Rank Progress - Previous Rank Progress</li>
 * </ul>
 *
 * @author Ben Proulx / Claude
 * @since 20260921
 */
public class PlayerStatsBO {

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
    public static double calculateAveragePlacement(PlayerStats stats) {
        if (stats == null || stats.getMatchesPlayed() <= 0 || stats.getTotalPlacement() < 0) {
            return 0;
        }
        return (double) stats.getTotalPlacement() / stats.getMatchesPlayed();
    }

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
}
