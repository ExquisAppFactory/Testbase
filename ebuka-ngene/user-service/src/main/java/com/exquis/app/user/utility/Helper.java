package com.exquis.app.user.utility;

import com.exquis.app.user.constant.Generic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.exquis.app.user.constant.Generic.ALL_ALPHANUMERIC;
import static com.exquis.app.user.constant.Generic.ALL_NUMERIC;

public class Helper {
    public static <T> boolean isEmpty(T value) {
        if (value == null) return true;
        if (value instanceof String && ((String) value).trim().isEmpty()) return true;
        return false;
    }

    public static <T> boolean isNotEmpty(T value) {
        return !isEmpty(value);
    }

    public static String getAlphaNumeric(int size)
    {
        String stringToPickFrom = ALL_ALPHANUMERIC;
        StringBuffer stringBuffer = new StringBuffer(size);
        for(int i=0; i<size; i++)
        {
            int index = (int)(stringToPickFrom.length() * Math.random());
            stringBuffer.append(stringToPickFrom.charAt(index));
        }
        return stringBuffer.toString();
    }

    public static String getNumeric(int size)
    {
        String numbersToPickFrom = ALL_NUMERIC;
        StringBuffer stringBuffer = new StringBuffer(size);
        for(int i=0; i<size; i++)
        {
            int index = (int)(numbersToPickFrom.length() * Math.random());
            stringBuffer.append(numbersToPickFrom.charAt(index));
        }
        return stringBuffer.toString();
    }

    public static String getUUID()
    {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long getDaysInMiliseconds(int day)
    {
        return TimeUnit.DAYS.toMillis(1);
    }
    public static long getHoursInMilliseconds(int hours)
    {
        long milliseconds = hours * 60 * 60 *1000;
        return milliseconds;
    }

    public static long getMinutesInMilliseconds(int minutes)
    {
        long milliseconds = minutes * 60 *1000;
        return milliseconds;
    }

    public static List<String> isValidPassword(String passwordhere) {

        List<String> errorList = new ArrayList<String>();

        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (passwordhere.length() <= Generic.PASSWORD_LENGTH) {
            errorList.add("Password length must have at least " + String.valueOf(Generic.PASSWORD_LENGTH ) + " character !!");
        }
        if (!lowerCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have at least one lowercase character !!");
        }
        if (!digitCasePatten.matcher(passwordhere).find()) {
            errorList.add("Password must have at least one digit character !!");
        }

        return errorList;

    }
}
