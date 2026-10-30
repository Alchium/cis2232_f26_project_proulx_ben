package ca.hccis.files.bo;

import ca.hccis.files.entity.Match;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatchBOTest {

    private static final double DELTA = 0.0001;

    @Test
    void testCalculateWinRate_2Of5() {
        List<Match> matches = new ArrayList<>();
        String[] won = {"n", "y", "n", "n", "y"};
        for (int i = 0; i < won.length; i++) {
            matches.add(new Match(i + 1, "p", "bronze", 10, 2, won[i]));
        }
        assertEquals(40.0, MatchBO.calculate(matches), DELTA);
    }

    @Test
    void testCalculateWinRate_noMatches() {
        assertEquals(0.0, MatchBO.calculate(new ArrayList<>()), DELTA);
    }

    @Test
    void testAverageEliminations() {
        List<Match> matches = List.of(new Match(1, "p", "b", 1, 3, "y"), new Match(2, "p", "b", 1, 5, "n"));
        assertEquals(4.0, MatchBO.calculateAverageEliminations(matches), DELTA);
    }
}
