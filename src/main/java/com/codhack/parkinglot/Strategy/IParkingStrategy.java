package com.codhack.parkinglot.Strategy;

public interface IParkingStrategy {

    boolean fillSlot(int i);

    int getSlot();

    boolean removeSlot(int slot);

    boolean isValidSlot(int slot);
}
