package com.codhack.parkinglot.model.User;

import lombok.Data;

@Data
public class User {

    private int age;

    public User(int age) {
        this.age = age;
    }
}
