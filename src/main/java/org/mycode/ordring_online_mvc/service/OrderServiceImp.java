package org.mycode.ordring_online_mvc.service;

import org.mycode.ordring_online_mvc.dao.OrderRepository;
import org.mycode.ordring_online_mvc.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService{


    @Autowired
    private OrderRepository orderRepository;  // Assume this is an interface extending JpaRepository

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional
    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
}
