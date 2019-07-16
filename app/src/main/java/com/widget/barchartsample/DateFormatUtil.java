package com.widget.barchartsample;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


/**
 * Created by fawzy on 26/12/18.
 */

public class DateFormatUtil {

    /**
     * takes date string in format dd/MM/yyyy-->23/08/2018 and returns datelong
     ***/
    public static long getDateLong(String dateString) {
        if (dateString == null)
            return 0000000;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(dateString);
            long startDate = date.getTime();
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 00000000;
    }

    public static boolean isPastDate(String dateLong) {

        try {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(dateLong).before(new Date());

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }


    }

    /**
     * takes long millieSeconds and return String minutes:seconds
     **/
    public static String getMinSec(long millis) {
        return String.format(Locale.ENGLISH, "%02d : %02d ",
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                , TimeUnit.MILLISECONDS.toMinutes(millis));
    }

    public static String getHourMinSec(long millisUntilFinished) {
        return String.format(Locale.ENGLISH, "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
    }

    public static String formatLongDate(long date, String pattern) {
        Log.e("DateFormatUtil", "longDateToFormat.given= " + date + ", returns " + android.text.format.DateFormat.format(pattern, date).toString());
        String dateString = android.text.format.DateFormat.format(pattern, date).toString();
        return dateString;
    }

    public static String getMonthByNumber(int month) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
            cal.set(Calendar.MONTH, month);
            String month_name = month_date.format(cal.getTime());
            return month_name;
        } catch (Exception e) {
            e.printStackTrace();
            return "" + month;
        }
    }

    public static String convertFromFormatMM_to_mm(String date) {
        try {
            return DateFormat.getDateInstance(DateFormat.LONG).format(new Date(date));
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    public static Date convertStringToDate(String date) {
        String formate = "yyyy-MM-dd'T'HH:mm:ssGMT";
        DateFormat df = new SimpleDateFormat(formate);
        Date startDate;
        try {
            startDate = df.parse(date);
            return startDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String setDateWithFormatUTC(String date, String time) {
        String formate = "yyyy-MM-dd'T'HH:mm:ssGMT";
        SimpleDateFormat dateUtcFormate = new SimpleDateFormat(formate, Locale.ENGLISH);
        String dateFormate = "dd/MM/yyyy HH a";

        Calendar cal = Calendar.getInstance();

        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormate, Locale.ENGLISH);
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(sdfDate.parse(date + " " + time));
            cal.set(Calendar.YEAR, calendarDate.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calendarDate.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, calendarDate.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
            return dateUtcFormate.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setDateWithFormat(String date, String format) {
        String formate = format;
        SimpleDateFormat dateUtcFormate = new SimpleDateFormat(formate, Locale.ENGLISH);
        String dateFormate = "dd/MM/yyyy HH a";

        Calendar cal = Calendar.getInstance();

        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormate, Locale.ENGLISH);
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(sdfDate.parse(date));
            cal.set(Calendar.YEAR, calendarDate.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calendarDate.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, calendarDate.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
            return dateUtcFormate.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String setDateWithFormatUTC(String date) {
        //2018-05-07T11:36:10.843
        String formate = "yyyy-MM-dd'T'HH:mm:ss.fff";
        SimpleDateFormat dateUtcFormate = new SimpleDateFormat(formate, Locale.ENGLISH);
        String dateFormate = "dd/MM/yyyy HH a";

        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat(dateFormate, Locale.ENGLISH);
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(sdfDate.parse(date));
            cal.set(Calendar.YEAR, calendarDate.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calendarDate.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, calendarDate.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
            return dateUtcFormate.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String ConvertFromFormatUTC(Locale lang, String dateTime) {
//        String formate = "YYYY-MM-dd'T'HH:mm:ss";
        String formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS";
        String dateFormate = "d MMMM yyyy  ";
        SimpleDateFormat dateUtcFormate = new SimpleDateFormat(dateFormate, lang);
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat(formate, lang);
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(sdfDate.parse(dateTime));
            sdfDate.setTimeZone(calendarDate.getTimeZone());
            cal.set(Calendar.YEAR, calendarDate.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calendarDate.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, calendarDate.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
            return dateUtcFormate.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String ConvertFromFormatUTCWithHour(Locale lang, String dateTime) {
//        String formate = "YYYY-MM-dd'T'HH:mm:ss";
        String formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS";
        String dateFormate = "mm : HH - d MMMM yyyy  ";
        SimpleDateFormat dateUtcFormate = new SimpleDateFormat(dateFormate, lang);
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat(formate, lang);
            Calendar calendarDate = Calendar.getInstance();
            calendarDate.setTime(sdfDate.parse(dateTime));
            sdfDate.setTimeZone(calendarDate.getTimeZone());
            date = sdfDate.parse(dateTime);
            Log.e("asgaLog" + "DateFormatUtil", "date" + date.getTime() + "");
            cal.set(Calendar.YEAR, calendarDate.get(Calendar.YEAR));
            cal.set(Calendar.MONTH, calendarDate.get(Calendar.MONTH));
            cal.set(Calendar.DAY_OF_MONTH, calendarDate.get(Calendar.DAY_OF_MONTH));
            cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
            Log.e("asgaLog" + "date", cal.getTime() + "");
            return dateUtcFormate.format(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String formatDateMM_dd_yyy(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

        return fmt.format(calendar.getTime());


    }

    public static String formatDate_dd_MMMM(Calendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MMMM ");

        return fmt.format(calendar.getTime());


    }

    public static String formatDateDDDD_dd_MMMM_yyyy(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE dd MMMM yyyy ");

        return fmt.format(calendar.getTime());


    }

    public static Date convertFromUTCToDate(String dateTime) {
        String formate = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS";
        SimpleDateFormat sdfDate = new SimpleDateFormat(formate);

        Date date = null;
        try {
            date = sdfDate.parse(dateTime);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public static int getAge(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();

        int age = 0;

        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            Log.e("asgaLog", "Can't be born in the future");
            throw new IllegalArgumentException("Can't be born in the future");
        }

        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;

            // If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    public static String reformatBadDate(String date) {
        Long dateNumber = substringDate(date);
        return android.text.format.DateFormat.format("dd MMMM yyyy", dateNumber).toString();
    }

    public static String reformatBadDate(String date, String pattern) {
        Long dateNumber = substringDate(date);
        long millisecond = Long.parseLong(String.valueOf(dateNumber));
        return android.text.format.DateFormat.format(pattern, millisecond).toString();
    }

    public static long substringDate(String date) {
        int startIndex = date.indexOf("(") + 1;
        int endIndex = date.indexOf("+");
        return Long.valueOf(date.substring(startIndex, endIndex));
    }

    public static String convertDateToHours(String s) {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return f.format(d);
    }

    public static String convertDateToDateMonths(String s) {
        SimpleDateFormat f = new SimpleDateFormat("dd MMMM");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date d = null;
        try {
            d = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return f.format(d);
    }

}
