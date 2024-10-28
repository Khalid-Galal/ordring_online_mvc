package org.mycode.ordring_online_mvc.controllers;
import org.mycode.ordring_online_mvc.entity.Order;
import org.mycode.ordring_online_mvc.service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderServiceImp orderService;

    // API to return orders as JSON
    @GetMapping("/orders")
    @ResponseBody
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders/bulk")
    @ResponseBody
    public ResponseEntity<?> saveOrders(@RequestBody List<Order> orders) {
        orderService.saveAll(orders);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/order")
    public String showOrders() {
        return "OrderPage";  // Returns a view named 'OrderPage'
    }
}
