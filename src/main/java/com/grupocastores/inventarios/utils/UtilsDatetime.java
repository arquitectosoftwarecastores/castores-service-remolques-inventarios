package com.grupocastores.inventarios.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * inventariosUtils - Clase para concentrar algunas funcionalidades comunes en el sistema.  
 *
 * @author Castores - Desarrollo TI
 *
 */
public class UtilsDatetime {
    /** Formato con fecha y hora ej. (13-08-2022 10:23:04) */
    public static final String FORMAT_DATE_HR = "yyyy-MM-dd HH:mm:ss";

    /** Formato con fecha ej.(13-08-2022) */
    public static final String FORMAT_DATE = "yyyy-MM-dd";
    
    public static final String REGEXP_DATE_HR = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01]) ([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9]:([0-5][0-9]|[6][0])$";
    
    public static final String REGEXP_DATE = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
    
    /**
     * getDateHrToday - Fecha y hr actual
     * @param
     * @return String formatt (yyyy-MM-dd HH:mm:ss)
     * */
    public String getDateHrToday() {
    	return new SimpleDateFormat(FORMAT_DATE_HR).format(Calendar.getInstance().getTime()); 
    }
    
    /**
     * getDateHrToday - Fecha
     * @param
     * @return String formatt (yyyy-MM-dd)
     * */
    public String getDateToday() {
    	return new SimpleDateFormat(FORMAT_DATE).format(Calendar.getInstance().getTime()); 
    }
    
    /**
     * @param Date inDate
     * @return String formatt (yyyy-MM-dd HH:mm:ss)
     * */
    public String dateToStringMDYHr(Date inDate) {
    	return new SimpleDateFormat(FORMAT_DATE_HR).format(inDate);
    }
    
    /**
     * @param Date
     * @return String formatt (yyyy-MM-dd)
     * */
    public String dateToStringDMY(Date inDate) {
    	return new SimpleDateFormat(FORMAT_DATE).format(inDate); 
    }
    
    /**
     * @param String yyyy-MM-dd 
     * @return Date 
     * @throws ParseException 
     * */
    public Date stringToDate(String dateStr) throws ParseException {
    	if(Boolean.TRUE.equals(isValidDateStr(dateStr))) {
    		return new SimpleDateFormat(FORMAT_DATE).parse(dateStr);
    	} else {
    		throw new ParseException("Cadena no valida", 0);
    	} 
    }
    
    /**
     * @param String yyyy-MM-dd HH:mm:ss
     * @return Date 
     * @throws ParseException 
     * */
    public Date stringToDateHr(String dateStr) throws ParseException {
    	if(Boolean.TRUE.equals(isValidDateStr(dateStr))) {
    		return new SimpleDateFormat(FORMAT_DATE).parse(dateStr);
    	} else {
    		throw new ParseException("Cadena no valida", 0);
    	} 
    }
    
    /**
     * @param String yyyy-MM-dd or yyyy-MM-dd HH:mm:ss
     * @return Boolean True if match w format 
     * */
    public Boolean isValidDateStr(String dateStr) {
    	Boolean resp = Boolean.FALSE;
    	if(!dateStr.equals("") && (dateStr.matches(REGEXP_DATE)||dateStr.matches(REGEXP_DATE_HR))) {
    		resp = Boolean.TRUE;
    	}
    	return resp;
    }
}
