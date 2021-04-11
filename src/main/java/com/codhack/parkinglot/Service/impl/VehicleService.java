package com.codhack.parkinglot.Service.impl;

import com.codhack.parkinglot.Service.IVehicleService;
import com.codhack.parkinglot.Utils.Constants;

import java.util.List;
import java.util.Objects;

public class VehicleService  implements IVehicleService {

    private static VehicleService vehicleService;

    @Override
    public boolean validate(String licensePlate) {

        if(Objects.isNull(licensePlate)){
            return false;
        }

        List<String> allowedTypes = Constants.VehicleConstant.allowedTypes;
        boolean valid = false;

        for (String allowedType : allowedTypes) {
            if(allowedType.length() != licensePlate.length()){
                continue;
            }

            valid = checkEquality(allowedType, licensePlate);
            if(valid) {
                break;
            }
        }

        return valid;
    }


    /**
     * For now just checking equal distances between dashes in licenseplate
     * for scalability if there are different logics for each licenseplate we can create classes and interface validate
     * with different validate logics with STRAGEGY Pattern
     * @param original  XX-XX-XX-XXXX
     * @param testing  AB-12-AD-1324
     * @return
     */
    private boolean checkEquality(String original, String testing){
        String[] originalInputs = original.split("-");
        String[] testingInputs = testing.split("-");

        if(originalInputs.length != testingInputs.length){
            return false;
        }

        for(int i =0; i<originalInputs.length; i++){
            if(originalInputs[i].length() != testingInputs[i].length()){
                return false;
            }
        }

        return true;
    }

    private VehicleService(){

    }


    public static VehicleService getInstance(){
        if(vehicleService == null){
            vehicleService = new VehicleService();
        }

        return vehicleService;
    }
}
