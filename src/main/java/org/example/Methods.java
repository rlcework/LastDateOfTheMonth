package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Methods {

    public static Logger logger = LogManager.getLogger();
    /*
     * leap year checker
     * */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

    /*
     * getting the last day of the month
     * */
    public static String getLastDayOfMonth(String inputDate) {

        try {
            DateFormat SDFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = SDFormat.parse(inputDate);

            Calendar cR = Calendar.getInstance();
            cR.setTime(date);
            int lastDay = cR.getActualMaximum(Calendar.DAY_OF_MONTH);

            return String.valueOf(lastDay);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle invalid date format
        }
    }

    /*
     * Validation of input date value
     * */
    public static boolean dateChecker(String inputDate) {
        String[] dateParts = inputDate.split("-");

        if (dateParts.length == 3) {
            String day = dateParts[0];
            String month = dateParts[1];
            String year = dateParts[2];

            // Check if day is in the range 1-31
            int dayValue = Integer.parseInt(day);
            if (dayValue < 1 || dayValue > 31) {
                logger.error("Invalid day value, please re-enter the correct value");
                throw new RuntimeException("Invalid day value, please re-enter the correct value");
            }
            logger.info("Day is valid");

            // Check if month is in the range Jan-Dec
            if (!isValidMonth(month)) {
                throw new RuntimeException("Invalid month value, please re-enter the correct value");
            }
            logger.info("Month is valid");

            // Check if year is in the range 0000-2099
            int yearValue = Integer.parseInt(year);
            if (yearValue < 0 || yearValue > 2099) {
                logger.error("Invalid year value, please re-enter the correct value");
                throw new RuntimeException("Invalid year value, please re-enter the correct value");
            }
            logger.info("Year is valid");

            //check if the input value for Febuary
            if (month.equals("Feb") && dayValue == 30) {
                logger.error("Invalid day value, No day 30 in " + month);
                throw new RuntimeException("Invalid day value, No day 30 in " + month);
            } else if (month.equals("Feb") && dayValue == 29 && !isLeapYear(yearValue)) {
                logger.error("Invalid day value, No 29 in year " + year);
                throw new RuntimeException("Invalid day value, No 29 in year " + year);
            }

            //checking months with 31
            if (!isValidMonthWith31(month) && dayValue == 31) {
                logger.error("Invalid day value, No day 31 in " + month);
                throw new RuntimeException("Invalid day value, No day 31 in " + month);
            }
        } else {
            logger.error("Invalid date format.");
            throw new RuntimeException("Invalid date format.");
        }
        return true;
    }

    // Validation of months
    private static boolean isValidMonth(String month) {
        String[] validMonths = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        int length = validMonths.length;

        for (String findValidMonth : validMonths) {
            if (findValidMonth.equals(month)) {
                return true;
            }
        }
        return false;
    }

    //Validation of months that has day 31
    private static boolean isValidMonthWith31(String month) {
        String[] validMonthWith31 = {"Jan", "Mar", "May", "Jul", "Aug", "Oct", "Dec"};
        for (String findValidMonthWith31 : validMonthWith31)
            if (findValidMonthWith31.equals(month)) {
                return true;
            }
        return false;
    }

    //formatting the output
    public static String outputValue(String inputDate) {
        String output = "";
        String lastDay = getLastDayOfMonth(inputDate);
        String[] dateParts = inputDate.split("-");
        if (dateParts.length == 3) {
            String Months = dateParts[1];
            String Years = dateParts[2];

            if (lastDay != null && !Months.equals("Feb")) {
                output = (lastDay + " (last day of " + Months + " in year " + Years + ")");
            }
            if (lastDay != null && Months.equals("Feb") && lastDay.equals("29")) {
                output = (lastDay + " (last day of " + Months + " in year " + Years + ", a leap year)");
            } else if (lastDay != null && Months.equals("Feb") && lastDay.equals("28")) {
                output = (lastDay + " (last day of " + Months + " in year " + Years + ", not a leap year)");
            }
        }
        return output;
    }

}
