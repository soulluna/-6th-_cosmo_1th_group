package Approval;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Main.MemberVO;

public class ApprovalDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private int sortList1Num=0;
	private int sortList2Num=0;
	private int sortList3Num=0;
	
	public ApprovalDAO() {
		try {
			// 커넥션풀 사용
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");

			// DNDI에 접근하기 위한 기본 경로 "java:/comp/env" 지정
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
			// 톰캣 context.xml에 설정한 name 값인 jdbc/oracle을 이용해 톰캣이 미리 연결한 DataSource 받아옴.

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 전체 글 목록 조회
	public List<ApprovalVO> selectAllApproval(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {
			
			String query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
			query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
			query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
			query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))" ;
			query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
			
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			for (int i = 1; i <= 9; i++) {
				pstmt.setString(i, mVO.getEno());
			}

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();

				approvalVO.setApplist(rs.getString("applist"));
				approvalVO.setProgress(rs.getString("progress"));
				approvalVO.setTxtnum(rs.getInt("txtnum"));
				approvalVO.setTxtname(rs.getString("txtname"));
				approvalVO.setEntrydate(rs.getDate("entrydate"));
				approvalVO.setEno(rs.getString("eno"));
				approvalVO.setMideno(rs.getString("midsugesteno"));
				approvalVO.setFineno(rs.getString("finsugesteno"));
				
				approvalList.add(approvalVO);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	// 검색 목록 가져오기
	public List<ApprovalVO> selectAllApproval(MemberVO mVO, String searchType, String searchKey) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			con = dataFactory.getConnection();
			System.out.println(searchType);
			System.out.println(searchKey);
			
			if (searchType.equals("1")) {
				query = "select * from Approval where (eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))) and applist like ? ";
				query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
				
			} else if (searchType.equals("2")) {
				query = "select * from Approval where (eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))) and txtname like ? ";
				query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
			}

			pstmt = con.prepareStatement(query);

			for (int i = 1; i <= 8; i++) {
				pstmt.setString(i, mVO.getEno());
			}
			pstmt.setString(9, "%" + searchKey + "%");
			pstmt.setString(10, mVO.getEno());
			
			ResultSet rs = pstmt.executeQuery();
			System.out.println("rs : " + rs);

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();
				int txtnum = rs.getInt("txtnum");
				String applist = rs.getString("applist");
				String progress = rs.getString("progress");
				String txtname = rs.getString("txtname");
				Date entrydate = rs.getDate("entrydate");
				String eno = rs.getString("eno");
				String midsugesteno = rs.getString("midsugesteno");
				String finsugesteno = rs.getString("finsugesteno");
				approvalVO.setApplist(applist);
				approvalVO.setProgress(progress);
				approvalVO.setTxtnum(txtnum);
				approvalVO.setTxtname(txtname);
				approvalVO.setEntrydate(entrydate);
				approvalVO.setEno(eno);
				approvalVO.setMideno(midsugesteno);
				approvalVO.setFineno(finsugesteno);
				approvalList.add(approvalVO);

			}

			// 메모리 누수 방지 위해서
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	// 기안서 상세 보기
	public ApprovalVO selectDraft(int txtnum) {
		ApprovalVO approval = new ApprovalVO();
		System.out.println("selectDraft");
		try {
			String query = "select applist, txtnum, txtname, txtcont, progress, ";
			query += "entrydate, middate, findate, eno, ename, rank, Midsugesteno, Finsugesteno,";
			query += "(select Employee.dname from employee where employee.eno=Approval.eno) as dname ";
			query += "from approval where txtnum=?";
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, txtnum);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			approval.setTxtname(rs.getString("txtname"));
			approval.setTxtcont(rs.getString("txtcont"));
			approval.setProgress(rs.getString("progress"));
			approval.setEntrydate(rs.getDate("entrydate"));
			approval.setMiddate(rs.getDate("middate"));
			approval.setFindate(rs.getDate("findate"));
			approval.setEno(rs.getString("eno"));
			approval.setEname(rs.getString("ename"));
			approval.setRank(rs.getString("rank"));
			approval.setDname(rs.getString("dname"));
			approval.setMideno(rs.getString("Midsugesteno"));
			approval.setFineno(rs.getString("Finsugesteno"));

			System.out.println("-----------------");
			System.out.println(rs.getString("Midsugesteno"));
			System.out.println(rs.getString("Finsugesteno"));
			System.out.println("-----------------");

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return approval;
	}

	// 휴가신청서 상세 보기

	public ApprovalVO selectVacation(int txtnum) {
		ApprovalVO approval = new ApprovalVO();
		System.out.println("selectVacation");
		try {
			String query = "select applist, txtnum, txtname, txtcont, progress, ";
			query += "entrydate, middate, findate, eno, ename, rank, Midsugesteno, Finsugesteno,VACLIST, VACSTART, VACEND,";
			query += "(select Employee.dname from employee where employee.eno=Approval.eno) as dname ";
			query += "from approval where txtnum=?";
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setInt(1, txtnum);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			approval.setTxtname(rs.getString("txtname"));
			approval.setTxtcont(rs.getString("txtcont"));
			approval.setProgress(rs.getString("progress"));
			approval.setEntrydate(rs.getDate("entrydate"));
			approval.setMiddate(rs.getDate("middate"));
			approval.setFindate(rs.getDate("findate"));
			approval.setEno(rs.getString("eno"));
			approval.setEname(rs.getString("ename"));
			approval.setRank(rs.getString("rank"));
			approval.setDname(rs.getString("dname"));
			approval.setMideno(rs.getString("Midsugesteno"));
			approval.setFineno(rs.getString("Finsugesteno"));
			approval.setVaclist(rs.getString("VACLIST"));
			approval.setVacstart(rs.getDate("VACSTART"));
			approval.setVacend(rs.getDate("VACEND"));

			System.out.println("-----------------");
			System.out.println(rs.getString("Midsugesteno"));
			System.out.println(rs.getString("Finsugesteno"));
			System.out.println(rs.getString("VACLIST"));
			System.out.println(rs.getDate("VACSTART"));
			System.out.println(rs.getDate("VACEND"));
			System.out.println("-----------------");

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return approval;
	}

	// 기안서 작성 화면에서 중간 결재자 정보
	public MemberVO midApprovalGet(MemberVO mVO) throws SQLException {
		// TODO Auto-generated method stub
		MemberVO midApproval = new MemberVO();
		System.out.println("midApprovalGet");
		System.out.println(mVO.getDname());
		System.out.println("midApprovalGet");
		ResultSet rs = null;

		String query = "select rank, ename from Employee where (dname=? and DNAME_TWO=?) and rank=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getDname());
			pstmt.setString(2, mVO.getDname_two());

			if (mVO.getRank().equals("사원") || mVO.getRank().equals("대리")) {
				pstmt.setString(3, "팀장");
			} else if (mVO.getRank().equals("팀장") || mVO.getRank().equals("부장")) {
				return midApproval;
			}

			rs = pstmt.executeQuery();
			rs.next();

			midApproval.setRank(rs.getString("rank"));
			midApproval.setEname(rs.getString("ename"));
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			pstmt.close();
			con.close();
		}
		return midApproval;
	}

	// 기안서 작성 화면에서 마지막 결재자 정보
	public MemberVO finApprovalGet(MemberVO mVO) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("마지막 유저 정보 finApprovalGet");
		MemberVO finApproval = new MemberVO();
		ResultSet rs = null;
		String query = null;
		if (mVO.getRank().equals("부장")) {
			query = "select rank, ename from Employee where rank=?";
		} else if (mVO.getDname_two().equals("1팀")) {
			query = "select rank, ename from Employee where rank=? and dname=? and DNAME_TWO=?";
		} else if (mVO.getDname_two().equals("2팀")) {
			query = "select rank, ename from Employee where rank=? and dname=?";
		}
		System.out.println(query);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			if (mVO.getRank().equals("사원") || mVO.getRank().equals("대리")) {
				pstmt.setString(1, "부장");
			} else if (mVO.getRank().equals("팀장")) {
				pstmt.setString(1, "부장");
			} else if (mVO.getRank().equals("부장")) {
				pstmt.setString(1, "이사");
			}

			if (!(mVO.getRank().equals("부장"))) {
				pstmt.setString(2, mVO.getDname());
				if (mVO.getDname_two().equals("1팀")) {
					pstmt.setString(3, mVO.getDname_two());
				}
			}

			rs = pstmt.executeQuery();
			rs.next();

			finApproval.setRank(rs.getString("rank"));
			finApproval.setEname(rs.getString("ename"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			rs.close();
			pstmt.close();
			con.close();
		}
		return finApproval;
	}

	public String appUserGetEno(String userEname) {
		// TODO Auto-generated method stub
		String midUserEno = null;

		String query = "select eno from Employee where ename=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEname);
			ResultSet rs = pstmt.executeQuery();
			try{
				rs.next();
				midUserEno = rs.getString("eno");
			}catch (Exception e) {
				rs.close();
			}
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return midUserEno;
	}

	// 기안서 작성하기
	public void draftInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO draftInset");
		String query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, PROGRESS, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO)";
		query += "values ('기안서', approval_seq.nextval, ?, ?, '대기', ?, ?, ?, ?, ?)";
		try {
			con = dataFactory.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setString(3, mVO.getEno());
			pstmt.setString(4, mVO.getEname());
			pstmt.setString(5, mVO.getRank());
			pstmt.setString(6, aVO.getMideno());
			pstmt.setString(7, aVO.getFineno());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 작성된 문서의 중간 결재 유저 정보 뽑아오기
	public MemberVO draftedmidUser(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO의 draftedmidUser");
		MemberVO createdMid = new MemberVO();

		String query = "select * from Employee where eno=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, approvalVO.getMideno());
			ResultSet rs = pstmt.executeQuery();
			try {
				rs.next();
				createdMid.setRank(rs.getString("rank"));
				createdMid.setEname(rs.getString("ename"));
				createdMid.setEno(rs.getString("eno"));
			} catch (Exception e) {
				rs.close();
			}

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdMid;
	}

	// 작성된 문서의 마지막 결재 유저 정보 뽑아오기
	public MemberVO draftedfinUser(ApprovalVO approvalVO) {
		// TODO Auto-generated method stub
		MemberVO createdFin = new MemberVO();

		String query = "select * from Employee where eno=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, approvalVO.getFineno());
			ResultSet rs = pstmt.executeQuery();
			rs.next();

			createdFin.setRank(rs.getString("rank"));
			createdFin.setEname(rs.getString("ename"));
			createdFin.setEno(rs.getString("eno"));

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return createdFin;
	}
	
	//기안서 수정
	public void modifydraft(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		System.out.println("modifydraft");
		String query = "update Approval set txtname=?, txtcont=?, entrydate=? where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			System.out.println(aVO.getTxtname());
			System.out.println(aVO.getTxtcont());
			System.out.println(currentTime);
			System.out.println(txtnum);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setString(3, currentTime);
			pstmt.setInt(4, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "delete Approval where txtnum=?";
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 중간 결재자 승인
	public void approvemidDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "update approval set MIDDATE=?, PROGRESS='진행' where txtnum=?";

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, currentTime);
			pstmt.setInt(2, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 마지막 결재자 승인
	public void approvefinDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "update approval set findate=?, PROGRESS='완료' where txtnum=?";

		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, currentTime);
			pstmt.setInt(2, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 중간 결재자 반려
	public void returnmidDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "update approval set middate=?, PROGRESS='반려1' where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, currentTime);
			pstmt.setInt(2, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 마지막 결재자 반려
	public void returnfinDraft(int txtnum) {
		// TODO Auto-generated method stub
		String query = "update approval set FINDATE=?, PROGRESS='반려2' where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, currentTime);
			pstmt.setInt(2, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 휴가신청서 등록
	public void vacationInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO vacationInset");
		String query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, PROGRESS, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO, VACLIST, VACSTART, VACEND)";
		query += "values ('휴가신청서', approval_seq.nextval, ?, ?, '대기', ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = dataFactory.getConnection();

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setString(3, mVO.getEno());
			pstmt.setString(4, mVO.getEname());
			pstmt.setString(5, mVO.getRank());
			pstmt.setString(6, aVO.getMideno());
			pstmt.setString(7, aVO.getFineno());
			pstmt.setString(8, aVO.getVaclist());
			System.out.println("getVaclist()까지 옴");

			pstmt.setDate(9, aVO.getVacstart());
			pstmt.setDate(10, aVO.getVacend());

			pstmt.executeUpdate();

			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//휴가신청서 수정
	public void modifyvacation(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		String query = "update Approval set txtname=?, txtcont=?, VACLIST=?, VACSTART=?, VACEND=?, entrydate=? where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(dt);
		
		try {
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			System.out.println(aVO.getTxtname());
			System.out.println(aVO.getTxtcont());
			System.out.println(txtnum);
			pstmt.setString(1, aVO.getTxtname());
			pstmt.setString(2, aVO.getTxtcont());
			pstmt.setString(3, aVO.getVaclist());
			pstmt.setDate(4, aVO.getVacstart());
			pstmt.setDate(5, aVO.getVacend());
			pstmt.setString(6, currentTime);
			pstmt.setInt(7, txtnum);
			System.out.println("executeUpdate");
			pstmt.executeUpdate();

			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//수신발신 정렬
	public List<ApprovalVO> sortList1(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			if (sortList1Num == 0) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by (CASE WHEN eno!=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
				sortList1Num = 1;
			} else if (sortList1Num == 1) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
				sortList1Num = 0;
			}
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			for (int i = 1; i <= 9; i++) {
				pstmt.setString(i, mVO.getEno());
			}

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();

				approvalVO.setApplist(rs.getString("applist"));
				approvalVO.setProgress(rs.getString("progress"));
				approvalVO.setTxtnum(rs.getInt("txtnum"));
				approvalVO.setTxtname(rs.getString("txtname"));
				approvalVO.setEntrydate(rs.getDate("entrydate"));
				approvalVO.setEno(rs.getString("eno"));
				approvalVO.setMideno(rs.getString("midsugesteno"));
				approvalVO.setFineno(rs.getString("finsugesteno"));
				
				approvalList.add(approvalVO);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	public List<ApprovalVO> sortList2(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			if (sortList2Num == 0) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by DECODE (PROGRESS, '대기', 5, '진행', 4, '반려1', 3, '반려2', 2, '완료', 1), (CASE WHEN eno=? THEN 1 ELSE 2 END), ENTRYDATE desc";
				sortList2Num = 1;
			} else if (sortList2Num == 1) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), (CASE WHEN eno=? THEN 1 ELSE 2 END), ENTRYDATE desc";
				sortList2Num = 0;
			}
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			for (int i = 1; i <= 9; i++) {
				pstmt.setString(i, mVO.getEno());
			}

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();

				approvalVO.setApplist(rs.getString("applist"));
				approvalVO.setProgress(rs.getString("progress"));
				approvalVO.setTxtnum(rs.getInt("txtnum"));
				approvalVO.setTxtname(rs.getString("txtname"));
				approvalVO.setEntrydate(rs.getDate("entrydate"));
				approvalVO.setEno(rs.getString("eno"));
				approvalVO.setMideno(rs.getString("midsugesteno"));
				approvalVO.setFineno(rs.getString("finsugesteno"));
				
				approvalList.add(approvalVO);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	public List<ApprovalVO> sortList3(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = null;
		try {
			if (sortList1Num == 0) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by ENTRYDATE, (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5)";
				sortList1Num = 1;
			} else if (sortList1Num == 1) {
				query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
				query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
				query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
				query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))";
				query += "order by ENTRYDATE desc, (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5)";
				sortList1Num = 0;
			}
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			for (int i = 1; i <= 9; i++) {
				pstmt.setString(i, mVO.getEno());
			}

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();

				approvalVO.setApplist(rs.getString("applist"));
				approvalVO.setProgress(rs.getString("progress"));
				approvalVO.setTxtnum(rs.getInt("txtnum"));
				approvalVO.setTxtname(rs.getString("txtname"));
				approvalVO.setEntrydate(rs.getDate("entrydate"));
				approvalVO.setEno(rs.getString("eno"));
				approvalVO.setMideno(rs.getString("midsugesteno"));
				approvalVO.setFineno(rs.getString("finsugesteno"));
				
				approvalList.add(approvalVO);
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	public List<ApprovalVO> listMain10(MemberVO mVO) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {
			
			String query = "select * from Approval where eno= (case PROGRESS when '대기' then ? else ? end) or ";
			query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
			query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
			query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))" ;
			query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc";
			
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			for (int i = 1; i <= 9; i++) {
				pstmt.setString(i, mVO.getEno());
			}

			ResultSet rs = pstmt.executeQuery();

			// 첫 번째 목록부터
			while (rs.next()) {
				// 값을 가져옴
				ApprovalVO approvalVO = new ApprovalVO();

				approvalVO.setApplist(rs.getString("applist"));
				approvalVO.setProgress(rs.getString("progress"));
				approvalVO.setTxtnum(rs.getInt("txtnum"));
				approvalVO.setTxtname(rs.getString("txtname"));
				approvalVO.setEntrydate(rs.getDate("entrydate"));
				approvalVO.setEno(rs.getString("eno"));
				approvalVO.setMideno(rs.getString("midsugesteno"));
				approvalVO.setFineno(rs.getString("finsugesteno"));
				
				approvalList.add(approvalVO);
				
			}
			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return approvalList;
	}

	/*
	 * //페이징 테스트 public List selectAllArticles(Map pagingMap) { // TODO
	 * Auto-generated method stub List articlesList = new ArrayList(); int section =
	 * (Integer) pagingMap.get("section"); int pageNum = (Integer)
	 * pagingMap.get("pageNum"); int pageAllNum = selectTotArticles(); String query
	 * =
	 * "select * from Approval where ? between(?-1)*100+(?-1)*10+1 and (?-1)*100+?*10)"
	 * ; try { con = dataFactory.getConnection(); pstmt =
	 * con.prepareStatement(query); pstmt.setInt(1, pageAllNum); pstmt.setInt(2,
	 * section); pstmt.setInt(3, pageNum); pstmt.setInt(4, section); pstmt.setInt(5,
	 * pageNum); ResultSet rs = pstmt.executeQuery(); while(rs.next()) { ApprovalVO
	 * article = new ApprovalVO(); String applist = rs.getString("applist"); int
	 * txtnum = rs.getInt("txtnum"); String txtname = rs.getString("TXTNAME");
	 * String txtcont = rs.getString("TXTCONT"); String progress =
	 * rs.getString("PROGRESS"); Date entrydate = rs.getDate("ENTRYDATE");
	 * article.setApplist(applist); article.setTxtnum(txtnum);
	 * article.setTxtname(txtname); article.setTxtcont(txtcont);
	 * article.setProgress(progress); article.setEntrydate(entrydate);
	 * articlesList.add(article); } rs.close(); pstmt.close(); con.close();
	 * 
	 * }catch(Exception e) { e.printStackTrace(); }
	 * 
	 * return articlesList; } public int selectTotArticles() { // TODO
	 * Auto-generated method stub String query =
	 * "select count(txtnum) from Approval"; try { con =
	 * dataFactory.getConnection(); pstmt = con.prepareStatement(query); ResultSet
	 * rs = pstmt.executeQuery(); if(rs.next()) { return(rs.getInt(1)); }
	 * rs.close(); pstmt.close(); con.close(); }catch(Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return 0; }
	 * 
	 * public List<ApprovalVO> selectAllArticles() { // TODO Auto-generated method
	 * stub return null; }
	 */
}
