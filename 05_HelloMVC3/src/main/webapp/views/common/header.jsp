<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.vo.Member" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Member loginMember=(Member)session.getAttribute("loginMember");
	Cookie[] cookies=request.getCookies();
	String saveId=null;
	if(cookies!=null){
		for(Cookie c : cookies){
			if(c.getName().equals("saveId")){
				saveId=c.getValue();
				break;
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMvc프로젝트</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css"/>
<script src="${pageContext.request.contextPath }/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<h1>HelloMVC</h1>
			<div class="login-container">
			<c:if test="${sessionScope.loginMember==null }">
			
<%-- 			<%if(loginMember==null){ %> --%>
				<form id="loginFrm" action="${pageContext.request.contextPath }/login.do" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="userId"id="userId" 
								placeholder="아이디" value="<%=saveId!=null?saveId:"" %>">
								
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="password" id="password" placeholder="패스워드">
							</td>
							<td>
								<input type="submit" value="로그인">
							</td>
						</tr>
						<tr>
							<td>
								<input type="checkbox" name="saveId" id="saveId"
								<%=saveId!=null?"checked":"" %>>
								<label for="saveId">아이디저장</label>
								<input type="button" value="회원가입"
								onclick="location.assign('${pageContext.request.contextPath }/member/enrollMember.do');">
							</td>
						</tr>
					</table>
				</form>
			</c:if>
			<c:if test="${sessionScope.loginMember!=null }">
<%-- 			<%}else{ %> --%>
				<table id="logged-in">
					<tr>
						<td colspan="2">
							<b>${loginMember.userName }님, 환영쓰</b>
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" value="내 정보보기"
							onclick="location.assign('${pageContext.request.contextPath }/member/memberView.do?id=${loginMember.userId }');">
						</td>
						<td>
							<input type="button" value="로그아웃"
							onclick="location.replace('${pageContext.request.contextPath}/logout.do');">
						</td>
					</tr>
				</table>
<%-- 			<%} %> --%>
			</c:if>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="">Home</a></li>
					<c:if test="${loginMember==null }">
<%-- 					<%if(loginMember==null){ %> --%>
						<li id="notice"><a href="" onclick="myalert();">공지사항</a></li>
					</c:if>
					
<%-- 					<%} else{%> --%>
					<c:if test="${loginMember!=null }">
						<li id="notice"><a href="${pageContext.request.contextPath }/notice/noticeList.do">공지사항</a></li>					
<%-- 					<%} %> --%>
					</c:if>
					<li id="board"><a href="${pageContext.request.contextPath }/board/boardList.do">게시판</a></li>
					<li id="gallary"><a href="">갤러리</a></li>
					<!-- 아이디가 admin이면 회원관리 메뉴 보이기 -->
					<c:if test="${loginMember!=null && loginMember.userId eq 'admin' }">
<%-- 					<%if(loginMember!=null&&loginMember.getUserId().equals("admin")){%>					 --%>
						<li id="memberManage">
							<a href="${pageContext.request.contextPath }/admin/memberList.do">회원관리</a>
						</li>
					</c:if>
<%-- 					<%} %> --%>
				</ul>
			</nav>
		</header>
		<script>
			const myalert=()=>{
				alert("로그인 한 사용자만 접근 가능합니다.");
				return false;
			}
		</script>