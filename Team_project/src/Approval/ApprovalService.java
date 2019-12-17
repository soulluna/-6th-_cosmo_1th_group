package Approval;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Main.MemberVO;

public class ApprovalService {

	ApprovalDAO approvalDAO;

	public ApprovalService() {
		approvalDAO = new ApprovalDAO();
	}
	
	// 문서 출력
	public List<ApprovalVO> listApproval(MemberVO mVO, String searchType, String searchKey, String sendReceive, String serachDocList, String serachDocState, String datepicker1, String datepicker2, int rowNum1, int rowNum2) {
		System.out.println("listApproval(매개변수) : " + searchType + ", " + searchKey);
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval(mVO, searchType, searchKey, sendReceive, serachDocList, serachDocState, datepicker1, datepicker2, rowNum1, rowNum2);
		return approvallist;
	}

	// 기안서 보기
	public ApprovalVO viewdraft(int txtnum) {
		ApprovalVO approval = approvalDAO.selectDraft(txtnum);
		return approval;
	}

	// 휴가신청서 보기
	public ApprovalVO viewvacation(int txtnum) {
		ApprovalVO approval = approvalDAO.selectVacation(txtnum);
		return approval;
	}

	// 기안서 작성 시 결재 정보
	public MemberVO midApprovalInfo(MemberVO mVO) throws SQLException {
		MemberVO midUser = approvalDAO.midApprovalGet(mVO);

		return midUser;
	}

	public MemberVO finApprovalInfo(MemberVO mVO) throws SQLException {
		MemberVO finUser = approvalDAO.finApprovalGet(mVO);

		return finUser;
	}

	public int draftAdd(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		int txtnum = approvalDAO.draftInset(aVO, mVO);
		return txtnum;
	}

	public String approvalUser(String userEname) {
		// TODO Auto-generated method stub
		String userEno = approvalDAO.appUserGetEno(userEname);
		return userEno;
	}

	public MemberVO middraftInfo(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		MemberVO memberVO = approvalDAO.draftedmidUser(approvalVO);
		return memberVO;
	}

	public MemberVO findraftInfo(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		MemberVO memberVO = approvalDAO.draftedfinUser(approvalVO);
		return memberVO;
	}

	// 기안서 수정 페이지의 등록 버튼 클릭
	public void draftmodify(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.modifydraft(aVO, txtnum);
	}

	public void draftDelete(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.deleteDraft(txtnum);
	}

	// 기안서 중간 결재
	public void draftmidApprove(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.approvemidDraft(txtnum);
	}

	public void draftfinApprove(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.approvefinDraft(txtnum);
	}

	// 중간 결재자 반려
	public void draftmidReturn(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.returnmidDraft(txtnum);
	}

	public void draftfinReturn(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.returnfinDraft(txtnum);
	}

	public int vacationAdd(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		int txtnum = approvalDAO.vacationInset(aVO, mVO);
		return txtnum;
	}

	public void vacationmodify(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.modifyvacation(aVO, txtnum);
	}

	// 메인 문서 10개
	public List<ApprovalVO> mainList10(MemberVO mVO) {
		List<ApprovalVO> approvallist = approvalDAO.listMain10(mVO);
		return approvallist;
	}

	// 글 개수 조회
	public int docSearchCount(MemberVO mVO, String searchType, String searchKey, String sendReceive, String serachDocList, String serachDocState, String searchDatepicker1, String searchDatepicker2) {
		// TODO Auto-generated method stub
		return approvalDAO.countSearchDoc(mVO, searchType, searchKey, sendReceive, serachDocList, serachDocState, searchDatepicker1, searchDatepicker2);
	}

}
