package de.nordakademie.multiplechoice.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by ferencbeutel on 28.10.16.
 */
public class DateTimeValidationUtils {
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
