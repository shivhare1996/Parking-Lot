package com.codhack.parkinglot.Service;

import java.util.List;
import java.util.Optional;

public interface IParkingLotService {

    void createParkingLot(int capacity);

    Optional<Integer> park(String regNumber, int age);

    boolean unPark(int slot);

    Optional<Integer> getSlot(String regNumber);

    List<Integer> getSlots(int age);

    List<String> getRegistrationNumbers(int age);

}
