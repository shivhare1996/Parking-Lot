package com.codhack.parkinglot.Utils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OperationUtils {

    private static int INT_MAX = 1000;
    private static int INT_MIN = 0;


    public static boolean isEmptyList(List<?> list){
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(String string){
        return string == null || string.length() == 0;
    }

    public static void printCommaSeperated(List<?> list){
        if(isEmptyList(list)){
            return;
        }

        String commaSeperated = list
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        System.out.println(commaSeperated);
    }

    /**
     * Return Optional empty if input is empty or its character is not a digit
     * @param input input string example "1234"
     * @return int 1234
     */
    public static Optional<Integer> convertStringToDigits(String input){

        if(isEmpty(input)){
            return Optional.empty();
        }

        int ret = 0;

        for (int i =0; i<input.length(); i++){

            if(input.charAt(i) - '0' < 0 || input.charAt(i) - '0' > 9){
                return Optional.empty();
            }
            ret = ret*10 + (input.charAt(i) - '0');
        }

        return Optional.of(ret);
    }

    public static boolean integerIsBetween(int input){
        return input >=0  && input <=1000;
    }
}
