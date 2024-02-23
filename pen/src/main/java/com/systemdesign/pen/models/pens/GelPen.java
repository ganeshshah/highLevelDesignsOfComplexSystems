package com.systemdesign.pen.models.pens;

import com.systemdesign.pen.models.refils.Refill;

public class GelPen extends RefillablePen {

    public GelPen(Refill refill, Brand brand, PenType penType) {
        super(refill,brand,penType);
    }

    @Override
    void write() {
        System.out.println("I am fluid like gel");
    }
}
