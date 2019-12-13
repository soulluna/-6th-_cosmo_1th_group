package Main;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	//회원가입 시에 데이터베이스에 해당 정보를 넣어둠
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
				dname="기술개발부";
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
	//데이터베이스에 있는 회원정보와 같은지 확인
	public int ConfirmID(String eno, String pwd) { //로그인시 인증
		int result = -1;
		String sql = "select eno, pwd from employee where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {//ID가 존재한다면 비밀번호 체크
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd))
					result=1;
			}else {
				result=-1;
			}
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
		System.out.println("결과 : "+result);
		return result;
	}
	//사원번호 중복검사
	public int checkUser(String eno) { //회원가입시 ID중복검사
		int result = -1;//result=-1 : 값이 존재하지 않음(사용가능함) / result=1 : 값이 존재함(사용 불가능함)
		String sql = "select eno from employee where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			System.out.println(sql);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {//ID가 존재한다면 : 값이 존재함
				result=1;
			}else {
				result=-1;
			}
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
		System.out.println("실행결과 : "+result);
		return result;
	}
	//로그인 시 로그인된 회원의 정보를 가져오는 메소드 : 이를 통해 세션에 개인의 정보를 담아서 사용하기 위함
	public MemberVO getMember(String eno) {
		MemberVO memberVO = null;
		String sql = "select * from employee where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, eno);
			rs = pstmt.executeQuery();
			if(rs.next()) {//사원번호를 포함한 사원정보를 가져옴
				memberVO = new MemberVO();
				memberVO.setEno(rs.getString("eno")); //rs.getString은 문자열을 뽑아낸다.
				memberVO.setPwd(rs.getString("pwd"));
				memberVO.setEname(rs.getString("ename"));
				memberVO.setEng_name(rs.getString("eng_name"));
				memberVO.setEmail(rs.getString("Email"));
				memberVO.setTel(rs.getString("tel"));
				memberVO.setDname(rs.getString("dname"));
				memberVO.setDname_two(rs.getString("dname_two"));
				memberVO.setHireDate(rs.getDate("hireDate"));
				memberVO.setRank(rs.getString("rank"));
				memberVO.setIsadmin(rs.getString("isadmin"));
			}
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
		return memberVO;	
	}
	//비밀번호 수정하기
	public int updatePwd(MemberVO memberVO) {
		// TODO Auto-generated method stub
		int result=-1;
		String sql = "update employee set pwd=? where eno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberVO.getPwd());
			pstmt.setString(2, memberVO.getEno());
			result=pstmt.executeUpdate();
			System.out.println("쿼리수행결과 : "+result);
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

	public int UpdateUserInfo(MemberVO memberVO) {
		// TODO Auto-generated method stub
		int result=-1; //기본값
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="update employee set ename=?, eng_name=?, tel=?, email=? where eno=?";
		try {
			System.out.println(sql);
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, memberVO.getEname());
			pstmt.setString(2, memberVO.getEng_name());
			pstmt.setString(3, memberVO.getTel());
			pstmt.setString(4, memberVO.getEmail());
			pstmt.setString(5, memberVO.getEno());
			result=pstmt.executeUpdate();
			System.out.println("결과 : "+result);
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
}


