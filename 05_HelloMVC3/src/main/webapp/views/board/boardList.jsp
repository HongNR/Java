<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,com.web.board.model.vo.Board" %>
<%-- <%
	List<Board> boards=(List<Board>)request.getAttribute("boards");
%> --%>
<%-- <%@ include file="/views/common/header.jsp"%> --%>
<jsp:include page="/views/common/header.jsp"/>
<style>
	section#board-container{width:600px; margin:0 auto; text-align:center;}
	section#board-container h2{margin:10px 0;}
	table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
	table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
	/*글쓰기버튼*/
	input#btn-add{float:right; margin: 0 0 15px;}
	/*페이지바*/
	div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
	div#pageBar span.cPage{color: #0066ff;}
</style>

	<section id="board-container">
		<h2>게시판 </h2>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>조회수</th>
			</tr>
			<c:if test="${empty boards }">
<%-- 			<%if(boards.isEmpty()){ %> --%>
			</c:if>
			<c:if test="${not empty boards }">
			<%-- <%}else %> --%>
			<c:forEach var="b" items="${boards }">
			<%-- <%{for(Board b: boards){%> --%>
				<tr>
				<td><c:out value="${b.boardNo }"/></td>
				<td>
					<!-- 타이틀을 누르면 상세화면으로 이동 -->
					<a href="${pageContext.request.contextPath }/board/boardView.do?boardNo=<c:out value="${b.boardNo }"/>"><c:out value="${b.boardTitle }"/></a>
				</td>
				<td><c:out value="${b.boardWriter }"/></td>
				<td><c:out value="${b.boardDate }"/></td>
				<td>
					<c:if test="${b.boardOriginalFilename!=null }">
					<%-- <%if(b.getBoardOriginalFilename()!=null) {%> --%>
						<img src="${pageContext.request.contextPath }/images/file.png" width="20">
					</c:if>
					<c:if test="${b.boardOriginalFilename==null }">
<%-- 					<%}else {%>	 --%>
					첨부파일 없음
					</c:if>
<%-- 					<%}%> --%>
				</td>
				<td><c:out value="${b.boardReadcount }"/></td>
			</tr>
			<%-- <% }%> --%>
			</c:forEach>
				<!-- }%> --> 
			</c:if>
		</table>
		<div id="pageBar">
			${pageBar }
			<%-- <%=request.getAttribute("pageBar") %> --%>
		</div>
	</section>
<jsp:include page="/views/common/footer.jsp"/>
<%-- <%@ include file="/views/common/footer.jsp"%> --%>