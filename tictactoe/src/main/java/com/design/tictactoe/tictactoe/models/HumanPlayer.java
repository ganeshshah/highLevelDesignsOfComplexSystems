package com.design.tictactoe.tictactoe.models;

import java.util.Scanner;

public class HumanPlayer extends Player{

    public HumanPlayer(Symbol symbol, String name) {
        super(symbol, PlayerType.HUMAN, name);
    }

    @Override
    public Move makeMove(Board board) {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Tell Row Number starting from 1");
        int row = myObj.nextInt();

        System.out.println("Tell Column Number where you want to make the move from 1");
        int col = myObj.nextInt();


        Move move = new Move();
        move.setCell(board.getCell(row - 1, col - 1));
        move.setPlayer(this);
        move.setSymbol(this.getSymbol());

        return move;
    }
}
