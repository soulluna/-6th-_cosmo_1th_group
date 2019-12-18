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

	// 목록 가져오기
	public List<ApprovalVO> selectAllApproval(MemberVO mVO, String searchType, String searchKey, String sendReceive,
			String serachDocList, String serachDocState, String datepicker1, String datepicker2, int rowNum1,
			int rowNum2) {
		List<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		String query = "";

		try {
			con = dataFactory.getConnection();
			System.out.println(searchType);
			System.out.println(searchKey);

			query = "select * from(select rownum as rownum2, A.* from ";
			query += "(select rownum as rownum1, applist, progress, txtnum, txtname, entrydate, eno, midsugesteno, finsugesteno, ENAME ";
			query += "from Approval where (eno= (case PROGRESS when '대기' then ? else ? end) or ";
			query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
			query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
			query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))) ";

			if (searchType.equals("1")) {
				query += "and txtname like '%'||?||'%' ";
			} else if (searchType.equals("2")) {
				query += "and TXTCONT like '%'||?||'%' ";
			} else if (searchType.equals("3")) {
				query += "and ENAME like '%'||?||'%' ";
			} else {
				query += "and txtname like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (sendReceive.equals("발신")) {
				query += "and eno like '%'||?||'%' ";
			} else if (sendReceive.equals("수신")) {
				query += "and eno not like '%'||?||'%' ";
			} else {
				query += "and eno like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (serachDocList.equals("기안서")) {
				query += "and APPLIST like '%'||?||'%' ";
			} else if (serachDocList.equals("휴가신청서")) {
				query += "and APPLIST like '%'||?||'%' ";
			} else {
				query += "and APPLIST like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (serachDocState.equals("대기")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("진행")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("완료")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("반려1")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("반려2")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else {
				query += "and PROGRESS like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (!datepicker1.equals("") && !datepicker2.equals("")) {
				query += "and ENTRYDATE between TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ";
			}

			query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc) A) ";
			query += "where rownum2 between ? and ?";

			pstmt = con.prepareStatement(query);

			for (int i = 1; i <= 8; i++) {
				pstmt.setString(i, mVO.getEno());
			}
			
			if (searchType.equals("1")) {
				pstmt.setString(9, searchKey);
			} else if (searchType.equals("2")) {
				pstmt.setString(9, searchKey);
			} else if (searchType.equals("3")) {
				pstmt.setString(9, searchKey);
			} else {
				pstmt.setString(9, "");
			}

			if (sendReceive.equals("발신")) {
				pstmt.setString(10, mVO.getEno());
			} else if (sendReceive.equals("수신")) {
				pstmt.setString(10, mVO.getEno());
			} else {
				pstmt.setString(10, "");
			}

			if (serachDocList.equals("기안서")) {
				pstmt.setString(11, serachDocList);
			} else if (serachDocList.equals("휴가신청서")) {
				pstmt.setString(11, serachDocList);
			} else {
				pstmt.setString(11, "");
			}

			if (serachDocState.equals("대기")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("진행")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("완료")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("반려1")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("반려2")) {
				pstmt.setString(12, serachDocState);
			} else {
				pstmt.setString(12, "");
			}

			if (!datepicker1.equals("") && !datepicker2.equals("")) {
				pstmt.setString(13, datepicker1 + " 00:00:00");
				pstmt.setString(14, datepicker2 + " 23:59:59");
				pstmt.setString(15, mVO.getEno());
				pstmt.setInt(16, rowNum1); // 1 16
				pstmt.setInt(17, rowNum2); // 15 30
			}else {
				pstmt.setString(13, mVO.getEno());
				pstmt.setInt(14, rowNum1); // 1 16
				pstmt.setInt(15, rowNum2); // 15 30
			}
			

			ResultSet rs = pstmt.executeQuery();

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
				approvalVO.setEname(rs.getString("ename"));
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
			try {
				rs.next();
				midUserEno = rs.getString("eno");
			} catch (Exception e) {
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
	public int draftInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO draftInset");
		int txtnum = 0;
		String query = "";
		query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, PROGRESS, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO)";
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

			query = "select txtnum from Approval where eno=? and APPLIST='기안서' order by txtnum desc";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getEno());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				txtnum = rs.getInt("txtnum");
				System.out.println(txtnum);
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return txtnum;

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

	// 기안서 수정
	public void modifydraft(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		System.out.println("modifydraft");
		String query = "update Approval set PROGRESS = '대기', MIDDATE = null, FINDATE = null, txtname=?, txtcont=?, entrydate=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	public int vacationInset(ApprovalVO aVO, MemberVO mVO) {
		// TODO Auto-generated method stub
		System.out.println("DAO vacationInset");
		String query = "";
		int txtnum = 0;
		query = "insert into Approval (APPLIST, TXTNUM, TXTNAME, TXTCONT, PROGRESS, ENO, ENAME, RANK, MIDSUGESTENO, FINSUGESTENO, VACLIST, VACSTART, VACEND)";
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
			pstmt.setDate(9, aVO.getVacstart());
			pstmt.setDate(10, aVO.getVacend());

			pstmt.executeUpdate();

			pstmt.close();

			query = "select txtnum from Approval where eno=? and APPLIST='휴가신청서' order by txtnum desc";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mVO.getEno());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				txtnum = rs.getInt("txtnum");
				System.out.println(txtnum);
			}

			rs.close();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return txtnum;
	}

	// 휴가신청서 수정
	public void modifyvacation(ApprovalVO aVO, int txtnum) {
		// TODO Auto-generated method stub
		String query = "update Approval set PROGRESS = '대기', MIDDATE = null, FINDATE = null, txtname=?, txtcont=?, VACLIST=?, VACSTART=?, VACEND=?, entrydate=TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') where txtnum=?";
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

	// 메인 문서 15개
	public ArrayList<ApprovalVO> listMain10(MemberVO mVO) {
		ArrayList<ApprovalVO> approvalList = new ArrayList<ApprovalVO>();
		try {

			String query = "select * from(select rownum as rownum2, A.* from ";
			query += "(select rownum as rownum1, applist, progress, txtnum, txtname, entrydate, eno, midsugesteno, finsugesteno ";
			query += "from Approval where (eno= (case PROGRESS when '대기' then ? else ? end) or ";
			query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
			query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
			query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))) ";
			query += "order by (CASE WHEN eno=? THEN 1 ELSE 2 END), DECODE (PROGRESS, '대기', 1, '진행', 2, '반려1', 3, '반려2', 4, '완료', 5), ENTRYDATE desc) A) ";
			query += "where rownum2 between 1 and 10";

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

	public int countSearchDoc(MemberVO mVO, String searchType, String searchKey, String sendReceive,
			String serachDocList, String serachDocState, String searchDatepicker1, String searchDatepicker2) {
		// TODO Auto-generated method stub
		int docMax = 0;
		String query = null;

		System.out.println("countSearchDoc함수 안");

		try {
			query = "select count(*) from Approval where (eno= (case PROGRESS when '대기' then ? else ? end) or ";
			query += "MIDSUGESTENO = (case PROGRESS when '대기' then ? else ? end) or ";
			query += "Finsugesteno = (case PROGRESS when '진행' then ? when '반려2' then ? when '완료' then ? end) or ";
			query += "((MIDSUGESTENO is null) and Finsugesteno = (case PROGRESS when '대기' then ? end))) ";

			if (searchType.equals("1")) {
				query += "and txtname like '%'||?||'%' ";
			} else if (searchType.equals("2")) {
				query += "and TXTCONT like '%'||?||'%' ";
			} else if (searchType.equals("3")) {
				System.out.println("searchType.equals가 3일 때");
				query += "and ENAME like '%'||?||'%' ";
			} else {
				query += "and txtname like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (sendReceive.equals("발신")) {
				query += "and eno like '%'||?||'%' ";
			} else if (sendReceive.equals("수신")) {
				query += "and eno not like '%'||?||'%' ";
			} else {
				query += "and eno like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (serachDocList.equals("기안서")) {
				query += "and APPLIST like '%'||?||'%' ";
			} else if (serachDocList.equals("휴가신청서")) {
				query += "and APPLIST like '%'||?||'%' ";
			} else {
				query += "and APPLIST like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (serachDocState.equals("대기")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("진행")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("완료")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("반려1")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else if (serachDocState.equals("반려2")) {
				query += "and PROGRESS like '%'||?||'%' ";
			} else {
				query += "and PROGRESS like '%'||?||'%' "; // 개수 맞추기 위해 그냥 넣은 쿼리
			}

			if (!searchDatepicker1.equals("") && !searchDatepicker2.equals("")) {
				query += "and ENTRYDATE between TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') and TO_DATE(?, 'YYYY-MM-DD HH24:MI:SS') ";
			}

			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);

			for (int i = 1; i <= 8; i++) {
				pstmt.setString(i, mVO.getEno());
			} // 8까지

			if (searchType.equals("1")) {
				pstmt.setString(9, searchKey);
			} else if (searchType.equals("2")) {
				pstmt.setString(9, searchKey);
			} else if (searchType.equals("3")) {
				pstmt.setString(9, searchKey);
			} else {
				pstmt.setString(9, "");
			}

			if (sendReceive.equals("발신")) {
				pstmt.setString(10, mVO.getEno());
			} else if (sendReceive.equals("수신")) {
				pstmt.setString(10, mVO.getEno());
			} else {
				pstmt.setString(10, "");
			}

			if (serachDocList.equals("기안서")) {
				pstmt.setString(11, serachDocList);
			} else if (serachDocList.equals("휴가신청서")) {
				pstmt.setString(11, serachDocList);
			} else {
				pstmt.setString(11, "");
			}

			if (serachDocState.equals("대기")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("진행")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("완료")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("반려1")) {
				pstmt.setString(12, serachDocState);
			} else if (serachDocState.equals("반려2")) {
				pstmt.setString(12, serachDocState);
			} else {
				pstmt.setString(12, "");
			}

			if (!searchDatepicker1.equals("") && !searchDatepicker2.equals("")) {
				pstmt.setString(13, searchDatepicker1 + " 00:00:00");
				pstmt.setString(14, searchDatepicker2 + " 23:59:59");
			}

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				docMax = rs.getInt("count(*)");
			}
			System.out.println("---함수안---");
			System.out.println(docMax);
			System.out.println("---함수안---");
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return docMax;

	}

}
