package com.example.lagun.play4me4.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtils {

    public static final String PATTERN_DATE = "dd/MM/yyyy";
    public static final String PATTERN_TIME = "HH:mm";
    public static final String PATTERN_DATETIME = PATTERN_DATE + " - " + PATTERN_TIME;
    public static final int DAYS_IN_MILLIS = 1000 * 60 * 60 * 24;

    public static String formatDate(GregorianCalendar calendar) {
        GregorianCalendar now = new GregorianCalendar();
        if (now.get(Calendar.YEAR) != calendar.get(Calendar.YEAR) || calendar.compareTo(now) < 0) {
            return new SimpleDateFormat(PATTERN_DATE).format(calendar.getTime());
        } else {
            int diff = daysBetween(now.getTime(), calendar.getTime());
            String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
            if (diff < 7) {
                if (now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                    return "Oggi";
                } else {
                    now.add(Calendar.DAY_OF_MONTH, 1);
                    if (now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                        return "Domani";
                    } else {
                        String weekday = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
                        return capitalize(weekday) + " " + day;
                    }
                }
            } else {
                String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
                return day + " " + capitalize(month);
            }
        }
    }

    public static String formatDateExtended(GregorianCalendar calendar) {
        return new SimpleDateFormat(PATTERN_DATE).format(calendar.getTime());
    }

    public static GregorianCalendar parseDateTime(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_DATETIME);
        GregorianCalendar calendar = new GregorianCalendar();
        try {
            calendar.setTime(formatter.parse(date));
            return calendar;
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }
    }

    public static String formatDateTime(GregorianCalendar calendar) {
        return new SimpleDateFormat(PATTERN_DATETIME).format(calendar.getTime());
    }

    public static String formatTime(GregorianCalendar calendar) {
        return new SimpleDateFormat(PATTERN_TIME).format(calendar.getTime());
    }

    private static int daysBetween(Date d1, Date d2) {
        return (int) ((d2.getTime() - d1.getTime()) / DAYS_IN_MILLIS);
    }

    private static String capitalize(String s) {
        return s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
    }

    public static boolean isDataValid(String data){
        String[] split = data.split("/");
        if(split.length!=3)
            return false;
        try {
            int giorno=Integer.parseInt(split[0]);
            int mese=Integer.parseInt(split[1]);
            int anno=Integer.parseInt(split[2]);
            if(giorno<1 || mese<1 || mese>12 || anno<2000)
                return false;
            switch(mese){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: if(giorno>31) return false; break;
                case 2: if(giorno>((anno%4)==0?29:28)) return false; break;
                default: if(giorno>30) return false;
            }
            return true;
        }catch (Exception e) {
            return false;
        }

    }

    public static boolean isHourValid(String orario){
        String[] split= orario.split(":");
        if(split.length!=2)
            return false;
        try{
            int ora=Integer.parseInt(split[0]);
            int minuti=Integer.parseInt(split[1]);
            if(ora<0 || ora>23 || minuti<0 || minuti>59)
                return false;
            return true;
        }catch(Exception e){
            return false;
        }

    }

    public static String getMese(String mese){
        if(mese.startsWith("0"))
            mese=mese.substring(1);
        switch(mese){
            case "1": return "GEN";
            case "2": return "FEB";
            case "3": return "MAR";
            case "4": return "APR";
            case "5": return "MAG";
            case "6": return "GIU";
            case "7": return "LUG";
            case "8": return "AGO";
            case "9": return "SET";
            case "10": return "OTT";
            case "11": return "NOV";
        }
        return "DIC";
    }


    public static String getMese(String mese, int intero){
        if(mese.startsWith("0"))
            mese=mese.substring(1);
        switch(mese){
            case "1": return "Gennaio";
            case "2": return "Febbraio";
            case "3": return "Marzo";
            case "4": return "Aprile";
            case "5": return "Maggio";
            case "6": return "Giugno";
            case "7": return "Luglio";
            case "8": return "Agosto";
            case "9": return "Settembre";
            case "10": return "Ottobre";
            case "11": return "Novembre";
        }
        return "Dicembre";
    }

    public static String getGiorno(int giorno){

        switch (giorno){
            case 1: return "Domenica";
            case 2: return "Lunedi";
            case 3: return "Martedi";
            case 4: return "Mercoledi";
            case 5: return "Giovedi";
            case 6: return "Venerdi";
            case 7: return "Sabato";
            default: return "error";
        }
    }
}
