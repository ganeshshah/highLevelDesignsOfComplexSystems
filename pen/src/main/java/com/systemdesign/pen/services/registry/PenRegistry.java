//package com.systemdesign.pen.services.registry;
//
//import com.systemdesign.pen.models.pens.*;
//
//import java.util.HashMap;
//
//public class PenRegistry {
//
//    private static PenRegistry penRegistry = null;
//
//    private PenRegistry() {
//    }
//
//    private HashMap<String, Pen> penRepo = new HashMap<>();
//
//    public Pen getPenInstanceOf(PenType penType) {
//
//        if (penRepo.containsKey(penType.toString())) {
//            return penRepo.get(penType);
//        } else {
//            Pen pen;
//            switch (penType) {
//
//                case BALL:
//                    pen = BallPen();
//                case GEL:
//                    pen = GelPen();
//                case FOUNTAIN:
//                    pen = FountainPen();
//                case MARKER:
//                    pen = MarkerPen();
//
//                    penRepo.put(penType.toString(), pen);
//            }
//            return pen;
//        }
//
//    }
//
//    public static synchronized PenRegistry getInstance() {
//        if (penRegistry == null) {
//            penRegistry = new PenRegistry();
//        }
//        return penRegistry;
//    }
//
//}
