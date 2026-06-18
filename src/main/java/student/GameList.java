package student;

import java.util.List;
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
       return List.of();
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public int count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public void saveGame(String filename) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGame'");
    }

    @Override
    public void addToList(String str, Stream<BoardGame> filtered) {
        filtered.filter(name -> name.getName().equals(str)).findFirst()
                .ifPresent(game -> games.add(game));
    }


    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFromList'");
    }


}
