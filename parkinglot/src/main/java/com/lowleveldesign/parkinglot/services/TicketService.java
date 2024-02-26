package com.lowleveldesign.parkinglot.services;

import com.lowleveldesign.parkinglot.models.*;
import com.lowleveldesign.parkinglot.repositories.ParkingLotRepository;
import com.lowleveldesign.parkinglot.repositories.TicketRepository;
import com.lowleveldesign.parkinglot.strategies.spotassignmentstrategy.SpotAssignmentStrategy;

import java.util.Date;

public class TicketService {

    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;
    private ParkingLotRepository parkingLotRepository;

    public TicketService(TicketRepository ticketRepository,
                         SpotAssignmentStrategy spotAssignmentStrategy,
                         ParkingLotRepository parkingLotRepository) {
        this.ticketRepository = ticketRepository;
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.parkingLotRepository = parkingLotRepository;
    }

    public Ticket generateTicket(Long parkingLotId, Vehicle vehicle, ParkingSpotType spotType, EntryGate entryGate) {
        // 1. Find a spot
        //
        ParkingLot parkingLot = parkingLotRepository.getById(parkingLotId);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(
                parkingLot,
                spotType,
                entryGate
        );

        if (parkingLot == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setEntryGate(entryGate);
        ticket.setGeneratedBy(entryGate.getOperator());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicle(vehicle);
        ticket.setEntryTime(new Date());


        return ticket;
    }
}
