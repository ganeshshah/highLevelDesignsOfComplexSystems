package com.lowleveldesign.parkinglot.controllers;


import com.lowleveldesign.parkinglot.dtos.GenerateTicketRequestDto;
import com.lowleveldesign.parkinglot.dtos.GenerateTicketResponseDto;
import com.lowleveldesign.parkinglot.services.TicketService;

public class TicketController {
    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(
            GenerateTicketRequestDto request
    ) {
        return null;
    }
}
