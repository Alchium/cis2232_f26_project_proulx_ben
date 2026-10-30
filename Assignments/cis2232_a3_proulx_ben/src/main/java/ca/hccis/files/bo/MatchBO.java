package ca.hccis.files.bo;

import ca.hccis.files.entity.Match;

import java.util.Collection;

/**
 * Business object holding the calculations (carried over from assignment 2).
 *
 * @author BP / Claude
 * @since 20261012
 */
public class MatchBO {

    private static final double PERCENT = 100.0;

    /**
     * Main calculation: win rate = (matches won / matches played) x 100.
     *
     * @param matches the matches to evaluate
     * @return win rate (0-100), or 0 if there are no matches
     */
    public static double calculate(Collection<Match> matches) {
        if (matches == null || matches.isEmpty()) {
            return 0;
        }
        int wins = 0;
        for (Match match : matches) {
            if ("y".equalsIgnoreCase(match.isGameWon())) {
                wins++;
            }
        }
        return (double) wins / matches.size() * PERCENT;
    }

    /**
     * Average eliminations per match.
     *
     * @return total eliminations / matches played, or 0 if there are no matches
     */
    public static double calculateAverageEliminations(Collection<Match> matches) {
        if (matches == null || matches.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Match match : matches) {
            total += match.getEliminations();
        }
        return (double) total / matches.size();
    }
}
