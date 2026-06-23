package student;
import java.util.stream.Stream;

/**
 * FilterGames class.
 * filters the list of games based on a filter string.
 * splits filter string into column, operator, value.
 * FilterGames dependency on Operations
 * FilterGames dependency on Planner
 */
public class FilterGames {
    public static Stream<BoardGame> filter(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        filter = filter.replaceAll(" ", "");
        String[] parts = filter.split(operator.getOperator());
        if (parts.length != 2) {
            return filteredGames;
        }
        GameData column;
        try {
            column = GameData.fromString(parts[0]);
        } catch (IllegalArgumentException error) {
            return filteredGames;
        }
        String value = parts[1];
        if (column == GameData.NAME) {
            return filteredGames.filter(game -> game.getName().equalsIgnoreCase(value));
        }
        return filteredGames;
    }
}
