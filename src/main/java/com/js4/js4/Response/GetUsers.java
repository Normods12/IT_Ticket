package com.js4.js4.Response;

import com.js4.js4.Entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetUsers {
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private User.Role role;
}
