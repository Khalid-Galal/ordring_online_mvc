package org.mycode.ordring_online_mvc.controllers;


import org.mycode.ordring_online_mvc.entity.User;
import org.mycode.ordring_online_mvc.security.JwtUtil;
import org.mycode.ordring_online_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/login")
    public String loginPage() {
        return "Login";
    }
//    @PostMapping("/login")
//    public String handleLogin(@RequestParam String username,
//                              @RequestParam String password,
//                              Model model) {
//        Optional<User> user = userService.findByUsername(username);
//
//        if (user.isPresent() && user.get().getPassword().equals(password)) {
//            return "redirect:/order";
//        } else {
//            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
//
//            return "Login";
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<?> handleLogin(@RequestParam String username,
                                     @RequestParam String password) {
        Optional<User> user = userService.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            // Generate JWT token with user details
            String token = jwtUtil.generateToken(user.get().getUserId(), user.get().getUsername(), user.get().getIsAdmin());

            // Return the token to the client
            return ResponseEntity.ok(token);  // Client stores this token (in cookies or local storage)
        } else {
            // Invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
    }
}


}