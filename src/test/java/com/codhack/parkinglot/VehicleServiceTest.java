package com.codhack.parkinglot;

import com.codhack.parkinglot.Service.impl.VehicleService;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VehicleServiceTest {

    VehicleService vehicleService = VehicleService.getInstance();

    @Test
    public void validateLicensePlate(){
        assertTrue(vehicleService.validate("AB-12-AB-1234"));
        assertFalse(vehicleService.validate("AB-123-A"));
        assertFalse(vehicleService.validate("random"));
        assertFalse(vehicleService.validate(null));
    }
}
