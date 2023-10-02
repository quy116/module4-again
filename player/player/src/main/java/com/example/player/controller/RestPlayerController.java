package com.example.player.controller;

import com.example.player.dto.PlaySoccerDto;
import com.example.player.model.PlayerSoccer;
import com.example.player.service.IPlaySoccerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player/")
@CrossOrigin("*")
public class RestPlayerController {
    @Autowired
    private IPlaySoccerService iPlaySoccerService;

    @GetMapping("list")
    public ResponseEntity<List<PlayerSoccer>> getList() {
        List<PlayerSoccer> list = iPlaySoccerService.showList();
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("detail{id}")
    public ResponseEntity<PlayerSoccer> detail(@PathVariable int id) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        if (playerSoccer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(playerSoccer, HttpStatus.OK);
        }
    }

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody PlaySoccerDto playSoccerDto) {
        PlayerSoccer playerSoccer = new PlayerSoccer();
        BeanUtils.copyProperties(playSoccerDto, playerSoccer);
        iPlaySoccerService.add(playerSoccer);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @PatchMapping("edit{id}")
    public ResponseEntity<?> edit(@PathVariable int id, @RequestBody PlaySoccerDto playSoccerDto) {
        PlayerSoccer playerSoccer = iPlaySoccerService.findById(id);
        if (playerSoccer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        BeanUtils.copyProperties(playSoccerDto, playerSoccer);
        iPlaySoccerService.add(playerSoccer);

        return new ResponseEntity<>("ok", HttpStatus.OK);

    }
}
