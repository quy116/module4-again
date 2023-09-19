package com.example.player.service.impl;

import com.example.player.model.PlayerSoccer;
import com.example.player.repository.IPlaySoccerRepository;
import com.example.player.service.IPlaySoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaySoccerService implements IPlaySoccerService {
    @Autowired
    private IPlaySoccerRepository iPlaySoccerRepository;

    @Override
    public List<PlayerSoccer> showList() {
        List<PlayerSoccer> list = iPlaySoccerRepository.findAll();
        return list;
    }

    @Override
    public PlayerSoccer findById(int id) {
        PlayerSoccer playerSoccer = iPlaySoccerRepository.findById(id).get();
        return playerSoccer;
    }

    @Override
    public void deleteById(int id) {
        iPlaySoccerRepository.deleteById(id);
    }

    @Override
    public void add(PlayerSoccer playerSoccer) {
        iPlaySoccerRepository.save(playerSoccer);
    }

    @Override
    public void edit(PlayerSoccer playSoccer) {
        iPlaySoccerRepository.save(playSoccer);
    }

    @Override
    public Page<PlayerSoccer> showListPage(Pageable pageable) {

        return iPlaySoccerRepository.findAll(pageable);
    }


}
