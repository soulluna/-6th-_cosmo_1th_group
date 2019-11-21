package Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Main.MemberDAO;
import Main.MemberVO;


/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Boardservice boardservice;
	BoardVO boardVO;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		boardservice = new Boardservice();
		boardVO = new BoardVO();
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
			if(action!=null && action.equals("/noticeBoardMain.do")) {
				System.out.println("noticeBoardMain.do");

				boardList = boardservice.listBoards();
				request.setAttribute("boardList", boardList);
				nextPage = "/Board01/noticeBoardMain.jsp";
				System.out.println("[1]" + nextPage);


			}else if(action.equals("/write.do")) {//글쓰기 
				System.out.println("write.do");
				String txtname = request.getParameter("w_title");
				String txtcont = request.getParameter("contents");
				String noticelist = request.getParameter("searchtype");
				String ename = request.getParameter("ename");
				String eno=request.getParameter("eno");

				MemberDAO memberDAO = new MemberDAO();
				MemberVO memberVO = new MemberVO();
				memberVO = memberDAO.getMember(eno);
				boardVO.setRank(memberVO.getRank());
				boardVO.setEname(memberVO.getEname());
				boardVO.setEno(memberVO.getEno());
				System.out.println(txtname+"   "+txtcont);
				boardVO.setTxtname(txtname);
				boardVO.setTxtcont(txtcont);
				boardVO.setNoticelist(noticelist);
				boardVO.setEname(ename);

				boardservice.addBoard(boardVO);
				nextPage="/Board/noticeBoardMain.do";

			}else if(action.equals("/details.do")) {//글 제목을 클릭하여 상세보기 페이지 이동(상세보기)
				System.out.println("details.do");//페이지 이동 확인하기 위한 출력구문(디버깅용)
				String num = request.getParameter("txtnum");//article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
				boardVO=boardservice.viewBoard(Integer.parseInt(num));//article번호를 읽어와서 boardService에 viewArticle함수를 요청
				request.setAttribute("details", boardVO);//가져온 결과값을 보내줌
				nextPage="/Board01/details.jsp";//결과페이지를 이동하기 위해 nextPage에 경로 지정

			}else if(action.equals("/modForm.do")) { //수정하기 페이지 이동
				         	 System.out.println("modForm.do");//페이지 이동 확인하기 위한 출력구문(디버깅용)
				              int num = (Integer.parseInt(request.getParameter("txtnum")));//article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함     
				              String name = request.getParameter("txtname");
				              String cont = request.getParameter("txtcont");
				              String noticelist = request.getParameter("noticelist");
				              
				              boardVO.setTxtnum(num);
				              boardVO.setTxtname(name); 
				              boardVO.setTxtname(cont);
				              boardVO.setTxtname(noticelist);
				              boardservice.modArticle(boardVO); //article번호를 읽어와서 boardService에 viewArticle함수를 요청
				              
				              request.setAttribute("modform", boardVO);//가져온 결과값을 보내줌
				         	 nextPage="/board01/update.jsp";
				         }
			else if(action.equals("/modArticle.do")){//글 수정하기
				         	 System.out.println("modArticle.do");
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
				         	 nextPage = "/board/noticeBoardMain.do";
			}
			else if(action.equals("/delArticle.do")){	//삭제하기
				System.out.println("delArticle.do");
				boardservice.delArticle(boardVO);
				nextPage = "noticeBoardMain.do";

			}
			else {
				boardList = boardservice.listBoards();
				request.setAttribute("boardsList", boardList);
				nextPage = "/board01/noticeBoardMain.jsp";
				System.out.println("[2]"+nextPage);


				boardList = boardservice.listBoards();
				request.setAttribute("boardList", boardList);
				nextPage = "/Board01/noticeBoardMain.jsp";
				System.out.println("[1]" + nextPage);
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);//모델2 기반
		}   
		catch(Exception e){
			e.printStackTrace();
		}
	}
}