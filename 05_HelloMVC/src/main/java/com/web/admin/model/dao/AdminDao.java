package com.web.admin.model.dao;

import static com.web.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.web.member.model.dao.MemberDao;
import com.web.member.model.vo.Member;

public class AdminDao {
	private Properties sql=new Properties();
	
	public AdminDao() {
			String path=AdminDao.class
					.getResource("/sql/admin/admin_sql.properties")
					.getPath();
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
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchMemberAll"));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				//MemberDao에 builder가 있어서 가져와서 씀
				result.add(MemberDao.getMember(rs));
//				result.add(getMember(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return result;
	}
	
//	private Member getMember(ResultSet rs) throws SQLException{
//		return Member.builder()
//				.userId(rs.getString("userid"))
//				.password(rs.getString("password"))
//				.userName(rs.getString("username"))
//				.gender(rs.getString("gender").charAt(0))
//				.age(rs.getInt("age"))
//				.email(rs.getString("email"))
//				.phone(rs.getString("phone"))
//				.address(rs.getString("address"))
//				.hobby(rs.getString("hobby").split(","))
//				.enrollDate(rs.getDate("enrolldate"))
//				.build();
//	}
}
