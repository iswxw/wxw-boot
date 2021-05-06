package com.wxw.controller;

import com.wxw.domain.Ticket;
import com.wxw.services.TicketBookingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class TicketBookingController {

    @Resource
    private TicketBookingService ticketBookingService;

    @GetMapping("/getAllTickets")
    public List<Ticket> getAllTickets(){
        return (List<Ticket>) ticketBookingService.getAllTickets();
    }

    @GetMapping("/getTicketById/{ticketId}")
    public Ticket getTicketById(@PathVariable Integer ticketId){
        return ticketBookingService.findTicketById(ticketId);
    }

    @GetMapping("/getTicketByEmail/{email:.+}")
    public Ticket getTicketByEmail(@PathVariable String email){
        return ticketBookingService.findTicketByEmail(email);
    }

    @PutMapping("/updateTicketbyId/{email:.+}/ticket/{ticketId}")
    public Ticket updateTicketById(@PathVariable String email, @PathVariable Integer ticketId){
        return ticketBookingService.updateEmailById(ticketId, email);
    }

    @PostMapping("/createTicket")
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketBookingService.createTicket(ticket);
    }

    @DeleteMapping("/deleteTicketById/ticket/{ticketId}")
    public boolean deleteTicketById(@PathVariable Integer ticketId){
        return ticketBookingService.deleteTicketById(ticketId);
    }

}
