package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.member.model.vo.Member;
import static com.web.common.JDBCTemplate.*;

public class AdminService {
	private AdminDao dao=new AdminDao();
	
	public List<Member> searchMemberAll(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberAll(conn,cPage,numPerpage);
		
		close(conn);
		return result;
	}
	//페이징 처리를 위해 전체 데이터 개수 카운트하는 로직
	public int selectMemberCount() {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn);
		close(conn);
		return result;
	}
	
	public List<Member> searchMemberList(String type, String keyword,int cPage,int numPerpage){
		Connection conn=getConnection();
		List<Member> list=dao.searchMemberList(conn,type,keyword,cPage,numPerpage);
		
		close(conn);
		return list;
	}
	//페이징 처리를 위해 아이디,이름,성별 검색에 따라 나온 데이터 개수 카운트하는 로직
	public int selectMemberCount(String type, String keyword) {
		Connection conn=getConnection();
		int result=dao.selectMemberCount(conn,type,keyword);
		close(conn);
		return result;
	}
	
}
