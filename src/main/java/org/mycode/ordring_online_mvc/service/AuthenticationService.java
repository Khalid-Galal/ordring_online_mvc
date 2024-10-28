package org.mycode.ordring_online_mvc.service;

import org.mycode.ordring_online_mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;


@Service
public class AuthenticationService {

    @Autowired
    private EntityManager entityManager;

    public boolean authenticate(String username, String password) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = query.getSingleResult();
            return user != null;
        } catch (Exception e) {
            return false;
        }
    }
}

