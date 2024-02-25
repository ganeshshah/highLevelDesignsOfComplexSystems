package com.design.snakeladder.snakeladdergame.factory;

import com.design.snakeladder.snakeladdergame.models.Board;
import com.design.snakeladder.snakeladdergame.models.GameLevel;
import com.design.snakeladder.snakeladdergame.strategy.gamestrategy.PopulateForeignEntitiesWithEasyStrategy;

public class PopulateBoardEntitiesFactory {

    public static void populatedForeignEntities(Board board, GameLevel gameLevel) {


        switch(gameLevel){
            case EASY -> {
                new PopulateForeignEntitiesWithEasyStrategy().populate(board);
            }
        }

    }
}
