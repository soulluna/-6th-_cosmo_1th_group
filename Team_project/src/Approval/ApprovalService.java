package Approval;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Main.MemberVO;

public class ApprovalService {

	ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO = new ApprovalDAO();
	}
	
	//그냥 문서 출력
	public List<ApprovalVO> listApproval(MemberVO mVO) {
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval(mVO);	
		return approvallist;
	}
	
	//검색어 있을 때 문서 출력
	public List<ApprovalVO> listApproval(String searchType, String searchKey){
		System.out.println("listApproval(매개변수) : " + searchType + ", " + searchKey);
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval(searchType, searchKey);	
		return approvallist;
	}
	
	
	// 기안서 보기
	public ApprovalVO viewdraft(int txtnum) {
		ApprovalVO approval = approvalDAO.selectDraft(txtnum);
		return approval;
	}
	
	/*
	 * //기안서 정보 public ApprovalVO writerdraft(int txtnum, long eno) { ApprovalVO
	 * approval = approvalDAO.writerInfomation(txtnum, eno); return approval; }
	 */
	
	// 휴가신청서 보기
	public ApprovalVO viewvacation(int txtnum) {
		ApprovalVO approval = approvalDAO.selectVacation(txtnum);
		return approval;
	}
	
	//기안서 작성 시 결재 정보
	public MemberVO midApprovalInfo(MemberVO mVO) throws SQLException {
		MemberVO midUser = approvalDAO.midApprovalGet(mVO);
		
		return midUser;
	}
	public MemberVO finApprovalInfo(MemberVO mVO) throws SQLException {
		MemberVO finUser = approvalDAO.finApprovalGet(mVO);
		
		return finUser;
	}
	

	public void draftAdd(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		approvalDAO.draftInset(aVO,mVO);
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

	public void draftmodify(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.modifydraft(aVO, txtnum);
	}

	public void draftDelete(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.deleteDraft(txtnum);
	}

	
	//기안서 중간 결재
	public void draftmidApprove(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.approvemidDraft(txtnum);
	}

	public void draftfinApprove(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.approvefinDraft(txtnum);
	}

	public void draftmidReturn(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.returnmidDraft(txtnum);
	}

	public void draftfinReturn(int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.returnfinDraft(txtnum);
	}

	public void vacationAdd(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		approvalDAO.vacationInset(aVO,mVO);
	}

	public void vacationmodify(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		approvalDAO.modifyvacation(aVO, txtnum);
	}
	



}
