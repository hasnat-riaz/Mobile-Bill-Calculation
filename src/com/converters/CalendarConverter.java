package com.converters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CalendarConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String str){
		try 
		{
			SimpleDateFormat simpleDateFormet = new SimpleDateFormat("dd-MMM-yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(simpleDateFormet.parse(str));
			return c;
		} 
		catch (Exception e) 
		{
			FacesMessage fm = new FacesMessage();
			fm.setDetail("Date Should be in dd-MMM-yyyy");
			fm.setSummary("Please enter correct date formet");
			throw new ConverterException();
		}
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object obj) {
		Calendar c = (Calendar) obj;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");		
		return sdf.format(c.getTime());
	}

}
