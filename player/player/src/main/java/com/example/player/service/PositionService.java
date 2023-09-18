package com.example.player.service;

import com.example.player.model.Position;
import com.example.player.repository.IPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.List;

@Service
public class PositionService implements IPositionService {
    @Autowired
    private IPositionRepository iPositionRepository;

    @Override
    public List<Position> showList() {
        List<Position> list = iPositionRepository.findAll();
        return list;
    }
}
