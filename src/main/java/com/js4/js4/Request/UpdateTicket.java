package com.js4.js4.Request;

import ch.qos.logback.core.status.Status;
import com.js4.js4.Entity.Ticket;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicket {
    private long ticketId;
    @Enumerated(EnumType.STRING)
    private Ticket.Status status;
    @Enumerated(EnumType.STRING)
    private Ticket.Priority priority;
}
