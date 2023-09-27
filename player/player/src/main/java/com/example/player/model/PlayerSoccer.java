package com.example.player.model;

import javax.persistence.*;

@Entity
public class PlayerSoccer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String code;
    @Column(nullable = false)
    private String fullName;
    @Column(columnDefinition = "DATE", unique = true, nullable = false)
    private String date;
    @Column(nullable = false)
    private String experience;
    @Column(nullable = false)
    private String image;
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;
    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    public PlayerSoccer() {
    }

    public PlayerSoccer(int id, String code, String fullName, String date, String experience, String image, Position position, Team team) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.date = date;
        this.experience = experience;
        this.image = image;
        this.position = position;
        this.team = team;
    }

    public PlayerSoccer(int id, String code, String fullName, String date, String experience, String image, Position position, Team team, Status status) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.date = date;
        this.experience = experience;
        this.image = image;
        this.position = position;
        this.team = team;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
