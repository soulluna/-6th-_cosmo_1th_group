package Board;

import java.util.List;

public class Boardservice {
   BoardDAO boardDAO;

   public Boardservice() {
      // TODO Auto-generated constructor stub
      boardDAO = new BoardDAO();
   }
//   
   public List<BoardVO> listBoards(){
	   System.out.println("listBoards()");
     List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards();
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
   
}