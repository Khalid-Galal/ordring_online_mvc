package org.mycode.ordring_online_mvc.dao;
import org.mycode.ordring_online_mvc.entity.Order;
import org.mycode.ordring_online_mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

}
