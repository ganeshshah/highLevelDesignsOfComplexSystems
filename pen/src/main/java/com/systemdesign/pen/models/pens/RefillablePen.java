package com.systemdesign.pen.models.pens;

import com.systemdesign.pen.models.refils.Refill;

public abstract class RefillablePen extends Pen {

   private Refill refill;

   public RefillablePen(Refill refill,Brand brand, PenType penType){
       super(brand,penType);
       this.refill = refill;
   }

}
