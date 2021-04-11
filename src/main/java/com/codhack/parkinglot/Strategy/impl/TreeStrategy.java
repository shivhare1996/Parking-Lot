package com.codhack.parkinglot.Strategy.impl;

import com.codhack.parkinglot.Strategy.IParkingStrategy;

import java.util.TreeSet;

public class TreeStrategy implements IParkingStrategy {

    private TreeSet<Integer> availableSlots;
    private int capacity;

    private TreeStrategy() {
    }

    public TreeStrategy(int capacity){
        this.capacity = capacity;
        this.availableSlots = new TreeSet<Integer>();

        for(int i =1; i<=capacity; i++){
            availableSlots.add(i);
        }
    }


    /**
     * O(Log(n)) time to remove slot from available TreeSet
     * @param slot fill the a
     * @return true if succesfully filled else false
     */
    @Override
    public boolean fillSlot(int slot) {
        if(!isValidSlot(slot) || availableSlots.size() == 0 || !availableSlots.contains(slot)){
            return false;
        }

        availableSlots.remove(slot);
        return true;
    }

    /**
     * Log(n) time to get first and again balance red black tree
     *
     * @return slot number
     */
    @Override
    public int getSlot() {

        if(availableSlots.size() == 0){
            return -1;
        }
        return availableSlots.first();
    }

    /**
     * Log(n) time to add slot in treeset
     * @param slot slot added
     * @return true if succesfully added else false
     */
    @Override
    public boolean removeSlot(int slot) {
        return availableSlots.add(slot);
    }

    @Override
    public boolean isValidSlot(int slot) {
        return slot > 0 && slot <= capacity;
    }

    public static IParkingStrategy getInstance(int capacity){
        return new TreeStrategy(capacity);
    }

}
