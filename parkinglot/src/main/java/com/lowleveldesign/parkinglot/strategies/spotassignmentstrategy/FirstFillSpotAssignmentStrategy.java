package com.lowleveldesign.parkinglot.strategies.spotassignmentstrategy;

import com.lowleveldesign.parkinglot.models.*;

public class FirstFillSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public ParkingSpot assignSpot(ParkingLot parkingLot, ParkingSpotType spotType, EntryGate entryGate) {
        for (ParkingFloor parkingFloor: parkingLot.getParkingFloor()) {
            for (ParkingSpot parkingSpot: parkingFloor.getParkingSpot()) {
                if (parkingSpot.getParkingSpotStatus().equals(ParkingSpotStatus.AVAILABLE)
                        && parkingSpot.getSpotType().equals(spotType)) {
                    return parkingSpot;
                }
            }
        }
        return null;
    }
}
