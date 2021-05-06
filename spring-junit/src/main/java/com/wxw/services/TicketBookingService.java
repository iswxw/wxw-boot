package com.wxw.services;


import com.wxw.domain.Ticket;

public interface TicketBookingService {

    Ticket findTicketByEmail(String email);

    Iterable<Ticket> getAllTickets();

    Ticket findTicketById(Integer ticketId);

    Ticket updateEmailById(Integer ticketId, String email);

    boolean deleteTicketById(Integer ticketId);

    Ticket createTicket(Ticket ticket);

}
