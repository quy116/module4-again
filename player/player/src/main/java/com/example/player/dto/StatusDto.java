package com.example.player.dto;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StatusDto implements Validator {
    private int id;
    private String name;

    public StatusDto() {
    }

    public StatusDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
