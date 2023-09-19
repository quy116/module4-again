package com.example.player.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "position")
    private Set<PlayerSoccer> playerSoccerSet;

    public Position() {
    }

    public Position(int id, String name, Set<PlayerSoccer> playerSoccerSet) {
        this.id = id;
        this.name = name;
        this.playerSoccerSet = playerSoccerSet;
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

    public Set<PlayerSoccer> getPlayerSoccerSet() {
        return playerSoccerSet;
    }

    public void setPlayerSoccerSet(Set<PlayerSoccer> playerSoccerSet) {
        this.playerSoccerSet = playerSoccerSet;
    }
}
