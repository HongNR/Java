<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>core태그 활용하기</title>
</head>
<body>
	<h3>set/out태그 이용하기</h3>
	<p>c:set태그, c:out태그는 값을 저장하고 페이지에 출력하는 태그</p>
	<ul>c:set태그 속성
		<li>var : 변수명</li>
		<li>value : 변수명에 저장될 값</li>
		<li>scope : 변수가 선언되는 객체를 지정</li>
	</ul>
	<ul>c:out태그 속성
		<li>value : 페이지에 출력될 값, 변수명(EL표현식으로)</li>
		<li>default : value에 있는 값이 없을 때 출력될 값</li>
		<li>escapeXml : value값에 html코드가 있을 때 해석할지 여부</li>
	</ul>
	
	<h3>변수선언하기</h3>
	<c:set var="comment" value="우리강의장도 이제 따뜻해진다!"/>
	<p>c:set태그로 저장된 내용은 EL로 출력할 수 있다.</p>
	<p>${comment }</p>
	<c:set var="path" value="${pageContext.request.contextPath }"/>
	<p>경로 ${path }</p>
	
	<h3>저장할 때 영역설정하기</h3>
	<c:set var="test" value="requestData" scope="request"/>
	<c:set var="test" value="sessionData" scope="session"/>
	<c:set var="test" value="applicationData" scope="application"/>
	<p>${test } ${sessionScope.test } ${applicationScope.test }</p>
	
	
	<h3>c:out 출력하기</h3>
	<p><c:out value="${comment }"/></p>
	<c:set var="testData" value="<script>location.href='http://www.naver.com'</script>"/>
<%-- 	<div>${testData }</div> --%>
	<!-- escapeXml="false" 면 태그로 읽고 true면 글자대로 출력함  -->
	<div>
		<c:out value="${testData }" escapeXml="true"/>
	</div>
	<c:out value="<h3>안녕하세요</h3>" escapeXml="false"/>
	
	<c:set var="noneTest" value="난 있는 값이야"/>
	<h2><c:out value="${noneTest }" default="값이 없음"/></h2><!-- 값이 없으면 default값 출력 -->
	
	
</body>
</html>