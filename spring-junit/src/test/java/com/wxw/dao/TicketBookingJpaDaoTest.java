package com.wxw.dao;

import com.wxw.domain.Ticket;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TicketBookingJpaDaoTest {

    @MockBean
    private TicketBookingJpaDao ticketBookingJpaDao;

    @MockBean
    private TestEntityManager testEntityManager;

    @Test
    public void testNewTicket() throws Exception{
        Ticket ticket = getTicket();
        Ticket saveInDb = testEntityManager.persist(ticket);
        Ticket getFromInDb = ticketBookingJpaDao.findById(saveInDb.getTicketId()).orElse(null);
        Assertions.assertThat(getFromInDb).isEqualTo(saveInDb);
    }

    @Test
    public void testGetTicketById() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Senthil");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("senthil@msn.com");

        //Insert Data into in memory database
        Ticket saveInDb = testEntityManager.persist(ticket);
        //Get Data from DB
        Ticket getInDb = ticketBookingJpaDao.findById(ticket.getTicketId()).orElse(null);
        Assertions.assertThat(getInDb).isEqualTo(saveInDb);
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

        //Save into in memory database
        testEntityManager.persist(ticket1);
        testEntityManager.persist(ticket2);

        //Retrieve all tickets
        List<Ticket> ticketList = (List<Ticket>) ticketBookingJpaDao.findAll();

        Assert.assertEquals(2, ticketList.size());
    }

    @Test
    public void testFindByEmail() throws Exception{
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Revi");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("revi@msn.com");

        Ticket saveInDb = testEntityManager.persist(ticket);
        Ticket getInDb = ticketBookingJpaDao.findTicketByEmail(saveInDb.getEmail());

        Assert.assertEquals(saveInDb.getEmail(), getInDb.getEmail());
    }

    @Test
    public void testDeleteTicketById() throws Exception{
        Ticket ticket1 = new Ticket();
        ticket1.setPassengerName("Kalai");
        ticket1.setFromStation("Chennai");
        ticket1.setToStation("Pune");
        ticket1.setBookingDate(new Date());
        ticket1.setEmail("kal@msn.com");

        Ticket ticket2 = new Ticket();
        ticket2.setPassengerName("Selvi");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("selvi@msn.com");

        Ticket ticket = testEntityManager.persist(ticket1);
        testEntityManager.persist(ticket2);

        //delete one ticket DB
        testEntityManager.remove(ticket);

        List<Ticket> tickets = (List<Ticket>) ticketBookingJpaDao.findAll();
        Assert.assertEquals(tickets.size(), 1);

    }

    @Test
    public void testUpdateTicket(){

        Ticket ticket2 = new Ticket();
        ticket2.setPassengerName("Maran");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("maran@msn.com");

        testEntityManager.persist(ticket2);

        Ticket getFromDb = ticketBookingJpaDao.findTicketByEmail("maran@msn.com");
        getFromDb.setEmail("maran11@msn.com");
        testEntityManager.persist(getFromDb);

        Assertions.assertThat(getFromDb.getEmail()).isEqualTo("maran11@msn.com");
    }


    private Ticket getTicket() {
        Ticket ticket = new Ticket();
        ticket.setPassengerName("Siva");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Bangalore");
        ticket.setBookingDate(new Date());
        ticket.setEmail("siva@msn.com");
        return ticket;
    }
}
