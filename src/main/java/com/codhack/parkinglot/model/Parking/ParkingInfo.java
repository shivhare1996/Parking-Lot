package com.codhack.parkinglot.model.Parking;

import lombok.Data;

@Data
public class ParkingInfo {

    private String registrationNo;
    private int userAge;

    public ParkingInfo(String registrationNo, int userAge) {
        this.registrationNo = registrationNo;
        this.userAge = userAge;
    }
}
