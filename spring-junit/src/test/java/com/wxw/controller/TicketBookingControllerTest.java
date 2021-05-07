package com.wxw.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxw.domain.Ticket;
import com.wxw.services.TicketBookingService;
import org.assertj.core.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Rest 风格单元测试
 *  注意参数类型严格保持一致
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = TicketBookingController.class)
public class TicketBookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketBookingService ticketBookingService;

    @Test
    public void testNewTicket() throws Exception{
        String URI = "/booking/createTicket";
        Ticket ticket = new Ticket();
        ticket.setTicketId(101);
        ticket.setPassengerName("Senthil");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("senthil@msn.com");
        String jsonInput = this.converttoJson(ticket);

        Mockito.when(ticketBookingService.createTicket(Mockito.any(Ticket.class))).thenReturn(ticket);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonInput)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        System.out.println("jsonOutput = " + jsonOutput);
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void testGetTicketById() throws Exception{
        String URI= "/booking/getTicketById/{ticketId}";
        Ticket ticket = new Ticket();
        ticket.setTicketId(102);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");
        String jsonInput = this.converttoJson(ticket);
        Mockito.when(ticketBookingService.findTicketById(Mockito.any())).thenReturn(ticket);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
    }

    @Test
    public void testGetTicketByIdIndividual() throws Exception{
        String URI= "/booking/getTicketById/{ticketId}";
        Ticket ticket = new Ticket();
        ticket.setTicketId(1026);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");
        String jsonInput = this.converttoJson(ticket);

        Mockito.when(ticketBookingService.findTicketById(Mockito.any())).thenReturn(ticket);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        System.out.println(jsonOutput);
        assertThat(jsonInput).isEqualTo(jsonOutput);
        assertThat(ticket.getEmail()).isEqualTo("kumar@msn.com");
    }


    @Test
    public void testGetAllBookedTickets() throws Exception{
        String URI = "/booking/getAllTickets";
        Ticket ticket1 = new Ticket();
        ticket1.setTicketId(103);
        ticket1.setPassengerName("Suresh");
        ticket1.setFromStation("Chennai");
        ticket1.setToStation("Pune");
        ticket1.setBookingDate(new Date());
        ticket1.setEmail("ser@msn.com");

        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(104);
        ticket2.setPassengerName("Mani");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("mani@msn.com");

        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(ticket1);
        ticketList.add(ticket2);

        String jsonInput = this.converttoJson(ticketList);

        Mockito.when(ticketBookingService.getAllTickets()).thenReturn(ticketList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }

    @Test
    public void testFindByEmail() throws Exception{
        String URI = "/booking/getTicketByEmail/{email:.+}";
        Ticket ticket = new Ticket();
        ticket.setTicketId(105);
        ticket.setPassengerName("Revi");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("revi@msn.com");

        String jsonInput = this.converttoJson(ticket);

        Mockito.when(ticketBookingService.findTicketByEmail(Mockito.anyString())).thenReturn(ticket);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, "revi@msn.com").accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }

    @Test
    public void testDeleteTicketById() throws Exception{
        String URI = "/booking/deleteTicketById/ticket/{ticketId}";
        Ticket ticket = new Ticket();
        ticket.setTicketId(105);
        ticket.setPassengerName("Kumar");
        ticket.setFromStation("Chennai");
        ticket.setToStation("Pune");
        ticket.setBookingDate(new Date());
        ticket.setEmail("kumar@msn.com");

        Mockito.when(ticketBookingService.findTicketById(Mockito.any())).thenReturn(ticket);
        Mockito.when(ticketBookingService.deleteTicketById(Mockito.any())).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 105).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

    }

    @Test
    public void testUpdateTicket() throws Exception{

        String URI = "/booking/updateTicketbyId/{email:.+}/ticket/{ticketId}";
        Ticket ticket2 = new Ticket();
        ticket2.setTicketId(105);
        ticket2.setPassengerName("Maran");
        ticket2.setFromStation("Chennai");
        ticket2.setToStation("Pune");
        ticket2.setBookingDate(new Date());
        ticket2.setEmail("maran34@msn.com");
        String jsonInput = this.converttoJson(ticket2);

        Mockito.when(ticketBookingService.updateEmailById(Mockito.any(),Mockito.anyString())).thenReturn(ticket2);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI, "\"maran@msn.com\"",105).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }

    /**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object ticket) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(ticket);
    }

}
