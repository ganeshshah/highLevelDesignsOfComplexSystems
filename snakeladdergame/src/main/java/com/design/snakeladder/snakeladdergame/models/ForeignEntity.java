package com.design.snakeladder.snakeladdergame.models;

public  class ForeignEntity {

    private ForeignEntityType foreignEntityType;

    private Integer from;
    private Integer to;

    public ForeignEntity(ForeignEntityType foreignEntityType) {
        this.foreignEntityType = foreignEntityType;
    }


    public ForeignEntityType getForeignEntityType() {
        return foreignEntityType;
    }

    public void setForeignEntityType(ForeignEntityType foreignEntityType) {
        this.foreignEntityType = foreignEntityType;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
