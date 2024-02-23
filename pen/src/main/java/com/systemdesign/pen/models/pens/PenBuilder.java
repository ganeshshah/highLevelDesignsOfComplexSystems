package com.systemdesign.pen.models.pens;

import com.systemdesign.pen.models.nib.Nib;
import com.systemdesign.pen.models.nib.NibType;
import com.systemdesign.pen.models.refils.InkColour;
import com.systemdesign.pen.models.refils.RefilType;
import com.systemdesign.pen.models.refils.Refill;
import com.systemdesign.pen.services.factory.PenFactory;


public class PenBuilder {
    private Brand brand;
    private PenType penType;
    private Refill refill = null;
    private Nib nib;
    
    private InkColour inkColour;

    public Brand getBrand() {
        return brand;
    }

    public PenType getPenType() {
        return penType;
    }

    public Refill getRefill() {
        return refill;
    }

    public Nib getNib() {
        return nib;
    }

    public InkColour getInkColour() {
        return inkColour;
    }

    public static PenBuilder get(){
        return new PenBuilder();
    }

    public PenBuilder setBrand(Brand brand){
        this.brand = brand;
        return this;
    }
    public PenBuilder setPenType(PenType penType){
        this.penType = penType;
        return this;
    }

    public PenBuilder setInkColour(InkColour inkColour){
        this.inkColour = inkColour;
        return this;
    }

    public PenBuilder setNib(double radius, NibType nibType){
        this.nib = new Nib(radius,nibType);
        return this;
    }

    public PenBuilder setRefill(RefilType refilType){
        Refill refill = new Refill.RefillBuilder().setRefilType(refilType)
                .setInkColour(this.inkColour)
                .setNib(nib)
                .build();
        this.refill = refill;
        return this;
    }

    public Pen build() throws Exception {
        return new PenFactory().getPen(this);
    }
    
}
