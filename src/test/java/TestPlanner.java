import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.BoardGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import student.Planner;
import student.IPlanner;
import student.GameData;


/**
 * JUnit test for the Planner class.
 *
 * Just a sample test to get you started, also using
 * setup to help out.
 */
public class TestPlanner {
    static Set<BoardGame> games;
    private IPlanner planner;

    @BeforeAll
    public static void setup() {
        games = new HashSet<>();
        games.add(new BoardGame("17 days", 6, 1, 8, 70, 70, 9.0, 600, 9.0, 2005));
        games.add(new BoardGame("Chess", 7, 2, 2, 10, 20, 10.0, 700, 10.0, 2006));
        games.add(new BoardGame("Go", 1, 2, 5, 30, 30, 8.0, 100, 7.5, 2000));
        games.add(new BoardGame("Go Fish", 2, 2, 10, 20, 120, 3.0, 200, 6.5, 2001));
        games.add(new BoardGame("golang", 4, 2, 7, 50, 55, 7.0, 400, 9.5, 2003));
        games.add(new BoardGame("GoRami", 3, 6, 6, 40, 42, 5.0, 300, 8.5, 2002));
        games.add(new BoardGame("Monopoly", 8, 6, 10, 20, 1000, 1.0, 800, 5.0, 2007));
        games.add(new BoardGame("Tucano", 5, 10, 20, 60, 90, 6.0, 500, 8.0, 2004));
    }

    @BeforeEach
    public void setupPlanner() {
        planner = new Planner(games);
    }

    /**
     * test filter(String filter) by name.
     * should return games that match name "Go".
     */
     @Test
    public void testFilterName() {
        IPlanner planner = new Planner(games);
        List<BoardGame> filtered = planner.filter("name == Go").toList();
        assertEquals(1, filtered.size());
        assertEquals("Go", filtered.get(0).getName());
    }

    /**
     * test filter(String filter) for empty string.
     * should return all games.
     */
    @Test
    public void testFilterNameEmpty() {
        List<BoardGame> filtered = planner.filter("").toList();
        assertEquals(8, filtered.size());
        assertEquals("17 days", filtered.get(0).getName());
        assertEquals("Tucano", filtered.get(7).getName());
    }

    /**
     * test filter(String filter) for no match.
     * should return empty.
     */
    @Test
    public void testFilterNoMatch() {
        List<BoardGame> result = planner.filter("name == InvalidGame").toList();
        assertEquals(0, result.size());
    }

    /**
     * test filter(String filter, GameData sortOn) for column.
     * should return games sorted by a specified column ascending.
     * sort by min players.
     */
    @Test
    public void testFilterSortOn() {
        List<BoardGame> result = planner.filter("", GameData.MIN_PLAYERS).toList();
        assertEquals(1, result.get(0).getMinPlayers());
    }

    /**
     * test filter(String filter, GameData sortOn, boolean ascending) for descending.
     * should return games sorted by a specified column descending.
     * sort by min players descending.
     */
    @Test
    public void testFilterDescending() {
        List<BoardGame> result = planner.filter("", GameData.MIN_PLAYERS, false).toList();
        assertEquals(10, result.get(0).getMinPlayers());
    }
}