<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl이용하기</title>
</head>
<body>
	<h2>jstl이용하기</h2>
	<p>
		jstl 아파치에서 만든 커스텀액션태그로 제공되는 jar파일을 추가하여 이용한다<br>
		core(조건문, 반복문, 변수선언, 출력 등)<br>
		fmt(숫자, 날짜 출력시 패턴을 만들어주는 기능)<br>
		function(처리하는 메소드 호출)<br>
	</p>
	<p>
		jstl사용하기<br>
		1. 프로젝트에 jar파일을 추가<br>
		2. 사용할 페이지에 지시자를 이용해서 사용선언 %@ taglib % 를 이용
	</p>
	
	<!-- core태그 사용하여 if문 -->
	<c:if test="${empty list }">
		<p>전달된 list가 없습니다</p>
	</c:if>
	<h3>
		<a href="${pageContext.request.contextPath }/views/jstl/core/01_outset.jsp">값 저장/출력하는 태그</a>
	</h3>

</body>
</html>