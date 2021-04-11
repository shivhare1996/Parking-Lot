package com.codhack.parkinglot;


import com.codhack.parkinglot.Service.IParkingLotService;
import com.codhack.parkinglot.Service.impl.ParkingLotService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class ParkingLotServiceTest {

    IParkingLotService parkingLot = ParkingLotService.getInstance();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        parkingLot = null;
    }

    @Test
    public void getRegistrationNumbers(){
        assertNull(parkingLot.getRegistrationNumbers(10));
        parkingLot.createParkingLot(2);
        parkingLot.park("KA-01-HH-1222", 20);
        parkingLot.park("KA-01-HH-1234", 20);
        assertEquals(Arrays.asList("KA-01-HH-1222", "KA-01-HH-1234"), parkingLot.getRegistrationNumbers(20));
        parkingLot.unPark(1);
        outContent.reset();
        assertNull(parkingLot.getRegistrationNumbers(24));
        assertEquals(outContent.toString(), "No parking found with the age 24 of user\n");
    }

    @Test
    public void getSlotsForAge(){
        assertNull(parkingLot.getSlots(10));
        parkingLot.createParkingLot(2);
        parkingLot.park("KA-01-HH-1222", 20);
        parkingLot.park("KA-01-HH-1234", 20);
        assertEquals(Arrays.asList(1,2), parkingLot.getSlots(20));
        parkingLot.unPark(1);
        assertEquals(Arrays.asList(2), parkingLot.getSlots(20));
        outContent.reset();
        assertNull(parkingLot.getSlots(24));
        assertEquals(outContent.toString(), "No parking found with the age 24 of user\n");
    }

    @Test
    public void getSlotForLicensePlate(){
        assertFalse(parkingLot.getSlot("ACD").isPresent());
        parkingLot.createParkingLot(1);
        parkingLot.park("KA-01-HH-1222", 20);
        outContent.reset();
        parkingLot.getSlot("BA-ST-II-1234");
        assertEquals("Invalid Request as this vehicle is not parked\n", outContent.toString());
        assertFalse(parkingLot.getSlot("ACD").isPresent());
        outContent.reset();
        assertEquals(java.util.Optional.of(1), parkingLot.getSlot("KA-01-HH-1222"));
    }

    @Test
    public void unPark(){
        assertFalse(parkingLot.unPark(1));
        parkingLot.createParkingLot(1);
        parkingLot.park("KA-01-HH-1222", 20);
        outContent.reset();
        assertFalse(parkingLot.unPark(2));
        assertEquals("Invalid Slot Request\n", outContent.toString());
        outContent.reset();
        parkingLot.unPark(1);
        assertEquals("Slot number 1 vacated, the car with vehile registration number \"KA-01-HH-1222\" left the space, the driver of the car was of age 20\n", outContent.toString());

        outContent.reset();
        parkingLot.unPark(1);
        assertEquals("Invalid Request as slot 1 is already empty\n", outContent.toString());
    }

    @Test
    public void park(){
        assertEquals(Optional.empty(), parkingLot.park("KA-01-HH-1222",22));
        parkingLot.createParkingLot(3);
        outContent.reset();
        parkingLot.park("KA-01-HH-1222", 20);
        assertTrue("Car with vehicle registration number \"KA-01-HH-1222\" has been parked at slot number 1\n".equalsIgnoreCase(outContent.toString()));
        outContent.reset();
        parkingLot.park("KA-01-HH-1111", 29);
        assertTrue("Car with vehicle registration number \"KA-01-HH-1111\" has been parked at slot number 2\n".equalsIgnoreCase(outContent.toString()));
        outContent.reset();
        parkingLot.park("KA-01-HH-1111", 29);
        assertTrue("Invalid Request as this vehicle is already parked KA-01-HH-1111\n".equalsIgnoreCase(outContent.toString()));
        outContent.reset();
        parkingLot.park("AB-01-HH-1111", 20);
        assertTrue("Car with vehicle registration number \"AB-01-HH-1111\" has been parked at slot number 3\n".equalsIgnoreCase(outContent.toString()));
        outContent.reset();
        parkingLot.park("AB-01-HH-1987", 20);
        assertTrue("Sorry, Parking lot is full\n".equalsIgnoreCase(outContent.toString()));
    }

    @Test
    public void createParkingLot(){
        parkingLot.createParkingLot(3);
        assertEquals("Created Parking of 3 slots\n", outContent.toString());
        outContent.reset();
        parkingLot.createParkingLot(6);
        assertEquals("Parking Lot Already Created\n", outContent.toString());
    }


}
