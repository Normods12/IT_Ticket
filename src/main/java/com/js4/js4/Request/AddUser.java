package com.js4.js4.Request;

import com.js4.js4.Entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUser {
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private User.Role role;
}
