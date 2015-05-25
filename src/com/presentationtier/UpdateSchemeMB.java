package com.presentationtier;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.persistencetier.BillService;
import com.to.SchemeTO;
import com.utility.AppConfig;

public class UpdateSchemeMB {
	private String schemeId;
	private Double amount;
	private String mobileOperator;

	private List<SelectItem>schemeList = new ArrayList<SelectItem>();

	private String message;


	//setter and getter methods
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getMobileOperator() {
		return mobileOperator;
	}
	public void setMobileOperator(String mobileOperator) {
		this.mobileOperator = mobileOperator;
	}
	public List<SelectItem> getSchemeList() {
		return schemeList;
	}
	public void setSchemeList(List<SelectItem> schemeList) {
		this.schemeList = schemeList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getAllSchemes(){
		try {
			List<String> allSchemeList=new BillService().getAllSchemes();
			for (String aString : allSchemeList) {
				SelectItem aSelectItem=new SelectItem(aString);
				this.schemeList.add(aSelectItem);
			}
			return "success";
		} 
		catch (Exception e) {
			// TODO: handle exception
			this.message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			return "failure";	
		}
			
		
	}
	public void getSchemeDetails(ValueChangeEvent event){
		this.schemeId=(String)event.getNewValue();
		try {
			SchemeTO aSchemeTO=new BillService().getSchemeDetails(this.schemeId);
			amount = aSchemeTO.getRechargeAmount();
			mobileOperator = aSchemeTO.getMobileOperator();			
		} 
		catch (Exception e) {
			// TODO: handle exception
			this.message = AppConfig.PROPERTIES.getProperty(e.getMessage());
		}
		
	}
	public String updateScheme(){
		try {
			SchemeTO aSchemeTO=new SchemeTO();
			aSchemeTO.setMobileOperator(this.mobileOperator);
			aSchemeTO.setRechargeAmount(this.amount);
			aSchemeTO.setSchemeId(this.schemeId);
			String result=new BillService().updateScheme(aSchemeTO);
			this.message=("SchemeId:"+result+"is updated successfully");
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			this.message=AppConfig.PROPERTIES.getProperty(e.getMessage());
			return "failure";
		}
		
	}
}
