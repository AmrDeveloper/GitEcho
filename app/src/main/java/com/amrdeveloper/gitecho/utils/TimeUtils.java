import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    /**
     * Different time is less than one day
     */
    private static final int ZERO_DAYS = 0;

    /**
     * Number of days in one month
     */
    private static final int DAYS_INT_MONTH = 30;

    /**
     * Pattern for Github API date
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * Pattern for date like Github.com for example Nov 3, 2018
     */
    private static final String DATE_DIFFERENT_FORMAT_PATTERN = "MMMMM dd, yyyy";

    /**
     * If Different Between create date and update date less than 1 day
     */
    private static final String DAYS_TODAY_FORMAT = "Updated Today";

    /**
     * If Different Between create date and update date is less than 30 day
     */
    private static final String DAYS_DIFFERENT_FORMAT = "Updated %d days ago";

    /**
     * If Different Between create date and update date is bigger than 30 day
     */
    private static final String DATE_DIFFERENT_FORMAT = "Updated on %s";

    /**
     * @param updatedAt : Updated at date as String Object
     * @return : return String to show in view formatted
     * : depend on different time between updated date and current date
     */
    public static String getUpdatedFromTime(String updatedAt) {
        Date currentDate = new Date();
        Date updatedDate = formatUpdatedDate(updatedAt);

        if (currentDate.before(updatedDate)) {
            throw new IllegalStateException("Invalid Updated Date");
        }

        long differentTimeMS = currentDate.getTime() - updatedDate.getTime();
        long daysFromMS = TimeUnit.MILLISECONDS.toDays(differentTimeMS);

        if (daysFromMS == ZERO_DAYS) {
            return DAYS_TODAY_FORMAT;
        } else if (daysFromMS < DAYS_INT_MONTH) {
            return String.format(DAYS_DIFFERENT_FORMAT, daysFromMS);
        } else {
            String formatUpdatedAt = formatUpdatedFromDate(updatedDate);
            return String.format(DATE_DIFFERENT_FORMAT, formatUpdatedAt);
        }
    }

    /**
     * @param updatedAt : Updated at time as String
     * @return : updated at time as Date Object formatted by DATE_PATTERN pattern
     */
    private static Date formatUpdatedDate(String updatedAt) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
            return dateFormat.parse(updatedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param updatedDate : Updated at date formatted from Github API
     * @return : convert date format to DATE_DIFFERENT_FORMAT_PATTERN format
     */
    private static String formatUpdatedFromDate(Date updatedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_DIFFERENT_FORMAT_PATTERN);
        return simpleDateFormat.format(updatedDate);
    }
}
