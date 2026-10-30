package ca.hccis.files.thread;

import ca.hccis.files.bo.MatchBO;
import ca.hccis.files.data.MatchStore;
import ca.hccis.files.entity.Match;
import ca.hccis.util.CisUtility;

import java.util.List;

/**
 * Thread 1: runs the whole application in the console.
 *
 * @author BP / Claude
 * @since 20261012
 */
public class ConsoleRunner extends Thread {

    public static final String MENU = "--CONSOLE-- "+ System.lineSeparator()
            + "A) Add" + System.lineSeparator()
            + "E) Edit" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + "C) Calculate win rate" + System.lineSeparator()
            + "X) Exit";

    public ConsoleRunner() {
        super("Console-Thread");
    }

    @Override
    public void run() {
        String option;
        do {
            option = CisUtility.getInputString(MENU).trim().toUpperCase();
            try {
                switch (option) {
                    case "A" -> add();
                    case "E" -> edit();
                    case "V" -> view();
                    case "C" -> calculate();
                    case "X" -> System.out.println("Goodbye (console)");
                    default -> System.out.println("Error");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: a number was expected.");
            }
        } while (!option.equals("X"));
    }

    private void add() {
        System.out.println("--Add Fortnite Match--");
        Match match = new Match();
        match.getInformation();
        MatchStore.AddResult result = MatchStore.add(match, false);
        if (result == MatchStore.AddResult.DUPLICATE) {
            System.out.println("Error");
            String answer = CisUtility.getInputString(
                    "New match number already exists on file. Overwrite the match info? (y/n)");
            if (answer.trim().equalsIgnoreCase("y")) {
                MatchStore.add(match, true);
            } else {
                return;
            }
        }
        System.out.println("Success");
        System.out.println(MatchStore.getAccumulatorSummary());
    }

    private void edit() {
        int oldNum = CisUtility.getInputInt("Match number to edit");
        if (MatchStore.get(oldNum) == null) {
            System.out.println("Error: match number not present on file.");
            return;
        }
        System.out.println("--Editing Fortnite Match--");
        Match updated = new Match();
        updated.getInformation();
        switch (MatchStore.edit(oldNum, updated)) {
            case EDITED -> System.out.println("Success");
            case NUMBER_IN_USE -> System.out.println("Error: new match number is already in use.");
            case NOT_FOUND -> System.out.println("Error: match number not present on file.");
        }
    }

    private void view() {
        List<Match> matches = MatchStore.getAll();
        matches.forEach(System.out::println);
        System.out.println(MatchStore.getAccumulatorSummary());
    }

    private void calculate() {
        List<Match> matches = MatchStore.getAll();
        System.out.printf("Win rate: %.1f%%%n", MatchBO.calculate(matches));
        System.out.printf("Average eliminations: %.2f%n", MatchBO.calculateAverageEliminations(matches));
    }
}
