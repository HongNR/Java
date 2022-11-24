package com.servletdata.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class testPersonServlet
 */
@WebServlet("/testperson.do")
public class testPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public testPersonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전송된 데이터 인코딩처리하기
		request.setCharacterEncoding("utf-8");
		
		//1. 단일데이터 값 받아오기
		//request.getParameter() -> 기본적인 데이터를 받아올 때 사용
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		double height=Double.parseDouble(request.getParameter("height"));
		String color=request.getParameter("color");
		String lunchMenu=request.getParameter("lunch");
		
		//2. 하나의 이름으로 여러값이 전송될 때 값 받아오기
		//request.getParameterValues() -> 다수의 값을 받을 때 사용
		//다수의 값이기 때문에 반환형 : String[]
		String[] animals=request.getParameterValues("animal");
		
		System.out.println("이름 : "+name);
		System.out.println("나이 : "+age);
		System.out.println("키 : "+height);
		System.out.println("색상 : "+color);
		System.out.println("점심메뉴 : "+lunchMenu);
		System.out.println("좋아하는 동물 : "+Arrays.toString(animals));
		
		for(String a : animals) {
			System.out.println(a);
		}
		
		//단일값도 getParameterValues()메소드를 이용해서 가져올 수 있다.
		String[] names=request.getParameterValues("name");
		//단일값을 배열의 0번인덱스에 값을 대입 후 반환
		System.out.println("values로 가져온 name : "+Arrays.toString(names));
		
		//클라이언트에서 전송된 name값을 모를 때 name값만 가져오기(key값들)
		//getParameterNames()메소드 이용 -> Enumeration클래스로 반환
		Enumeration<String> paramName=request.getParameterNames();
		while(paramName.hasMoreElements()){
			String key=paramName.nextElement();
			String[] values=request.getParameterValues(key);
			System.out.println(Arrays.toString(values));
		}
		
		//전송된 데이터를 key:value형식으로 반환 받기 -> Map
		//request.getParameterMap();
		Map<String,String[]> param=request.getParameterMap();
		System.out.println("===== map으로 데이터 받기 =====");
		
		Set<String> keys=param.keySet();
		Iterator<String> it=keys.iterator();
		while(it.hasNext()) {
			String key=it.next();
			System.out.println(key);
			System.out.println(Arrays.toString(param.get(key)));
		}
		
		//클라이언트의 요청에 대한 응답페이지를 작성
		//HttpServletResponse클래스를 이용한다.
		//1. 응답데이터에 대한 타입을 설정 -> MIME타입
		response.setContentType("text/html;charset=utf-8");
		
		//2. 클라이언트의 Stream을 가져온다.
		// 1) getWriter() : html코드전송할 때, 일반 문자열데이터 보낼 때
		// 2) getOutputStream() : 파일전송할 때
		PrintWriter out=response.getWriter();
		//3. 연결된 스트림으로 원하는 데이터를 전송
		//write(), append() 메소드 이용
		String html="<html>";
		html+="<head>";
		html+="<title>당신의 취향 분석</title>";
		html+="</head>";
		html+="<body>";
		html+="<h1>"+name+"님의 취향은</h1>";
		html+="<h2>당신의 이름은 "+name+"이고 나이는 "+age+"살입니다.</h2>";
		html+="<h3>키는 "+height+"cm이고 좋아하는 색은 <span style='color:"+color+";'>"+color+"</span>입니다.</h3>";
		html+="<ul>좋아하는 동물은 ";
		for(String a : animals) {
			html+="<li>";
			String src="";
				switch(a) {
					case "강아지" :
						src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Golde33443.jpg/280px-Golde33443.jpg";break;
					case "고양이" : 
						src="https://www.sisain.co.kr/news/photo/202110/45791_82634_4851.jpg"; break;
					case "코끼리" : 
						src="https://dimg.donga.com/wps/NEWS/IMAGE/2022/09/01/115259050.2.jpg"; break;
					case "하마" : 
						src="https://w.namu.la/s/2e0d9c39288218a2f6e0384a59faf2207968b042f05911aebda2f769a240c7bc315ae11ebdf7786caf249cc8244623cc66100a1a5df82b09b5a4abe1f024ea2aade45deaac95de2c24b3dd8d3a0668f680afab45673909f4320e36b50caeadf8"; break;
					case "표범" : 
						src="https://w.namu.la/s/30e034fd3019d0fa3b34697fdfd8f4aa595905d1bbc08b1e08578690031b5fe2e8c11624ef22304dd17fbd7b1fde0f2b35bac9e48809362e34c72558f82cd2108c0affd375e88aa7a2527b1b9d0846195e936551db1152f6620d96a8829ab857c1f74c1e2fd389ee5fc37218be9e5610"; break;
					case "다람쥐" : 
						src="https://www.alpacaworld.co.kr/uploadimages/joyapc2021_33.jpg"; break;
				}
			html+="<img src='"+src+"' width='100' height='100'>";
			html+="</li>";
		}
		html+="</ul>";
		html+="<h3>오늘의 점심은 "+lunchMenu+"였습니다.</h3>";
		html+="</body>";
		html+="</html>";
		
		out.write(html);
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
