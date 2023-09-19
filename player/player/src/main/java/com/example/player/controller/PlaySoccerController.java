package com.example.player.controller;

import com.example.player.model.PlayerSoccer;
import com.example.player.service.IPlaySoccerService;
import com.example.player.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public String showList(@RequestParam(defaultValue = "0",required = false) int page,
                           @RequestParam(defaultValue = "",required = false) String searchName,
            @RequestParam(defaultValue = "2",required = false) int size
            ,Model model) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("fullName").ascending());
        Page<PlayerSoccer> playerSoccers = iPlaySoccerService.showListPage(pageable);
        model.addAttribute("list", playerSoccers);
        model.addAttribute("size", size);
        return "list";
    }

    @GetMapping("/showDetail")
    public String showDetail(@RequestParam int id, Model model) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        model.addAttribute("playSoccer", playerSoccer);
        model.addAttribute("id", playerSoccer.getId());
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        iPlaySoccerService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/showCreate")
    public String showCreate(Model model) {
        model.addAttribute("PlaySoccer", new PlayerSoccer());
        model.addAttribute("list", iPositionService.showList());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute PlayerSoccer playerSoccer) {
        iPlaySoccerService.add(playerSoccer);
        return "redirect:/";
    }

    @GetMapping("/showEdit{id}")
    public String showEdit(@RequestParam int id, Model model) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        model.addAttribute("playSoccer", playerSoccer);
        model.addAttribute("list", iPositionService.showList());
        return "edit";
    }
    @PostMapping ("/edit")
    public String edit(PlayerSoccer playSoccer) {
        iPlaySoccerService.edit(playSoccer);
        return "redirect:/";
    }
}
