<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!-- 응답할 html코드만 작성하는 것 -->
<% List<String> names=(List<String>)request.getAttribute("data"); %>
<ul>
	<%for(String n : names){ %>
		<li><%=n %></li>
	<%} %>
</ul>