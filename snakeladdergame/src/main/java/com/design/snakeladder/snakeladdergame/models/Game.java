package com.design.snakeladder.snakeladdergame.models;

import java.util.List;

public class Game {

    private Board board;
    private Dice dice;
    private List<Player> players;
    private List<Player> rankings;

    private GameStatus gameStatus;

    private Integer totalButtonPerPlayer;

    private Integer lastPlayerMoveIndex;

    public Board getBoard() {
        return board;
    }

    public Dice getDice() {
        return dice;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getRankings() {
        return rankings;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Integer getTotalButtonPerPlayer() {
        return totalButtonPerPlayer;
    }

    public Integer getLastPlayerMoveIndex() {
        return lastPlayerMoveIndex;
    }

    public Game(Board board, Dice dice, List<Player> players, List<Player> rankings, GameStatus gameStatus, Integer totalButtonPerPlayer, Integer lastPlayerMoveIndex) {
        this.board = board;
        this.dice = dice;
        this.players = players;
        this.rankings = rankings;
        this.gameStatus = gameStatus;
        this.totalButtonPerPlayer = totalButtonPerPlayer;
        this.lastPlayerMoveIndex = lastPlayerMoveIndex;
    }

}
