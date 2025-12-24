<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="login.title"/></title>
</head>
<body>
<form:form>
<form:errors/> <!-- 글로벌 에러코드 표현하는 부분 -->
<p>
	<spring:message code="email"/>
	<form:input path="email"/>
	<form:errors path="email"/> <!-- 폼 테그에서 유효성 검사를 시작함 그리고 자바 메서드로가서 어떤 오류가 있는지 파악하고 
								그 오류에 맞는 메서지를 출력하게함 그다음 properties 파일에 있는 적절한 메세지를 가져옴  -->
</p>
<p>
	<spring:message code="password"/>
	<form:password path="password"/>
	<form:errors path="password"/>
</p>
<input type="submit" value="<spring:message code="login.btn"/>">
</form:form>
</body>
</html>







