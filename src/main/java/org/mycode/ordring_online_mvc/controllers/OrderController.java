package org.mycode.ordring_online_mvc.controllers;
import org.mycode.ordring_online_mvc.entity.Order;
import org.mycode.ordring_online_mvc.security.JwtUtil;
import org.mycode.ordring_online_mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/order")
    public String orderPage(@RequestHeader("Authorization") String token, Model model) {
        // Remove the "Bearer " prefix from the token (if included)
        token = token.substring(7);
        // Validate the token and extract user details
        if (jwtUtil.isTokenExpired(token)) {
            // Token is expired, return to login page
            return "redirect:/login";
        }
        // Extract user data from token
        String username = jwtUtil.extractUsername(token);
        Long userId = jwtUtil.extractUserId(token);
        Boolean isAdmin = jwtUtil.extractIsAdmin(token);

        // Pass the user information to the view
        model.addAttribute("username", username);
        model.addAttribute("isAdmin", isAdmin);

        // Return the Thymeleaf template for the order page
        return "OrderPage";

    }
    // Page to display the Order list in HTML (using Thymeleaf or any other templating engine)
//    @Controller
//    public static class PageController {
//        @GetMapping("/order")
//        public String orderPage() {
//            return "OrderPage"; // This will map to a Thymeleaf template named "OrderPage.html"
//        }
//    }
}