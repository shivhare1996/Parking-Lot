package com.codhack.parkinglot;

import com.codhack.parkinglot.Commands.Validation;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommandTest {

    @Test
    public void validationTest(){
        assertTrue(Validation.validate("Create_parking_lot 6"));
        assertFalse(Validation.validate("Create_parking_lot 6000"));
        assertFalse(Validation.validate("Random command"));
        assertTrue(Validation.validate("Park KA-01-HH-1234 driver_age 21"));
        assertTrue(Validation.validate("Slot_numbers_for_driver_of_age 21"));
        assertTrue(Validation.validate("Slot_number_for_car_with_number PB-01-HH-1234"));
        assertTrue(Validation.validate("Park HR-29-TG-3098 driver_age 39"));
        assertTrue(Validation.validate("Vehicle_registration_number_for_driver_of_age 18"));
        assertFalse(Validation.validate("Vehicle_registration_number_for_driver_of_age ok 18"));
        assertFalse(Validation.validate("Parking KA-01-HH-1234 driver_age 21"));
    }
}
