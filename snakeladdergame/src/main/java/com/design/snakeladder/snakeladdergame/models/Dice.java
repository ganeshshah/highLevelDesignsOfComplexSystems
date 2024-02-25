package com.design.snakeladder.snakeladdergame.models;

import java.util.Random;

public class Dice {

    private Integer maxDiceNumber;
    public Dice(Integer maxDiceNumber){
        this.maxDiceNumber = maxDiceNumber;
    }
    // todo
    public Integer roll(){
       Random random = new Random();
       return random.nextInt(this.maxDiceNumber + 1);
    }
}
