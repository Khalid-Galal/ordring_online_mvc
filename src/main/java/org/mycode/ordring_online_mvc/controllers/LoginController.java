package org.mycode.ordring_online_mvc.controllers;

import org.mycode.ordring_online_mvc.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    // Display the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "Login"; // Returns the login view
    }

    // Process the login form submission
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        if (authenticationService.authenticate(username, password)) {
            return "redirect:/order"; // Redirect to the orders page if authentication is successful
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "Login"; // Stay on the login page and show error
        }
    }
}
