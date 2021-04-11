package com.codhack.parkinglot.Commands;

import com.codhack.parkinglot.Service.impl.VehicleService;
import com.codhack.parkinglot.Utils.CommandValidationMap;
import com.codhack.parkinglot.Utils.Constants;
import com.codhack.parkinglot.Utils.OperationUtils;

import java.util.Optional;

public class Validation {


    private static final CommandValidationMap commandValidationMap = CommandValidationMap.getCommandValidationObject();
    private static final VehicleService vehicleService = VehicleService.getInstance();

    /**
     * Written this for scalability purpose if logic changes in future.
     */
    public static boolean validate(String command){

        String[] inputs = command.split(" ");
        Integer commandNumber = commandValidationMap.getValidationMethod(inputs[0]);

        //command not exist
        if(commandNumber == null){
            return false;
        }

        boolean isValid;
        switch (commandNumber){
            case 1:

            case 3:

            case 4:

            case 6:
                isValid = (inputs.length == 2) && isValidNumber(inputs[1]);
                break;

            case 2:
                isValid = validateParking(inputs);
                break;

            case 5:
                isValid = (inputs.length == 2) && vehicleService.validate(inputs[1]);
                break;

            default:
                isValid = false;
                break;
        }


        return isValid;

    }

    /**
     * Example : Park KA-01-HH-1234 driver_age 21
     */
    private static boolean validateParking(String[] inputs){
        if(inputs.length != 4){
            return false;
        }

        boolean valid = vehicleService.validate(inputs[1]) ;
        valid = valid && Constants.DRIVER_AGE.equals(inputs[2]) ;

        if(valid){
            Optional<Integer> number =  OperationUtils.convertStringToDigits(inputs[3]);
            valid = number.filter(OperationUtils::integerIsBetween).isPresent();
        }

        return valid;
    }

    /**
     * Example :
     * Leave 2
     * Create_parking_lot 6
     * Vehicle_registration_number_for_driver_of_age 18
     * Slot_numbers_for_driver_of_age 21
     */
    private static boolean isValidNumber(String input){
        Optional<Integer> number =  OperationUtils.convertStringToDigits(input);
        return number.filter(OperationUtils::integerIsBetween).isPresent();
    }


}
