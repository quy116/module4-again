package com.example.player.dto;

import com.example.player.model.Position;
import com.example.player.model.Status;
import com.example.player.model.Team;
import jdk.nashorn.internal.objects.Global;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PlaySoccerDto implements Validator {
    private int id;
    @NotBlank(message = "not empty")
    private String code;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$",message = "không được chứa ký tự đặt biệt")
    private String fullName;
//    @Min(value = 16,message = "lớn hơn 16")
    private String date;
    @Pattern(regexp = "^[1-9]\\d*$", message = "là số nguyên dương")
    private String experience;
    private String image;
    private PositionDto position;
    private TeamDto team;
    private StatusDto status;

    public PlaySoccerDto() {
    }

    public PlaySoccerDto(int id, String code, String fullName, String date, String experience, String image, PositionDto position, TeamDto team, StatusDto status) {
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

    public PositionDto getPosition() {
        return position;
    }

    public void setPosition(PositionDto position) {
        this.position = position;
    }

    public TeamDto getTeam() {
        return team;
    }

    public void setTeam(TeamDto team) {
        this.team = team;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
