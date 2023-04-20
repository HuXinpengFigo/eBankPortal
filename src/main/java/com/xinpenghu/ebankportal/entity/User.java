package com.xinpenghu.ebankportal.entity;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
