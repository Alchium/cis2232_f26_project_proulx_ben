package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.entity.PlayerStats;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerStatsBOTest {

    private static final double DELTA = 0.0001;

    /**
     * Test 1 created by BP following TDD.
     * README example: 5 wins / 20 matches x 100 = 25% win rate.
     *
     * @author Ben Proulx
     * @since 20260921
     */
    @Test
    void testCalculateWinRate_5Wins20Matches() {

        PlayerStats stats = new PlayerStats();

        stats.setMatchesPlayed(20);
        stats.setWins(5);

        double actual = PlayerStatsBO.calculate(stats);

        assertEquals(25.0, actual, DELTA);
    }

    /**
     * Test 2 created by BP following TDD.
     * No matches played must return 0 rather than dividing by zero.
     *
     * @author Ben Proulx
     * @since 20260921
     */
    @Test
    void testCalculateWinRate_noMatchesPlayed() {

        PlayerStats stats = new PlayerStats();

        stats.setMatchesPlayed(0);
        stats.setWins(0);

        double actual = PlayerStatsBO.calculate(stats);

        assertEquals(0.0, actual, DELTA);
    }

    /**
     * Test 3 created by BP following TDD.
     * A partial win rate must always fall strictly between 0 and 100.
     *
     * @author Ben Proulx
     * @since 20260921
     */
    @Test
    void testCalculateWinRate_withinZeroAndOneHundred() {

        PlayerStats stats = new PlayerStats();

        stats.setMatchesPlayed(7);
        stats.setWins(3);

        double actual = PlayerStatsBO.calculate(stats);

        assertTrue(actual > 0 && actual < 100);
    }


    //****************************************************************************
    //The following were the unit tests that were created by Claude AI
    //****************************************************************************

    private PlayerStats buildStats(int matches, int eliminations, int wins,
                                   int totalPlacement, double previousProgress,
                                   double currentProgress) {
        PlayerStats stats = new PlayerStats();
        stats.setPlayerName("Tester");
        stats.setCurrentRank("Gold");
        stats.setMatchesPlayed(matches);
        stats.setEliminations(eliminations);
        stats.setWins(wins);
        stats.setTotalPlacement(totalPlacement);
        stats.setPreviousRankProgress(previousProgress);
        stats.setRankProgress(currentProgress);
        return stats;
    }

    // ---- Win rate (calculate) ----

    // Edge case: win rate that does not divide evenly
    @Test
    void calculate_nonTerminatingDecimal() {
        PlayerStats stats = buildStats(3, 0, 1, 0, 0, 0);

        assertEquals(100.0 / 3.0, PlayerStatsBO.calculate(stats), DELTA);
    }

    // Boundary: no wins and all wins
    @Test
    void calculate_boundaryZeroAndOneHundred() {
        assertEquals(0.0, PlayerStatsBO.calculate(buildStats(10, 0, 0, 0, 0, 0)), DELTA);
        assertEquals(100.0, PlayerStatsBO.calculate(buildStats(10, 0, 10, 0, 0, 0)), DELTA);
    }

    // Edge case: a single match that was won
    @Test
    void calculate_singleMatchWon() {
        assertEquals(100.0, PlayerStatsBO.calculate(buildStats(1, 0, 1, 0, 0, 0)), DELTA);
    }

    // Invalid input: null, negative values, and more wins than matches
    @Test
    void calculate_invalidInputsReturnZero() {
        assertEquals(0.0, PlayerStatsBO.calculate(null), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculate(buildStats(-5, 0, 1, 0, 0, 0)), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculate(buildStats(5, 0, -1, 0, 0, 0)), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculate(buildStats(5, 0, 6, 0, 0, 0)), DELTA);
    }

    // ---- Average eliminations ----

    // README example: 56 eliminations / 20 matches = 2.8
    @Test
    void averageEliminations_readmeExample() {
        PlayerStats stats = buildStats(20, 56, 0, 0, 0, 0);

        assertEquals(2.8, PlayerStatsBO.calculateAverageEliminations(stats), DELTA);
    }

    @Test
    void averageEliminations_zeroEliminations() {
        PlayerStats stats = buildStats(10, 0, 0, 0, 0, 0);

        assertEquals(0.0, PlayerStatsBO.calculateAverageEliminations(stats), DELTA);
    }

    @Test
    void averageEliminations_invalidInputsReturnZero() {
        assertEquals(0.0, PlayerStatsBO.calculateAverageEliminations(null), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculateAverageEliminations(buildStats(0, 10, 0, 0, 0, 0)), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculateAverageEliminations(buildStats(10, -3, 0, 0, 0, 0)), DELTA);
    }

    // ---- Average placement ----

    // README example: 240 total placement / 20 matches = 12th
    @Test
    void averagePlacement_readmeExample() {
        PlayerStats stats = buildStats(20, 0, 0, 240, 0, 0);

        assertEquals(12.0, PlayerStatsBO.calculateAveragePlacement(stats), DELTA);
    }

    @Test
    void averagePlacement_withDecimal() {
        PlayerStats stats = buildStats(2, 0, 0, 21, 0, 0);

        assertEquals(10.5, PlayerStatsBO.calculateAveragePlacement(stats), DELTA);
    }

    @Test
    void averagePlacement_invalidInputsReturnZero() {
        assertEquals(0.0, PlayerStatsBO.calculateAveragePlacement(null), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculateAveragePlacement(buildStats(0, 0, 0, 100, 0, 0)), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculateAveragePlacement(buildStats(10, 0, 0, -1, 0, 0)), DELTA);
    }

    // ---- Rank change ----

    // README example: 45% -> 62% = +17%
    @Test
    void rankChange_gain() {
        PlayerStats stats = buildStats(0, 0, 0, 0, 45, 62);

        assertEquals(17.0, PlayerStatsBO.calculateRankChange(stats), DELTA);
    }

    // Loss: progress dropped, so the change is negative
    @Test
    void rankChange_loss() {
        PlayerStats stats = buildStats(0, 0, 0, 0, 62, 45);

        double actual = PlayerStatsBO.calculateRankChange(stats);

        assertEquals(-17.0, actual, DELTA);
        assertTrue(actual < 0);
    }

    @Test
    void rankChange_noChangeAndNull() {
        assertEquals(0.0, PlayerStatsBO.calculateRankChange(buildStats(0, 0, 0, 0, 50, 50)), DELTA);
        assertEquals(0.0, PlayerStatsBO.calculateRankChange(null), DELTA);
    }
}
