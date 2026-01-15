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
        homeTeam = new Team("Spain");
        awayTeam = new Team("Brazil");
    }

    @Test
    void shouldStartGame() {
        Long gameId = scoreboard.startGame(homeTeam, awayTeam);
        assertNotNull(gameId);
        assertEquals(1L, gameId);

        List<Game> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
    }

    @Test
    void shouldFinishGame() {
        Long gameId = scoreboard.startGame(homeTeam, awayTeam);

        scoreboard.finishGame(gameId);

        List<Game> summary = scoreboard.getSummary();
        assertTrue(summary.isEmpty());
    }

    @Test
    void shouldUpdateScore() {
        Long gameId = scoreboard.startGame(homeTeam, awayTeam);

        scoreboard.updateScore(gameId, 1,2 );
        Game game = scoreboard.getSummary().get(0);

        assertEquals(1, game.getHomeScore());
        assertEquals(2, game.getAwayScore());
    }

    @Test
    void shouldThrowGameNotFoundExceptionForUpdateScore() {
        assertThrows(GameNotFoundException.class, () -> scoreboard.updateScore(5L, 2, 2));
    }

    @Test
    void shouldGetSummaryByTotalScore() {
        Long gameId1 = scoreboard.startGame(homeTeam, awayTeam);
        Long gameId2 = scoreboard.startGame(new Team("Mexico"), new Team("Canada"));

        scoreboard.updateScore(gameId1, 1, 5);
        scoreboard.updateScore(gameId2, 7, 2);

        List<Game> summary = scoreboard.getSummary();

        assertEquals(2, summary.size());
        assertEquals(9, summary.get(0).getTotalScore());
        assertEquals(6, summary.get(1).getTotalScore());
    }

    @Test
    void shouldGetSummaryByTotalScoreAndRecentlyAdded() {
        Long gameId1 = scoreboard.startGame(homeTeam, awayTeam);
        Long gameId2 = scoreboard.startGame(new Team("Mexico"), new Team("Canada"));

        scoreboard.updateScore(gameId1, 1, 5);
        scoreboard.updateScore(gameId2, 3, 3);

        List<Game> summary = scoreboard.getSummary();
        Game game1 = summary.get(0);
        Game game2 = summary.get(1);

        assertEquals(2, summary.size());
        assertEquals(6, game1.getTotalScore());
        assertEquals(6, game2.getTotalScore());
        assertEquals("Mexico", game1.getHomeTeam().getName());
        assertEquals("Canada", game1.getAwayTeam().getName());
    }
}