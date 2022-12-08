package com.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ajax.model.vo.Actor;
import com.google.gson.Gson;
import com.web.admin.model.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class BasicJsonServlet
 */
@WebServlet("/ajax/basicJson.do")
public class BasicJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicJsonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsonsimple라이브러리를 이용해서 객체 전송하기
		//단일객체를 전송
		//JSONObject객체를 생성해서 응답해줌
		//Map방식으로 필드값을 저장함
		
		//다수객체를 전송
		//JSONArray객체를 생성해서 응답해줌
		//JSONArray는 List방식으로 JSONObject를 다수저장
		Actor a=new Actor("박보검","01012345678","parkBogum.jpg");
		
		JSONObject o=new JSONObject();
		//각필드의 값을 key:value형식으로 저장
		//JSONObject.put()메소드 이용
		o.put("name", a.getName());
		o.put("phone", a.getPhone());
		o.put("profile", a.getProfile());
		o.put("age",new Integer(19));
		o.put("height", new Double(180.5));
		o.put("flag", true);
//		o.put("today", new Date().toString());
		
		//전송할때는 contentType설정, 		
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().print(o);
		
		//다수의 데이터 받아오기
		List<Member> members=new AdminService().searchMemberAll(1, 20);
		JSONArray jarr=new JSONArray();//리스트방식으로 JSONObject를 대입
		for(Member m:members) {
			JSONObject jo=new JSONObject();
			jo.put("userId", m.getUserId());
			jo.put("userName", m.getUserName());
			jo.put("email", m.getEmail());
			jo.put("phone", m.getPhone());
			jo.put("address", m.getAddress());//map방식
			jarr.add(jo);//list방식
		}
		
		//전송할때는 contentType설정, 
		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().print(jarr);
		
		//GSon라이브러리 이용해서 데이터 전송하기
	      Gson g=new Gson();
	      //toJson()메소드이용
	      g.toJson(members,response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
