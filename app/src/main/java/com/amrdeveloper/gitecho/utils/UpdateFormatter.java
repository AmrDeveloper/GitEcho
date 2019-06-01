package com.amrdeveloper.gitecho.utils;

import java.util.Date;

public class UpdateFormatter extends DateUtils{

    /**
     * If Different Between create date and update date less than 1 day
     */
    private static final String DAYS_TODAY_FORMAT = "Today";

    /**
     * If Different Between create date and update date is less than 30 day
     */
    private static final String DAYS_DIFFERENT_FORMAT = "%d days ago";

    /**
     * If Different Between create date and update date is bigger than 30 day
     */
    private static final String DATE_DIFFERENT_FORMAT = "on %s";

    public static String getUpdatedFromTime(String updatedAt){
        long differentInDays = getDifferentFromTime(updatedAt);
        if (differentInDays == ZERO_DAYS) {
            return DAYS_TODAY_FORMAT;
        } else if (differentInDays < DAYS_INT_MONTH) {
            return String.format(DAYS_DIFFERENT_FORMAT, differentInDays);
        } else {
            Date updatedDate = formatStringToDate(updatedAt);
            String formatUpdatedAt = formatDateToString(updatedDate);
            return String.format(DATE_DIFFERENT_FORMAT, formatUpdatedAt);
        }
    }
}
