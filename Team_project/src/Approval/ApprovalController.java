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
import javax.servlet.http.HttpSession;

import Main.MemberVO;

@WebServlet("/Approval/*")
public class ApprovalController extends HttpServlet {

	ApprovalVO approvalVO;
	ApprovalService approvalService;

	public void init(ServletConfig config) throws ServletException {
		approvalService = new ApprovalService();
		approvalVO = new ApprovalVO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberVO mVO = (MemberVO) session.getAttribute("loginUser");

		String nextPage = null; // 다음 페이지를 담을 변수 선언
		request.setCharacterEncoding("utf-8"); // request 반응 타입 명시함
		response.setContentType("text/html;utf-8"); // response 데이터 종류 명시함

		String action = request.getPathInfo(); // 맵핑 이름 뒤 경로를 받아옴

		try {
			List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();

			if (action != null && action.equals("/docList.do")) {
				System.out.println("/docList.do");
				System.out.println("action : " + action);
				String searchType = request.getParameter("searchType");
				System.out.println("searchType : " + searchType);
				String searchKey = request.getParameter("searchKey");
				System.out.println("searchKey : " + searchKey);
				if (searchKey == null || searchKey.equals("")) {
					System.out.println("searchKey가 null인 if문");
					approvalList = approvalService.listApproval(mVO);
				} else {
					approvalList = approvalService.listApproval(searchType, searchKey);
				}
				request.setAttribute("approvalList", approvalList);
				nextPage = "/Approval01/docList.jsp";

			} /*
				 * else if (action.equals("/vacationWait.do")) { // 휴가신청서 상세보기
				 * System.out.println(); System.out.println("vacationWait.do");
				 * System.out.println("action : " + action); String txtnum =
				 * request.getParameter("txtnum"); System.out.println(txtnum); approvalVO =
				 * approvalService.viewvacation(Integer.parseInt(txtnum));
				 * request.setAttribute("approval", approvalVO); nextPage =
				 * "/Approval01/vacationWait.jsp";
				 * 
				 * }
				 */ else if (action.equals("/draftWait.do")) { // 기안서 상세보기
				System.out.println();
				System.out.println("draftWait.do");
				System.out.println("action : " + action);
				int txtnum = Integer.parseInt(request.getParameter("txtnum"));
				
				System.out.println(txtnum);
				
				approvalVO = approvalService.viewdraft(txtnum);
				System.out.println("컨트롤러 MIDENO: "+approvalVO.getMideno());
				
				MemberVO createdMidUser = approvalService.middraftInfo(approvalVO);
				MemberVO createdFinUser = approvalService.findraftInfo(approvalVO);
				request.setAttribute("createdMidUser", createdMidUser);
				request.setAttribute("createdFinUser", createdFinUser);
				request.setAttribute("txtnum", txtnum);
				request.setAttribute("approval", approvalVO);
				nextPage = "/Approval01/draftWait.jsp";

			} else if (action.equals("/draft.do")) { // 기안서 작성 페이지
				
				MemberVO midUser = approvalService.midApprovalInfo(mVO);
				MemberVO finUser = approvalService.finApprovalInfo(mVO);
				request.setAttribute("midUser", midUser);
				request.setAttribute("finUser", finUser);
				nextPage = "/Approval01/draft.jsp";

			} else if (action.equals("/drafted.do")) { // 기안서 페이지 등록 완료 시
				System.out.println("기안서 등록 클릭");
				String midUser = request.getParameter("midUser");
				String finUser = request.getParameter("finUser");
				System.out.println(midUser);
				System.out.println(finUser);
				
				ApprovalVO aVO = new ApprovalVO();
				String midUserEno = approvalService.approvalUser(midUser);
				String finUserEno = approvalService.approvalUser(finUser);
				aVO.setMideno(midUserEno);
				aVO.setFineno(finUserEno);
				aVO.setTxtname(request.getParameter("title"));
				aVO.setTxtcont(request.getParameter("reason"));
				approvalService.draftAdd(aVO, mVO);
				nextPage = "/Approval/docList.do";
				
			}else if(action.equals("/draftModify.do")) { // 기안서 수정 페이지
				System.out.println("/draftModify.do");
				int txtnum = Integer.parseInt(request.getParameter("txtnum"));
				System.out.println("txtnum : " + txtnum);
				MemberVO midUser = approvalService.midApprovalInfo(mVO);
				MemberVO finUser = approvalService.finApprovalInfo(mVO);
				request.setAttribute("midUser", midUser);
				request.setAttribute("finUser", finUser);
				approvalVO = approvalService.viewdraft(txtnum);
				request.setAttribute("txtnum", txtnum);
				request.setAttribute("approvalVO", approvalVO);
				nextPage = "/Approval01/draftModify.jsp";
				
			}else if(action.equals("/modified.do")) { // 기안서 수정 버튼 클릭
				System.out.println("/modified.do");
				ApprovalVO aVO = new ApprovalVO();
				int txtnum = Integer.parseInt(request.getParameter("txtnum"));
				aVO.setTxtname(request.getParameter("title"));
				aVO.setTxtcont(request.getParameter("reason"));
				System.out.println("----------");
				System.out.println(aVO.getTxtname());
				System.out.println(aVO.getTxtcont());
				System.out.println(txtnum);
				System.out.println("----------");
				approvalService.draftmodify(aVO, txtnum);
				nextPage = "/Approval/docList.do";
				
			}else if(action.equals("/draftdelete.do")) { //문서 삭제
				System.out.println("draftdelete.do");
				int txtnum = Integer.parseInt(request.getParameter("txtnum"));
				System.out.println(txtnum);
				approvalService.draftDelete(txtnum);
				nextPage = "/Approval/docList.do";
				
			}else {
				String searchType = request.getParameter("searchType");
				System.out.println("searchType : " + searchType);
				String searchKey = request.getParameter("searchKey");
				System.out.println("searchKey : " + searchKey);
				if (searchKey == null || searchKey.equals("")) {
					approvalList = approvalService.listApproval(mVO);
				} else {
					approvalList = approvalService.listApproval(searchType, searchKey);
				}
				request.setAttribute("approvalList", approvalList);
				nextPage = "/Approval01/docList.jsp";
			}

			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
