package com.example.player.service;

import com.example.player.model.PlayerSoccer;

import java.util.List;

public interface IPlaySoccerService {
    List<PlayerSoccer> showList();

    PlayerSoccer findById(int id);

    void deleteById(int id);

    void add(PlayerSoccer playerSoccer);
    void edit(PlayerSoccer playSoccer);

}
