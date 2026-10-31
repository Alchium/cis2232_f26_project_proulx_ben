package ca.hccis.fortnite.entity;

import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the reports.
 * @author bjmaclean
 * @since 20261008
 */
public class ReportInput {
    private String name;
    private String startDate;
    private String endDate;

    private ArrayList<MatchEntry> fortniteMatches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



    public ArrayList<MatchEntry> getFortniteMatches() {
        return fortniteMatches;
    }

    public void setFortniteMatches(ArrayList<MatchEntry> fortniteMatches) {
        this.fortniteMatches = fortniteMatches;
    }
}