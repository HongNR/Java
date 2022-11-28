package com.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.member.model.service.MemberService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/login.do")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인처리 기능하는 서블릿
		// 클라이언트가 보낸 id,password를 가지고 DB에 일치하는 데이터가 있는지 확인하고
		// 있으면 데이터를 가져오고 없으면 null값을 가져오는 기능
		request.setCharacterEncoding("utf-8");
		
		String userId=request.getParameter("userId");
		String password=request.getParameter("password");
		
		Member m=new MemberService().searchMember(userId,password);
//		System.out.println(m);
		
		//아이디저장 로직 구현하기
		//클라이언트가 아이디 저장 checkbox를 체크했으면 저장 : 체크되어있으면 on
		//checkbox를 체크하지 않았으면 저장 안함	: 체크 안되어있으면 null
		String saveId=request.getParameter("saveId");
		System.out.println(saveId);
		
		if(saveId!=null) {
			//아이디저장 체크했을 때
			Cookie c=new Cookie("saveId",userId);
			c.setMaxAge(60*60*24*7);//일주일
			response.addCookie(c);
		}else {
			Cookie c=new Cookie("saveId","");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
//		if(m!=null&&saveId.equals("on")) {
//			//아이디저장 체크하고 로그인 성공했을 때
//			Cookie c=new Cookie("saveId",m.getUserId());
//			response.addCookie(c);
//		}
		
		//웹애플리케이션에서 로그인처리하기
		if(m!=null) {
			//로그인 성공
			HttpSession session=request.getSession();
			session.setAttribute("loginMember", m);
			
			//응답할 페이지를 구성 -> jsp
			response.sendRedirect(request.getContextPath());
		}else {
			//로그인 실패
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/");//		/저장해놓기
			request.getRequestDispatcher("/views/common/msg.jsp")
			.forward(request, response);
			//request에 저장했기때문에 getRequestDispatcher로 넘겨야 함
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
