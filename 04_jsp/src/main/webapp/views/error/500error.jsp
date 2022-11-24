<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
    <!-- isErrorPage="true" :이 페이지가 에러페이지가 맞으면  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500에러페이지</title>
</head>
<body>
	<h1 style="color:red">에러발생발생발생</h1>
	<p>3초 후 메인화면으로 이동합니다.</p>
	<p><%=exception.getMessage() %></p> <!-- null -->
	<script>
		setInterval(() => {
			location.replace('<%=request.getContextPath()%>');
		},3000);
	</script>

</body>
</html>