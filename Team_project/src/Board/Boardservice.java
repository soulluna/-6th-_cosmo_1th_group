package Board;

import java.util.List;

public class Boardservice {
   BoardDAO boardDAO;

   public Boardservice() {
      // TODO Auto-generated constructor stub
      boardDAO = new BoardDAO();
   }
//   
//   public List<BoardVO> listArticles(){
//      List<BoardVO> articlesList = boardDAO.selectAllArticles();
//      return articlesList;
//   }
//   
//   public BoardVO viewArticle(int articleNo) {
//	   BoardVO article = boardDAO.selectArticle(articleNo);
//      return article;
//   }
//   public void modArticle(BoardVO article) {
//	   boardDAO.updateArticle(article);
//   }
//   
//   public void addArticle(BoardVO article) {
//	   boardDAO.insertArticle(article); 
//   }
//   public void delArticle(BoardVO article) {
//	   boardDAO.deleteArticle(article);
//   }
//   
}