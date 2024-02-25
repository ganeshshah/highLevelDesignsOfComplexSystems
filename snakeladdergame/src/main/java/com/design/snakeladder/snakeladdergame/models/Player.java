package com.design.snakeladder.snakeladdergame.models;


import java.util.List;

public class Player {

    private List<Button> buttons;
    private String name;
    private Integer score = 0;

    public Player(List<Button> buttons,String name){
        this.buttons = buttons;
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }
}
