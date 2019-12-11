package Board;

import java.util.List;

import Main.MemberVO;

public class Boardservice {
	BoardDAO boardDAO;

	public Boardservice() {
		// TODO Auto-generated constructor stub
		boardDAO = new BoardDAO();
	}
	/*
	 * noticeList의 매개변수가 있는 경우 : 각 종류별 게시판(부서, 취미, 자유)를 지정하고 검색하는 경우
	 * searchKey : 검색어가 있는 경우 없는 경우
	 */  
	public List<BoardVO> listBoard(int rownum1, int rownum2){
		System.out.println("listBoard()");
		List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(rownum1, rownum2);
		return noticeBoardsList;
	}
	public List<BoardVO> listBoard(int rownum1, int rownum2, String noticeList){
		System.out.println("listBoard()");
		List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(rownum1, rownum2, noticeList);
		return noticeBoardsList;
	}
	
	public List<BoardVO> listBoard(String searchType, String searchKey, int rownum1, int rownum2) {
		// TODO Auto-generated method stub
		List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(searchType, searchKey, rownum1, rownum2);
		return noticeBoardsList;
	}
	public List<BoardVO> listBoard(String searchType, String searchKey, int rownum1, int rownum2, String noticeList) {
		// TODO Auto-generated method stub
		List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(searchType, searchKey, rownum1, rownum2, noticeList);
		return noticeBoardsList;
	}

	public BoardVO viewBoard(int articleNo) {
		System.out.println("--viewBoard--");
		BoardVO article = boardDAO.selectBoard(articleNo);
		return article;
	}
	public void modArticle(BoardVO board) {
		boardDAO.updateArticle(board);
	}
	public void addBoard(BoardVO boardVO) {
		System.out.println("보드보드");
		boardDAO.insertBoard(boardVO); 
	}
	public void delArticle(String txtnum) {
		boardDAO.deleteArticle(txtnum);
	}
	public void likeBoard(int txtnum) {
		// TODO Auto-generated method stub
		boardDAO.updateLike(txtnum);
	}
	public List<BoardVO> alignBoard(String noticelist) {
		List<BoardVO> noticeBoardsList = boardDAO.selectAlign(noticelist);
		return noticeBoardsList;
		// TODO Auto-generated method stub

	}
	public void addComment(BoardVO boardVO) {
		boardDAO.InsertComment(boardVO);
		// TODO Auto-generated method stub

	}


	/*
	 * noticeList의 매개변수가 있는 경우 : 각 종류별 게시판(부서, 취미, 자유)를 지정하고 검색하는 경우
	 */
	public int docAllCount() { // TODO Auto-generated method stub
		return boardDAO.countAllDoc(); 
	}
	public int docAllCount(String noticeList) {
		// TODO Auto-generated method stub
		return boardDAO.countAllDoc(noticeList);
	}
	/*
	 * noticeList의 매개변수가 있는 경우 : 각 종류별 게시판(부서, 취미, 자유)를 지정하고 검색하는 경우
	 */
	public int docSearchCount(String searchType, String searchKey) {
		// TODO Auto-generated method stub
		return boardDAO.countSearchDoc(searchType, searchKey);
	}
	public int docSearchCount(String searchType, String searchKey, String noticeList) { //
		// TODO Auto-generated method stub
		return boardDAO.countSearchDoc(searchType, searchKey, noticeList);
	}
	public List<BoardVO> selectAllBoards10() {
		// TODO Auto-generated method stub
		return boardDAO.selectAllBoards10();
	}
	public int announceCount() {
		// TODO Auto-generated method stub
		return boardDAO.countIsAnnoucement();
	}
	public List<BoardVO> getAnnounceList() {
		// TODO Auto-generated method stub
		return boardDAO.selectAnnounceBoards();
	}
	public int docMinCount() {
		// TODO Auto-generated method stub
		return boardDAO.firstTxtnum();
	}
	public int docMaxCount() {
		// TODO Auto-generated method stub
		return boardDAO.lastTxtnum();
	}
	public BoardVO getPrevBoard(int txtnum) {
		// TODO Auto-generated method stub
		return boardDAO.selectPrevBoard(txtnum);
	}
	public BoardVO getNextBoard(int txtnum) {
		// TODO Auto-generated method stub
		return boardDAO.selectNextBoard(txtnum);
	}


}
