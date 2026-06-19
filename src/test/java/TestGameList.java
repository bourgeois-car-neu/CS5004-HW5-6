import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import student.GameList;
import student.IGameList;
import student.BoardGame;
import java.util.stream.Stream;

/**
 * JUnit test for the GameList class.
 */
public class TestGameList {
    /** Field for GameList. */
    private IGameList list;


    @BeforeEach
    public void setup() {
        list = new GameList();
    }

    /**
     * test getGameNames() for empty list.
     */
    @Test
    public void testGetGameNamesEmpty() {
        assertEquals(List.of(), list.getGameNames());
    }

    /**
     * test getGameNames() for ascending alphabetical order.
     */
    @Test
    public void testGetGameNamesAscendAlphaOrder() {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("all", filtered);
        assertEquals(List.of("Azul", "Chess", "Monopoly"), list.getGameNames());
    }

    /**
     * test addToList() for a single name.
     */
    @Test
    public void testAddToListSingle() {
        BoardGame game = new BoardGame("Chess", 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game);
        list.addToList("Chess", filtered);
        assertEquals(List.of("Chess"), list.getGameNames());

    }
}
