package com.systemdesign.pen.models.nib;

public class Nib {
    private double radius;
    private NibType nibType;

    public Nib(double radius, NibType nibType) {
        this.radius = radius;
        this.nibType = nibType;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }



}
