package org.mycode.ordring_online_mvc.controllers;
import org.mycode.ordring_online_mvc.entity.Order;
import org.mycode.ordring_online_mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    // API to return orders as JSON
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping("/orders/bulk")
    public ResponseEntity<?> saveOrders(@RequestBody List<Order> orders) {
        orderService.saveAll(orders);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Page to display the Order list in HTML (using Thymeleaf or any other templating engine)
    @Controller
    public static class PageController {
        @GetMapping("/order")
        public String orderPage() {
            return "OrderPage"; // This will map to a Thymeleaf template named "OrderPage.html"
        }
    }
}
