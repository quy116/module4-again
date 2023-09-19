package com.example.player.repository;

import com.example.player.model.PlayerSoccer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface IPlaySoccerRepository extends JpaRepository<PlayerSoccer, Integer> {
    @Query(value = "select * from player_soccer where full_name like :searchName", nativeQuery = true)
    Page<PlayerSoccer> findAllByFullNameContaining(@Param("searchName") String searchName, Pageable pageable);
    @Query(value = "select * from player_soccer where date >:startDay and date <:startEnd", nativeQuery = true)
    Page<PlayerSoccer> findByDateRange(Pageable pageable,
                                       @Param("startDay") String startDay,
                                       @Param("startEnd") String startEnd);
}
