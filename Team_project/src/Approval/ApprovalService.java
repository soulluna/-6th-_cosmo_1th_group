package Approval;

import java.util.List;

public class ApprovalService {

	ApprovalDAO approvalDAO;
	
	public ApprovalService() {
		approvalDAO = new ApprovalDAO();
	}
	
	
	public List<ApprovalVO> listApproval() {
		List<ApprovalVO> approvallist = approvalDAO.selectAllApproval();
		
		return approvallist;
	}
	
	
}
