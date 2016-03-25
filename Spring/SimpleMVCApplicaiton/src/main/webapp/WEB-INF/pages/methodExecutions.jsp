<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
    <title>Movie Theater</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <jsp:include page="essentials/essentials.jsp"/>
</head>
<body>
<h1>All executions</h1>
<div id="content">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>id</th>
            <th>method name</th>
            <th>duration</th>
            <th>execution started</th>
            <th>is long</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${executions}" var="execution">
            <tr>
                <td>${execution.id}</td>
                <td>${execution.methodName}</td>
                <td>${execution.duration}</td>
                <td>${execution.executed}</td>
                <td>${execution.permittedDurationExceeded}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>