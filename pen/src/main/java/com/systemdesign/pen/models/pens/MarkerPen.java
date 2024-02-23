package com.systemdesign.pen.models.pens;

public class MarkerPen extends NonRefillablePen{

    MarkerPen(Brand brand, PenType penType) {
        super(brand, penType);
    }

    @Override
    void write() {
        System.out.println("I am a non-refillable Marker pen");
    }
}
