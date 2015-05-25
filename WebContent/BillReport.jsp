<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Report</title>
</head>
<body>
<center>
<h1>Mysore One</h1>
<f:view>
	<h:form>
		<h:panelGrid border="2" columns="4">
			<h:outputText value="Start Date"></h:outputText>
			<h:inputText value="#{reportMB.fromDate}" required="true" requiredMessage="Can not be empty">
				<f:converter converterId="calendarConverterId"/>
			</h:inputText>
			<h:outputText value="End Date"></h:outputText>
			<h:inputText value="#{reportMB.toDate}" required="true" requiredMessage="Can not be empty">
				<f:converter converterId="calendarConverterId"/>
			</h:inputText>
		</h:panelGrid>
		<h:commandButton value="Get Report" action="#{reportMB.reportDetails}"></h:commandButton>
		
		<h:dataTable var="bill" value="#{reportMB.billList}" rendered="#{reportMB.billList}!=null">
			<h:column>
				<f:facet name="header">
					<h:outputText value="Bill Id"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.billId}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Name"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.name}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Mobile Number"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.mobileNumber}"></h:outputText>
			</h:column>			
			<h:column>
				<f:facet name="header">
					<h:outputText value="Amount"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.amount}"></h:outputText>
			</h:column>			
			<h:column>
				<f:facet name="header">
					<h:outputText value="Mobile Operator"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.mobileOperator}"></h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Day of Payment"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.dateOfPayment}">
						<f:converter converterId="calendarConverterId"/>
					</h:outputText>
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputText value="Type of Connection"></h:outputText>					
				</f:facet>
					<h:outputText value="#{bill.typeOfConnection}">
						<f:converter converterId="connectionTypeConverterId"/>
					</h:outputText>
			</h:column>
		</h:dataTable>
	</h:form>
	<h:messages></h:messages><br>
	<h:outputLink value="Home.jsp">Home Page</h:outputLink>
</f:view>
</center>
</body>
</html>