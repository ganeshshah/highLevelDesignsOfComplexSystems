package com.design.snakeladder.snakeladdergame.controller;

import com.design.snakeladder.snakeladdergame.models.*;

import java.util.List;

public class GameController {

    private Game game;

    public Game createGame(Board board, Dice dice, List<Player> players, Integer totalButtonPerPlayer){
        return new Game(board,dice,players,GameStatus.IN_PROGRESS,totalButtonPerPlayer,-1);
    }



}
