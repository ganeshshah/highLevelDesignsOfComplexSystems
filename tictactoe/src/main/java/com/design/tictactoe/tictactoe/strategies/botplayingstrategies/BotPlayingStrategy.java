package com.design.tictactoe.tictactoe.strategies.botplayingstrategies;

import com.design.tictactoe.tictactoe.models.Board;
import com.design.tictactoe.tictactoe.models.Move;
import com.design.tictactoe.tictactoe.models.Player;

public interface BotPlayingStrategy {

    Move makeNextMove(Board board, Player player);
}
