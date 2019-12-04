package Board;

import java.util.List;

import Main.MemberVO;

public class Boardservice {
   BoardDAO boardDAO;

   public Boardservice() {
      // TODO Auto-generated constructor stub
      boardDAO = new BoardDAO();
   }
//   
   public List<BoardVO> listBoard(int rownum1, int rownum2){
	   System.out.println("listBoard()");
     List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(rownum1, rownum2);
     return noticeBoardsList;
  }


   public List<BoardVO> listBoard(String searchType, String searchKey, int rownum1, int rownum2) {
 		// TODO Auto-generated method stub
 	   List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards(searchType, searchKey, rownum1, rownum2);
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

	
public int docAllCount() { // TODO Auto-generated method stub
	return boardDAO.countAllDoc(); }
	 
public int docSearchCount(String searchType, String searchKey) {
	// TODO Auto-generated method stub
	return boardDAO.countSearchDoc(searchType, searchKey);
}

   
}
