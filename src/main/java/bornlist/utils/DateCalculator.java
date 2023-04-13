package bornlist.utils;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DateCalculator {

    private final String TODAY = "Today";
    private final String TOMORROW = "Tomorrow";
    private final String AFTERTOMORROW = "Day after tomorrow";

    public String countDaysBetweenToday(Date date){
        DateTime today = new DateTime();
        DateTime inputDate = new DateTime(date.getTime());
        DateTime birthday = new DateTime(
                today.getYear(),
                inputDate.getMonthOfYear(),
                inputDate.getDayOfMonth(),
                0,0);
        if(birthday.isBefore(today)){
            birthday = new DateTime(
                    today.getYear()+1,
                    birthday.getMonthOfYear(),
                    birthday.getDayOfMonth(),
                    0,
                    0
            );
        }
        int test = Days.daysBetween(
                new LocalDate(today),
                new LocalDate(birthday)).getDays();

        if(test == 366){
            return TODAY;
        }
        if(test == 1){
            return TOMORROW;
        }
        if(test == 2){
            return AFTERTOMORROW;
        }
        return String.valueOf(test);
    }

}
