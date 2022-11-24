package com.servletdata.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//post방식으로 알파벳,숫자를 제외한 문자를 받았을 경우
		//데이터에 대한 인코딩처리를 해줘야한다. 안하면 문자가 깨짐
		request.setCharacterEncoding("utf-8");
		
		
		//클라이언트가 보낸 데이터 확인하기
		//HttpServletRequest클래스가 제공하는 메소드를 이용해서
		//데이터를 가져올 수 있음!!
		//getParameter("name속성의 값") ->String으로 value값을 반환해줌
		String userId=request.getParameter("userId");
		System.out.println(userId);
		String password=request.getParameter("password");
		System.out.println(password);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String userId=request.getParameter("userId");
//		String password=request.getParameter("password");
//		System.out.println("post받기 "+userId+" "+password);
		doGet(request,response);
	}

}
