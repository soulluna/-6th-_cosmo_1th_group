package Main;

import java.io.IOException;
import java.io.PrintWriter;

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
            String pwd=request.getParameter("pwd");
            System.out.println(eno+"   "+pwd);
            memberVO.setEno(eno);
            memberVO.setPwd(pwd);
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
            String pwd=request.getParameter("pwd");
            String dname=request.getParameter("dname");
            String dname_two=request.getParameter("dname_two");
            String rank="사원";
            System.out.println(eno+"   "+ename+"   "+pwd+"   "+dname+"   "+dname_two);
            memberVO.setEno(eno);
            memberVO.setEname(ename);
            memberVO.setPwd(pwd);
            memberVO.setDname(dname);
            memberVO.setDname_two(dname_two);
            memberVO.setRank(rank);
            memberDAO.addMember(memberVO);
            nextPage="/index.jsp";
         }
         else if(action.equals("/pwdConfirm.do")){
            String pwd=request.getParameter("pwd");
            System.out.println(pwd);
            memberVO.setPwd(pwd);
            
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