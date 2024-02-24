package com.design.tictactoe.tictactoe.strategies.botplayingstrategies;

import com.design.tictactoe.tictactoe.models.Board;
import com.design.tictactoe.tictactoe.models.Cell;
import com.design.tictactoe.tictactoe.models.Move;
import com.design.tictactoe.tictactoe.models.Player;

import java.util.List;

public class RandomizedBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeNextMove(Board board, Player player) {

        for (List<Cell> row: board.getBoard()) {
            for (Cell cell: row) {
                if (cell.isEmpty()) {
                    Move move = new Move();
                    move.setSymbol(player.getSymbol());
                    move.setPlayer(player);
                    move.setCell(cell);
                    return move;
                }
            }
        }
        return null;
    }
}
