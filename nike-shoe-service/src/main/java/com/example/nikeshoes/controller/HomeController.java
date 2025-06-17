package com.example.nikeshoes.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isLogin = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());
        String userId = "";

        if (isLogin) {
            userId = auth.getName(); // 로그인한 유저 아이디
        }

        model.addAttribute("isLogin", isLogin);
        model.addAttribute("userId", userId);

        return "index";
    }
}

