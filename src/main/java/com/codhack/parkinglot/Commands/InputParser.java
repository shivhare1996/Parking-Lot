package com.codhack.parkinglot.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputParser {

    public InputParser(){
    }

    public void parseFileInput(String path) {
        // Assuming valid path
        File inputFile = new File(path);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            try {
                while ((line = br.readLine()) != null) {
                    boolean isValid = Validation.validate(line.trim());
                    if(!isValid){
                        System.out.println("Invalid Input");
                    }else{
                        Execution.execute(line.trim());
                    }
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found at path specified");
        }
    }

}
