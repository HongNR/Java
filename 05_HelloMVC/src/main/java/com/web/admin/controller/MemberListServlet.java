package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList.do")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이징처리하기
		//클라이언트로부터 2개의 값을 받아옴
		//현재페이지, 페이지당 출력개수
		int cPage;
		try{
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		//페이지당 데이터 출력개수
		int numPerpage=5;
		
		//DB에서 member테이블에 있는 전체 데이터를 가져와
		List<Member> list=new AdminService().searchMemberAll(cPage,numPerpage);
		
		//pageBar 만들어서 반환하기
		//1. totalData 전체페이지의 수를 알기위해
		int totalData=new AdminService().selectMemberCount();
		
		//pageBar html코드를 저장할 수 있는 변수 선언
		String pageBar="";
		// 1) pageBar의 번호개수
		int pageBarSize=5;
		// 2) 총 페이지수
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);//ceil은 무조건 올림
		// 3) 출력할 번호세팅: 페이지바사이즈의 첫번째숫자 마지막숫자
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//html코드 생성하기
		// 이전에 대한 값 작성
		if(pageNo==1) {
//			pageBar+="<span>[이전]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+
					"/admin/memberList.do?cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		//이전과 다음 사이의 숫자들
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				//보고있는 페이지
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getContextPath()+
						"/admin/memberList.do?cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//다음에 대한 값
		if(pageNo>totalPage) {
//			pageBar+="<span>[다음]</span>";
		}else {
			pageBar+="<a href='"+request.getContextPath()+
					"/admin/memberList.do?cPage="+pageNo+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		
		request.setAttribute("members", list);
		
		//화면에 전송
		RequestDispatcher rd=request.getRequestDispatcher("/views/member/memberList.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
