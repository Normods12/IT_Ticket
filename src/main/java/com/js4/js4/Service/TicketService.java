package com.js4.js4.Service;

import com.js4.js4.Entity.Ticket;
import com.js4.js4.Entity.User;
import com.js4.js4.Repository.TicketCommentRepo;
import com.js4.js4.Repository.TicketRepo;
import com.js4.js4.Repository.UserRepo;
import com.js4.js4.Request.AddTicket;
import com.js4.js4.Request.AddUser;
import com.js4.js4.Request.AssignTicket;
import com.js4.js4.Request.UpdateTicket;
import com.js4.js4.Response.GetTickets;
import com.js4.js4.Response.GetTicketsForStaff;
import com.js4.js4.Response.GetTicketsForUser;
import com.js4.js4.Response.GetUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketRepo ticketRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TicketCommentRepo ticketCommentRepo;

    public void newUser(AddUser addUser){
        User user = new User();
        user.setName(addUser.getName());
        user.setRole(addUser.getRole());
        user.setEmail(addUser.getEmail());
        userRepo.save(user);
    }

    public List<GetUsers> allUsers(){
        List<User> users = userRepo.findAll();
        List<GetUsers> getUsers = new ArrayList<>();
        for(User user: users){
            GetUsers getUser = new GetUsers();
            getUser.setName(user.getName());
            getUser.setRole(user.getRole());
            getUser.setId(user.getId());
            getUsers.add(getUser);
        }
        return getUsers;
    }

    public void newTicket(AddTicket addTicket){
        Ticket ticket = new Ticket();
        ticket.setDescription(addTicket.getDescription());
        User user = userRepo.findById(addTicket.getCreator_id())
                .orElseThrow(()-> new RuntimeException("User not found"));
        ticket.setCreator(user);
        ticket.setCreated_at(Date.valueOf(LocalDate.now()));
        ticket.setStatus(Ticket.Status.OPEN);
        ticket.setUpdated_at(Date.valueOf(LocalDate.now()));
        ticketRepo.save(ticket);
    }

    public List<GetTicketsForUser> userTickets(long id){
        List<Ticket> tickets = ticketRepo.findAll();
        List<GetTicketsForUser> getTickets = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(ticket.getCreator().getId() == id){
                GetTicketsForUser getTicket = new GetTicketsForUser();
                getTicket.setDescription(ticket.getDescription());
                getTicket.setId(ticket.getId());
                getTicket.setStatus(ticket.getStatus());
                getTicket.setCreated_by(ticket.getCreator().getId());
                getTicket.setCreated_at(ticket.getCreated_at());
                getTicket.setUpdated_at(ticket.getUpdated_at());
                getTickets.add(getTicket);
            }
        }
        return getTickets;
    }

    public List<GetTicketsForStaff> unassignedTickets(){

        List<Ticket> tickets = ticketRepo.findAll();
        List<GetTicketsForStaff> getTickets = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(ticket.getStatus().equals(Ticket.Status.OPEN)){
                GetTicketsForStaff getTicket = new GetTicketsForStaff();
                getTicket.setDescription(ticket.getDescription());
                getTicket.setId(ticket.getId());
                getTicket.setStatus(ticket.getStatus());
                getTicket.setCreated_by(ticket.getCreator().getId());
                getTicket.setCreated_at(ticket.getCreated_at());
                getTicket.setUpdated_at(ticket.getUpdated_at());
                getTicket.setTicket_comments(ticket.getTicket_comments());
                getTickets.add(getTicket);
            }
        }
        return getTickets;
    }

    public void assignTickets(AssignTicket assignTicket){
        Ticket ticket = ticketRepo.findById(assignTicket.getTicketId()).orElseThrow(()-> new RuntimeException("Ticket not found"));
        User user = userRepo.findById(assignTicket.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));
        ticket.setAssigned_staff(user);
        ticketRepo.save(ticket);
    }

    public void updateTickets(UpdateTicket updateTicket){
        Ticket ticket = ticketRepo.findById(updateTicket.getTicketId()).orElseThrow(()-> new RuntimeException("Ticket not found"));
        ticket.setPriority(updateTicket.getPriority());
        ticket.setStatus(updateTicket.getStatus());
        ticketRepo.save(ticket);
    }

    public List<GetTickets> allTickets(){
        List<Ticket> tickets = ticketRepo.findAll();
        List<GetTickets> getTickets = new ArrayList<>();
        for(Ticket ticket: tickets){
                GetTickets getTicket = new GetTickets();
                getTicket.setDescription(ticket.getDescription());
                getTicket.setId(ticket.getId());
                getTicket.setStatus(ticket.getStatus());
                getTicket.setCreated_by(ticket.getCreator().getId());
                getTicket.setCreated_at(ticket.getCreated_at());
                getTicket.setPriority(ticket.getPriority());
                getTicket.setUpdated_at(ticket.getUpdated_at());
                getTicket.setAssigned_staff(ticket.getAssigned_staff().getId());
                getTicket.setTicket_comments(ticket.getTicket_comments());
                getTickets.add(getTicket);
        }
        return getTickets;
    }

    public List<GetTickets> getTicketsByStatus(String status){



        List<Ticket> tickets = ticketRepo.findAll();
        List<GetTickets> getTickets = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(ticket.getStatus().name().equalsIgnoreCase(status)){
                GetTickets getTicket = new GetTickets();
                getTicket.setDescription(ticket.getDescription());
                getTicket.setId(ticket.getId());
                getTicket.setStatus(ticket.getStatus());
                getTicket.setCreated_by(ticket.getCreator().getId());
                getTicket.setCreated_at(ticket.getCreated_at());
                getTicket.setPriority(ticket.getPriority());
                getTicket.setUpdated_at(ticket.getUpdated_at());
                getTicket.setAssigned_staff(ticket.getAssigned_staff().getId());
                getTicket.setTicket_comments(ticket.getTicket_comments());
                getTickets.add(getTicket);
            }
        }
        return getTickets;
    }

    public List<GetTickets> getTicketsByPriority(String priority){

        List<Ticket> tickets = ticketRepo.findAll();
        List<GetTickets> getTickets = new ArrayList<>();
        for(Ticket ticket: tickets){
            if(ticket.getPriority().name().equalsIgnoreCase(priority)){
                GetTickets getTicket = new GetTickets();
                getTicket.setDescription(ticket.getDescription());
                getTicket.setId(ticket.getId());
                getTicket.setStatus(ticket.getStatus());
                getTicket.setCreated_by(ticket.getCreator().getId());
                getTicket.setCreated_at(ticket.getCreated_at());
                getTicket.setPriority(ticket.getPriority());
                getTicket.setUpdated_at(ticket.getUpdated_at());
                getTicket.setAssigned_staff(ticket.getAssigned_staff().getId());
                getTicket.setTicket_comments(ticket.getTicket_comments());
                getTickets.add(getTicket);
            }
        }
        return getTickets;
    }
}
