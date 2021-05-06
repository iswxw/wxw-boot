package com.wxw.dao;

import com.wxw.domain.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBookingJpaDao extends CrudRepository<Ticket, Integer> {

    Ticket findTicketByEmail(String email);
}
