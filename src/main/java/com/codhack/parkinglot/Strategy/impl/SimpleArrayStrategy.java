package com.codhack.parkinglot.Strategy.impl;

import com.codhack.parkinglot.Strategy.IParkingStrategy;


public class SimpleArrayStrategy implements IParkingStrategy {

    private boolean[] slots;
    private int capacity;

    private SimpleArrayStrategy() {
    }

    public SimpleArrayStrategy(int capacity){
        this.capacity = capacity;
        slots = new boolean[capacity];
    }

    /**
     * O(1) time to fill slot
     * @param slot slot (1-1000)
     * @return true if successfully filled else false if slot is invalid or slot is already parked
     */
    @Override
    public boolean fillSlot(int slot) {
        slot = slot -1;

        if(!isValidSlot(slot) || slots[slot]){
            return false;
        }

        slots[slot] = true;
        return true;
    }

    /**
     * O(n) time to find the nearest slot
     * @return available nearest Slot if available else -1
     */
    @Override
    public int getSlot() {

        for(int i =0; i<capacity; i++){
            if(!slots[i]){
                return i+1;
            }
        }

        return -1;
    }

    /**
     * O(1) time
     * @param slot slot (1-1000)
     * @return true if successfully freed else false if slot is invalid or slot is already empty
     */
    @Override
    public boolean removeSlot(int slot) {
        slot = slot-1;


        if(!isValidSlot(slot) || !slots[slot]){
            //do nothing
            return false;
        }


        slots[slot] = false;

        return true;
    }


    @Override
    public boolean isValidSlot(int slot){
        return slot >= 0 && slot < capacity;
    }

    public static IParkingStrategy getInstance(int capacity){
        return new SimpleArrayStrategy(capacity);
    }

}
