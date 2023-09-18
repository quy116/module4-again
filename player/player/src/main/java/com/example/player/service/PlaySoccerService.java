package com.example.player.service;

import com.example.player.model.PlayerSoccer;
import com.example.player.repository.IPlaySoccerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}