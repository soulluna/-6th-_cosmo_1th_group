package Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Main.MemberVO;




/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boardservice boardservice;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		boardservice = new Boardservice();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		try {
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			HttpSession session = request.getSession();
			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");	
			if(loginUser==null) {
				nextPage = "/index.jsp";
			}
			if(action!=null && action.equals("/noticeBoardMain.do")) {
				System.out.println("noticeBoardMain.do");
				boardList = boardservice.listBoards();
				request.setAttribute("boardList", boardList);
				nextPage = "/Board01/noticeBoardMain.jsp";
			}
			else if(action.equals("/searchKeyword.do")) {
				System.out.println("searchKeyword.do");
				String noticelist = request.getParameter("noticelist");
				System.out.println(noticelist);
				boardList = boardservice.alignBoard(noticelist);
				request.setAttribute("boardList", boardList);
				nextPage = "/Board01/noticeBoardMain.jsp";
			}
			else if(action.equals("/write.do")) {//글쓰기 
				System.out.println("write.do");
				BoardVO boardVO = new BoardVO();
				String txtname = request.getParameter("w_title");				
				String txtcont = request.getParameter("contents");				
				int noticeList = Integer.parseInt(request.getParameter("noticeList"));											
				boardVO.setRank(loginUser.getRank());				
				boardVO.setEname(loginUser.getEname());				
				boardVO.setEno(loginUser.getEno());
				System.out.println(loginUser.getEno());				
				boardVO.setNoticelist(noticeList);				
				boardVO.setTxtname(txtname);				
				boardVO.setTxtcont(txtcont);
				boardservice.addBoard(boardVO);				
				nextPage="/Board/noticeBoardMain.do";
			}
			else if(action.equals("/details.do")) {//글 제목을 클릭하여 상세보기 페이지 이동(상세보기)
				System.out.println("details.do");//페이지 이동 확인하기 위한 출력구문(디버깅용)
				BoardVO boardVO = new BoardVO();
				String txtnum = request.getParameter("txtnum");//article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
				System.out.println(txtnum);
				boardVO=boardservice.viewBoard(Integer.parseInt(txtnum));//article번호를 읽어와서 boardService에 viewArticle함수를 요청
				request.setAttribute("board", boardVO);//가져온 결과값을 보내줌
				nextPage="/Board01/details.jsp";//결과페이지를 이동하기 위해 nextPage에 경로 지정

			}
			else if(action.equals("/like.do")) {//글 제목을 클릭하여 상세보기 페이지 이동(상세보기)
				System.out.println("like.do");//페이지 이동 확인하기 위한 출력구문(디버깅용)
				String txtnum = request.getParameter("txtnum");//article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
				boardservice.likeBoard(Integer.parseInt(txtnum));//article번호를 읽어와서 boardService에 viewArticle함수를 요청
				nextPage="/Board/details.do?txtnum="+txtnum;//결과페이지를 이동하기 위해 nextPage에 경로 지정
			}
			else if(action.equals("/modForm.do")) { //수정하기 페이지 이동
				System.out.println("modForm.do");//페이지 이동 확인하기 위한 출력구문(디버깅용)
				BoardVO boardVO = new BoardVO();
				int txtnum = (Integer.parseInt(request.getParameter("txtnum")));//article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함     
				boardVO=boardservice.viewBoard(txtnum);
				
				request.setAttribute("board", boardVO);//가져온 결과값을 보내줌
				nextPage="/Board01/update.jsp";
			}
			else if(action.equals("/modArticle.do")){//글 수정하기
				System.out.println("modArticle.do");
				BoardVO boardVO = new BoardVO();
				int txtnum = Integer.parseInt(request.getParameter("txtnum"));
				String txtname = request.getParameter("txtname");
				String txtcont = request.getParameter("txtcont");
				System.out.println(txtnum);
				System.out.println(txtname);
				System.out.println(txtcont);         	
				boardVO.setTxtnum(txtnum);
				boardVO.setTxtname(txtname);
				boardVO.setTxtcont(txtcont);
				boardservice.modArticle(boardVO);
				nextPage = "/Board/noticeBoardMain.do";
			}
			else if(action.equals("/delArticle.do")){	//삭제하기
				System.out.println("delArticle.do");
				String txtnum=request.getParameter("txtnum");
				
				boardservice.delArticle(txtnum);
				nextPage = "/Board/noticeBoardMain.do";
			}
			else if(action.equals("/addComment.do")) {
				System.out.println("addComment.do");
				String txtnum=request.getParameter("txtnum");
				String comcont=request.getParameter("comment");
				String comuser = loginUser.getEname();
				System.out.println(comcont+"   "+txtnum);
				BoardVO boardVO = new BoardVO();
				boardVO.setTxtnum(Integer.parseInt(txtnum));
				boardVO.setComcont(comcont);
				boardVO.setComuser(comuser);
				boardservice.addComment(boardVO);
				nextPage = "/Board/details.dotxtnum="+txtnum;
			}
			else {
				boardList = boardservice.listBoards();
				request.setAttribute("boardsList", boardList);
				nextPage = "/Board01/noticeBoardMain.jsp";
			}
			System.out.println("다음페이지 : "+nextPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);//모델2 기반
		}   
		catch(Exception e){
			e.printStackTrace();
		}
	}
}