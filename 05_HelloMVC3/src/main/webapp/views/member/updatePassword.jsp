<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@page import="com.web.member.model.vo.Member" %>
<%
	out.print(request.getParameter("userId"));
	out.print(((Member)session.getAttribute("loginMember").getUserId());
%> --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="updatePassword-container">
		<form name="updatePwdFrm" action="${pageContext.request.contextPath }/member/updatePasswordEnd" method="post" 
		onsubmit="return passwordCheck();">
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="password" id="password" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="password_new" id="password_new" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="password_chk" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="변경" />&nbsp;
						<input type="button" value="닫기" />						
					</td>
				</tr>
			</table>
			<!-- userId값도 form안에서 같이 넘어가야하기 때문에 아이디값도 같이 전송해주는 역할 -->
			<input type="hidden" name="userId" value="<c:out value="${userId }"/>">
		</form>
	</div>
	<script>
		const passwordCheck=()=>{
			const password=document.querySelector("[name=password_new]").value;
			const passwordck=document.querySelector("#password_chk").value;
			if(password.trim().length<8){
				alert("비밀번호는 8글자 이상 작성해야합니다.");
				return false;
			}
			if(password!=passwordck){
				alert("비밀번호가 일치하지않습니다.");
				return false;
			}
		}
	</script>

    <style>
    div#updatePassword-container{
        background:red;
    }
    div#updatePassword-container table {
        margin:0 auto;
        border-spacing: 20px;
    }
    div#updatePassword-container table tr:last-of-type td {
        text-align:center;
    }
    </style>
    
</body>
</html>