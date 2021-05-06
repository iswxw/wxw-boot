package com.wxw.dao;

import com.wxw.domain.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketBookingJpaDao extends CrudRepository<Ticket, Integer> {

    Ticket findTicketByEmail(String email);
}
