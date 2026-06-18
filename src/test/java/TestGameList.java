import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import student.GameList;
import student.IGameList;

/**
 * JUnit test for the GameList class.
 */
public class TestGameList {
    /** Field for TestGameList. */
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
}
