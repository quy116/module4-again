package com.example.player.controller;

import com.example.player.model.PlayerSoccer;
import com.example.player.service.IPlaySoccerService;
import com.example.player.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PlaySoccerController {
    @Autowired
    private IPlaySoccerService iPlaySoccerService;
    @Autowired
    private IPositionService iPositionService;
    @GetMapping("/")
    public String showList(Model model){
        model.addAttribute("list", iPlaySoccerService.showList());
        return "list";
    }
    @GetMapping("showDetail")
    public String showDetail(@RequestParam int id, Model model){
     PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
     model.addAttribute("playSoccer", playerSoccer);
     model.addAttribute("id", playerSoccer.getId());
        return "detail";
    }
    @GetMapping("delete")
    public String delete(@RequestParam int id){
        iPlaySoccerService.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("showCreate")
    public String showCreate(Model model){
        model.addAttribute("PlaySoccer", new PlayerSoccer());
        model.addAttribute("list", iPositionService.showList());
        return "create";
    }
    @PostMapping("create")
    public String create(@ModelAttribute PlayerSoccer playerSoccer ){
        iPlaySoccerService.add(playerSoccer);
        return "redirect:/";
    }
}
