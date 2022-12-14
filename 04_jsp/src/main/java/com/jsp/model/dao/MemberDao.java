package com.jsp.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.model.vo.Member;
import static com.jsp.common.JDBCTemplate.close;

public class MemberDao {
	
	public List<Member> searchMemberAll(Connection conn){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM MEMBER";
		List<Member> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				list.add(Member.builder()
						.memberId(rs.getString("member_id"))
						.memberName(rs.getString("member_name"))
						.gender(rs.getString("gender").charAt(0))
						.age(rs.getInt("age"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.hobby(rs.getString("hobby").split(","))
						.enrollDate(rs.getDate("enroll_date"))
						.build()
						);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return list;
	}
	
	public List<Member> searchMemberName(Connection conn,String name){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?";
		List<Member> list=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,"%"+ name +"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(Member.builder()
						.memberId(rs.getString("member_id"))
						.memberName(rs.getString("member_name"))
						.gender(rs.getString("gender").charAt(0))
						.age(rs.getInt("age"))
						.email(rs.getString("email"))
						.phone(rs.getString("phone"))
						.address(rs.getString("address"))
						.hobby(rs.getString("hobby").split(","))
						.enrollDate(rs.getDate("enroll_date"))
						.build()
						);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstmt);
		}return list;
		
		
	}
}
