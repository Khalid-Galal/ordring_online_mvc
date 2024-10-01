package org.mycode.ordring_online_mvc.service;

import org.mycode.ordring_online_mvc.dao.OrderRepository;
import org.mycode.ordring_online_mvc.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService{


    @Autowired
    private OrderRepository orderRepository;

    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public void saveAll(List<Order> orders) {
        orderRepository.saveAll(orders);  // This will save the list of orders to the database
    }
}
