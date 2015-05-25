package com.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class NameValidator implements Validator{

	@Override
	public void validate(FacesContext fc, UIComponent ui, Object obj)
			throws ValidatorException {
		String name = obj.toString();
		for(int i=0; i<name.length();i++)
		{
			Character t = name.charAt(i);
			if(!Character.isLetterOrDigit(t))
			{
				throw new ValidatorException(new FacesMessage("Name cannot contain any special character(s)"));
			}
		}
	}

}
