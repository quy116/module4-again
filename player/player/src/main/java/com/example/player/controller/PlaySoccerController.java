package com.example.player.controller;

import com.example.player.dto.PlayerCardDto;
import com.example.player.dto.PlaySoccerDto;
import com.example.player.model.PlayerSoccer;
import com.example.player.service.IPlaySoccerService;
import com.example.player.service.IPositionService;
import com.example.player.service.IStatusService;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@SessionAttributes("cart")
public class PlaySoccerController {
    @Autowired
    private IPlaySoccerService iPlaySoccerService;
    @Autowired
    private IPositionService iPositionService;
    @Autowired
    private ITeamService iTeamService;
    @Autowired
    private IStatusService iStatusService;

    @ModelAttribute("cart")
    public PlayerCardDto initCartDto() {
        return new PlayerCardDto();
    }

    @GetMapping("/")
    public String showListTable(@RequestParam(defaultValue = "0", required = false) int page,
                                @RequestParam(defaultValue = "", required = false) String searchName,
                                @RequestParam(defaultValue = "4", required = false) int size,
                                @RequestParam(defaultValue = "", required = false) String startDay,
                                @RequestParam(defaultValue = "", required = false) String endDay
            , Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("full_name").descending());
        Page<PlayerSoccer> playerSoccers = iPlaySoccerService.showListPage(pageable, searchName, startDay, endDay);
        model.addAttribute("list", playerSoccers);
        model.addAttribute("size", size);
        model.addAttribute("searchName", searchName);
        model.addAttribute("startDay", startDay);
        model.addAttribute("endDay", endDay);
        return "list";
    }

    @GetMapping("/showTableDetail")
    public String showTableDetail(@RequestParam int id, Model model) {
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

    @GetMapping("/showFormCreate")
    public String showFormCreate(Model model) {
        model.addAttribute("playSoccerDto", new PlaySoccerDto());
        model.addAttribute("list", iPositionService.showList());
        model.addAttribute("listTeam", iTeamService.showList());
        model.addAttribute("listStatus", iStatusService.showList());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute PlaySoccerDto playSoccerDto,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        new PlaySoccerDto().validate(playSoccerDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("PlaySoccer", new PlayerSoccer());
            model.addAttribute("list", iPositionService.showList());
            model.addAttribute("listTeam", iTeamService.showList());
            model.addAttribute("listStatus", iStatusService.showList());
            return "create";
        }
        PlayerSoccer playerSoccer1 = new PlayerSoccer();
        BeanUtils.copyProperties(playSoccerDto, playerSoccer1);
        if (iPlaySoccerService.add(playerSoccer1) == false) {
            model.addAttribute("PlaySoccer", new PlayerSoccer());
            model.addAttribute("list", iPositionService.showList());
            model.addAttribute("listTeam", iTeamService.showList());
            model.addAttribute("listStatus", iStatusService.showList());
            model.addAttribute("mess", "dai lai di");
            return "/create";
        }
        iPlaySoccerService.add(playerSoccer1);
        return "redirect:/";
    }

    @GetMapping("/showFormEdit")
    public String showFormEdit(@RequestParam int id, Model model) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        model.addAttribute("playSoccer", playerSoccer);
        model.addAttribute("list", iPositionService.showList());
        model.addAttribute("listTeam", iTeamService.showList());

        return "edit";
    }

    @PostMapping("/edit")
    public String edit(PlayerSoccer playSoccer) {
        iPlaySoccerService.edit(playSoccer);
        return "redirect:/";
    }

    @GetMapping("/signUpForSoccer")
    public String signUpForSoccer(@RequestParam int id) {
        iPlaySoccerService.signUpForSoccer(id);
        return "redirect:/";
    }

    @GetMapping("/reserveRegistration")
    public String reserveRegistration(@RequestParam int id) {
        iPlaySoccerService.reserveRegistration(id);
        return "redirect:/";
    }

    @GetMapping("/favourite")
    public String favourite(@RequestParam int id,
                            @SessionAttribute(value = "cart")
                            PlayerCardDto playerCardDto,
                            HttpServletResponse httpServletResponse,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        PlaySoccerDto playSoccerDto = new PlaySoccerDto();
        BeanUtils.copyProperties(playerSoccer, playSoccerDto);
        playerCardDto.addPlayer(playSoccerDto);
        Cookie cookie = new Cookie("cardId", id + "");
        cookie.setMaxAge(30);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        redirectAttributes.addFlashAttribute("mess", "cầu thủ này đã đc yeu thích");
        return "redirect:/";
    }

    @GetMapping("/showListFavourite")
    public String showListFavourite(@CookieValue(defaultValue = "-1", required = false) int cardId, Model model) {
    PlayerSoccer playerSoccer = iPlaySoccerService.findById(cardId);
    model.addAttribute("playerSoccer",playerSoccer);
    return "detail";
    }
}
