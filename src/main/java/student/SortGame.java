package student;

import java.util.Comparator;
import java.util.stream.Stream;

public class SortGame {
    private SortGame() {
    }

    /**
     * sorts stream of BoardGame based on GameData.
     * @param games stream of BoardGames.
     * @param sortOn GameData for sorting.
     * @param ascending default sort by ascending.
     * @return stream of BoardGame based on sorting type.
     */
    public static Stream<BoardGame> sortOn(Stream<BoardGame> games, GameData sortOn, boolean ascending) {
        Comparator<BoardGame> comparator;
        if (sortOn == GameData.NAME) {
            comparator = Comparator.comparing(game -> game.getName().toLowerCase());
        } else if (sortOn == GameData.RATING) {
            comparator = Comparator.comparingDouble(BoardGame :: getRating);
        } else if (sortOn == GameData.DIFFICULTY) {
            comparator = Comparator.comparingDouble(BoardGame :: getDifficulty);
        } else if (sortOn == GameData.RANK) {
            comparator = Comparator.comparingDouble(BoardGame :: getRank);
        } else if (sortOn == GameData.MIN_PLAYERS) {
            comparator = Comparator.comparingInt(BoardGame :: getMinPlayers);
        } else if (sortOn == GameData.MAX_PLAYERS) {
            comparator = Comparator.comparingInt(BoardGame :: getMaxPlayers);
        } else if (sortOn == GameData.MIN_TIME) {
            comparator = Comparator.comparingInt(BoardGame :: getMinPlayTime);
        } else if (sortOn == GameData.MAX_TIME) {
            comparator = Comparator.comparingInt(BoardGame :: getMaxPlayTime);
        } else if (sortOn == GameData.YEAR) {
            comparator = Comparator.comparingInt(BoardGame :: getYearPublished);
        } else {
            comparator = Comparator.comparing(game -> game.getName().toLowerCase());
        }
        if (!ascending) {
            comparator = comparator.reversed();
        }
        return games.sorted(comparator);
    }

}
