package com.wxw.service;


import com.wxw.dao.TicketBookingJpaDao;
import com.wxw.domain.Ticket;
import com.wxw.services.TicketBookingService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketBookingServiceTest {

    @MockBean
    private TicketBookingJpaDao ticketBookingJpaDao;

    @Autowired
    private TicketBookingService ticketBookingService;

    @Test
    public void testCreateTicket(){
        Ticket ticket = new Ticket();
        ticket.setTicketId(100);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");
        Mockito.when(ticketBookingJpaDao.save(ticket)).thenReturn(ticket);
        Assertions.assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
    }

    @Test
    public void testGetTicketById() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(102);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");
        Mockito.when(ticketBookingJpaDao.findById(100).orElse(null)).thenReturn(ticket);
        Assertions.assertThat(ticketBookingService.findTicketById(100)).isEqualTo(ticket);
    }

    @Test
    public void testGetAllBookedTickets() throws Exception{
        Ticket ticket1 = new Ticket();
        ticket1.setPassengerName("Suresh");
        ticket1.setFromStation("Chennai");
        ticket1.setToStation("Pune");
        ticket1.setBookingDate(new Date());
        ticket1.setEmail("ser@msn.com");

        Ticket ticket2 = new Ticket();
        ticket2.setPassengerName("Mani");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("mani@msn.com");

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        Mockito.when(ticketBookingJpaDao.findAll()).thenReturn(ticketList);
        Assertions.assertThat(ticketBookingService.getAllTickets()).isEqualTo(ticketList);
    }


    @Test
    public void testFindByEmail() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Revi");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("revi@msn.com");

        Mockito.when(ticketBookingJpaDao.findTicketByEmail("revi@msn.com")).thenReturn(ticket);
        Assertions.assertThat(ticketBookingService.findTicketByEmail("revi@msn.com")).isEqualTo(ticket);
    }

    @Test
    public void testDeleteTicketById() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(105);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");

        Mockito.when(ticketBookingJpaDao.save(ticket)).thenReturn(ticket);
        Mockito.when(ticketBookingJpaDao.findById(105).orElse(null)).thenReturn(ticket);
        ticketBookingJpaDao.deleteById(ticket.getTicketId());
        Mockito.when(ticketBookingJpaDao.findById(105).orElse(null)).thenReturn(ticket);
        Assert.assertNotEquals(ticket, new Ticket());
        Assert.assertEquals(ticketBookingService.deleteTicketById(105), false);
    }

    @Test
    public void testDeleteTicketByNull() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setTicketId(1005);
        Ticket nullTicket = null;
        Mockito.when(ticketBookingJpaDao.findById(1005).orElse(null)).thenReturn(nullTicket);
        ticketBookingJpaDao.deleteById(ticket.getTicketId());
        Assert.assertEquals(ticketBookingService.deleteTicketById(1005), true);
    }

    @Test
    public void testUpdateTicket() throws Exception{

        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(100);
        ticket2.setPassengerName("Maran");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("maran@msn.com");

        Mockito.when(ticketBookingJpaDao.findById(100).orElse(null)).thenReturn(ticket2);
        ticket2.setEmail("maran100@msn.com");
        Mockito.when(ticketBookingJpaDao.save(ticket2)).thenReturn(ticket2);
        System.out.println(ticket2.getEmail());
        Assertions.assertThat(ticketBookingService.updateEmailById(100, "maran100@msn.com")).isEqualTo(ticket2);
    }

}
