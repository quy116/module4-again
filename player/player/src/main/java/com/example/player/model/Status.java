package com.example.player.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonBackReference
    @OneToMany(mappedBy = "status")
    private Set<PlayerSoccer> playerSoccers;

    public Status() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerSoccer> getPlayerSoccers() {
        return playerSoccers;
    }

    public void setPlayerSoccers(Set<PlayerSoccer> playerSoccers) {
        this.playerSoccers = playerSoccers;
    }
}
