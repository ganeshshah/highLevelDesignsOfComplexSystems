package com.systemdesign.pen.models.pens;

import com.systemdesign.pen.models.nib.Nib;
import com.systemdesign.pen.models.nib.NibType;
import com.systemdesign.pen.models.refils.InkColour;
import com.systemdesign.pen.models.refils.RefilType;
import com.systemdesign.pen.models.refils.Refill;

public class BallPen extends RefillablePen {

    
    public BallPen(Refill refill, Brand brand, PenType penType) {
        super(refill, brand, penType);
    }

    @Override
    void write() {
        System.out.println("Ball pen writing strategy");
    }

}
