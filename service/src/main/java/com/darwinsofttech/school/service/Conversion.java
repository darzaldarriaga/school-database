package com.darwinsofttech.school.service;

public class Conversion {

    public final static String convertNames(String input){
        String[] results;
        String finalResult = "";
        results = input.split(" ");
        for(String result : results){
            finalResult += result.toUpperCase().charAt(0) + result.substring(1).toLowerCase() + " ";
        }
        return finalResult;
    }
}
