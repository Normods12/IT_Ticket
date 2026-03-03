package com.js4.js4.Controller;

import com.js4.js4.Entity.Ticket;
import com.js4.js4.Request.AddTicket;
import com.js4.js4.Request.AddUser;
import com.js4.js4.Request.AssignTicket;
import com.js4.js4.Request.UpdateTicket;
import com.js4.js4.Response.GetTickets;
import com.js4.js4.Response.GetTicketsForStaff;
import com.js4.js4.Response.GetTicketsForUser;
import com.js4.js4.Response.GetUsers;
import com.js4.js4.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/about")
    public String greet(){
        return "This is about of IT Ticket Management System";
    }

    @PostMapping("/user")
    public void newUser(@RequestBody AddUser addUser){
        ticketService.newUser(addUser);
    }

    @GetMapping("/users")
    public List<GetUsers> allUsers(){
        return ticketService.allUsers();
    }

    @PostMapping("/ticket")
    public void newTicket(@RequestBody AddTicket addTicket){
        ticketService.newTicket(addTicket);
    }

    @GetMapping("/tickets/{id}")
    public List<GetTicketsForUser> userTickets(@PathVariable long id){

        return ticketService.userTickets(id);
    }

    @GetMapping("/tickets/unassigned")
    public List<GetTicketsForStaff> unassignedTickets(){
        return ticketService.unassignedTickets();
    }

    @PostMapping("/ticket/assign")
    public void assignTicket(@RequestBody AssignTicket assignTicket){
        ticketService.assignTickets(assignTicket);
    }

    @PostMapping("/ticket/update")
    public void updateTickets(@RequestBody UpdateTicket updateTicket){
        ticketService.updateTickets(updateTicket);
    }

    @GetMapping("/tickets")
    public List<GetTickets> allTickets(){
        return ticketService.allTickets();
    }

    @GetMapping("/tickets/filter/s")
    public List<GetTickets> getTicketsByStatus(@RequestParam("status")  String status){
        return ticketService.getTicketsByStatus(status);
    }

    @GetMapping("/tickets/filter/p")
    public List<GetTickets> getTicketsByPriority(@RequestParam("priority")  String priority){
        return ticketService.getTicketsByPriority(priority);
    }

}
