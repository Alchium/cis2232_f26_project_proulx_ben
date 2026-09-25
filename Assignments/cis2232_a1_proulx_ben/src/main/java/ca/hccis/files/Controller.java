package ca.hccis.files;

import ca.hccis.files.entity.Match;
import ca.hccis.util.CisUtility;
import com.google.gson.Gson;

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
    public static final String PATH_NAME = "matches_proulx_ben.json";

    public static void main(String[] args) {

        initialize();

        //Gson
//        Camper test = camperMap.get(22334);
//        String camperJson = gson.toJson(test);
//        IO.println(camperJson);
//
//        Camper camperFromJson = gson.fromJson(camperJson, Camper.class);
//        System.out.println(camperFromJson.toString());


        String menuOption;

        do {
            menuOption = CisUtility.getInputString(MENU);

            switch (menuOption) {
                case "X":
                    System.out.println(MESSAGE_EXIT);
                    break; //Break out of the loop as we're finished.
                case "A":
                    add();
                    break;
                case "E":
                    edit();
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
    public static void add() {
        Match newMatch = new Match();
        IO.println("--Add Fortnite Match--");
        newMatch.getInformation();

        //TODO what if the match id already exists.  Give the user a warning and ask if they want to overwrite
        //the row.
        //read file nad see if the new match is already there, and if so check with user to see if should overwrite
        readAll();
        for (Match current : matchMap.values()) {
            int currentId = current.getMatchNum();
            if (currentId == newMatch.getMatchNum()) {
                System.out.println(MESSAGE_ERROR);
                String overwrite = CisUtility.getInputString("New match number already exists on file. would you like to overwrite the match info? (Y/N) ");
                switch (overwrite) {
                    case "Y" :
                        matchMap.put(newMatch.getMatchNum(), newMatch);
                        break;
                    case "N" :
                        break;
                }
            }
            else {
                matchMap.put(newMatch.getMatchNum(), newMatch);
            }
        }
        writeAll();
    }

    /**
     * Processing for menu option 2.
     *
     * @author BP
     * @since 20260918
     */
    public static void edit() {
        System.out.println("Processing option 2");
        int matchNum = CisUtility.getInputInt("Match number: ");
        Match editingMatch = matchMap.get(matchNum);
        editingMatch.edit();
        //TODO What if the matchID not found?
        //Handle this situation.
        writeAll(); //save to file
    }

    /**
     * Processing for menu option 3.
     *
     * @author BP
     * @since 20260918
     */
    public static void viewAll() {
        readAll();
        //TODO Need to show all the campers.  Note want to show the latest from the file, not just
        //what is currently in the map.
        for (Match current : matchMap.values()) {
            System.out.println(current.toString());
        }
    }


    public static void writeAll() {
        try {
            FileWriter writer = new FileWriter(PATH_NAME, false);
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

    public static void readAll() {
        try {
            FileReader reader = new FileReader(PATH_NAME);
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

        Path path = Paths.get(PATH_NAME);

        // Check if the file exists
        if (Files.exists(path)) {
            System.out.println("Matches present in file.");
            readAll();
        } else {


            Match match = new Match(1, "Alchiumi", "bronze", 23, 3,false);
            Match match2 = new Match(2, "Alchiumi", "bronze", 45, 7,true);
            Match match3 = new Match(3, "Alchiumi", "bronze", 67, 5,false);
            Match match4 = new Match(4, "Alchiumi", "bronze", 58, 0,false);
            Match match5 = new Match(5, "Alchiumi", "bronze", 80, 6,true);
            matchMap.put(match.getMatchNum(), match);
            matchMap.put(match2.getMatchNum(), match2);
            matchMap.put(match3.getMatchNum(), match3);
            matchMap.put(match4.getMatchNum(), match4);
            matchMap.put(match5.getMatchNum(), match5);

            writeAll();
        }

    }
}
