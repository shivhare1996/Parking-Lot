package com.codhack.parkinglot.Commands;

import com.codhack.parkinglot.Service.IParkingLotService;
import com.codhack.parkinglot.Service.impl.ParkingLotService;
import com.codhack.parkinglot.Utils.CommandValidationMap;
import com.codhack.parkinglot.Utils.OperationUtils;

public class Execution {

//    private Execution execution;
    private static final IParkingLotService parkingLotService = ParkingLotService.getInstance();;
    private static final CommandValidationMap commandValidationMap = CommandValidationMap.getCommandValidationObject();

    private Execution(){

    }

    public static void execute(String command){
        String[] inputs = command.split(" ");
        int commandNumber = commandValidationMap.getValidationMethod(inputs[0]);

        switch (commandNumber){
            case 1:
                parkingLotService.createParkingLot(OperationUtils.convertStringToDigits(inputs[1]).get());
                break;
            case 2:
                parkingLotService.park(inputs[1], OperationUtils.convertStringToDigits(inputs[3]).get());
                break;
            case 3:
                parkingLotService.unPark(OperationUtils.convertStringToDigits(inputs[1]).get());
                break;
            case 4:
                parkingLotService.getSlots(OperationUtils.convertStringToDigits(inputs[1]).get());
                break;

            case 5:
                parkingLotService.getSlot(inputs[1]);
                break;

            case 6:
                parkingLotService.getRegistrationNumbers(OperationUtils.convertStringToDigits(inputs[1]).get());
                break;

            default:
                //it will never reach here
                System.out.println("Invalid Input");
                break;
        }
    }



//    public Execution getInstance(Integer executionStrategy){
//        if(execution == null){
//            execution = new Execution();
//            parkingLotService = ParkingLotService.getInstance();
//        }
//
//        return execution;
//    }
}
