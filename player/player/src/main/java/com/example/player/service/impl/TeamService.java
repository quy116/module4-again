package com.example.player.service.impl;

import com.example.player.model.PlayerSoccer;
import com.example.player.model.Team;
import com.example.player.repository.ITeamRepository;
import com.example.player.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements ITeamService {
    @Autowired
    ITeamRepository iTeamRepository;

    @Override
    public List<Team> showList() {
        List<Team> list = iTeamRepository.findAll();
        return list;
    }
}
