package com.design.tictactoe.tictactoe.controllers;

import com.design.tictactoe.tictactoe.exceptions.EmptyMovesUndoOperationException;
import com.design.tictactoe.tictactoe.models.Game;
import com.design.tictactoe.tictactoe.models.GameStatus;
import com.design.tictactoe.tictactoe.models.Player;
import com.design.tictactoe.tictactoe.strategies.gamewinningstrategies.GameWinningStrategy;


import java.util.List;

// Start a game
// Make a move
// Undo
// CheckWinner
// GetCurrentState
public class GameController {
    public Game createGame(int dimensionOfBoard,
                           List<Player> players,
                           List<GameWinningStrategy> strategies) {
        Game game = null;

        try {
            game = Game.create()
                    .setDimenston(dimensionOfBoard)
                    .addPlayers(players)
                    .addGameWinningStrategies(strategies)
                    .build();
        } catch (Exception exception) {
            System.out.println("We did something wrong");
            exception.printStackTrace();
        }

        return game;
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public boolean undo(Game game) throws  EmptyMovesUndoOperationException {
        return game.undo();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void display(Game game) {
        game.getBoard().printBoard();
    }
}
