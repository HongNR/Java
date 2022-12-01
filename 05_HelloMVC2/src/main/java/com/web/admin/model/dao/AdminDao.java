package com.web.admin.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.member.model.vo.Member;

public class AdminDao {
	private Properties sql=new Properties();
	
	public AdminDao() {
		String path=AdminDao.class.
				getResource("/sql//admin/admin_sql.properties").
				getPath();
		try {
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Member> searchMemberAll(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList();
		
		
		
		return result;
	}
}
