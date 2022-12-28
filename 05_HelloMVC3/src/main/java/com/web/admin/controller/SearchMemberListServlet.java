package com.web.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.admin.model.service.AdminService;
import com.web.member.model.vo.Member;

/**
 * Servlet implementation class SearchMemberListServlet
 */
@WebServlet("/admin/searchMember")
public class SearchMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("searchType");
		//input태그의 name값을 가져오면 input에 적은 내용을 가져옴
		String keyword=request.getParameter("searchKeyword");
		
		int cPage;
		int numPerpage=5;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		
		List<Member> list
			=new AdminService().searchMemberList(type,keyword,cPage,numPerpage);
		request.setAttribute("members", list);
		
		String pageBar="";
		//전체데이터 가져오기
		//조건이 적용된 전체데이터 가져오기!
		int totalData=new AdminService().selectMemberCount(type,keyword);
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);//전체페이지 수
		int pageBarSize=5;	//페이지바의 개수
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		if(pageNo!=1) {
			pageBar+="<a href='"+request.getRequestURI()+//request.getRequestURI()=/admin/searchMember까지 나옴
					"?searchType="+type
					+"&searchKeyword="+keyword
					+"&cPage="+(pageNo-1)+"'>[이전]</a>";
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(pageNo==cPage) {
				pageBar+="<span>"+pageNo+"</span>";
			}else {
				pageBar+="<a href='"+request.getRequestURI()+//request.getRequestURI()=/admin/searchMember까지 나옴
						"?searchType="+type
						+"&searchKeyword="+keyword
						+"&cPage="+pageNo+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		
		if(pageNo<totalPage) {
			pageBar+="<a href='"+request.getRequestURI()+//request.getRequestURI()=/admin/searchMember까지 나옴
					"?searchType="+type
					+"&searchKeyword="+keyword
					+"&cPage="+(pageNo)+"'>[다음]</a>";
		}
		
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/views/member/memberList.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
