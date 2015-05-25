package com.presentationtier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.persistencetier.BillService;
import com.to.BillTO;
import com.utility.AppConfig;

public class ReportMB {
	private Calendar fromDate;
	private Calendar toDate;
	private List<BillTO>billList=new ArrayList<BillTO>();
	private String message;

	//setter and getter methods
	public Calendar getFromDate() {
		return fromDate;
	}
	public void setFromDate(Calendar fromDate) {
		this.fromDate = fromDate;
	}
	public Calendar getToDate() {
		return toDate;
	}
	public void setToDate(Calendar toDate) {
		this.toDate = toDate;
	}
	public List<BillTO> getBillList() {
		return billList;
	}
	public void setBillList(List<BillTO> billList) {
		this.billList = billList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String reportDetails(){
		try {
			Calendar today=Calendar.getInstance();
			if(this.fromDate.before(this.toDate) || this.toDate.after(today)){
				throw new Exception("Start Date can not be before End date or End date can not be after current date");
			}
			this.billList = new BillService().getBillReport(fromDate, toDate);
			if((this.billList==null)||(this.billList.isEmpty()))
			{
				throw new Exception("No bill report is found for this date intervel");
			}

			return "success";
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			this.message = AppConfig.PROPERTIES.getProperty(e.getMessage());
			return "failure";
		}
	}
}
