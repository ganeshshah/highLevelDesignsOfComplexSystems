package com.lowleveldesign.parkinglot.dtos;


import com.lowleveldesign.parkinglot.models.Ticket;

public class GenerateTicketResponseDto extends ResponseDto {
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
