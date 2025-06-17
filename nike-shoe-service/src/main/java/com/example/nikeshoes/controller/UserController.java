
package com.example.nikeshoes.controller;

import com.example.nikeshoes.domain.entity.User;
import com.example.nikeshoes.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/register";
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "user/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
        return "redirect:/";
    }
}
