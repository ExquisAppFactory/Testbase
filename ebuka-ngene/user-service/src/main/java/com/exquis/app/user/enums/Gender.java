package com.exquis.app.user.enums;

import com.exquis.app.user.utility.Dto;

public enum Gender implements Dto {
    M("Male"),
    F("Female"),
    OTHER("Other");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Gender fromName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return Gender.valueOf(name);
    }
}
