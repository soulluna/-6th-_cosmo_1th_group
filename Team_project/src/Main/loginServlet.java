package Main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/index/*")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String nextPage="";
		String action=request.getPathInfo();

		try {
			if(action!=null&&action.equals("/login.do")) { //로그인 버튼 눌렀을때 페이지
				String eno = request.getParameter("eno");
				String pwd = request.getParameter("pwd");
				regVO regVO = new regVO();			
				regVO.setEno(eno);
				regVO.setPwd(pwd);
				loginDAO dao = new loginDAO();
				boolean result = dao.isExist(regVO);
				if(result) {
					HttpSession session = request.getSession();
					session.setAttribute("isLogin", true);
					session.setAttribute("login.eno", eno);
					session.setAttribute("login.pwd", pwd);
					nextPage="/Main/IndexMain.jsp";
				}
			}
			else { //메인페이지
				nextPage="/index.jsp";
			}
			System.out.println(nextPage);//디버깅용 확인구문
			RequestDispatcher dispatch=request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
