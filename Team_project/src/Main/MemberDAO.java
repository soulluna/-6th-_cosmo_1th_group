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
	private Connection con;//데이터베이스와의 연결을 위한 커넥션객체
	private PreparedStatement pstmt;//데이터베이스에 쿼리문을 전송해는 전송객체
	private DataSource dataFactory;//data소스를 저장하는 JNDI
	//생성자에서 DB 연결
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//회원 인증(id와 pw확인)
	public boolean isMember(MemberVO member) {
		try {
			con = dataFactory.getConnection();//데이터베이스와 연결
			String eno=member.getEno();
			String pwd=member.getPwd();
			String query = "select pwd from employee where eno=?";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, eno);
			ResultSet rs=pstmt.executeQuery();
			rs.next();
			String checkPwd=rs.getString("pwd");
			System.out.println(checkPwd);
			if(pwd.equals(checkPwd)) {
				rs.close();
				pstmt.close();
				con.close();
				return true;
			}
			else {
				rs.close();
				pstmt.close();
				con.close();
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	public void addMember(MemberVO member) {
		// TODO Auto-generated method stub
		try {
			con = dataFactory.getConnection();//데이터베이스와 연결
			String eno=member.getEno();
			String ename=member.getEname();
			String pwd=member.getPwd();
			String dname=member.getDname();
			String dname_two=member.getDname_two();
			String rank=member.getRank();
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
			String query="insert into employee(eno, ename, pwd, dname, dname_two, rank) values(?, ?, ?, ?, ?, ?)";
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, eno);
			pstmt.setString(2, ename);
			pstmt.setString(3, pwd);
			pstmt.setString(4, dname);
			pstmt.setString(5, dname_two);
			pstmt.setString(6, rank);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean confirmMember(String eno) { //중복확인
		boolean result = false;
		try {
			String query = "select eno from employee where eno=?";
			pstmt = con.prepareStatement(query); 
			pstmt.setString(1, eno);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				result=true;
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
