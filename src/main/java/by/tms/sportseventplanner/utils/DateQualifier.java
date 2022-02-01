package by.tms.sportseventplanner.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateQualifier {

    public static LocalDate getDateByDayNumber(int dayOfWeek){
        Calendar now = Calendar.getInstance();
        int weekday = now.get(Calendar.DAY_OF_WEEK);
        int days = dayOfWeek - weekday;
        if (days < 0) days += 7;
        now.add(Calendar.DAY_OF_YEAR, days);
        return now.getTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static int getDayNumberOld(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
}
