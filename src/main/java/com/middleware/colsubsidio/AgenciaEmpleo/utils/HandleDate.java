package com.middleware.colsubsidio.AgenciaEmpleo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
@Component
public class HandleDate {

    @Autowired
    PropertiesUtil propertiesUtil;
    private static TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");

    public static Date retornDateNow() {
        Date date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timeZone);
            date = Calendar.getInstance().getTime();
        } catch (Exception e) {}
        return date;
    }

    public static String retornDateNowSimpleDateFormat() {
        String date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timeZone);
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            date = formatter.format(calendar.getTime());
        } catch (Exception e) {}
        return date;
    }

    public static Date retornDateByDay(int day) {
        Date date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeZone(timeZone);
            calendar.add(Calendar.DAY_OF_YEAR, -day);
            date =  calendar.getTime();
        } catch (Exception e) {}
        return date;
    }

    public static String retornDateTimeFormat(String date){
        String str = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sdf.parse(date);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            str= sdf2.format(d);
        } catch (Exception e) {}
        return str;
    }

    public static Date retornDateToString(String d) {
        Date date = null;
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            calendar.setTime(sdf.parse(d));
            calendar.setTimeZone(timeZone);
            date = calendar.getTime();
        } catch (Exception e) {}
        return date;
    }

    public Long getMillisecondsFromStartDate(Date startdate) {
		LocalDateTime startDateCast = startdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return startDateCast.until(LocalDateTime.now(), ChronoUnit.MILLIS);
	}

   public  String retornFechaString(String dia, String mes, String hora){
        String date = null;
        Calendar calendar = Calendar.getInstance();
         try {
              date = "el día " + dia + " del "+mes+" a las"+hora;
         }catch (Exception e){}
         return date;
   }

   public String dateToString(Date fecha){
            SimpleDateFormat frm= new SimpleDateFormat(propertiesUtil.getValidaFormatoFechaApp());
            return frm.format(fecha);
        }
}
