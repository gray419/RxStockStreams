package com.graywolf.rxstockstreams.utils;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Locale;

public class TimeUtils {

    public static String getFormattedTimeStamp(String time) {
        DateTime date = ISODateTimeFormat.dateTimeParser().parseDateTime(time);
        DateTime now = new DateTime();
        int minutesElapsedInt, secondsElapsedInt, hoursElapsedInt;

        Period period = new Period(date, now);
        PeriodFormatter secondsFmt = new PeriodFormatterBuilder()
                .appendSeconds()
                .printZeroNever()
                .toFormatter();

        PeriodFormatter minutesFmt = new PeriodFormatterBuilder()
                .appendMinutes()
                .toFormatter();

        PeriodFormatter hoursFmt = new PeriodFormatterBuilder()
                .appendHours()
                .toFormatter();

        String secondsElapsed = secondsFmt.print(period);
        if(!secondsElapsed.equals(""))
            secondsElapsedInt = Integer.valueOf(secondsElapsed);
        else secondsElapsedInt = 0;

        String minutesElapsed = minutesFmt.print(period);
        if(!minutesElapsed.equals(""))
            minutesElapsedInt = Integer.valueOf(minutesElapsed);
        else
            minutesElapsedInt = 0;

        String hoursElapsed = hoursFmt.print(period);
        if(!hoursElapsed.equals(""))
            hoursElapsedInt = Integer.valueOf(hoursElapsed);
        else
            hoursElapsedInt = 0;

        DateTimeFormatter todayFmt = DateTimeFormat.forPattern("h:mm a").withLocale(Locale.US);
        DateTimeFormatter notTodayFmt = DateTimeFormat.forPattern("M/d/YY").withLocale(Locale.US);

        if((date.toLocalDate()).equals(new LocalDate())){
            if(secondsElapsedInt < 60 && minutesElapsedInt == 0 && hoursElapsedInt == 0){
                return String.format("%ss",secondsElapsed);
            }
            else if(minutesElapsedInt < 60 && hoursElapsedInt == 0){
                return String.format("%sm",minutesElapsed);
            }
            else if (hoursElapsedInt >= 1)
                return date.toString(todayFmt);

            return date.toString(todayFmt);
        }
        else{
            return date.toString(notTodayFmt);
        }
    }
}