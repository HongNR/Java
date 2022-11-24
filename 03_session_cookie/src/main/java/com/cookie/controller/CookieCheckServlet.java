package com.cookie.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieCheckServlet
 */
@WebServlet("/cookiecheck.do")
public class CookieCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 가져오는 cookie값 확인하기
		Cookie[] cookies=request.getCookies();
		String readMovie="";
		if(cookies!=null) {
			for(Cookie c : cookies) {
				//값은 key, value 따로 가져옴
				System.out.println(c.getName());//key
				System.out.println(c.getValue());//value
				if(c.getName().equals("readMovie")) {
					readMovie=c.getValue();
				}
			}
		}
		String[] movies=readMovie.split("|");
		System.out.println(Arrays.toString(movies));
		
		response.sendRedirect("createcookie.do");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
