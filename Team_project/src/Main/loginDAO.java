package Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class loginDAO {
	private Connection con; //DB연결 객체
	private PreparedStatement pstmt; //쿼리전송객체
	private ResultSet rs; //쿼리 결과값 받는 객체
	private DataSource dataFactory;

	
	//커넥션풀
	public loginDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
			
//			String dbId = "admin";
//			String dbPwd = "admin";
//			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
//
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection(dbId, dbPwd, dburl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List listMembers() {
		
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select * from employee";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void addMember(regVO regVO) {
		try {
			con = dataFactory.getConnection();
			String eno = regVO.getEno();
			String pwd = regVO.getPwd();
			String ename = regVO.getEname();
			String eng_name = regVO.getEng_name();
			String email = regVO.getEmail();
			String tel = regVO.getTel();
			String dname = regVO.getDname();
			String dname_two = regVO.getDname_two();
			Date hireDate = regVO.getHireDate();
			String rank = regVO.getRank();
			String isadmin = regVO.getIsadmin();
			
			
			String query = "insert into employee";
			query += "(eno,userpw,ename,eng_name,email,tel,dname,dname_two,hireDate,rank,isadmin)";
			query += "values(?,?,?,?,?)";
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eno);
			pstmt.setString(2, pwd);
			pstmt.setString(3, ename);
			pstmt.setString(4, eng_name);
			pstmt.setString(5, email);
			pstmt.setString(6, tel);
			pstmt.setString(7, dname);
			pstmt.setString(8, dname_two);
			pstmt.setDate(9, hireDate);
			pstmt.setString(10, rank);
			pstmt.setString(10, isadmin);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public boolean login(String eno, String pwd) {
		String query = "select userpw from employee where eno = ?";
		//?값은 eno
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			rs.next();
			String confirmPw=rs.getString(1);
			if(pwd==confirmPw) {
				return true;
			}
			else {
				return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	

	public boolean isExist(regVO regVO) {
		boolean result = false;
		String eno = regVO.getEno();
		String pwd = regVO.getPwd();
		try {
			con = dataFactory.getConnection();
			String query = "select decode(count(*),1,'true','false')as result from employee where eno=? and pwd=?";
			System.out.println(query);
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, eno);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("reuslt"));
			System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
