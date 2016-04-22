<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<title>Movie Theater</title>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<jsp:include page="essentials/essentials.jsp" />
</head>
<body>
	<div id="shell">
		<!-- Main -->
		<div id="main">
			<form:form method="post" action="register" modelAttribute="newUser">
				<div class="input-group input-sm">
					<form:label path="firstName" cssClass="input-group-addon">First Name</form:label>
					<form:input path="firstName" cssClass="form-control"
						required="required"></form:input>
				</div>
				<div class="input-group input-sm">
					<form:label path="lastName" cssClass="input-group-addon">Last Name</form:label>
					<form:input path="lastName" cssClass="form-control"
						required="required"></form:input>
				</div>
				<div class="input-group input-sm">
					<form:label path="email" cssClass="input-group-addon">Email</form:label>
					<form:input path="email" cssClass="form-control"
						required="required"></form:input>
				</div>
				<div class="input-group input-sm">
					<form:label path="password" cssClass="input-group-addon">Password</form:label>
					<form:input path="password" cssClass="form-control" type="password"
						required="required"></form:input>
				</div>
				<div class="input-group input-sm">
					<form:label path="roles" cssClass="input-group-addon">Roles</form:label>
					<form:select path="roles" cssClass="form-control"
						multiple="multiple">
						<form:options items="${availableRoles}"></form:options>
					</form:select>
				</div>
				<form:button type="submit" class="btn right">Submit</form:button>
			</form:form>
		</div>
	</div>
</body>
</html>