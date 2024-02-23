package com.systemdesign.pen.models.pens;

public class FountainPen extends NonRefillablePen{
    FountainPen(Brand brand, PenType penType) {
        super(brand, penType);
    }

    @Override
    void write() {

    }
}
