package com.example.player.service.impl;

import com.example.player.model.PlayerSoccer;
import com.example.player.model.Status;
import com.example.player.repository.IPlaySoccerRepository;
import com.example.player.service.IPlaySoccerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public boolean add(PlayerSoccer playerSoccer) {
        iPlaySoccerRepository.save(playerSoccer);
        return true;
    }

    @Override
    public void edit(PlayerSoccer playSoccer) {
        iPlaySoccerRepository.save(playSoccer);
    }

    @Override
    public boolean findByName(String code) {
        if(iPlaySoccerRepository.findAllByCode("%" + code + "%") == null){
            return false;
        }

        return true;
    }

    @Override
    public Page<PlayerSoccer> showListPage(Pageable pageable, String searchName, String startDay, String startEnd) {
        if (startDay.equals("") && startEnd.equals("")){
         return   iPlaySoccerRepository.findAllByFullNameContaining("%" + searchName + "%", pageable);
        }
        return iPlaySoccerRepository.findByDateRange(pageable,startDay,startEnd);
    }

    @Override
    public void signUpForSoccer(int id) {
        PlayerSoccer playerSoccer = iPlaySoccerRepository.findById(id).get();
        Status newStatus = new Status();
        newStatus.setId(2);
        playerSoccer.setStatus(newStatus);
        iPlaySoccerRepository.save(playerSoccer);
    }

    @Override
    public void reserveRegistration(int id) {
        PlayerSoccer playerSoccer = iPlaySoccerRepository.findById(id).get();
        Status newStatus = new Status();
        newStatus.setId(1);
        playerSoccer.setStatus(newStatus);
        iPlaySoccerRepository.save(playerSoccer);
    }


}
