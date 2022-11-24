package com.cookie.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cookie.model.vo.Member;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/searchmember.do")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 보낸 데이터 가져오기
		request.setCharacterEncoding("utf-8");
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from member where member_id=? and member_pwd=?";
		Member m=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"student","student");
			conn.setAutoCommit(false);
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=new Member();
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender").charAt(0));
				m.setAge(rs.getInt("age"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setEmail(rs.getString("email"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null && !rs.isClosed()) rs.close();
				if(pstmt!=null && !pstmt.isClosed()) pstmt.close();
				if(conn!=null && !conn.isClosed()) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("searchMember", m);
		
		request.getRequestDispatcher("searchmemberview.do")
			.forward(request, response);
		
//		//데이터를 가져왔으면 응답페이지 작성
//		response.setContentType("text/html;charset=utf-8");
//		
//		String html="<html>";
//		html+="<body>";
//		html+="<h1>아이디 : "+userId+"에 대한 조회결과</h1>";
//		if(m==null) {
//			html+="<h3>조회된 데이터가 없습니다</h3>";
//		}
//		else {
//			html+="<ul>";
//			html+="<li>"+m.getMemberId()+"</li>";
//			html+="<li>"+m.getMemberPwd()+"</li>";
//			html+="<li>"+m.getMemberName()+"</li>";
//			html+="<li>"+m.getGender()+"</li>";
//			html+="<li>"+m.getAge()+"</li>";
//			html+="<li>"+m.getEmail()+"</li>";
//			html+="<li>"+m.getPhone()+"</li>";
//			html+="<li>"+m.getAddress()+"</li>";
//			html+="<li>"+m.getHobby()+"</li>";
//			html+="<li>"+m.getEnrollDate()+"</li>";
//			html+="</ul>";
//		}
//			html+="</body>";
//			html+="</html>";
//		response.getWriter().print(html);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
