package com.example.player.service;

import com.example.player.model.PlayerSoccer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IPlaySoccerService {
    List<PlayerSoccer> showList();

    PlayerSoccer findById(int id);

    void deleteById(int id);

    boolean add(PlayerSoccer playerSoccer);
    void edit(PlayerSoccer playSoccer);
    boolean findByName(String name);

    Page<PlayerSoccer> showListPage(Pageable pageable, String searchName, String startDay, String startEnd);

    void signUpForSoccer(int id);

    void reserveRegistration(int id);
}
