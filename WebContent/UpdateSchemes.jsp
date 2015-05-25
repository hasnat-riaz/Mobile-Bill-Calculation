<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Scheme</title>
</head>
<body>
<center>
<h1>Mysore One</h1>
<f:view>
	<h:form>
		<h:panelGrid border="2" columns="2">
			<h:outputText value="Schemes"></h:outputText>
			<h:selectOneMenu value="#{updateSchemeMB.schemeId}" onclick="submit()" valueChangeListener="#{updateSchemeMB.getSchemeDetails}">
				<f:selectItem itemLabel="--Select--" itemValue="0"/>
				<f:selectItems value="#{updateSchemeMB.schemeList}"/>
			</h:selectOneMenu>
		</h:panelGrid>
		<h:panelGrid border="2" columns="2">
			<h:outputText value="Scheme ID"></h:outputText>
			<h:outputText value="#{updateSchemeMB.schemeId}"></h:outputText>
			<h:outputText value="MobileOperator"></h:outputText>
			<h:outputText value="#{updateSchemeMB.mobileOperator}"></h:outputText>
			<h:outputText value="Recharge Amount"></h:outputText>
			<h:inputText value="#{updateSchemeMB.amount}"></h:inputText>
		</h:panelGrid>
		<h:commandButton value="Update" action="#{updateSchemeMB.updateScheme}"></h:commandButton><br>
		<h:messages></h:messages>
	</h:form>
</f:view>
</center>
</body>
</html>