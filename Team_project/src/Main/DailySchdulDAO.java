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
	public List<DailyScadulVO> listScadul(MemberVO memberVO) {
		// TODO Auto-generated method stub
		List<DailyScadulVO> scadulList = new ArrayList<DailyScadulVO>();
		String sql="select * from dailyscadul where eno=?";
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
				DailyScadulVO dsVO = new DailyScadulVO();
				System.out.println(1);
				dsVO.setSchnum(rs.getInt("schnum"));
				dsVO.setStartDate(rs.getTimestamp("startdate"));
				dsVO.setEndDate(rs.getTimestamp("enddate"));
				dsVO.setSchname(rs.getString("schname"));
				dsVO.setSchcont(rs.getString("schcont"));
				dsVO.setEno(rs.getString("eno"));
				dsVO.setEname(rs.getString("ename"));
				dsVO.setRank(rs.getString("rank"));
				scadulList.add(dsVO);
				System.out.println(2);
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
