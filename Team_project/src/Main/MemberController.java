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
@WebServlet("/Main/*")

public class MemberController extends HttpServlet implements Servlet {
	MemberVO memberVO;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		memberVO = new MemberVO();
	}
	//get / post 요청을 모두 처리하는 doProcess
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nextPage = "";
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getPathInfo(); // 내 Path 가져오기
		System.out.println("action : " + action);
		try {
			if(action!=null&&action.equals("/MemberLogin.do")){


			}else if(action.equals("/MemberLoginAction.do")){


			}else if(action.equals("/MemberJoin.do")){

				nextPage="./member/joinForm.jsp";
			}else if(action.equals("/MemberJoinAction.do")){

			}
			else {
				nextPage="/index.jsp";//처리 결과를 이동할 경로 지정
			}
		}catch (Exception e) {
			e.printStackTrace();
		}


		//        }else if(command.equals("/MemberListAction.do")){
		//            action=new MemberListAction();
		//            try {
		//                forward=action.execute(request, response);
		//            } catch (Exception e) {
		//                e.printStackTrace();
		//            }
		//        }else if(command.equals("/MemberViewAction.do")){
		//            action=new MemberViewAction();
		//            try {
		//                forward=action.execute(request, response);
		//            } catch (Exception e) {
		//                e.printStackTrace();
		//            }
		//        }else if(command.equals("/MemberDeleteAction.do")){
		//            action=new MemberDeleteAction();
		//            try {
		//                forward=action.execute(request, response);
		//            } catch (Exception e) {
		//                e.printStackTrace();
		//            }

		//nextPage로 경로 지정한 부분을 실제로 넘겨주는 부분
		RequestDispatcher dispatcher=request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

}
