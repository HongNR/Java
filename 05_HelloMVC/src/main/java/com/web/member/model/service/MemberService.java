package com.web.member.model.service;

import static com.web.common.JDBCTemplate.*;
import static com.web.common.JDBCTemplate.getConnection;

import java.sql.Connection;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.vo.Member;

public class MemberService {
	
	private MemberDao dao=new MemberDao();
	
	public Member searchMember(String userId, String password) {
		Connection conn=getConnection();
		Member m=dao.searchMember(conn,userId,password);
		close(conn);
		return m;
	}
	
	public int insertMember(Member m) {
		Connection conn=getConnection();
		int result=dao.insertMember(conn,m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	public Member searchMemberId(String userId) {
		Connection conn=getConnection();
		Member m=dao.searchMemberId(conn,userId);
		close(conn);
		return m;
	}
}
