<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body>
<center>
<h1>Mysore One</h1>
<f:view>
	<h:outputLink value="BillPayment.jsp"> Bill Payment </h:outputLink><br>
	<h:outputLink value="BillReport.jsp"> Bill Report </h:outputLink><br>
	<h:outputLink value="UpdateSchemes.jsp"> Update Scheme </h:outputLink>
</f:view>
</center>
</body>
</html>