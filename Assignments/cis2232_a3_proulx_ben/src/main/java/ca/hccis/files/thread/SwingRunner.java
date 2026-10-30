package ca.hccis.files.thread;

import ca.hccis.files.bo.MatchBO;
import ca.hccis.files.data.MatchStore;
import ca.hccis.files.entity.Match;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.util.List;

/**
 * Thread 2: runs the whole application using JOptionPane dialogs.
 * Same functionality as {@link ConsoleRunner}.
 *
 * @author BP / Claude
 * @since 20261012
 */
public class SwingRunner implements Runnable {

    private static final String TITLE = "Fortnite Matches (JOptionPane)";

    public static final String MENU = "A) Add\nE) Edit\nV) View\nC) Calculate win rate\nX) Exit";

    @Override
    public void run() {
        String option;
        do {
            option = JOptionPane.showInputDialog(null, MENU, TITLE, JOptionPane.QUESTION_MESSAGE);
            if (option == null) { // Cancel / close = exit
                option = "X";
            }
            option = option.trim().toUpperCase();
            switch (option) {
                case "A" -> add();
                case "E" -> edit();
                case "V" -> view();
                case "C" -> calculate();
                case "X" -> JOptionPane.showMessageDialog(null, "Goodbye (JOptionPane)", TITLE,
                        JOptionPane.INFORMATION_MESSAGE);
                default -> error("Invalid option");
            }
        } while (!option.equals("X"));
    }

    private void add() {
        Match match = new Match();
        Integer num = askInt("Match number");
        if (num == null || !askRemaining(match, num)) {
            return;
        }
        MatchStore.AddResult result = MatchStore.add(match, false);
        if (result == MatchStore.AddResult.DUPLICATE) {
            int answer = JOptionPane.showConfirmDialog(null,
                    "New match number already exists on file. Overwrite the match info?",
                    TITLE, JOptionPane.YES_NO_OPTION);
            if (answer != JOptionPane.YES_OPTION) {
                return;
            }
            MatchStore.add(match, true);
        }
        JOptionPane.showMessageDialog(null, "Success\n" + MatchStore.getAccumulatorSummary(), TITLE,
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void edit() {
        Integer oldNum = askInt("Match number to edit");
        if (oldNum == null) {
            return;
        }
        if (MatchStore.get(oldNum) == null) {
            error("Match number not present on file.");
            return;
        }
        Integer newNum = askInt("New match number");
        Match updated = new Match();
        if (newNum == null || !askRemaining(updated, newNum)) {
            return;
        }
        switch (MatchStore.edit(oldNum, updated)) {
            case EDITED -> JOptionPane.showMessageDialog(null, "Success", TITLE, JOptionPane.INFORMATION_MESSAGE);
            case NUMBER_IN_USE -> error("New match number is already in use.");
            case NOT_FOUND -> error("Match number not present on file.");
        }
    }

    private void view() {
        StringBuilder sb = new StringBuilder();
        for (Match match : MatchStore.getAll()) {
            sb.append(match).append('\n');
        }
        sb.append('\n').append(MatchStore.getAccumulatorSummary());
        JTextArea area = new JTextArea(sb.toString(), 15, 70);
        area.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(area), TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    private void calculate() {
        List<Match> matches = MatchStore.getAll();
        JOptionPane.showMessageDialog(null,
                String.format("Win rate: %.1f%%%nAverage eliminations: %.2f",
                        MatchBO.calculate(matches), MatchBO.calculateAverageEliminations(matches)),
                TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    /** Fill every field but the number; false if the user cancelled. */
    private boolean askRemaining(Match match, int num) {
        match.setMatchNum(num);
        String name = askString("Player name");
        String rank = askString("Current rank");
        Integer progress = name == null || rank == null ? null : askInt("New rank progress");
        Integer elims = progress == null ? null : askInt("Eliminations this match");
        String won = elims == null ? null : askYesNo("Game won? (y/n)");
        if (won == null) {
            return false;
        }
        match.setPlayerName(name);
        match.setCurrentRank(rank);
        match.setRankProgress(progress);
        match.setEliminations(elims);
        match.setGameWon(won);
        return true;
    }

    /** @return the entered text, or null if cancelled */
    private String askString(String prompt) {
        String value = JOptionPane.showInputDialog(null, prompt, TITLE, JOptionPane.QUESTION_MESSAGE);
        return value == null ? null : value.trim();
    }

    /** Re-asks until a valid whole number is entered; null if cancelled. */
    private Integer askInt(String prompt) {
        while (true) {
            String value = askString(prompt);
            if (value == null) {
                return null;
            }
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                error("Please enter a whole number.");
            }
        }
    }

    /** Re-asks until y or n is entered; null if cancelled. */
    private String askYesNo(String prompt) {
        while (true) {
            String value = askString(prompt);
            if (value == null) {
                return null;
            }
            if (value.equalsIgnoreCase("y") || value.equalsIgnoreCase("n")) {
                return value.toLowerCase();
            }
            error("Please enter y or n.");
        }
    }

    private void error(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
