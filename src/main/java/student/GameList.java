package student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.HashSet;


public class GameList implements IGameList {
    /** collection type, object type, variable. */
    private HashSet<BoardGame> games;


    /**
     * Constructor for the GameList.
     */
    public GameList() {
        games = new HashSet<>();
    }

    /**
     * gets a list of game names.
     * @return list.
     */
    @Override
    public List<String> getGameNames() {
        return games.stream()
                .map(game -> game.getName())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
       games.clear();
    }

    /**
     * counts size of list.
     * @return size of list as int.
     */
    @Override
    public int count() {
       return games.size();
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGame'");
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) {
        if (str.equals("all")) {
            filtered.forEach(game -> games.add(game));
        } else if (str.matches("\\d+")) {
            int index = Integer.parseInt(str);
            filtered.skip(index - 1).findFirst().ifPresent(game -> games.add(game));
        } else if (str.contains("-")) {
            String[] parts = str.split("-");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            filtered.skip(start - 1).limit(end - start + 1).forEach(game -> games.add(game));
        } else {
            BoardGame found = filtered.filter(name -> name.getName().equals(str)).findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("game not found")); games.add(found);
        }
    }


    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromList'");
    }
}
