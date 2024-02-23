package com.systemdesign.pen.services.factory;

import com.systemdesign.pen.models.pens.*;

public class PenFactory {
    public Pen getPen(PenBuilder penBuilder){

        PenType penType = penBuilder.getPenType();

        switch(penType){
            case BALL : return new BallPen(penBuilder.getRefill(),penBuilder.getBrand(),penBuilder.getPenType());
            case GEL : return new GelPen(penBuilder.getRefill(),penBuilder.getBrand(),penBuilder.getPenType());
//            case FOUNTAIN: return new FountainPen();
//            case MARKER: return new MarkerPen();
        }
        return null;
    }
}
