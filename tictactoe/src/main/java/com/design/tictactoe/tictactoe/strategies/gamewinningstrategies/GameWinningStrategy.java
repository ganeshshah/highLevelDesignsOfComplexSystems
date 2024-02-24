package com.design.tictactoe.tictactoe.strategies.gamewinningstrategies;

import com.design.tictactoe.tictactoe.models.Board;
import com.design.tictactoe.tictactoe.models.Cell;
import com.design.tictactoe.tictactoe.models.Player;

public interface GameWinningStrategy {
    boolean checkIfWon(Board board, Player player, Cell moveCell);
}
