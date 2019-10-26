package Approval;

import java.sql.Date;

public class ApprovalVO {

	private int txtnum; //글번호
	private String txtname; //글제목
	private String txtcont; //글내용
	private Date entrydate; //등록일
	private int eno; //사원번호
	private String ename; //사원명
	private String rank; //직급
	private int applist; //전자결재종류
	private String midsugest; //중간결재자
	private String finsugest; //최종결재자
	private int vaclist; //휴가종류
	private Date vacstart; //휴가시작일
	private Date vacend; //휴가종료일
	
	
	private String applist2;
	private String call;
	private String progress;
		
	public ApprovalVO() {
		super();
	}
	
	public ApprovalVO(String call, String applist2, String progress, String txtname, Date entrydate) {
		this.call = call;
		this.applist2 = applist2;
		this.progress = progress;
		this.txtname = txtname;
		this.entrydate = entrydate;
	}
	
	public int getTxtnum() {
		return txtnum;
	}
	public void setTxtnum(int txtnum) {
		this.txtnum = txtnum;
	}
	public String getTxtname() {
		return txtname;
	}
	public void setTxtname(String txtname) {
		this.txtname = txtname;
	}
	public String getTxtcont() {
		return txtcont;
	}
	public void setTxtcont(String txtcont) {
		this.txtcont = txtcont;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public int getEno() {
		return eno;
	}
	public void setEno(int eno) {
		this.eno = eno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getApplist() {
		return applist;
	}
	public void setApplist(int applist) {
		this.applist = applist;
	}
	public String getMidsugest() {
		return midsugest;
	}
	public void setMidsugest(String midsugest) {
		this.midsugest = midsugest;
	}
	public String getFinsugest() {
		return finsugest;
	}
	public void setFinsugest(String finsugest) {
		this.finsugest = finsugest;
	}
	public int getVaclist() {
		return vaclist;
	}
	public void setVaclist(int vaclist) {
		this.vaclist = vaclist;
	}
	public Date getVacstart() {
		return vacstart;
	}
	public void setVacstart(Date vacstart) {
		this.vacstart = vacstart;
	}
	public Date getVacend() {
		return vacend;
	}
	public void setVacend(Date vacend) {
		this.vacend = vacend;
	}

	public String getApplist2() {
		return applist2;
	}

	public void setApplist2(String applist2) {
		this.applist2 = applist2;
	}

	public String getCall() {
		return call;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	
	
}