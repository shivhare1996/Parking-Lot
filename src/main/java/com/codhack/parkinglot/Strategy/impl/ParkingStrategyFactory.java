package com.codhack.parkinglot.Strategy.impl;

import com.codhack.parkinglot.Strategy.IParkingStrategy;

public class ParkingStrategyFactory {

    public static IParkingStrategy getParkingStrategy(int strategy, int capacity){

        if(strategy == 0){
            return SimpleArrayStrategy.getInstance(capacity);
        }else if(strategy == 1){
            return TreeStrategy.getInstance(capacity);
        }

        return null;
    }
}
