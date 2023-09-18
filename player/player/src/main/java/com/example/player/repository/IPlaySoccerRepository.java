package com.example.player.repository;

import com.example.player.model.PlayerSoccer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlaySoccerRepository extends JpaRepository<PlayerSoccer,Integer> {
}
