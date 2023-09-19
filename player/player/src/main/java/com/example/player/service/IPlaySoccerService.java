package com.example.player.service;

import com.example.player.model.PlayerSoccer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlaySoccerService {
    List<PlayerSoccer> showList();

    PlayerSoccer findById(int id);

    void deleteById(int id);

    void add(PlayerSoccer playerSoccer);
    void edit(PlayerSoccer playSoccer);

    Page<PlayerSoccer> showListPage(Pageable pageable);
}
