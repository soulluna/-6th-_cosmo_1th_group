package Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	Connection con; // DB와 연결하기 위한 객체
	PreparedStatement pstmt; // //쿼리를 담을 객체
	ResultSet rs; // 쿼리를 반환하는 객체
	
	//생성자에서 DB 연결
	public MemberDAO() {
		super();
		try {
			//오라클 DB와 연결
			Context init = new InitialContext();
				//Datasource 객체로 커넥션 풀을 사용할 수 있음
				DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/OracleDB");
				con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	//회원 인증(id와 pw확인)
	public int isMember(MemberVO member) {
		String sql = "select pwd from employee where eno=?";
		int result=-1;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getEno());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd").equals(member.getPwd())) {
					result=1; //일치
				}else {
					result=0; //불일치
				}
				}else {
					result=-1; //아이디 존재하지 않음.
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		}
		return result;
	}
	
	//회원가입
	
	public boolean joinMember(MemberVO member) {
		String sql = "insert into employee values(?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		try {
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, member.getEno());
		pstmt.setString(2, member.getPwd());
		pstmt.setString(3, member.getEname());
		pstmt.setString(4, member.getEng_name());
		pstmt.setString(5, member.getEmail());
		pstmt.setString(6, member.getTel());
		pstmt.setString(7, member.getDname());
		pstmt.setString(8, member.getDname_two());
		pstmt.setDate(9, member.getHireDate());
		pstmt.setString(10, member.getRank());
		pstmt.setString(11, member.getIsadmin());
		result=pstmt.executeUpdate();
		
		if(result!=0) {
			return true;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		}
		return false;
	}
	
	
		
	
	

}
