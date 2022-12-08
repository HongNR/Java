package com.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AjaxFileUploadServlet
 */
@WebServlet("/fileUpload")
public class AjaxFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=getServletContext().getRealPath("/upload/");
		
		MultipartRequest mr=new MultipartRequest(request, path,
				1024*1024*10,"UTF-8",new DefaultFileRenamePolicy());
		
		Enumeration e=mr.getFileNames();
		//form.append(key,file); ->key값!!!
		List<String> fileList=new ArrayList();
		while(e.hasMoreElements()) {
			String name=(String)e.nextElement();
			System.out.println("리네임 : "+mr.getFilesystemName(name));
			System.out.println("원본 : "+mr.getOriginalFileName(name));
			fileList.add(mr.getFilesystemName(name));//key값!
			String paramName=mr.getParameter("name");
			System.out.println(paramName);
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
