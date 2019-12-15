package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DailySchdulDAO {

	public DailySchdulDAO() {}
	
	private static DailySchdulDAO instance = new DailySchdulDAO();
	public static DailySchdulDAO getInstance() {
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
	public List<DailySchdulVO> listScadul(MemberVO memberVO) {
		// TODO Auto-generated method stub
		List<DailySchdulVO> scadulList = new ArrayList<DailySchdulVO>();
		String sql="select * from (select * from dailyscadul where eno=? order by schnum desc) where rownum<10";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getEno());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				DailySchdulVO dsVO = new DailySchdulVO();
				dsVO.setSchnum(rs.getInt("schnum"));
				dsVO.setStartDate(rs.getTimestamp("startdate"));
				dsVO.setEndDate(rs.getTimestamp("enddate"));
				dsVO.setSchname(rs.getString("schname"));
				dsVO.setSchcont(rs.getString("schcont"));
				dsVO.setEno(rs.getString("eno"));
				dsVO.setEname(rs.getString("ename"));
				dsVO.setRank(rs.getString("rank"));
				scadulList.add(dsVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return scadulList;
	}
	public DailySchdulVO selectSchdul(String schnum) {
		// TODO Auto-generated method stub
		DailySchdulVO schVO = null;
		String sql="select * from dailyscadul where schnum=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, schnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				schVO = new DailySchdulVO();
				schVO.setSchnum(rs.getInt("schnum"));
				schVO.setStartDate(rs.getTimestamp("startdate"));
				schVO.setEndDate(rs.getTimestamp("enddate"));
				schVO.setSchname(rs.getString("schname"));
				schVO.setSchcont(rs.getString("schcont"));
				schVO.setEno(rs.getString("eno"));
				schVO.setEname(rs.getString("ename"));
				schVO.setRank(rs.getString("rank"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return schVO;
	}
	public void insertSchdul(DailySchdulVO schVO) {
		// TODO Auto-generated method stub
		String sql="insert into dailyscadul(schnum, startdate, enddate, schname, schcont, eno, ename, rank) values(Scadul_seq.nextval, ?,?,?,?,?,?,?)";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setTimestamp(1, schVO.getStartDate());
			pstmt.setTimestamp(2, schVO.getEndDate());
			pstmt.setString(3, schVO.getSchname());
			pstmt.setString(4, schVO.getSchcont());
			pstmt.setString(5, schVO.getEno());
			pstmt.setString(6, schVO.getEname());
			pstmt.setString(7, schVO.getRank());
			pstmt.executeUpdate();
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
	}
	public void deleteSchdul(String schnum) {
		// TODO Auto-generated method stub
		String sql="delete from dailyscadul where schnum=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, schnum);
			pstmt.executeUpdate();
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
	}
	public void updateSchdul(DailySchdulVO schVO) {
		// TODO Auto-generated method stub
		String sql="update dailyscadul set schname=?, schcont=?, startDate=?, endDate=?, eno=?, ename=?, rank=? where schnum=?";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, schVO.getSchname());
			pstmt.setString(2, schVO.getSchcont());
			pstmt.setTimestamp(3, schVO.getStartDate());
			pstmt.setTimestamp(4, schVO.getEndDate());
			pstmt.setString(5, schVO.getEno());
			pstmt.setString(6, schVO.getEname());
			pstmt.setString(7, schVO.getRank());
			pstmt.setInt(8, schVO.getSchnum());
			pstmt.executeUpdate();
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
	}
	public List<DailySchdulVO> listScadul(MemberVO memberVO, String getDate) {
		List<DailySchdulVO> scadulList = new ArrayList<DailySchdulVO>();
		String sql="select * from (select * from dailyscadul where eno=? and startdate between ? and ? order by schnum desc) where rownum<10";
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberVO.getEno());
			pstmt.setString(2, getDate+" 00:00:00");
			pstmt.setString(3, getDate+" 23:59:00");
			rs=pstmt.executeQuery();
			while(rs.next()) {
				DailySchdulVO dsVO = new DailySchdulVO();
				dsVO.setSchnum(rs.getInt("schnum"));
				dsVO.setStartDate(rs.getTimestamp("startdate"));
				dsVO.setEndDate(rs.getTimestamp("enddate"));
				dsVO.setSchname(rs.getString("schname"));
				dsVO.setSchcont(rs.getString("schcont"));
				dsVO.setEno(rs.getString("eno"));
				dsVO.setEname(rs.getString("ename"));
				dsVO.setRank(rs.getString("rank"));
				scadulList.add(dsVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(conn!=null)
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			} 
		}
		return scadulList;
	}
}
