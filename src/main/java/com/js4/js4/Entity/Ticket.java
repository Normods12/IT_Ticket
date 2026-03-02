package com.js4.js4.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    public enum Status{
        OPEN,ASSIGNED,CLOSED,IN_PROGRESS,RESOLVED
    }
    @Enumerated(EnumType.STRING)
    private Status status;
    private Date created_at;
    private Date updated_at;
    public enum Priority{
            LOW,MEDIUM,HIGH
    }
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creater_by", nullable = false)
    private User creator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    private User assigned_staff;
    @OneToMany(mappedBy = "ticket",cascade = CascadeType.ALL)
    private List<TicketComment> ticket_comments;


}
