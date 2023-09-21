package com.example.player.controller;

import com.example.player.model.PlaySoccerDto;
import com.example.player.model.PlayerSoccer;
import com.example.player.service.IPlaySoccerService;
import com.example.player.service.IPositionService;
import com.example.player.service.ITeamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class PlaySoccerController {
    @Autowired
    private IPlaySoccerService iPlaySoccerService;
    @Autowired
    private IPositionService iPositionService;
    @Autowired
    private ITeamService iTeamService;

    @GetMapping("/")
    public String showList(@RequestParam(defaultValue = "0",required = false) int page,
                           @RequestParam(defaultValue = "",required = false) String searchName,
            @RequestParam(defaultValue = "2",required = false) int size,
            @RequestParam(defaultValue = "",required = false) String startDay,
            @RequestParam(defaultValue = "",required = false) String endDay
            ,Model model) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("full_name").descending());
        Page<PlayerSoccer> playerSoccers = iPlaySoccerService.showListPage(pageable,searchName,startDay,endDay);
        model.addAttribute("list", playerSoccers);
        model.addAttribute("size", size);
        model.addAttribute("searchName", searchName);
        model.addAttribute("startDay", startDay);
        model.addAttribute("endDay", endDay);
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
        model.addAttribute("playSoccerDto", new PlaySoccerDto());
        model.addAttribute("list", iPositionService.showList());
        model.addAttribute("listTeam", iTeamService.showList());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute PlaySoccerDto playSoccerDto,
                         BindingResult bindingResult,
                         Model model) {
        new PlaySoccerDto().validate(playSoccerDto,bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("PlaySoccer", new PlayerSoccer());
            model.addAttribute("list", iPositionService.showList());
            model.addAttribute("listTeam", iTeamService.showList());
            return "create";
        }
        PlayerSoccer playerSoccer1 = new PlayerSoccer();
        BeanUtils.copyProperties(playerSoccer1,playSoccerDto);
        iPlaySoccerService.add(playerSoccer1);
        return "redirect:/";
    }

    @GetMapping("/showEdit{id}")
    public String showEdit(@RequestParam int id, Model model) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        model.addAttribute("playSoccer", playerSoccer);
        model.addAttribute("list", iPositionService.showList());
        model.addAttribute("listTeam", iTeamService.showList());

        return "edit";
    }
    @PostMapping ("/edit")
    public String edit(PlayerSoccer playSoccer) {
        iPlaySoccerService.edit(playSoccer);
        return "redirect:/";
    }
}
