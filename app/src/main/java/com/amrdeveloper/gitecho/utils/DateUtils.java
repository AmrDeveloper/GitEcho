package com.amrdeveloper.gitecho.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    /**
     * Different time is less than one day
     */
    public static final int ZERO_DAYS = 0;

    /**
     * Number of days in one month
     */
    public static final int DAYS_INT_MONTH = 30;

    /**
     * Pattern for Github API date
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * Pattern for date like Github.com for example Nov 3, 2018
     */
    private static final String DATE_DIFFERENT_FORMAT_PATTERN = "MMMMM dd, yyyy";

    /**
     * @param updatedAt : Updated at date as String Object
     * @return : return String to show in view formatted
     * : depend on different time between updated date and current date
     */
    public static long getDifferentFromTime(String updatedAt) {
        Date currentDate = new Date();
        Date updatedDate = formatStringToDate(updatedAt);

        if (currentDate.before(updatedDate)) {
            throw new IllegalStateException("Invalid Updated Date");
        }

        long differentTimeMS = currentDate.getTime() - updatedDate.getTime();
        return TimeUnit.MILLISECONDS.toDays(differentTimeMS);
    }

    /**
     * @param date : Updated at time as String
     * @return : updated at time as Date Object formatted by DATE_PATTERN pattern
     */
    public static Date formatStringToDate(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param date : Updated at date formatted from Github API
     * @return : convert date format to DATE_DIFFERENT_FORMAT_PATTERN format
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_DIFFERENT_FORMAT_PATTERN,Locale.ENGLISH);
        return simpleDateFormat.format(date);
    }
}
