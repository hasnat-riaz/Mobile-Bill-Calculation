<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bill Payment</title>
</head>
<body>
<center>
<h1>Mysore One</h1>
<f:view>
	<h:form>
		<h:panelGrid columns="2" border="2">
			<h:outputLabel for="name" value="Name"></h:outputLabel>
			<h:inputText id="name" value="#{billSchemeMB.name}">
				<f:validator validatorId="nameValidatorId"/>
			</h:inputText>
			<h:outputLabel for="conn" value="Type of Connection"></h:outputLabel>
			<h:selectOneRadio id="conn" value="#{billSchemeMB.typeOfConnection}">
				<f:selectItem itemLabel="PostPaid" itemValue="post"/>
				<f:selectItem itemLabel="PrePaid" itemValue="pre"/>
			</h:selectOneRadio>
			<h:outputLabel for="mblNo" value="Mobile Number"></h:outputLabel>
			<h:inputText id="mblNo" value="#{billSchemeMB.mobileNumber}"></h:inputText>
			<h:outputLabel for="mblOperator" value="Mobile Operator"></h:outputLabel>
			<h:selectOneMenu id="mblOperator" value="#{billSchemeMB.mobileOperator}" onchange="submit()" valueChangeListener="#{billSchemeMB.generateSchemeIds}">
				<f:selectItem itemLabel="--Select--"/>
				<f:selectItem itemLabel="Airtel" itemValue="Airtel"/>
				<f:selectItem itemLabel="Vodafone" itemValue="Vodafone"/>
			</h:selectOneMenu>
		</h:panelGrid>
		<h:panelGrid columns="2" border="2">
			<h:outputText value="Scheme"></h:outputText>
			<h:selectOneMenu value="#{billSchemeMB.schemeId}" onclick="submit()" valueChangeListener="#{billSchemeMB.getSchemeDetails}">
				<f:selectItem itemLabel="--Select--"/>
				<f:selectItems value="#{billSchemeMB.schemeList}"/>
			</h:selectOneMenu>
			<h:outputText value="Amount"></h:outputText>
			<h:outputText value="#{billSchemeMB.amount}"></h:outputText>
			<h:outputText value=""></h:outputText>
			<h:commandButton value="Submit" action="#{billSchemeMB.saveBill}"></h:commandButton>
		</h:panelGrid>
	</h:form>
	<h:messages></h:messages><br>
	<h:outputLink value="Home.jsp">Home Page</h:outputLink>
</f:view>
</center>
</body>
</html>