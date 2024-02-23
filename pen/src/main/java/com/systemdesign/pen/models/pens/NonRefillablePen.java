package com.systemdesign.pen.models.pens;


import com.systemdesign.pen.models.nib.Nib;
import com.systemdesign.pen.models.refils.InkColour;

public abstract class NonRefillablePen extends Pen{

    private InkColour inkColour;
    private Nib nib;
    NonRefillablePen(Brand brand, PenType penType) {
        super(brand, penType);
    }
}
