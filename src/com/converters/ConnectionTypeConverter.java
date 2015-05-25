package com.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class ConnectionTypeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext fc, UIComponent ui, String str) {		
		return str;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent ui, Object obj) {
		if (obj.toString().equals("pre")) {
			return "PrePaid";
		}
		return "PostPaid";
	}

}
