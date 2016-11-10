package de.nordakademie.multiplechoice.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is used to validate if strings are parseable to Dates and Times
 * @author  Ferenc Beutel, Max Hort, Melanie Beckmann, Hendrik Peters
 */
public class DateTimeValidationUtils {
    /**
     * This method checks if a string is parseable to a date
     * @param dateString string that is checked
     * @param formatter used to determine whether string is a date
     * @return boolean whether string is parseable with formatter
     */
    public static boolean isDateParseable(String dateString, DateTimeFormatter formatter) {
        if(dateString != null) {
            try {
                LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * This method checks if a string is parseable to a time
     * @param dateString string that is checked
     * @param formatter used to determine whether string is a date
     * @return boolean whether string is parseable with formatter
     */
    public static boolean isTimeParseable(String dateString, DateTimeFormatter formatter) {
        if(dateString != null) {
            try {
                LocalTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
