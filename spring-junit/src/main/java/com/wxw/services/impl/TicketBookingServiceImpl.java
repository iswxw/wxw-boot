package com.wxw.services.impl;

import com.wxw.dao.TicketBookingJpaDao;
import com.wxw.domain.Ticket;
import com.wxw.services.TicketBookingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
//@Transactional
public class TicketBookingServiceImpl implements TicketBookingService {

    @Resource
    private TicketBookingJpaDao ticketBookingJpaDao;

    @Override
    public Ticket findTicketByEmail(String email) {
        return ticketBookingJpaDao.findTicketByEmail(email);
    }

    @Override
    public Iterable<Ticket> getAllTickets() {
        return ticketBookingJpaDao.findAll();
    }

    @Override
    public Ticket findTicketById(Integer ticketId) {
        return ticketBookingJpaDao.findById(ticketId).orElse(null);
    }

    @Override
    public Ticket updateEmailById(Integer ticketId, String email) {
        Ticket ticket = ticketBookingJpaDao.findById(ticketId).orElse(null);
        ticket.setEmail(email);
        return ticketBookingJpaDao.save(ticket);
    }

    @Override
    public boolean deleteTicketById(Integer ticketId) {
        ticketBookingJpaDao.deleteById(ticketId);
        Ticket ticket = ticketBookingJpaDao.findById(ticketId).orElse(null);
        if(null == ticket){
            return true;
        }
        return false;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketBookingJpaDao.save(ticket);
    }
}
