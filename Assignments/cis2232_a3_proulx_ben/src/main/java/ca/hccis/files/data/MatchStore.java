package ca.hccis.files.data;

import ca.hccis.files.entity.Match;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The single data store shared by the console thread and the JOptionPane
 * thread. Every public method is synchronized so the two threads can never
 * corrupt the map or the file. It also holds the accumulator: the total
 * number of entries added by BOTH threads combined.
 *
 * @author BP / Claude
 * @since 20261012
 */
public final class MatchStore {

    public enum AddResult { ADDED, REPLACED, DUPLICATE }
    public enum EditResult { EDITED, NOT_FOUND, NUMBER_IN_USE }

    public static final String PATH_NAME = "c:/cis2232/";
    public static final String FILE_NAME = "matches_proulx_ben.json";

    private static final Map<Integer, Match> matchMap = new TreeMap<>();
    private static final Gson gson = new Gson();

    /** Accumulator: entries added during this run, from both threads. */
    private static final AtomicInteger entriesAdded = new AtomicInteger();

    private MatchStore() {
    }

    /**
     * Create the folder/file if needed, otherwise load what is on file.
     */
    public static synchronized void initialize() {
        File folder = new File(PATH_NAME);
        if (folder.mkdirs()) {
            System.out.println("Folder not present. Created new folder.");
        }
        Path path = Paths.get(PATH_NAME + FILE_NAME);
        if (Files.exists(path)) {
            readAll();
            System.out.println("Loaded " + matchMap.size() + " match(es) from file.");
        } else {
            String[] seed = {"n", "y", "n", "n", "y"};
            int[] progress = {23, 45, 67, 58, 80};
            int[] elims = {3, 7, 5, 0, 6};
            for (int i = 0; i < seed.length; i++) {
                matchMap.put(i + 1, new Match(i + 1, "Alchiumi", "bronze", progress[i], elims[i], seed[i]));
            }
            writeAll();
        }
    }

    /**
     * Add a match. If the number exists it is only replaced when overwrite is true.
     */
    public static synchronized AddResult add(Match match, boolean overwrite) {
        boolean exists = matchMap.containsKey(match.getMatchNum());
        if (exists && !overwrite) {
            return AddResult.DUPLICATE;
        }
        matchMap.put(match.getMatchNum(), match);
        entriesAdded.incrementAndGet();
        writeAll();
        return exists ? AddResult.REPLACED : AddResult.ADDED;
    }

    /**
     * Replace the match stored under oldNum with the updated one.
     */
    public static synchronized EditResult edit(int oldNum, Match updated) {
        if (!matchMap.containsKey(oldNum)) {
            return EditResult.NOT_FOUND;
        }
        if (updated.getMatchNum() != oldNum && matchMap.containsKey(updated.getMatchNum())) {
            return EditResult.NUMBER_IN_USE;
        }
        matchMap.remove(oldNum);
        matchMap.put(updated.getMatchNum(), updated);
        writeAll();
        return EditResult.EDITED;
    }

    public static synchronized Match get(int matchNum) {
        return matchMap.get(matchNum);
    }

    /** A snapshot copy, safe to iterate while the other thread keeps working. */
    public static synchronized List<Match> getAll() {
        return new ArrayList<>(matchMap.values());
    }

    public static synchronized int size() {
        return matchMap.size();
    }

    public static int getEntriesAdded() {
        return entriesAdded.get();
    }

    /**
     * Text shown after every add, by whichever thread did the add.
     */
    public static synchronized String getAccumulatorSummary() {
        return "Entries added this run (console + JOptionPane): " + entriesAdded.get()
                + " | Total matches on file: " + matchMap.size();
    }

    private static void readAll() {
        try {
            for (String line : Files.readAllLines(Paths.get(PATH_NAME + FILE_NAME), StandardCharsets.UTF_8)) {
                if (line.isBlank()) {
                    continue;
                }
                Match match = gson.fromJson(line, Match.class);
                matchMap.put(match.getMatchNum(), match);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void writeAll() {
        StringBuilder sb = new StringBuilder();
        for (Match match : matchMap.values()) {
            sb.append(gson.toJson(match)).append(System.lineSeparator());
        }
        try {
            Files.writeString(Paths.get(PATH_NAME + FILE_NAME), sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
