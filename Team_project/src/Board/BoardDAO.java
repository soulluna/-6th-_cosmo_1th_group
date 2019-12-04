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

import Main.MemberVO;

public class BoardDAO {
	private static Connection con;
	private static PreparedStatement pstmt;
	private static DataSource dataFactory; 

	public BoardDAO() {
		try {
			Context ctx = new InitialContext(); 
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<BoardVO> selectAllBoards(int rownum1, int rownum2) {	//게시판 리스트 보여주기
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			con = dataFactory.getConnection();
			String query ="select * from(select rownum as rownum2 , a.* from" 
					
					+ " (select rownum as rownum1, txtnum, txtname, txtcont, ename, noticeList, entrydate, viewtotal, likenum"
					+ " from NOTICE order by txtnum desc) a) where rownum2 between ? and ?";
			
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, rownum1);
			pstmt.setInt(2, rownum2);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				BoardVO boardVO = new BoardVO();
				boardVO.setTxtnum(rs.getInt("txtnum"));
				boardVO.setTxtname(rs.getString("txtname"));
				boardVO.setTxtcont(rs.getString("txtcont"));
				boardVO.setEname(rs.getString("ename"));
				boardVO.setNoticelist(rs.getInt("noticelist"));
				boardVO.setEntrydate(rs.getDate("entrydate"));
				boardVO.setViewtotal(rs.getInt("viewtotal"));
				boardVO.setLikenum(rs.getInt("likenum"));
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
	public List<BoardVO> selectAllBoards(String searchType, String searchKey, int rownum1, int rownum2) {
		// TODO Auto-generated method stub
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			con = dataFactory.getConnection();
			String query = null;
			if (searchType.equals("1")) {
				 query ="select * from(select rownum as rownum2 , a.* from" 
						+ " (select rownum as rownum1, txtnum, txtname, txtcont, ename, noticeList, entrydate, viewtotal, likenum"
						+ " from NOTICE where txtname like ? order by txtnum desc) a) where rownum2 between ? and ?";
				
			}else if (searchType.equals("2")) {
				 query ="select * from(select rownum as rownum2 , a.* from" 
						+ " (select rownum as rownum1, txtnum, txtname, txtcont, ename, noticeList, entrydate, viewtotal, likenum"
						+ " from NOTICE where txtname like ?  or txtcont like ? order by txtnum desc) a) where rownum2 between ? and ?";
				
			}else if (searchType.equals("3")) {
				 query ="select * from(select rownum as rownum2 , a.* from" 
						+ " (select rownum as rownum1, txtnum, txtname, txtcont, ename, noticeList, entrydate, viewtotal, likenum"
						+ " from NOTICE where ename like ? order by txtnum desc) a) where rownum2 between ? and ?";
				
			}
			
			
			pstmt = con.prepareStatement(query);
			if (searchType.equals("1")) {
				pstmt.setString(1, "%" + searchKey + "%");
				pstmt.setInt(2, rownum1);
				pstmt.setInt(3, rownum2);
			} else if (searchType.equals("2")) {
				pstmt.setString(1, "%" + searchKey + "%");
				pstmt.setString(2, "%" + searchKey + "%");
				pstmt.setInt(3, rownum1);
				pstmt.setInt(4, rownum2);
			} else if (searchType.equals("3")) {
				pstmt.setString(1, "%" + searchKey + "%");
				pstmt.setInt(2, rownum1);
				pstmt.setInt(3, rownum2);
			}
		

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				BoardVO boardVO = new BoardVO();
				boardVO.setTxtnum(rs.getInt("txtnum"));
				boardVO.setTxtname(rs.getString("txtname"));
				boardVO.setTxtcont(rs.getString("txtcont"));
				boardVO.setEname(rs.getString("ename"));
				boardVO.setNoticelist(rs.getInt("noticelist"));
				boardVO.setEntrydate(rs.getDate("entrydate"));
				boardVO.setViewtotal(rs.getInt("viewtotal"));
				boardVO.setLikenum(rs.getInt("likenum"));
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
	
	
	
	
	public BoardVO selectBoard(int num) {	//게시판 상세페이지 이동하기
		System.out.println("--selectBoard--");
		BoardVO board = new BoardVO();
		updateViewTotal(num);
		
		try {
			con = dataFactory.getConnection();
			String query = "select * from NOTICE where txtnum=?";
			pstmt = con.prepareStatement(query);// query를 con객체를 이용하여 db에 쿼리문을 보냄
			pstmt.setInt(1, num);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();// 보낸 쿼리문에 대한 결과를 rs객체를 이용하여 받음
			if(rs.next()) {// 커서 이동
				int noticelist = rs.getInt("noticelist");
				int txtnum = rs.getInt("txtnum");
				String txtname = rs.getString("txtname");
				String txtcont = rs.getString("txtcont");
				String ename = rs.getString("ename");
				String eno=rs.getString("eno");
				Date entrydate = rs.getDate("entrydate");
				int viewtotal = rs.getInt("viewtotal");
				int likenum = rs.getInt("likenum");
				String comcont=rs.getString("comcont");
				String comuser=rs.getString("comuser");
System.out.println(noticelist+"   "+txtnum+"   "+txtname+"   "+txtcont+"   "+ename+"   "+eno+"   "+entrydate+"   "+viewtotal+"   "+likenum+"  "+comcont+"   "+comuser);
				board.setNoticelist(noticelist);
				board.setTxtnum(txtnum);
				board.setTxtname(txtname);
				board.setTxtcont(txtcont);
				board.setEname(ename);
				board.setEntrydate(entrydate);
				board.setViewtotal(viewtotal);
				board.setLikenum(likenum);
				board.setEno(eno);
				board.setComcont(comcont);
				board.setComuser(comuser);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
	public void updateViewTotal(int num) {
		try {
			con = dataFactory.getConnection();
			String query = "update NOTICE set viewTotal=viewTotal+1 where txtnum=?";
			System.out.println(query);
			pstmt = con.prepareStatement(query);// query를 con객체를 이용하여 db에 쿼리문을 보냄
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
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

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return txtnum;
	}
	
	public void deleteArticle(String txtnum) {
		// TODO Auto-generated method stub
		System.out.println("deleteArticle");
		try { 
			con = dataFactory.getConnection();
			System.out.println(txtnum);
			String query = "delete from NOTICE where txtnum=?";
			System.out.println(query);
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, txtnum);
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
		int noticelist = board.getNoticelist();
		int txtnum = board.getTxtnum();
		String txtname = board.getTxtname();
		String txtcont = board.getTxtcont();
		try {
			con = dataFactory.getConnection();
			String query="update notice set noticelist=?, txtname=?, txtcont=? where txtnum=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, noticelist);
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

	public void updateLike(int txtnum) {
		// TODO Auto-generated method stub
		System.out.println("updateLike");
		String query="update notice set likenum=likenum+1 where txtnum=?";
		System.out.println(query);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, txtnum);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void insertBoard(BoardVO boardVO) {
		// TODO Auto-generated method stub
		int txtnum=selecttxtnum();
		System.out.println("insertBoard");
		String query="insert into notice(txtnum, txtname, txtcont, ename, noticelist, rank, eno) values(?, ?, ?, ?, ?, ?, ?)";
		try {
			con=dataFactory.getConnection();
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, txtnum);
			pstmt.setString(2, boardVO.getTxtname());
			pstmt.setString(3, boardVO.getTxtcont());
			pstmt.setString(4, boardVO.getEname());
			pstmt.setInt(5, boardVO.getNoticelist());
			pstmt.setString(6, boardVO.getRank());
			pstmt.setString(7, boardVO.getEno());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<BoardVO> selectAlign(String noticelist) {//정렬 버튼 클릭 시 게시글 정렬하기
		// TODO Auto-generated method stub
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			
			String query = "select txtnum, txtname, txtcont, ename, noticeList, entrydate, viewtotal, likenum"
					+ " from NOTICE where noticelist=? order by txtnum desc";
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, noticelist);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// articleVO인스턴스에 받은 값을 매개변수로 생성함
				BoardVO boardVO = new BoardVO();
				boardVO.setTxtnum(rs.getInt("txtnum"));
				boardVO.setTxtname(rs.getString("txtname"));
				boardVO.setTxtcont(rs.getString("txtcont"));
				boardVO.setEname(rs.getString("ename"));
				boardVO.setNoticelist(rs.getInt("noticelist"));
				boardVO.setEntrydate(rs.getDate("entrydate"));
				boardVO.setViewtotal(rs.getInt("viewtotal"));
				boardVO.setLikenum(rs.getInt("likenum"));
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

	public void InsertComment(BoardVO boardVO) {//댓글 달기
		// TODO Auto-generated method stub
		System.out.println("InsertComment");
		String query="update notice set comcont=?, comuser=?  where txtnum=?";
		try {
			con=dataFactory.getConnection();
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, boardVO.getComcont());
			pstmt.setString(2, boardVO.getComuser());
			pstmt.setInt(3, boardVO.getTxtnum());
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int countAllDoc() { //일반
		// TODO Auto-generated method stub
		int docMax = 0;
		
		String query = "select count(*) from notice";
	
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				docMax = rs.getInt("count(*)");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docMax;
	}
		//쿼리문 수정해야함
	public int countSearchDoc(String searchType, String searchKey) {//검색시 전체문서수
		// TODO Auto-generated method stub
		int docMax = 0;
		String query = null;
		if (searchType.equals("1")) {
			query = "select count(*) from notice where txtname like ?";
			
		}else if (searchType.equals("2")) {
			query = "select count(*) from notice where txtname like ?  or txtcont like ?";
			
		}else if (searchType.equals("3")) {
			query = "select count(*) from notice where ename like ?";
			
		}
		
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			if (searchType.equals("1")) {
				pstmt.setString(1, "%" + searchKey + "%");
			} else if (searchType.equals("2")) {
				pstmt.setString(1, "%" + searchKey + "%");
				pstmt.setString(2, "%" + searchKey + "%");
			} else if (searchType.equals("3")) {
				pstmt.setString(1, "%" + searchKey + "%");
			}
			

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				docMax = rs.getInt("count(*)");
			}
			System.out.println("---함수안---");
			System.out.println(docMax);
			System.out.println("---함수안---");
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docMax;
		
	}

}

