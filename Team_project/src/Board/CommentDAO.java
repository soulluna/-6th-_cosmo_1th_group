package Board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CommentDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory; 

	public CommentDAO() {
		try {
			Context ctx = new InitialContext(); 
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CommentVO> listComments(String txtnum) { // 댓글을 모두 읽어오기
		// TODO Auto-generated method stub
		ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
		String query = "select * from commenttb where txtnum=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, txtnum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				CommentVO commentVO = new CommentVO();
				commentVO.setEno(rs.getString("eno"));
				commentVO.setRank(rs.getString("rank"));
				commentVO.setEname(rs.getString("ename"));
				commentVO.setTxtnum(rs.getInt("txtnum"));
				commentVO.setComnum(rs.getInt("comnum"));
				commentVO.setComcont(rs.getString("comcont"));
				commentVO.setComdate(rs.getTimestamp("comdate"));
				commentList.add(commentVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commentList;
	}

	public void insertComment(CommentVO commentVO) {
		// TODO Auto-generated method stub
		String query="insert into commenttb(eno, rank, ename, txtnum, comnum, comcont) values(?, ?, ?, ?, Comment_seq.nextval, ?)";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, commentVO.getEno());
			pstmt.setString(2, commentVO.getRank());
			pstmt.setString(3, commentVO.getEname());
			pstmt.setInt(4, commentVO.getTxtnum());
			pstmt.setString(5, commentVO.getComcont());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteComment(String comnum) {//댓글 삭제
		// TODO Auto-generated method stub
		String query="delete from commenttb where comnum=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, comnum);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getComcont(String comnum) {
		// TODO Auto-generated method stub
		String result="";
		String query="select comcont where comnum=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, comnum);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getString("comcont");
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
