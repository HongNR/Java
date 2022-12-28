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
	
	public List<Member> searchMemberAll(Connection conn,int cPage, int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList();
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("searchMemberAll"));
			//시작값
			pstmt.setInt(1, (cPage-1)*numPerpage+1);
			//끝값
			pstmt.setInt(2, cPage*numPerpage);
			
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
	//페이징 처리를 위한 데이터의 개수 찾는 로직
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=0;
		
		try {
			pstmt=conn.prepareStatement(sql.getProperty("selectMemberCount"));
			rs=pstmt.executeQuery();
			if(rs.next()) count=rs.getInt(1); //count=rs.getInt("cnt");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return count;
	}
	
	public List<Member> searchMemberList(Connection conn,String type,String keyword
										,int cPage,int numPerpage){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Member> result=new ArrayList();
		//문자열을 조작해서 다른 글자로 대체해줌
		String query=sql.getProperty("searchMemberListKeyword");
		query=query.replace("$COL", type);
		
//		System.out.println(query);
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, type.equals("gender")?keyword:"%"+keyword+"%");
			pstmt.setInt(2, (cPage-1)*numPerpage+1);
			pstmt.setInt(3, cPage*numPerpage);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				result.add(MemberDao.getMember(rs));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	//페이징 처리를 위해 아이디,이름,성별 검색에 따라 나온 데이터 개수 카운트하는 로직
	public int selectMemberCount(Connection conn, String type, String keyword) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int result=0;
		String query=sql.getProperty("selectMemberCountKeyword");
		query=query.replace("$COL", type);
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,"%"+keyword+"%");
			rs=pstmt.executeQuery();

			if(rs.next()) result=rs.getInt(1);
			
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
