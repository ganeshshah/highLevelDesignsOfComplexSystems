package com.lowleveldesign.parkinglot.strategies.spotassignmentstrategy;

import com.lowleveldesign.parkinglot.models.EntryGate;
import com.lowleveldesign.parkinglot.models.ParkingLot;
import com.lowleveldesign.parkinglot.models.ParkingSpot;
import com.lowleveldesign.parkinglot.models.ParkingSpotType;

public interface SpotAssignmentStrategy {

    ParkingSpot assignSpot(ParkingLot parkingLot,
                           ParkingSpotType spotType,
                           EntryGate entryGate);

}
