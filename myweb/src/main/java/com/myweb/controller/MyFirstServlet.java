package com.myweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4803578306694922582L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException,IOException{
			System.out.println("우와 내가 만든게 실행되다니~~");
		}
	
	
}
