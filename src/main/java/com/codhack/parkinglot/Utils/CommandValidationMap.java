package com.codhack.parkinglot.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Class for fetching method classes
 */
public class CommandValidationMap {

    private static CommandValidationMap commandValidationMap;
    private static final Map<String, Integer> commandToValidationMethod = new HashMap<>();

    static
    {
        commandToValidationMethod.put(Constants.CREATE_PARKING_LOT, 1);
        commandToValidationMethod.put(Constants.PARK, 2);
        commandToValidationMethod.put(Constants.LEAVE, 3);
        commandToValidationMethod.put(Constants.SLOT_NUMBERS_AGE, 4);
        commandToValidationMethod.put(Constants.SLOT_NUMBER_REGISTRATION_NUMBER, 5);
        commandToValidationMethod.put(Constants.REGISTRATION_NUMBER_AGE, 6);
    }


    /**
     * This will return number command and according to that method will be called.
     * @param command Input command
     * @return Integer Method
     */
    public Integer getValidationMethod(String command){
        return commandToValidationMethod.get(command);
    }

    private CommandValidationMap(){

    }

    public static CommandValidationMap getCommandValidationObject(){
        if(commandValidationMap == null){
            commandValidationMap =  new CommandValidationMap();
        }

        return commandValidationMap;
    }
}
