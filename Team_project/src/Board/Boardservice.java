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
	   BoardVO article = boardDAO.selectBoard(articleNo);
      return article;
   }
   public void modArticle(BoardVO board) {
	   boardDAO.updateArticle(board);
   }
   
   public void addBoard(BoardVO board) {
	   boardDAO.insertBoard(board); 
   }
   public void delArticle(BoardVO board) {
	   boardDAO.deleteArticle(board);
   }
public BoardVO likeBoard(int txtnum) {
	// TODO Auto-generated method stub
	boardDAO.updateLike(txtnum);
	return null;
}
   
}