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

	public List<BoardVO> selectAllBoards() {	//게시판 리스트 보여주기
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			con = dataFactory.getConnection();
			String query = "select noticelist, txtnum, txtname, txtcont, ename, entrydate, viewtotal, likenum"
					+ " from NOTICE order by txtnum desc";
					
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
				
				System.out.println(query);
				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				BoardVO boardVO = new BoardVO(noticelist, txtnum, txtname, txtcont, ename, entrydate, viewtotal, likenum);
				boardList.add(boardVO);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

	public BoardVO selectBoard(int num) {	//게시판 상세페이지 이동하기위한거
		BoardVO board = new BoardVO();
		try {
			con = dataFactory.getConnection();
			String query = "select * from NOTICE where txtnum=?";
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
			
			board.setNoticelist(noticelist);
			board.setTxtnum(txtnum);
			board.setTxtname(txtname);
			board.setTxtcont(txtcont);
			board.setEname(ename);
			board.setEntrydate(entrydate);
			board.setViewtotal(viewtotal);
			board.setLikenum(likenum);
			
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	public int selecttxtnum() {
		   
	      int txtnum = 0;
	      try {
	         con = dataFactory.getConnection();
	         String query = "select max(txtnum)+1 as maxtxtnum from NOTICE";
	         System.out.println(query);
	         pstmt = con.prepareStatement(query);
	         pstmt.executeQuery(query);
	         ResultSet rs =  pstmt.getResultSet();
	         rs.next();
	         txtnum = rs.getInt("maxtxtnum");
	         System.out.println(txtnum);
	         con.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return txtnum;
	   }
	
	
	
	
	
	public void insertBoard(BoardVO board) {	//글쓰기
		System.out.println("insertBaord");
		int txtnum = selecttxtnum();

		try {
			con = dataFactory.getConnection();
			
			String noticelist = board.getNoticelist();
			String txtname = board.getTxtname();
			String ename = board.getEname();
			String eno = board.getEno();
			int viewtotal = board.getViewtotal();
			String txtcont = board.getTxtcont();
			String rank = board.getRank();
			
			String query = "insert into NOTICE(noticelist, txtname, txtnum, ename, viewtotal, txtcont, eno, rank) values(?,?,?,?,?,?,?,?)";
				
			System.out.println(noticelist);
				System.out.println(txtname);
				System.out.println(txtnum);
				System.out.println(ename);
				System.out.println(viewtotal);
				System.out.println(txtcont);
				
				System.out.println(query);
			pstmt = con.prepareStatement(query);
		    
			pstmt.setString(1, noticelist);
			pstmt.setString(2, txtname);
			pstmt.setInt(3, txtnum);
			pstmt.setString(4, ename);
			pstmt.setInt(5, viewtotal);
			pstmt.setString(6, txtcont);
			pstmt.setString(7, eno);
			pstmt.setString(8, rank);
					
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteArticle(BoardVO board) {
		// TODO Auto-generated method stub
			   System.out.println("deleteArticle");
			   try { 
				   con = dataFactory.getConnection();
				   int txtnum = board.getTxtnum();
				   System.out.println(txtnum);
				   String query = "delete from NOTICE where txtnum=?";
				   System.out.println(query);
				   pstmt=con.prepareStatement(query);
				   pstmt.setInt(1, txtnum);
				   pstmt.executeUpdate();
				   pstmt.close();
				   con.close();
			   }catch (Exception e) {
			         e.printStackTrace();
			      }
		   }

	public void updateArticle(BoardVO board) {
		// TODO Auto-generated method stub
			System.out.println("updateArticle");
			String noticelist = board.getNoticelist();
		   int txtnum = board.getTxtnum();
		   String txtname = board.getTxtname();
		   String txtcont = board.getTxtcont();
		   try {
			   con = dataFactory.getConnection();
			   String query="update notice set noticelist=?, txtname=?, txtcont=? where txtnum=?";
			   pstmt = con.prepareStatement(query);
			   pstmt.setString(1, noticelist);
			   pstmt.setString(2,txtname);
			   pstmt.setString(3,txtcont);
			   pstmt.setInt(4, txtnum);
			   
			   pstmt.executeUpdate();
			   pstmt.close();
			   con.close();
			   System.out.println(query);
		   }catch(Exception e){
	           e.printStackTrace();
	        }
	   }
	}

