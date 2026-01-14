package com.live.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreboardTest {
    private Scoreboard scoreboard;
    private Team homeTeam;
    private Team awayTeam;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
        homeTeam = new Team("homeTeam");
        awayTeam = new Team("awayTeam");
    }

    @Test
    void shouldStartGame() {
        Long gameId = scoreboard.startGame(homeTeam, awayTeam);
        assertNotNull(gameId);
        assertEquals(1L, gameId);

        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
    }


}