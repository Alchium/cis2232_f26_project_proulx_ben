package ca.hccis.fortnite.dao;

import ca.hccis.fortnite.jpa.entity.MatchEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class MatchEntryDAO {
    private static ResultSet rs;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(MatchEntryDAO.class);

    public MatchEntryDAO() {

        String propFileName = "application";
        ResourceBundle rb = ResourceBundle.getBundle(propFileName);
        String connectionString = rb.getString("spring.datasource.url");
        String userName = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Select all player names sorted alphabetically
     *
     * @since 20261019
     * @author BJM
     */
    public ArrayList<String> selectAllPlayers() {
        ArrayList<String> players = new ArrayList<>();
        HashSet<String> playerSet = new HashSet<>();

        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            String sqlStatement = "select * from matchentry";
            rs = stmt.executeQuery(sqlStatement);

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            ArrayList<MatchEntry> fortniteMatches = loadList(rs);

            for(MatchEntry current: fortniteMatches){
                playerSet.add(current.getPlayerName());
            }

            players.addAll(playerSet);
            Collections.sort(players);

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return players;
    }


    /**
     * Select all by name
     *
     * @since 20261009
     * @author BJM
     */
    public ArrayList<MatchEntry> selectAllByPlayerName(String name) {
        ArrayList<MatchEntry> fortniteMatches = null;
        Statement stmt = null;

        //******************************************************************
        //Use the DriverManager to get a connection to our MySql database.  Note
        //that in the dependencies, we added the Java connector to MySql which
        //will allow us to connect to a MySql database.
        //******************************************************************
        //******************************************************************
        //Create a statement object using our connection to the database.  This
        //statement object will allow us to run sql commands against the database.
        //******************************************************************
        try {

            stmt = conn.createStatement();
            String sqlStatement = "select * from matchentry " +
                    "where playerName like '%"+name+"%'" +
                    " ORDER BY lastUpdated;";
            rs = stmt.executeQuery(sqlStatement);

            //******************************************************************
            //Loop through the result set using the next method.
            //******************************************************************
            fortniteMatches = loadList(rs);

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            try {
                stmt.close();
            } catch (SQLException ex) {
                System.out.println("There was an error closing");
            }
        }
        return fortniteMatches;
    }

    public ArrayList<MatchEntry> loadList(ResultSet rs) throws SQLException {
        ArrayList<MatchEntry> fortniteMatches = new  ArrayList();

        while (rs.next()) {

            MatchEntry matchEntry = new MatchEntry();
            matchEntry.setMatchId(rs.getInt("matchId"));

            matchEntry.setPlayerName(rs.getString("playerName"));

            matchEntry.setCurrentRank(rs.getString("currentRank"));
            matchEntry.setRankProgress(rs.getFloat("rankProgress"));

            matchEntry.setEliminations(rs.getInt("eliminations"));
            matchEntry.setWin(rs.getBoolean("win"));

            matchEntry.setMatchDate(rs.getString("matchDate"));
            matchEntry.setMatchTime(rs.getString("matchTime"));

            fortniteMatches.add(matchEntry);
        }
        return fortniteMatches;
    }


}