package com.codhack.parkinglot;

import com.codhack.parkinglot.Commands.InputParser;

public class Main {

    public static void main(String[] args){
        InputParser inputParser = new InputParser();
        inputParser.parseFileInput("input");
    }

}
