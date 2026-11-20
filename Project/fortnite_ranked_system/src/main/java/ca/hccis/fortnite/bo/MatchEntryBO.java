package ca.hccis.fortnite.bo;

import ca.hccis.fortnite.dao.MatchEntryDAO;
import ca.hccis.fortnite.entity.PlayerStats;
import ca.hccis.fortnite.jpa.entity.MatchEntry;
import ca.hccis.fortnite.util.CisUtilityFile;

import java.util.ArrayList;

/**
 * Class to hold business logic for retrieving player names
 *
 * @author Ben Proulx
 * @since 20261030
 */
public class MatchEntryBO {


    public ArrayList<MatchEntry> processSelectAllByPlayerName(String name) {

        //**********************************************************************
        // This could be done using the repository but there will be times when
        // jdbc will be useful.  For the reports, the requirements state that you
        // are to use jdbc to obtain the data for the report.
        //**********************************************************************
        MatchEntryDAO matchEntryDAO = new MatchEntryDAO();
        ArrayList<MatchEntry> matches = matchEntryDAO.selectAllByPlayerName(name);

        //Also write the report to a file
        CisUtilityFile.writeReportToFile("matchEntryReport", matches);

        return matches;
    }

    public ArrayList<String> getAllPlayerNames(){
        MatchEntryDAO matchEntryDAO = new MatchEntryDAO();
        return matchEntryDAO.selectAllPlayers();
    }
}
