package com.example.player.controller;

import com.example.player.dto.PlayerCardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("cart")
public class CartController {
    @GetMapping("")
    public String showCard(@SessionAttribute(value = "cart",required = false) PlayerCardDto playerCardDto, Model model){
        model.addAttribute("cart", playerCardDto);
        return "detail";
    }
}
