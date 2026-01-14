package com.live.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scoreboard {

    private final Map<Long, Game> games = new HashMap<>();
    private Long idCounter = 0L;

    public Long startGame(Team homeTeam, Team awayTeam) {
        Game game = new Game(homeTeam, awayTeam, 0, 0);
        Long id = ++idCounter;
        games.put(id, game);
        return id;
    }

    public void finishGame(Long gameId) {
        games.remove(gameId);
    }

    public void updateScore(Long gameId, int homeScore, int awayScore) {
    }

    public List<Game> getSummary() {
        return new ArrayList<>(games.values());
    }
}
