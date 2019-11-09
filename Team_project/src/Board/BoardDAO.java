package Board;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public BoardDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List selectAllArticles() {	//게시판 리스트 보여주기
		List articlesList = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = "select noticelist, txtnum, txtname, txtcont, ename, entrydate, viewtotal, likenum "
					+ "title title, content, writeDate, id from NOTICE "
					+ "start with num=0 ";
					

			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String noticelist = rs.getString("noticelist");
				int txtnum = rs.getInt("txtnum");
				String txtname = rs.getString("txtname");
				String txtcont = rs.getString("txtcont");
				String ename = rs.getString("ename");
				Date entrydate = rs.getDate("entrydate");
				int viewtotal = rs.getInt("viewtotal");
				int likenum = rs.getInt("likenum");
				

				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				BoardVO boardVO = new BoardVO(noticelist, txtnum, txtname, txtcont, ename, entrydate, viewtotal, likenum);
				articlesList.add(boardVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlesList;
	}

	public BoardVO selectArticle(int num) {	//게시판 상세페이지 이동하기위한거
		BoardVO article = new BoardVO();
		try {
			con = dataFactory.getConnection();
			String query = "\"select noticelist, txtnum, txtname, txtcont, ename, entrydate, viewtotal, likenum  from NOTICE where num=?";
			pstmt = con.prepareStatement(query);// query를 con객체를 이용하여 db에 쿼리문을 보냄
			pstmt.setInt(1, num);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();// 보낸 쿼리문에 대한 결과를 rs객체를 이용하여 받음

			System.out.println(query);

			rs.next(); // 커서 이동
			String noticelist = rs.getString("noticelist");
			int txtnum = rs.getInt("txtnum");
			String txtname = rs.getString("txtname");
			String txtcont = rs.getString("txtcont");
			String ename = rs.getString("ename");
			Date entrydate = rs.getDate("entrydate");
			int viewtotal = rs.getInt("viewtotal");
			int likenum = rs.getInt("likenum");
			
			article.setNoticelist(noticelist);
			article.setTxtnum(txtnum);
			article.setTxtname(txtname);
			article.setTxtcont(txtcont);
			article.setEname(ename);
			article.setEntrydate(entrydate);
			article.setViewtotal(viewtotal);
			article.setLikenum(likenum);
			
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return article;
	}

	public int selecttxtnum() {
		   
	      int num = 0;
	      try {
	         
	         con = dataFactory.getConnection();
	         String query = "select max(txtnum)+1 as maxtxtnum from NOTICE";
	         System.out.println(query);
	         pstmt = con.prepareStatement(query);
	         pstmt.executeQuery(query);
	         ResultSet rs =  pstmt.getResultSet();
	         rs.next();
	         num = rs.getInt("maxtxtnum");
	         System.out.println(num);
	         con.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return num;
	   }
	
	
	
	
	
	public void insertArticle(BoardVO article) {	//글쓰기
		System.out.println("insertArticle");

		int txtnum = selecttxtnum();

		try {
			con = dataFactory.getConnection();
			
			 String txtname = article.getTxtname();
		     String txtcont = article.getTxtcont();
			String ename = "chahyinjin";
			String query = "insert into NOTICE(txtnum, txtname, txtcont, ename) values(?,?,?,?)";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
		    pstmt.setInt(1, txtnum);
			pstmt.setString(2, txtname);
			pstmt.setString(3, txtcont);
			pstmt.setString(4, ename);
		     
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
