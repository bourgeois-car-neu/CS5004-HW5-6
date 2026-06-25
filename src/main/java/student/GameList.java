package student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
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

    /**
     * removes all games in the list (clears it out completely).
     */
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

    /**
     * save list of games to a new file.
     * @param filename The name of the file to save the list to.
     */
    @Override
    public void saveGame(String filename) {
        try {
            Path path = Path.of(filename);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, getGameNames());
        } catch (IOException error) {
            throw new RuntimeException("error saving file: " + error.getMessage());
        }
    }

    /**
     * add game to list.
     * adds either single name.
     * name from filtered list.
     * range of names.
     * all names.
     * error if invalid.
     * @param str      the string to parse and add games to the list.
     * @param filtered the filtered list to use as a basis for adding.
     */
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


    /**
     * removes a game or games from the list.
     * either single name.
     * name from filtered list.
     * range of names.
     * all names.
     * error if invalid.
     * @param str The string to parse and remove games from the list.
     * @throws IllegalArgumentException
     */
    @Override
    public void removeFromList(String str) throws IllegalArgumentException {
        if (str.contains("-")) {
            List<BoardGame> sortedGames = games.stream()
                    .sorted(Comparator.comparing(game -> game.getName().toLowerCase()))
                    .collect(Collectors.toList());
            String[] parts = str.split("-");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            List<BoardGame> toRemove = sortedGames.subList(start - 1, end);
            games.removeAll(toRemove);
        } else if (str.matches("\\d+")) {
            List<BoardGame> sortedGames = games.stream()
                    .sorted(Comparator.comparing(game -> game.getName().toLowerCase()))
                    .collect(Collectors.toList());
            int index = Integer.parseInt(str);
            games.remove(sortedGames.get(index - 1));
        } else if (str.equals("all")) {
            clear();
        } else {
            BoardGame found = games.stream()
                    .filter(game -> game.getName().equals(str))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("game not found"));
            games.remove(found);
        }
    }

}
