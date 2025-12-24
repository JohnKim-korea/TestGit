<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
<h2><spring:message code="member.info"/></h2>
<form:form action="step3">
<p>
	<spring:message code="email"/>
	<form:input path="email"/>
	<form:errors path="email"/>
</p>
<p>
	<spring:message code="name"/>
	<form:input path="name"/>
	<form:errors path="name"/>
</p>
<p>
	<spring:message code="password"/>
	<form:password path="password"/>
	<form:errors path="password"/>
</p>
<p>
	<spring:message code="confirmPassword"/>
	<form:password path="confirmPassword"/>
	<form:errors path="confirmPassword"/>
</p>
<input type="submit" value="<spring:message code="register.btn"/>">
</form:form>
</body>
</html>







