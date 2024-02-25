package com.design.snakeladder.snakeladdergame.models;

public class Button {
    private Integer position;
    private ButtonStatus status;
    private ButtonColour colour;

    public Button(ButtonColour colour){
        this.position = 0;
        this.status = ButtonStatus.LOCKED;
        this.colour = colour;
    }
}
