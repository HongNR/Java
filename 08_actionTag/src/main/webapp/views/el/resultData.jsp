<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿에서 보낸 데이터 받기</title>
</head>
<body>
	<h3>이메일 : ${email }</h3>
	<h3>등록된 이름 : ${names }</h3>
	<ul>
		<li>${names.get(0) }</li>
		<li>${names.get(1) }</li>
		<li>${names.get(2) }</li>
		<li>${names.get(3) }</li>
		
	</ul>
	<h3>userId : ${userId }</h3>
	<h3>전달된 vo 객체 데이터 출력하기</h3>
	<p>${s }</p><!-- toString 으로 출력됨 -->
	<p>이름 : ${s.name }</p>
	<p>나이 : ${s.age }</p>
	<p>키 : ${s.height }</p>
	<p>성별 : ${s.gender }</p>
	<!-- 값 한개씩 가져옴 -->
	
	<h3>리스트로 전달된 vo객체 출력</h3>
	<p>${students }</p><!-- toString 으로 List 출력됨 -->
	<p>${students.get(0) }</p><!-- 유병승의 데이터가 옴 -->
	<p>${students.get(0).name }</p><!-- 이름만 넘어옴 -->
	<p>${students.get(0).age }</p>
	<p>${students.get(0).height }</p>
	<p>${students.get(0).gender }</p>
	
	<h3>키가 중복되는 것은??</h3><!-- session에도, request에도 userId가 저장되어있음  -->
	<p>userId : ${userId }</p><!-- request 값이 출력 -->
	<h4>중복값이 있을 때는 접근할 객체를 지정해서 호출한다.</h4>
	<p>
		ruquestScope, sessionScope, applicationScope
	</p>
	<p>request : ${requestScope.userId }</p>
	<p>session : ${sessionScope.userId }</p>
	<p>application : ${applicationScope.userId }</p><!-- 값이 없으면 공란을 출력함 -->
	
	<h3>파라미터로 보낸 데이터 가져오기</h3>
	<p>
		단일값 : param.name이름 으로 접근해서 가져온다. <br>
		다수값 : paramValues.name이름 으로 접근해서 가져온다.
	</p>
	<p>단일값 : ${param.str } / ${str }</p><!-- / 파라미터에 저장해놔서 오른쪽 값은 못 찾음 -->
	<p>다수값 : ${paramValues.hobby[0] }</p>
	<p>다수값 : ${paramValues.hobby[1] }</p>
	<p>다수값 : ${paramValues.hobby[2] }</p>
	<p>다수값 : ${paramValues.hobby[3] }</p><!-- 공란출력 -->
	

</body>
</html>