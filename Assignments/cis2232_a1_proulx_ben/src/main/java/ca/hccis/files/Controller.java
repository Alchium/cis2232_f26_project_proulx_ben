package ca.hccis.files;

import ca.hccis.files.entity.Match;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

/**
 * Controls the overall flow of the program.
 *
 * @author BP
 * @since 20260918
 */
public class Controller {

    public static final int EXIT = 0;

    public static final String MENU = "A) Add" + System.lineSeparator()
            + "E) Edit" + System.lineSeparator()
            + "V) View" + System.lineSeparator()
            + "X) Exit"
            + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    private static HashMap<Integer, Match> matchMap = new HashMap();
    private static Gson gson = new Gson();
    public static final String PATH_NAME = "c:\\cis2232\\";
    public static final String FILE_NAME = "matches_proulx_ben.json";

    public static void main(String[] args) {

        initialize();
        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU);

            switch (menuOption) {
                case "X":
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case "A":
                    addMatch();
                    break;
                case "E":
                    editMatch();
                    break;
                case "V":
                    viewAll();
                    break;
                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }
        } while (!menuOption.equals("X"));
    }

    /**
     * Processing for menu option 1
     *
     * @author BP
     * @since 20260918
     */
    public static void addMatch() {
        Match newMatch = new Match();
        boolean overwriteDecision = true;
        IO.println("--Add Fortnite Match--");
        newMatch.getInformation();
        mapAll();
        for (Match current : matchMap.values()) {
            int currentId = current.getMatchNum();
            if (currentId == newMatch.getMatchNum()) {
                System.out.println(MESSAGE_ERROR);
                String overwriteOutput = CisUtility.getInputString("New match number already exists on file. would you like to overwrite the match info? (y/n) ");
                switch (overwriteOutput) {
                    case "y" :
                        break;
                    case "n" :
                        overwriteDecision = false;
                        break;
                }
            }
        }
        if (overwriteDecision){
            matchMap.put(newMatch.getMatchNum(), newMatch);
            System.out.println(MESSAGE_SUCCESS);
        }
        writeAll();
    }

    /**
     * Processing for menu option 2.
     *
     * @author BP
     * @since 20260918
     */
    public static void editMatch() {
        System.out.println("Processing option 2");
        int matchNum = CisUtility.getInputInt("Match number: ");
        Match editingMatch = matchMap.get(matchNum);
        try {
            editingMatch.edit();
            writeAll(); //save to file
        } catch (NullPointerException e) {
            System.out.println(MESSAGE_ERROR);
            System.out.println("Match ID not present on file.");
        }
    }

    /**
     * Processing for menu option 3.
     *
     * @author BP
     * @since 20260918
     */
    public static void viewAll() {
        mapAll();
        try {
            FileReader reader = new FileReader(PATH_NAME + FILE_NAME);
            List<String> lines = reader.readAllLines();
            for(int i = 0; i < lines.size(); i++) {
                Match matchFromJson = gson.fromJson(lines.get(i), Match.class);
                System.out.println(matchFromJson.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }
    }


    public static void writeAll() {
        try {
            FileWriter writer = new FileWriter(PATH_NAME + FILE_NAME, false);
            for (Match current : matchMap.values()) {
                writer.append(gson.toJson(current));
                writer.append(System.lineSeparator());
                System.out.println("Successfully written JSON string to file.");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mapAll() {
        try {
            FileReader reader = new FileReader(PATH_NAME + FILE_NAME);
            List<String> lines = reader.readAllLines();
            for(int i = 0; i < lines.size(); i++) {
                Match matchFromJson = gson.fromJson(lines.get(i), Match.class);
                matchMap.put(matchFromJson.getMatchNum(), matchFromJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initialize() {
        // Check if folder exists
        File folder = new File(PATH_NAME);
        if (folder.mkdir()) {
            System.out.println("Folder not present. Created new folder.");
        } else {
            System.out.println("Folder present.");
        }

        // Check if the file exists
        Path path = Paths.get(PATH_NAME + FILE_NAME);

        if (Files.exists(path)) {
            System.out.println("Matches present in file.");
            mapAll();
        } else {


            Match match = new Match(1, "Alchiumi", "bronze", 23, 3,"n");
            Match match2 = new Match(2, "Alchiumi", "bronze", 45, 7,"y");
            Match match3 = new Match(3, "Alchiumi", "bronze", 67, 5,"n");
            Match match4 = new Match(4, "Alchiumi", "bronze", 58, 0,"n");
            Match match5 = new Match(5, "Alchiumi", "bronze", 80, 6,"y");
            matchMap.put(match.getMatchNum(), match);
            matchMap.put(match2.getMatchNum(), match2);
            matchMap.put(match3.getMatchNum(), match3);
            matchMap.put(match4.getMatchNum(), match4);
            matchMap.put(match5.getMatchNum(), match5);

            writeAll();
        }

    }
}
