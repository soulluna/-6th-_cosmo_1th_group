package Approval;

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

@WebServlet("/Approval/*")
public class ApprovalController extends HttpServlet {
	ApprovalVO approvalVO;
	ApprovalService approvalService;

	
	public void init(ServletConfig config) throws ServletException {	
		approvalService = new ApprovalService(); 
		approvalVO = new ApprovalVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = ""; //다음 페이지를 담을 변수 선언
		request.setCharacterEncoding("utf-8"); //request 반응 타입 명시함
		response.setContentType("text/html;utf-8"); //response 데이터 종류 명시함 
		String action = request.getPathInfo(); //맵핑 이름 뒤 경로를 받아옴
		System.out.println("try 전의 action : " + action); // action 출력하여 확인
		
		try {
			List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
			
			if (false) {
				
			}else {
				System.out.println();
				System.out.println("else");
				System.out.println("action : " + action);
				approvalList = approvalService.listApproval();
				System.out.println(approvalList);
				
				request.setAttribute("approvalList", approvalList);
				
				nextPage = "/Approval01/docList.jsp";
			}
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
