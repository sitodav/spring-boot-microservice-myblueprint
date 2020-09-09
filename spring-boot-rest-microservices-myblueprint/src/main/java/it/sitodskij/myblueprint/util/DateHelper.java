package it.sitodskij.myblueprint.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author sitodskij
 *
 * Classe di helper per date 
 */
public class DateHelper {

    private static final Logger log = LoggerFactory.getLogger(DateUtils.class);


    public static final String DATE_PATTERN_GIORNO_MESE_ANNO = "dd/MM/yyyy";

    public static Date parseDateString(String dateString) {
        try {
        	if(dateString !=null && dateString.length()>9) {
        		SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN_GIORNO_MESE_ANNO, Locale.ITALY);            					  
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(formatter.parse(dateString.substring(0,10)));
                calendar.add(Calendar.HOUR_OF_DAY, 2);
        		Date date =calendar.getTime();    		  
                return date;    
        	}        	    	
        	
		} catch (ParseException e) {
            log.error(e.getMessage());
		}
        return null;
    }

    public static String formatStringDate(Date date) {
        return  new SimpleDateFormat(DATE_PATTERN_GIORNO_MESE_ANNO).format(date);
    }

    public static boolean isDataBeforeNow(Date date) {
        if (date == null)
            return false;
//        Date now = new Date();
        Date now = getCurrentDate();
        return  date.before(now) && !DateUtils.isSameDay(date, now);
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date getCurrentDate() {
    	
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        Date currentDate = calendar.getTime();
        return currentDate;
    }
    
    public static Boolean areDateRangesOverlapping(Date startDateA, Date endDateA, Date startDateB, Date endDateB) {
        return (startDateA.before(endDateB) || DateUtils.isSameDay(startDateA,endDateB)) && (endDateA.after(startDateB) || DateUtils.isSameDay(endDateA,startDateB));
    }

    public static Boolean isFirstDateRangeCoveringTheSecond(Date startDateA, Date endDateA, Date startDateB, Date endDateB) {
        return (startDateA.before(startDateB) || DateUtils.isSameDay(startDateA,startDateB)) && (endDateA.after(endDateB) || DateUtils.isSameDay(endDateA,endDateB));
    }

}
