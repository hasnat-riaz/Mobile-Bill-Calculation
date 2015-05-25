package com.presentationtier;

import java.util.Calendar;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.persistencetier.BillService;
import com.to.BillTO;
import com.to.SchemeTO;

public class BillSchemeMB {
	private String schemeId;
	private Long mobileNumber;
	private String name;
	private Double amount;
	private String mobileOperator;
	private String typeOfConnection;
	private List<SelectItem> schemeList;
	private String message;
	
	public void generateSchemeIds(ValueChangeEvent event)
	{
		try 
		{
			mobileOperator = (String) event.getNewValue();
			List<String> list = new BillService().getSchemeList(mobileOperator);
			for (String string : list) 
			{
				SelectItem selectItem = new SelectItem(string);
				schemeList.add(selectItem);
			}
		} 
		catch (Exception ex) 
		{
			message = ex.getMessage();
		}
	}
	public void getSchemeDetails(ValueChangeEvent event)
	{
		try 
		{
			schemeId = event.getNewValue().toString();
			SchemeTO sto = new BillService().getSchemeDetails(schemeId);
			amount = sto.getRechargeAmount();
		} 
		catch (Exception e) 
		{
			message = e.getMessage();
		}
	}
	public String saveBill()
	{
		try 
		{
			BillTO billTO = new BillTO();
			billTO.setDateOfPayment(Calendar.getInstance());
			Integer billId = new BillService().saveBill(billTO);
			if(billId != null)
			{
				message = "Bill generated with the BillId " + billId;
				return "success";
			}			
		} 
		catch (Exception e) 
		{
			message = e.getMessage();			
		}
		return "failure";
	}
	//setters and getters
	public String getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getTypeOfConnection() {
		return typeOfConnection;
	}
	public void setTypeOfConnection(String typeOfConnection) {
		this.typeOfConnection = typeOfConnection;
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
}
