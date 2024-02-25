package com.design.snakeladder.snakeladdergame.strategy.gamestrategy;

import com.design.snakeladder.snakeladdergame.models.Board;
import com.design.snakeladder.snakeladdergame.models.ForeignEntity;
import com.design.snakeladder.snakeladdergame.models.ForeignEntityType;

import java.util.*;

public class PopulateForeignEntitiesWithEasyStrategy implements PopulateForeignEntityStrategy{

    private static Set<Integer> positionOccupied = new HashSet<>();

    @Override
    public void populate(Board board) {

        Integer boardSize =  board.getBoardSize();
        HashMap<Integer, ForeignEntity> entityPositionMap = board.getPositionMap();

        List<ForeignEntityType> foreignEntityTypes = List.of(ForeignEntityType.LADDER,ForeignEntityType.SNAKE);

       Integer totalForeignEntities = boardSize / ( 2 * 4 );

       for(int i=0;i<totalForeignEntities / foreignEntityTypes.size() ;i++){

           for(int j=0;j< foreignEntityTypes.size();j++){
               ForeignEntity entity = new ForeignEntity(foreignEntityTypes.get(j));
               checkAndSetFromTo(entityPositionMap,entity);
           }

       }
    }

    private void checkAndSetFromTo(HashMap<Integer, ForeignEntity> entityPositionMap, ForeignEntity foreignEntity) {

        Random random = new Random();
      if(foreignEntity.getForeignEntityType().equals(ForeignEntityType.SNAKE)){
          // snake logic
          Integer from = random.nextInt(100);
          while(positionOccupied.contains(from)){
              from = random.nextInt(100);
          }

          Integer to = random.nextInt(from);
          while(positionOccupied.contains(to)){
              to = random.nextInt(from);
          }
          positionOccupied.add(from);
          positionOccupied.add(to);

          foreignEntity.setFrom(from);
          foreignEntity.setTo(to);

          entityPositionMap.put(from,foreignEntity);
      }else if(foreignEntity.getForeignEntityType().equals(ForeignEntityType.LADDER)){

          Integer from = random.nextInt(60);
          while(positionOccupied.contains(from)){
              from = random.nextInt(100);
          }
          Integer to = from + random.nextInt(100-from);
          while(positionOccupied.contains(to)){
              to = random.nextInt(from);
          }

          positionOccupied.add(from);
          positionOccupied.add(to);

          foreignEntity.setFrom(from);
          foreignEntity.setTo(to);
          entityPositionMap.put(from,foreignEntity);
      }
        // ladder logic
    }
}
