package com.example.player.repository;

import com.example.player.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status, Integer> {
}
