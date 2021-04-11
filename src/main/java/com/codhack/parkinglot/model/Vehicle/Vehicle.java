package com.codhack.parkinglot.model.Vehicle;
import lombok.Data;

@Data
public abstract class Vehicle {

    private String registrationNo;

    public Vehicle(String registrationNo) {
        this.registrationNo = registrationNo;
    }
}
