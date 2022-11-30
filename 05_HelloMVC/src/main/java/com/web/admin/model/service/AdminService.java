package com.web.admin.model.service;

import java.sql.Connection;
import java.util.List;

import com.web.admin.model.dao.AdminDao;
import com.web.member.model.vo.Member;
import static com.web.common.JDBCTemplate.*;

public class AdminService {
	private AdminDao dao=new AdminDao();
	
	public List<Member> searchMemberAll(){
		Connection conn=getConnection();
		List<Member> result=dao.searchMemberAll(conn);
		
		close(conn);
		return result;
	}
}
