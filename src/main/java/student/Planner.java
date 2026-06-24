package student;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Planner implements IPlanner {
    private Set<BoardGame> games;
    private Set<BoardGame> filtered;

    public Planner(Set<BoardGame> games) {
        this.games = games;
        this.filtered = new HashSet<>(games);
    }

    @Override
    public Stream<BoardGame> filter(String filter) {
        Stream<BoardGame> result = FilterGames.filter(filter, filtered.stream());
        filtered = result.collect(Collectors.toSet());
        return filtered.stream()
                .sorted(Comparator.comparing(game -> game.getName().toLowerCase()));
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn) {
        return filter(filter, sortOn, true);
    }

    @Override
    public Stream<BoardGame> filter(String filter, GameData sortOn, boolean ascending) {
        Stream<BoardGame> result = FilterGames.filter(filter, filtered.stream());
        filtered = result.collect(Collectors.toSet());
        return SortGame.sortOn(filtered.stream(), sortOn, ascending);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

}
