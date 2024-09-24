package org.mycode.ordring_online_mvc.dao;

import org.mycode.ordring_online_mvc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}