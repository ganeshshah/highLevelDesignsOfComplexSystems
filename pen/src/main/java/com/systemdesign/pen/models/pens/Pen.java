package com.systemdesign.pen.models.pens;

import com.systemdesign.pen.models.refils.InkColour;

public abstract class Pen {

    private Brand brand;
    private PenType penType;

    Pen(Brand brand, PenType penType){
        this.brand = brand;
        this.penType = penType;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public PenType getPenType() {
        return penType;
    }

    public void setPenType(PenType penType) {
        this.penType = penType;
    }
    abstract void write();

}
