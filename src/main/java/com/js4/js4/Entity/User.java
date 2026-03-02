package com.js4.js4.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    public enum Role{
        EMPLOYEE,SUPPORT,ADMIN
    }
    @Enumerated(EnumType.STRING)
    private Role role;
    private String email;
    @OneToMany(mappedBy = "creator",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
    @OneToMany(mappedBy = "assigned_staff",cascade = CascadeType.ALL)
    private List<Ticket> assigned_tickets;
}
