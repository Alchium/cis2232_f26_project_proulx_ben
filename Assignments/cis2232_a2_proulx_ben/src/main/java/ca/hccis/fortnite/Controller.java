package ca.hccis.fortnite;

import ca.hccis.fortnite.bo.PlayerStatsBO;
import ca.hccis.fortnite.entity.PlayerStats;
import ca.hccis.util.CisUtility;

/**
 * Controls the overall flow of the Fortnite Ranked Tracker.
 *
 * @author Ben Proulx / Claude
 * @since 20260921
 */
public class Controller {

    public static final int EXIT = 0;

    public static final String MENU = "Fortnite Ranked Tracker" + System.lineSeparator()
            + "1) Enter player stats and calculate" + System.lineSeparator()
            + EXIT + ") Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";

    public static void main(String[] args) {

        int menuOption;

        do {
            menuOption = CisUtility.getInputInt(MENU);

            switch (menuOption) {
                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;
                case 1:
                    processOption1();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (menuOption != EXIT);
    }

    /**
     * Gather the player's stats and display the calculated results.
     *
     * @author Ben Proulx
     * @since 20260921
     */
    public static void processOption1() {
        PlayerStats stats = new PlayerStats();
        stats.getInformation();

        stats.setAveragePlacement(PlayerStatsBO.calculateAveragePlacement(stats));

        System.out.println(stats);
        System.out.printf("Win Rate: %.1f%%%n", PlayerStatsBO.calculate(stats));
        System.out.printf("Average Eliminations: %.2f%n", PlayerStatsBO.calculateAverageEliminations(stats));
        System.out.printf("Average Placement: %.2f%n", stats.getAveragePlacement());
        System.out.printf("Rank Change: %+.1f%%%n", PlayerStatsBO.calculateRankChange(stats));
    }
}
