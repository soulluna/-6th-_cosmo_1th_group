package Main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class MemberController
 */
@WebServlet("/main/*")

public class MemberController extends HttpServlet implements Servlet {
	MemberVO memberVO;
	MemberDAO memberDAO;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		memberVO = new MemberVO();
		memberDAO = new MemberDAO();
	}
	//get / post 요청을 모두 처리하는 doProcess
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nextPage = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getPathInfo(); // 내 Path 가져오기
		System.out.println("action : " + action);
		try {
			if(action!=null&&action.equals("/memberLogin.do")){
				System.out.println("memberLogin.do");
				String eno=request.getParameter("eno");
				String userpw=request.getParameter("userpw");
				System.out.println(eno+"   "+userpw);
				memberVO.setEno(eno);
				memberVO.setPwd(userpw);
				boolean isloginDB=memberDAO.isMember(memberVO);
				System.out.println(isloginDB);
				if(isloginDB) {
					nextPage="/Main01/indexMain.jsp";
					System.out.println(nextPage);
				}
				else {
					nextPage="/index.jsp";
					System.out.println(nextPage);
				}
			}
			else if(action.equals("/addMember.do")) {
				System.out.println("addMember.do");
				String eno=request.getParameter("eno");
				String ename=request.getParameter("ename");
				String pwd=request.getParameter("userpw");
				String dname1=request.getParameter("dname1");
				String dname2=request.getParameter("dname2");
				System.out.println(eno+"   "+ename+"   "+pwd+"   "+dname1+"   "+dname2);
				nextPage="/index.jsp";
			}
			else if(action.equals("/MemberLoginAction.do")){

			}
			else if(action.equals("/MemberJoin.do")){

				nextPage="./member/joinForm.jsp";
			}
			else if(action.equals("/MemberJoinAction.do")){

			}
			else {
				nextPage="/index.jsp";//처리 결과를 이동할 경로 지정
			}
			//nextPage로 경로 지정한 부분을 실제로 넘겨주는 부분
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);//모델2 기반      
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
	}

}
