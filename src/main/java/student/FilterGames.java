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

    /**
     * compareNumbers() is helper method for all number comparisons.
     * @param gameValue actual value from BoardGame (e.g. game.getMinPlayers).
     * @param operator the comparator operator.
     * @param filterValue the number on the other side of operator.
     * @return true for applicable operation.
     */
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
     * handles filter strings with commas: minPlayer>2,maxPlayer<10.
     * extracts operator.
     * applies filter.
     * @param filter filter string to apply.
     * @param filteredGames stream of games to filter.
     * @return stream of games that match filter.
     */
    public static Stream<BoardGame> filter(String filter, Stream<BoardGame> filteredGames) {
        // handles filter strings with commas: minPlayer>2,maxPlayer<10.
        if (filter.contains(",")) {
            String[] filters = filter.split(",");
            Stream<BoardGame> result = filteredGames;
            for (String singleFilter : filters) {
                result = filter(singleFilter, result);
            }
            return result;
        }
        // extracts operator.
        Operations operator = Operations.getOperatorFromStr(filter);
        if (operator == null) {
            return filteredGames;
        }
        filter = filter.strip();
        // splits into columns & values.
        // parts[0] = minPlayer (column)
        // parts[1] = "4" (value)
        String[] parts = filter.split("\\s*" + operator.getOperator() + "\\s*");
        if (parts.length != 2) {
            return filteredGames;
        }
        // identify column & convert into enum value.
        // minPlayer becomes enum.
        GameData column;
        try {
            column = GameData.fromString(parts[0]);
        } catch (IllegalArgumentException error) {
            return filteredGames;
        }
        // parts[1] becomes String named "value".
        String value = parts[1];
        // for NAME column.
        if (column == GameData.NAME) {
            return filteredGames.filter(game -> {
                // checks operator is used & applies matching string comparison.
                if (operator == Operations.EQUALS) {
                    return game.getName().equalsIgnoreCase(value);
                }
                if (operator == Operations.CONTAINS) {
                    return game.getName().toLowerCase().contains(value.toLowerCase());
                }
                // true for games that come after 'name' alphabetically.
                if (operator == Operations.GREATER_THAN) {
                    return game.getName().compareToIgnoreCase(value) > 0;
                }
                // true for games that come before 'name' alphabetically.
                if (operator == Operations.LESS_THAN) {
                    return game.getName().compareToIgnoreCase(value) < 0;
                }
                // true for games that have 'name' and all games after alphabetically.
                if (operator == Operations.GREATER_THAN_EQUALS) {
                    return game.getName().compareToIgnoreCase(value) >= 0;
                }
                // true for games that have 'name' and all games before it alphabetically.
                if (operator == Operations.LESS_THAN_EQUALS) {
                    return game.getName().compareToIgnoreCase(value) <= 0;
                }
                if (operator == Operations.NOT_EQUALS) {
                    return game.getName().compareToIgnoreCase(value) != 0;
                }
                return true;
            });
        } else if (column == GameData.MIN_PLAYERS) {
            // converts value from string to double.
            double numValue = Double.parseDouble(value);
            // for every game in stream...
            // get each game's MIN_PLAYER.
            // pass into compareNumbers() along with operator and value user input.
            // compareNumbers() returns true or false.
            // .filter() keeps only games that return true.
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

