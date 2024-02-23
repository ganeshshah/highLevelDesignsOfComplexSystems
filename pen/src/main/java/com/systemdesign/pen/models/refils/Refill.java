package com.systemdesign.pen.models.refils;

import com.systemdesign.pen.models.nib.Nib;

public class Refill {

    private Nib nib;
    private RefilType refilType;

    private InkColour inkColour;

    private Refill(Nib nib, RefilType refilType, InkColour inkColour){
        this.nib = nib;
        this.refilType = refilType;
        this.inkColour = inkColour;
    }

    public Nib getNib() {
        return nib;
    }

    public RefilType getRefilType() {
        return refilType;
    }

    public InkColour getInkColour() {
        return inkColour;
    }

    public static RefillBuilder getBuilder(){
        return new RefillBuilder();
    }

    public static class RefillBuilder{
        private Nib nib;
        private RefilType refilType;
        private InkColour inkColour;

        public RefillBuilder setNib(Nib nib){
            this.nib = nib;
            return this;
        }
        public RefillBuilder setRefilType(RefilType refilType) {
            this.refilType = refilType;
            return this;
        }

        public RefillBuilder setInkColour(InkColour inkColour){
            this.inkColour = inkColour;
            return this;
        }

        public Refill build(){
            Refill refill = new Refill(nib,refilType,inkColour);
            return refill;
        }
    }
}
