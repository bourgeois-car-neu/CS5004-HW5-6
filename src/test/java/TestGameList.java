import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * should return empty list.
     */
    @Test
    public void testGetGameNamesEmpty() {
        assertEquals(List.of(), list.getGameNames());
    }

    /**
     * test getGameNames() for ascending alphabetical order.
     * simultaneously tests addToList() for "all".
     * should return list in alphabetical order.
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
     * should return single name to list.
     */
    @Test
    public void testAddToListSingle() {
        BoardGame game = new BoardGame("Chess", 0, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game);
        list.addToList("Chess", filtered);
        assertEquals(List.of("Chess"), list.getGameNames());
    }

    /**
     * test addToList() for number selected.
     * should return 1st element in list.
     */
    @Test
    public void testAddToListNumber() {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1", filtered);
        assertEquals(List.of("Monopoly"), list.getGameNames());
    }

    /**
     * test addToList() for range.
     * should return range of elements in list.
     */
    @Test
    public void testAddToListRange() {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1-2", filtered);
        assertEquals(List.of("Chess", "Monopoly"), list.getGameNames());
    }

    /**
     * test addToList() for range outside list.
     * should return 3 elements if range is 1-5.
     */
    @Test
    public void testAddToListOutsideRange() {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1-5", filtered);
        assertEquals(List.of("Azul", "Chess", "Monopoly"), list.getGameNames());
    }

    /**
     * test addToList() for invalid String.
     * should return error.
     */
    @Test
    public void testAddToListInvalid() {
        BoardGame game = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game);
        assertThrows(IllegalArgumentException.class, () -> list.addToList("invalid", filtered));
    }

    /**
     * test clear() for all games in list.
     * should return empty list.
     */
    @Test
    public void testClear() {
        BoardGame game = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game);
        list.addToList("Monopoly", filtered);
        list.clear();
        assertEquals(List.of(), list.getGameNames());
    }

    /**
     * test count() for the list of games.
     * should return the number of elements in the list.
     */
    @Test
    public void testCount() {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1-3", filtered);
        assertEquals(3, list.count());
    }

    /**
     * test count() for the list of 0 games.
     * should return 0.
     */
    @Test
    public void testCountZero() {
        assertEquals(0, list.count());
    }

    /**
     * test saveGame() for the list of games.
     * should write to new file.
     */
    @Test
    public void testSaveGameNew() throws IOException {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1-3", filtered);
        list.saveGame("testfile.txt");
        List<String> lines = Files.readAllLines(Path.of("testfile.txt"));
        assertEquals(list.getGameNames(), lines);
    }

    /**
     * test saveGame() for the list of games.
     * should overwrite an existing file.
     */
    @Test
    public void testSaveGameOverwrite() throws IOException {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1);
        list.addToList("Monopoly", filtered);
        list.saveGame("testfile.txt");
        list.clear();
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered2 = Stream.of(game2);
        list.addToList("Chess", filtered2);
        list.saveGame("testfile.txt");
        List<String> lines = Files.readAllLines(Path.of("testfile.txt"));
        assertEquals(list.getGameNames(), lines);
    }

    /**
     * test saveGame() for empty list of games.
     * should create a new file.
     */
    @Test
    public void testSaveGameEmpty() throws IOException {
        list.saveGame("testfile.txt");
        List<String> lines = Files.readAllLines(Path.of("testfile.txt"));
        assertEquals(List.of(), lines);
    }

    /**
     * test removeFromList() for single name.
     */
    @Test
    public void testRemoveFromListSingle() throws IOException {
        BoardGame game1 = new BoardGame("Monopoly", 1, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game2 = new BoardGame("Chess", 2, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        BoardGame game3 = new BoardGame("Azul", 3, 0, 0, 0, 0, 0.0, 0, 0.0, 0);
        Stream<BoardGame> filtered = Stream.of(game1, game2, game3);
        list.addToList("1-3", filtered);
        list.removeFromList("Azul");
        assertEquals(List.of("Chess", "Monopoly"), list.getGameNames());
    }

}
