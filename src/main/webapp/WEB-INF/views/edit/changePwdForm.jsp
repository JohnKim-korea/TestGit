<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="change.pwd.title"/></title>
</head>
<body>
<form:form>
	<p>
		<spring:message code="curPassword"/>
		<form:password path="curPassword"/>
		<form:errors path="curPassword"/>
	</p>
	<p>
		<spring:message code="newPassword"/>
		<form:password path="newPassword"/>
		<form:errors path="newPassword"/>
	</p>
	<input type="submit" value="<spring:message code="change.btn"/>">
</form:form>
</body>
</html>





