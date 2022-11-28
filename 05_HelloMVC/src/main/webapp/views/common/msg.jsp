<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg=(String)request.getAttribute("msg");
	String loc=(String)request.getAttribute("loc");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지페이지</title>
</head>
<body>
	<script>
		alert('<%=msg%>');
		
		location.replace("<%=request.getContextPath()%><%=loc%>");
		// /05_HelloMVC/	->인덱스메인페이지로 이동함
	</script>

</body>
</html>