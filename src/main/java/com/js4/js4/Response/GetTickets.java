package com.js4.js4.Response;

import com.js4.js4.Entity.Ticket;
import com.js4.js4.Entity.TicketComment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTickets {
    private long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private Ticket.Status status;
    private Date created_at;
    private Date updated_at;
    @Enumerated(EnumType.STRING)
    private Ticket.Priority priority;
    private long created_by;
    private long assigned_staff;
    private List<TicketComment> ticket_comments;
}
