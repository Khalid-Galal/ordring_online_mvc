package org.mycode.ordring_online_mvc.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
public class UserSession {
    private Long userId;
    private String username;
    private String password;
    private Boolean isAdmin;
}
