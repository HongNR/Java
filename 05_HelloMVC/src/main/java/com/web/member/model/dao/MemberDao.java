package com.web.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;
import static com.web.common.JDBCTemplate.close;
import com.web.member.model.vo.Member;

public class MemberDao {
	private Properties sql=new Properties();
	
	public MemberDao() {
		try {
			String path=MemberDao.class
					.getResource("/sql/member/member_sql.properties")
					.getPath();
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member searchMember(Connection conn, String userId,String password) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Member m=null;
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchIdPassword"));
			pstmt.setString(1, userId);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				m=getMember(rs);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	
	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt=null;
		int result=0;
		
		try {		
				pstmt=conn.prepareStatement(sql.getProperty("insertMember"));
				pstmt.setString(1, m.getUserId());
				pstmt.setString(2, m.getPassword());
				pstmt.setString(3, m.getUserName());
				pstmt.setString(4, String.valueOf(m.getGender()));
				pstmt.setInt(5, m.getAge());
				pstmt.setString(6, m.getEmail());
				pstmt.setString(7, m.getPhone());
				pstmt.setString(8, m.getAddress());
//				pstmt.setString(9, Arrays.toString(m.getHobby()));
				pstmt.setString(9, String.join(",", m.getHobby()));
				
				result=pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	private Member getMember(ResultSet rs) throws SQLException{
		return Member.builder()
				.userId(rs.getString("userid"))
				.password(rs.getString("password"))
				.userName(rs.getString("username"))
				.gender(rs.getString("gender").charAt(0))
				.age(rs.getInt("age"))
				.email(rs.getString("email"))
				.phone(rs.getString("phone"))
				.address(rs.getString("address"))
				.hobby(rs.getString("hobby").split(","))
				.enrollDate(rs.getDate("enrolldate"))
				.build();
	}
	
	
}
