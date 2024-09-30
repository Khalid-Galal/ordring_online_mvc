package org.mycode.ordring_online_mvc.controllers;

import org.mycode.ordring_online_mvc.dao.OrderRepository;
import org.mycode.ordring_online_mvc.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/order")
    public String orderPage() {
        return "OrderPage";
    }
}
