<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List,com.web.notice.model.vo.Notice" %>
<%
	List<Notice> list=(List<Notice>)request.getAttribute("notices");
%>
<!-- 헤더 -->    
<%-- <%@ include file="/views/common/header.jsp" %> --%>
<jsp:include page="/views/common/header.jsp"/>
 <style>
    section#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse;}
    table#tbl-notice th, table#tbl-notice td {border:1px solid; padding: 5px 0; text-align:center;} 
</style>

<section id="notice-container">
        <h2>공지사항</h2>
        <button onclick="location.assign('${pageContext.request.contextPath}/notice/write.do')">글쓰기</button>
        <table id="tbl-notice">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>첨부파일</th>
                <th>작성일</th>
            </tr>
				<!-- 내용출력할것
				첨부파일 있으면 이미지, 없으면 공란으로 표시
				이미지파일은 web/images/file.png에 저장 -->
			<c:if test="${empty notices }">
<%-- 			<%if(list.isEmpty()) {%> --%>
				<tr>
					<td colspan="5">조회된 공지사항이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty notices }">
<%-- 			<%}else { --%>
				<c:forEach var="n"	items="${notices }">
<!-- 				for(Notice n : list){%> -->
				
				<tr>
					<td><c:out value="${n.noticeNo}"/></td>
					<td>
						<a href="${pageContext.request.contextPath }/notice/noticeView.do?noticeNo=${n.noticeNo}">${n.noticeTitle }</a>
					</td>
					<td><c:out value="${n.noticeWriter}"/></td>
					<td>
						<c:if test="${n.filePath!=null }">
<%-- 						<%if(n.getFilePath()!=null) {%> --%>
							<img src="${pageContext.request.contextPath }/images/file.png"
							 width="20" height="20">
						</c:if>
						<c:if test="${n.filePath==null }">
						<%-- <%} else{ %> --%>
							첨부파일 없음
						<%-- <%} %> --%>
						</c:if>
					</td>
					<td><c:out value="${n.noticeDate}"/></td>
				</tr>
				<%-- <%}
				}%> --%>
					</c:forEach>
				</c:if>
        </table>
        <div id="pageBar">
        	${pageBar }
        	<%-- <%=request.getAttribute("pageBar") %> --%>
        </div>
    </section>

<!-- 푸터 -->
<jsp:include page="/views/common/footer.jsp"/>
<%-- <%@ include file="/views/common/footer.jsp" %> --%>