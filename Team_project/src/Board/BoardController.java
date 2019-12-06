package Board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			if (loginUser == null) {
				nextPage = "/index.jsp";
			} else {
				if (action != null && action.equals("/noticeBoardMain.do")) {// 전체게시글
					Map pagingMap = new HashMap();
					System.out.println("noticeBoardMain.do");
					String searchType = request.getParameter("searchType");
					String searchKey = request.getParameter("searchKey");
					String _pageNum = request.getParameter("pageNum");
					String _pageSessionNum = request.getParameter("pageSession");
					String noticeList = request.getParameter("noticeList");
					System.out.println("noticeList : "+noticeList);
					int pageNum = (Integer.parseInt((_pageNum == null ? "1" : _pageNum)));
					int pageSessionNum = (Integer.parseInt((_pageSessionNum == null ? "1" : _pageSessionNum)));
					int docMaxNum = 0;
					int maxPageNum = 0;
					if (searchKey == null || searchKey.equals("")) {
						if(noticeList==null||noticeList.length()==0) {
							docMaxNum = boardservice.docAllCount();
						}
						else {
							docMaxNum = boardservice.docAllCount(noticeList);
						}
					} else {
						if(noticeList==null||noticeList.length()==0) {
							docMaxNum = boardservice.docSearchCount(searchType, searchKey);
						}
						else{
							docMaxNum = boardservice.docSearchCount(searchType, searchKey, noticeList);
						}
						
					}
					System.out.println("------");
					System.out.println(docMaxNum);
					System.out.println("------");
					int maxSessionNum = 0;
					if (docMaxNum % 15 == 0) {
						maxPageNum = docMaxNum / 15;
					} else {
						maxPageNum = docMaxNum / 15 + 1;
					}
					if (maxPageNum % 5 == 0) {
						maxSessionNum = maxPageNum / 5;
					} else {
						maxSessionNum = maxPageNum / 5 + 1;
					}
					pagingMap.put("pageNum", pageNum);
					System.out.println(pageNum + "페이지넘버");
					pagingMap.put("docMaxNum", docMaxNum);
					System.out.println(docMaxNum + "최후");
					pagingMap.put("maxPageNum", maxPageNum);
					System.out.println(maxPageNum + "다음페이지");
					pagingMap.put("maxSessionNum", maxSessionNum);
					System.out.println(maxSessionNum + " 마지막 페이지넘버");
					pagingMap.put("pageSessionNum", pageSessionNum);
					System.out.println(pageSessionNum + "세션페이지");
					if (searchKey == null || searchKey.equals("")) {
						System.out.println("searchKey가 null인 if문");
						for (int i = 1; i <= maxPageNum; i++) {
							if (pageNum == i) {
								if(noticeList==null||noticeList.length()==0) {
									boardList = boardservice.listBoard(1 + ((i - 1) * 15), 15 + ((i - 1) * 15));
								}
								else {
									boardList = boardservice.listBoard(1 + ((i - 1) * 15), 15 + ((i - 1) * 15), noticeList);								}
							}
								
						}
					} else {
						for (int i = 1; i <= maxPageNum; i++) {
							if (pageNum == i) {
								if(noticeList==null||noticeList.length()==0) {
									boardList = boardservice.listBoard(searchType, searchKey, 1 + ((i - 1) * 15), 15 + ((i - 1) * 15));
								}
								else {
									boardList = boardservice.listBoard(searchType, searchKey, 1 + ((i - 1) * 15), 15 + ((i - 1) * 15), noticeList);
								}
							}
						}
					}
					request.setAttribute("boardList", boardList);
					request.setAttribute("pagingMap", pagingMap);
					request.setAttribute("searchType", searchType);
					request.setAttribute("searchKey", searchKey);
					request.setAttribute("noticeList", noticeList);
					nextPage = "/Board01/noticeBoardMain.jsp";
					/*------------------------------------------------------------------*/

				}else if (action.equals("/write.do")) {// 글쓰기
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
					nextPage = "/Board/noticeBoardMain.do";
				} else if (action.equals("/details.do")) {// 글 제목을 클릭하여 상세보기 페이지 이동(상세보기)
					System.out.println("details.do");// 페이지 이동 확인하기 위한 출력구문(디버깅용)
					BoardVO boardVO = new BoardVO();
					CommentDAO commentDAO = new CommentDAO();
					ArrayList<CommentVO> commentList = new ArrayList<CommentVO>();
					String txtnum = request.getParameter("txtnum");// article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
					String pageNum = request.getParameter("pageNum");
					int maxTxtnum = boardservice.docAllCount();
					System.out.println("txtnum : "+txtnum);
					System.out.println("pageNum : "+pageNum);
					boardVO = boardservice.viewBoard(Integer.parseInt(txtnum));// article번호를 읽어와서 boardService에 viewArticle함수를 요청
					commentList = commentDAO.listComments(txtnum);
					request.setAttribute("board", boardVO);// 가져온 결과값을 보내줌
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("commentList", commentList);
					request.setAttribute("maxTxtnum", maxTxtnum);
					request.setAttribute("updateComment", "0");
					nextPage = "/Board01/details.jsp";// 결과페이지를 이동하기 위해 nextPage에 경로 지정

				} else if (action.equals("/like.do")) {// 글 제목을 클릭하여 상세보기 페이지 이동(상세보기)
					System.out.println("like.do");// 페이지 이동 확인하기 위한 출력구문(디버깅용)
					String txtnum = request.getParameter("txtnum");// article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
					boardservice.likeBoard(Integer.parseInt(txtnum));// article번호를 읽어와서 boardService에 viewArticle함수를 요청
					nextPage = "/Board/details.do?txtnum=" + txtnum;// 결과페이지를 이동하기 위해 nextPage에 경로 지정
				} else if (action.equals("/modForm.do")) { // 수정하기 페이지 이동
					System.out.println("modForm.do");// 페이지 이동 확인하기 위한 출력구문(디버깅용)
					BoardVO boardVO = new BoardVO();
					int txtnum = (Integer.parseInt(request.getParameter("txtnum")));// article번호를 읽어와서 articleNo 에 따른 db의 데이터를 가져오기위함
					boardVO = boardservice.viewBoard(txtnum);
					request.setAttribute("board", boardVO);// 가져온 결과값을 보내줌
					nextPage = "/Board01/update.jsp";
				} else if (action.equals("/modArticle.do")) {// 글 수정하기
					System.out.println("modArticle.do");
					BoardVO boardVO = new BoardVO();

					int txtnum = Integer.parseInt(request.getParameter("txtnum"));
					int noticelist = Integer.parseInt(request.getParameter("noticelist"));
					String txtname = request.getParameter("txtname");
					String txtcont = request.getParameter("txtcont");
					System.out.println(txtnum);
					System.out.println(txtname);
					System.out.println(txtcont);
					boardVO.setTxtnum(txtnum);
					boardVO.setNoticelist(noticelist);
					boardVO.setTxtname(txtname);
					boardVO.setTxtcont(txtcont);
					boardservice.modArticle(boardVO);
					nextPage = "/Board/noticeBoardMain.do";
				} else if (action.equals("/delArticle.do")) { // 삭제하기
					System.out.println("delArticle.do");
					String txtnum = request.getParameter("txtnum");
					String pageNum= request.getParameter("pageNum");
					boardservice.delArticle(txtnum);
					nextPage = "/Board/noticeBoardMain.do?pageNum="+pageNum;
				}
				// 여기서부터 댓글에 관련된 내용
				else if (action.equals("/addComment.do")) {
					System.out.println("addComment.do");
					CommentDAO commentDAO = new CommentDAO();
					CommentVO commentVO = new CommentVO();
					String txtnum = request.getParameter("txtnum");
					String comcont = request.getParameter("comcont");
					System.out.println(comcont + "   " + txtnum);
					commentVO.setEno(loginUser.getEno());
					commentVO.setRank(loginUser.getRank());
					commentVO.setEname(loginUser.getEname());
					commentVO.setTxtnum(Integer.parseInt(txtnum));
					commentVO.setComcont(comcont);
					commentDAO.insertComment(commentVO);
					request.setAttribute("pageNum", request.getParameter("pageNum"));
					nextPage = "/Board/details.do?txtnum="+txtnum;
				} else if(action.equals("/updateCommentForm.do")) {
					System.out.println("updateCommentForm.do");
					CommentDAO commentDAO = new CommentDAO();
					String comnum="0";
					String prevComcont="";
					comnum=request.getParameter("comnum");
					String txtnum = request.getParameter("txtnum");
					if(!comnum.equals("0")) {
						prevComcont = commentDAO.getComcont(comnum);
					}
					request.setAttribute("pageNum", request.getParameter("pageNum"));
					request.setAttribute("updateComment", comnum);
					request.setAttribute("prevComcont", prevComcont);
					nextPage = "/Board/details.do?txtnum="+txtnum;
					
				} else if(action.equals("/delComment.do")) {
					System.out.println("delComment.do");
					String comnum = request.getParameter("comnum");
					String txtnum = request.getParameter("txtnum");
					CommentDAO commentDAO = new CommentDAO();
					commentDAO.deleteComment(comnum);
					request.setAttribute("pageNum", request.getParameter("pageNum"));
					nextPage = "/Board/details.do?txtnum="+txtnum;
				}
				
				else {
					/* boardList = boardservice.listBoard(); */
					request.setAttribute("boardsList", boardList);
					nextPage = "/Board01/noticeBoardMain.jsp";
				}
				System.out.println("다음페이지 : " + nextPage);
				RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
				dispatch.forward(request, response);// 모델2 기반
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
