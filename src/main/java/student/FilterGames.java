package student;
import java.util.stream.Stream;

/**
 * FilterGames class.
 * filters the list of games based on a filter string.
 * splits filter string into column, operator, value.
 * FilterGames dependency on Operations
 * FilterGames dependency on Planner
 */
public final class FilterGames {
    private FilterGames() {
    }

    private static boolean compareNumbers(double gameValue, Operations operator, double filterValue) {
        switch (operator) {
            case GREATER_THAN:
                return gameValue > filterValue;
            case GREATER_THAN_EQUALS:
                return gameValue >= filterValue;
            case LESS_THAN:
                return gameValue < filterValue;
            case LESS_THAN_EQUALS:
                return gameValue <= filterValue;
            case EQUALS:
                return gameValue == filterValue;
            case NOT_EQUALS:
                return gameValue != filterValue;
            default:
                return false;
        }
    }
    /**
     * filters stream of BoardGame based on filter String.
     * @param filter filter string to apply.
     * @param filteredGames stream of games to filter.
     * @return stream of games that match filter.
     */
    public static Stream<BoardGame> filter(String filter, Stream<BoardGame> filteredGames) {
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        filter = filter.strip();
        String[] parts = filter.split("\\s*" + operator.getOperator() + "\\s*");
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
            return filteredGames.filter(game -> {
                if (operator == Operations.EQUALS) {
                    return game.getName().equalsIgnoreCase(value);
                }
                if (operator == Operations.CONTAINS) {
                    return game.getName().toLowerCase().contains(value.toLowerCase());
                }
                return true;
            });
        } else if (column == GameData.MIN_PLAYERS) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getMinPlayers(), operator, numValue));
        } else if (column == GameData.MAX_PLAYERS) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getMaxPlayers(), operator, numValue));
        } else if (column == GameData.MIN_TIME) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getMinPlayTime(), operator, numValue));
        } else if (column == GameData.MAX_TIME) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getMaxPlayTime(), operator, numValue));
        } else if (column == GameData.RATING) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getRating(), operator, numValue));
        } else if (column == GameData.DIFFICULTY) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getDifficulty(), operator, numValue));
        } else if (column == GameData.RANK) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getRank(), operator, numValue));
        } else if (column == GameData.YEAR) {
            double numValue = Double.parseDouble(value);
            return filteredGames.filter(game -> compareNumbers(game.getYearPublished(), operator, numValue));
        }
        return filteredGames;
    }
}

