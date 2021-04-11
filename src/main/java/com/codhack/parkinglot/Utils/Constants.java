package com.codhack.parkinglot.Utils;

import java.util.Arrays;
import java.util.List;

public interface Constants {

    //COMMANDS CONSTANTS _ First unique
    String CREATE_PARKING_LOT               =         "Create_parking_lot";
    String LEAVE                            =         "Leave";
    String PARK                             =         "Park";
    String SLOT_NUMBERS_AGE                 =         "Slot_numbers_for_driver_of_age";
    String SLOT_NUMBER_REGISTRATION_NUMBER  =         "Slot_number_for_car_with_number";
    String REGISTRATION_NUMBER_AGE          =         "Vehicle_registration_number_for_driver_of_age";

    String DRIVER_AGE                       =         "driver_age";


    interface VehicleConstant{
        List<String> allowedTypes = Arrays.asList("XX-XX-XX-XXXX");
    }
}
