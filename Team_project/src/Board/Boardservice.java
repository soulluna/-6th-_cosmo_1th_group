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
     List<BoardVO> noticeBoardsList = boardDAO.selectAllBoards();
     return noticeBoardsList;
  }
   
   public BoardVO viewBoard(int articleNo) {
	   BoardVO article = boardDAO.selectBoard(articleNo);
      return article;
   }
//   public void modArticle(BoardVO article) {
//	   boardDAO.updateArticle(article);
//   }
//   
   public void addBoard(BoardVO board) {
	   boardDAO.insertBoard(board); 
   }
//   public void delArticle(BoardVO article) {
//	   boardDAO.deleteArticle(article);
//   }
//   
}