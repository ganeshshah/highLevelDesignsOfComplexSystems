package com.design.tictactoe.tictactoe.models;

public abstract class Player {

    private Symbol symbol;
    private PlayerType playerType;
    private String name;

    public Player(Symbol symbol, PlayerType playerType, String name) {
        this.symbol = symbol;
        this.playerType = playerType;
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getName(){
        return name;
    }

    public abstract Move makeMove(Board board);

}
