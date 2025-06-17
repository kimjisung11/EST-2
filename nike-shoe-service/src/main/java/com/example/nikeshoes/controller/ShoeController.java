package com.example.nikeshoes.controller;

import com.example.nikeshoes.domain.entity.Shoe;
import com.example.nikeshoes.service.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shoes")
public class ShoeController {

    private final ShoeService shoeService;

    @GetMapping
    public String showShoeList(Model model) {
        model.addAttribute("shoes", shoeService.findAll());
        return "shoes";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("shoe", new Shoe());
        return "shoe_register";
    }

    @PostMapping("/register")
    public String registerShoe(@Valid @ModelAttribute Shoe shoe, org.springframework.validation.BindingResult result) {
        if (result.hasErrors()) {
            return "shoe_register";
        }
        shoeService.save(shoe);
        return "redirect:/shoes";
    }

    // 상품 삭제 처리
    @PostMapping("/delete/{id}")
    public String deleteShoe(@PathVariable Long id) {
        shoeService.deleteById(id);
        return "redirect:/shoes";
    }
}


