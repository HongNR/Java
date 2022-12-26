<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- <%@ include file="header.jsp" %> --%>
<!-- 
	헤더페이지 호출시 원하는 데이터를 보낼 수 있음
	jsp:include태그의 자식태그로 jsp:param태그를 이용하면 됨
  -->
  
<!-- 반드시 /로 닫기태그 해줘야함 -->
<%request.setCharacterEncoding("utf-8"); %>
<jsp:include page="header.jsp">
	<jsp:param name="title" value="메인기본화면"/>
	<jsp:param name="content" value="이거슨 내용"/>
</jsp:include>	<!-- 반드시 /로 닫기태그 해줘야함  -->

<section>
	<h2>여기는 본문입니다.</h2>
	<h3>데이터를 출력하자.....</h3><%-- <%=name %>로그인함 --%>
</section>
</body>
</html>