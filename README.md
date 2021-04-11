# Problem Statement (Parking Lot)

We own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. We want to create an automated ticketing system that allows our customers to use my parking lot without human intervention. 

When a car enters my parking lot, we want to have a ticket issued to the driver. The ticket issuing process includes:-
 
1. Taking note of the number written on the vehicle registration plate and the age of the driver of the car
2. And allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them)

The customer should be allocated a parking slot that is nearest to the entry. At the exit, the customer returns the ticket which then marks the slot they were using as being available. 

Due to government regulation, the system should provide me with the ability to find out:

a) Vehicle Registration numbers for all cars which are parked by the driver of a certain age. <br>
b) Slot number in which a car with a given vehicle registration plate is parked. <br>
c) Slot numbers of all slots where cars of drivers of a particular age are parked. 

We interact with the system via file based input system i.e. it should accept a filename as an input. The file referenced by filename will contain a set of commands seperated by a newline, we need to execute the commands in order and produce output. 
All the numbers will be under 1000

### Example: File Input

To run the program:

$ my_program file_inputs.txt > output.txt

Input (in file):

```sh
Create_parking_lot 6

Park KA-01-HH-1234 driver_age 21

Park PB-01-HH-1234 driver_age 21

Slot_numbers_for_driver_of_age 21

Park PB-01-TG-2341 driver_age 40

Slot_number_for_car_with_number PB-01-HH-1234

Leave 2

Park HR-29-TG-3098 driver_age 39

Vehicle_registration_number_for_driver_of_age 18
```

Output (to console, newline after every output):

```sh
Created parking of 6 slots

Car with vehicle registration number "KA-01-HH-1234" has been parked at slot number 1

Car with vehicle registration number "PB-01-TG-2341" has been parked at slot number 2

1,2

Car with vehicle registration number "PB-01-TG-1234" has been parked at slot number 3

2

Slot number 2 vacated, the car with vehile registration number "PB-01-HH-1234" left the space, the driver of the car was of age 21

Car with vehicle registration number "HR-29-TG-3098" has been parked at slot number 2

No parking found with the age 18 of user
```

### Assumptions 
1. Registration Numbers of all cars will be of same length(13) and will be following the same format(example : "KA-01-HH-1234")
2. There'll be only one Create_parking_lot in complete test file and it'll be at the beginning of the file 
3. All cars will have a distinct unique registration number
4. Commands will always be in the right format
5. ~If the command is incorrect will print **"Sorry, Wrong command entered"** and carry instructions for further commands
6. ~Printing **"Sorry, Parking lot is full"** when there is a request to park a cark and parking lot is full

# Project Requirements

* JDK 1.8.
* Maven 3. (3.6.3) 

# Parking Lot Strategy
```
Implemented 2 Strategies for Parking Service. 

1. Simple Array Strategy, which takes 
-> O(n) time to find the nearest slot
-> O(1) time to fill slot 
-> O(1) time to remove slot

2. TreeSet Strategy which uses O(log(n)) time for all the operations

Default Strategy is  TreeSet Strategy, to change to Simple Array Strategy pass ~0~ while fetching ParkingStrategy instance in Parking Strategy Factory.
```

# Build Instructions

Run the following command -
```sh
mvn clean install
```


#Running the project
From the project directory, run this command - 

```java
   $ java -jar target/Parking_Lot-1.0-SNAPSHOT.jar <inputfile> (File input)
```
   

