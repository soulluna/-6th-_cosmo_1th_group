package Main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
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
	private static final long serialVersionUID = 1L;
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MemberLogin.do")){
            forward=new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./index.jsp");
        }else if(command.equals("/MemberLoginAction.do")){
            action=new MemberLoginAction();
            try {
                forward=action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(command.equals("/MemberJoin.do")){
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./member/joinForm.jsp");
        }else if(command.equals("/MemberJoinAction.do")){
            action=new MemberJoinAction();
            try {
                forward=action.execute(request, response);
            } catch (Exception e) {
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
        }
        if(forward!=null){
            if(forward.isRedirect()){
                response.sendRedirect(forward.getPath());
            }else{
                RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }        
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
