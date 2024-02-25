package com.design.snakeladder.snakeladdergame.models;

import com.design.snakeladder.snakeladdergame.factory.PopulateBoardEntitiesFactory;

import java.util.HashMap;

public class Board {

    private Integer boardSize;
    private HashMap<Integer,ForeignEntity> positionMap;
    public Integer getBoardSize() {
        return boardSize;
    }

    public HashMap<Integer, ForeignEntity> getPositionMap() {
        return positionMap;
    }

    public Board(Integer boardSize,GameLevel gameLevel){

        this.boardSize = boardSize;
        this.positionMap = new HashMap<>();
        PopulateBoardEntitiesFactory.populatedForeignEntities(this,gameLevel);
    }

    public void displayBoard(){

        //for()

    }

}
