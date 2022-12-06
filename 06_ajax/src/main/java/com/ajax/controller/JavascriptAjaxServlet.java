package com.ajax.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JavascriptAjaxServlet
 */
@WebServlet("/ajax/jsAjax.do")
public class JavascriptAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavascriptAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Thread t=new Thread(()->{
			try {
				Thread.sleep(2000);				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		});
//		t.run();
		String name=request.getParameter("name");
//		System.out.println("ajax로 실행!");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("<div><h2>"+name+"님이 만든 첫 ajax응답</h2></div>");
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
