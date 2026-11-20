package ca.hccis.fortnite.entity;

import ca.hccis.fortnite.jpa.entity.MatchEntry;

import java.util.ArrayList;
import java.util.List;

public class MatchEntryDto {
    private List<MatchEntry> matchEntries;

    // default and parameterized constructor


    public MatchEntryDto() {
        matchEntries = new ArrayList<MatchEntry>();
    }

    public MatchEntryDto(List<MatchEntry> squashMatches) {
        this.matchEntries = squashMatches;
    }

    public void addSquashMatch(MatchEntry squashMatch) {
        this.matchEntries.add(squashMatch);
    }

    public List<MatchEntry> getMatchEntries() {
        return matchEntries;
    }

    public void setMatchEntries(List<MatchEntry> fortniteMatches) {
        this.matchEntries = fortniteMatches;
    }
}
