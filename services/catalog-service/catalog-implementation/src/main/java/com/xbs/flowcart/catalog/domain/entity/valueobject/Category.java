package com.xbs.flowcart.catalog.domain.entity.valueobject;

import lombok.Getter;

@Getter
public enum Category {
    LAPTOP("Laptop"),
    WORKSTATION("Workstation"),
    DISPLAY("Display"),
    MOUSE("Mouse"),
    KEYBOARD("Keyboard");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}