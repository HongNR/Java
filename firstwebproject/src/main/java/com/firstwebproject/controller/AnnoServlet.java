package com.firstwebproject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어노테이션으로 서블릿 매핑시키기
//클래스 선언부에 @WebServlet("매핑주소")
@WebServlet("/anno.do")

public class AnnoServlet extends HttpServlet{

	private static final long serialVersionUID = -7877015203361932279L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException,IOException{
		System.out.println("어노테이션방식으로 연결된 서블릿 get메소드");
		
		//서버에서 응답하기 -> 페이지(html)
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		out.write("<html>");
		out.write("<head>");
		out.write("<title>내가 만든 첫 응답페이지</title>");
		out.write("</head>");
		out.write("<body>");
		out.write("<h2>내가 만든 첫 응답페이지</h2>");
		out.write("</body>");
		out.write("</html>");
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		System.out.println("어노테이션방식으로 연결된 서블릿 post메소드");
	}
	
}
