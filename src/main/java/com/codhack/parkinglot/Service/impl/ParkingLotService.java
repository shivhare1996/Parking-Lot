package com.codhack.parkinglot.Service.impl;

import com.codhack.parkinglot.Service.IParkingLotService;
import com.codhack.parkinglot.Strategy.IParkingStrategy;
import com.codhack.parkinglot.Strategy.impl.ParkingStrategyFactory;
import com.codhack.parkinglot.Utils.OperationUtils;
import com.codhack.parkinglot.model.Parking.ParkingInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ParkingLotService implements IParkingLotService {

    private ParkingLotService(){

    }


    private static IParkingLotService parkingLotService;

    private IParkingStrategy parkingStrategy;

    // Map of Slot, Car
    private Map<Integer, ParkingInfo> slotToParkingInfo;

    // Map of RegNo, Slot
    private Map<String, Integer> regNumToSlot;

    // Map of age, List of slots
    private Map<Integer, List<Integer>> ageToSlots;

    // Map of age, List of registration number
    private Map<Integer, List<String>> ageToRegNum;



    @Override
    public void createParkingLot(int capacity) {
        if(Objects.nonNull(parkingStrategy)){
            System.out.println("Parking Lot Already Created");
            return;
        }
        parkingStrategy = ParkingStrategyFactory.getParkingStrategy(1, capacity);
        slotToParkingInfo = new HashMap<>();
        regNumToSlot = new HashMap<>();
        ageToSlots = new HashMap<>();
        ageToRegNum = new HashMap<>();
        System.out.println("Created Parking of " + capacity + " slots");

    }

    @Override
    public Optional<Integer> park(String regNumber, int age) {
        if(parkingLotNotCreated()){
            return Optional.empty();
        }

        if(regNumToSlot.containsKey(regNumber)){
            System.out.println("Invalid Request as this vehicle is already parked "+ regNumber);
            return Optional.empty();
        }

        int nextSlot = parkingStrategy.getSlot();
        if(nextSlot == -1){
            System.out.println("Sorry, Parking lot is full");
            return Optional.empty();
        }

        System.out.println("Car with vehicle registration number \"" + regNumber + "\" has been parked at slot number " + nextSlot);
        parkingStrategy.fillSlot(nextSlot);
        ParkingInfo parkingInfo = new ParkingInfo(regNumber, age);
        regNumToSlot.put(regNumber, nextSlot);
        slotToParkingInfo.put(nextSlot, parkingInfo);
        addInAgeToSlots(age, nextSlot);
        addInAgeToRegNumHash(age, regNumber);

        return Optional.of(nextSlot);
    }

    private void addInAgeToRegNumHash(int age, String regNumber){
        if(ageToRegNum.containsKey(age)){
            ageToRegNum.get(age).add(regNumber);
        }else{
            List<String> regNumbers = new ArrayList<>();
            regNumbers.add(regNumber);
            ageToRegNum.put(age, regNumbers);
        }
    }

    private void addInAgeToSlots(int age, int nextSlot){
        if(ageToSlots.containsKey(age)){
            ageToSlots.get(age).add(nextSlot);
        }else{
            List<Integer> slots = new ArrayList<>();
            slots.add(nextSlot);
            ageToSlots.put(age, slots);
        }
    }


    @Override
    public boolean unPark(int slot) {

        if(parkingLotNotCreated()){
            return false;
        }
        if(!parkingStrategy.isValidSlot(slot)){
            System.out.println("Invalid Slot Request");
            return false;
        }

        if(!slotToParkingInfo.containsKey(slot)){
            System.out.println("Invalid Request as slot " +slot + " is already empty");
            return false;
        }

        ParkingInfo parkingInfo = slotToParkingInfo.get(slot);
        System.out.println("Slot number " +slot+ " vacated, the car with vehile registration number \"" + parkingInfo.getRegistrationNo() + "\" left the space, the driver of the car was of age " + parkingInfo.getUserAge());
        slotToParkingInfo.remove(slot);
        regNumToSlot.remove(parkingInfo.getRegistrationNo());

        parkingStrategy.removeSlot(slot);
        List<Integer> slots = ageToSlots.get(parkingInfo.getUserAge());
        slots.remove((Object) slot);

        List<String> registrationNumbers =  ageToRegNum.get(parkingInfo.getUserAge());
        registrationNumbers.remove(parkingInfo.getRegistrationNo());

        return true;
    }

    @Override
    public Optional<Integer> getSlot(String regNumber) {

        if(parkingLotNotCreated()){
            return Optional.empty();
        }
        if(!regNumToSlot.containsKey(regNumber)){
            System.out.println("Invalid Request as this vehicle is not parked");
            return Optional.empty();
        }

        int slot = regNumToSlot.get(regNumber);
        System.out.println(slot);
        return Optional.of(slot);
    }

    @Override
    public List<Integer> getSlots(int age) {

        if(parkingLotNotCreated()){
            return null;
        }

        List<Integer> slots = ageToSlots.get(age);
        return (List<Integer>) processList(age, slots);
    }

    @Override
    public List<String> getRegistrationNumbers(int age) {

        if(parkingLotNotCreated()){
            return null;
        }

        List<String> regNos = ageToRegNum.get(age);
        return (List<String>) processList(age, regNos);
    }



    private List<?> processList(int age, List<?> list){
        if(OperationUtils.isEmptyList(list)){
            System.out.println("No parking found with the age " + age + " of user");
            return null;
        }

        OperationUtils.printCommaSeperated(list);
        return list;
    }

    public static IParkingLotService getInstance(){
        if(parkingLotService == null){
            parkingLotService = new ParkingLotService();
        }

        return parkingLotService;
//        parkingLotService = new ParkingLotService();
//        return parkingLotService;
    }


    private boolean parkingLotNotCreated(){

        if(Objects.isNull(parkingStrategy)){
            System.out.println("Parking Lot is not Created");
            return true;
        }

        return false;
    }

}
