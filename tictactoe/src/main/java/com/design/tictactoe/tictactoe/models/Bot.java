package com.design.tictactoe.tictactoe.models;

import com.design.tictactoe.tictactoe.strategies.botplayingstrategies.BotPlayingStrategy;

public class Bot extends Player{

    private BotPlayingStrategy botPlayingStrategy;
    private BotDifficultyLevel botDifficultyLevel;
    public Bot(Symbol symbol, String name,BotDifficultyLevel botDifficultyLevel, BotPlayingStrategy botPlayingStrategy) {
        super(symbol, PlayerType.BOT, name);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategy;
    }

    @Override
    public Move makeMove(Board board) {
        return null;
    }
}
