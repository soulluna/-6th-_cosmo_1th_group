package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	public MemberDAO() {
	}

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception{ //DB연결
		Connection conn = null;
		Context ctx = new InitialContext();
		Context envContext = (Context)ctx.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
		conn = ds.getConnection();
		return conn;
	}

	public int insertMember(MemberVO member) { //회원가입
		int result=-1; //기본값
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into employee(eno, ename, pwd, dname, dname_two,rank)values(?,?,?,?,?,?)";
		

		try {
			String dname=member.getDname();
			String dname_two=member.getDname_two();
			String rank = member.getRank();
			//숫자로 들어오는 옵션 값에 맞추어서 값 튜닝
			switch(dname) {
			case "1":
				dname="영업부";
			case "2":
				dname="인사부";
			case "3":
				dname="기술지원팀";
			}
			switch(dname_two) {
			case "4":
				dname_two="1팀";
			case "5":
				dname_two="2팀";
			case "6":
				dname_two="3팀";	
			}
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getEno());
			pstmt.setString(2, member.getEname());
			pstmt.setString(3, member.getPwd());
			pstmt.setString(4, dname);
			pstmt.setString(5, dname_two);
			pstmt.setString(6, rank);

			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return result;
	}

	public int ConfirmID(String eno, String pwd) { //로그인시 인증
		int result = -1;
		String sql = "select eno, pwd from employee where eno=? pwd=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {//ID가 존재한다면
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd))
					result=1;
			}else {
				result=-1;
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return result;
	}


	public int checkUser(String eno) { //회원가입시 ID중복검사
		int result = -1;
		String sql = "select eno from employee where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {//ID가 존재한다면
				result=1;
			}else {
				result=-1;
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return result;
	}

	public MemberVO getMember(String eno) {
		MemberVO member = null;
		String sql = "select * from employee where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();

			if(rs.next()) {//ID가 존재한다면
				member = new MemberVO();
				member.setEno(rs.getString("eno")); //rs.getString은 문자열을 뽑아낸다.
				member.setEname(rs.getString("ename"));
				member.setPwd(rs.getString("pwd"));
				member.setDname(rs.getString("dname"));
				member.setDname_two(rs.getString("dname_two"));
				member.setRank(rs.getString("rank"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 

		}
		return member;	
	}
}


