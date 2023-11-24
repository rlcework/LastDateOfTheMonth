package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        /*
         * This is to enter the value of date by the user
         * */
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a date in the format dd-MMM-yyyy (e.g., 01-Jan-2099): ");
        String inputDate = scanner.nextLine();
        scanner.close();
        Methods.logger.info("User input date :" + inputDate);
        /*
         * For Hardcode date
         *
         * String inputDate = "29-Feb-2024";
         */
        Methods.dateChecker(inputDate);
        Methods.logger.info("Successfully validate the input date");

        String output = Methods.outputValue(inputDate);
        Methods.logger.info("Successfuly get the last date value");
        Methods.logger.info(output);
        Methods.logger.info("************************");

        System.out.println("************************");
        System.out.println(output);
    }

}