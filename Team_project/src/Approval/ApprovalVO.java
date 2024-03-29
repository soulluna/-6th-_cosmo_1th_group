package Approval;

import java.sql.Date;

public class ApprovalVO {

	private String txtcall;
	private String applist;
	private int txtnum;
	private String txtname;
	private String txtcont;
	private String progress;
	private Date entrydate;
	private Date middate;
	private Date findate;
	private String eno;
	private String ename;
	private String rank;
	private String mideno;
	private String fineno;
	private String vaclist;
	private Date vacstart;
	private Date vacend;
	private String dname;

	public ApprovalVO() {
		super();

	}

	public ApprovalVO(int txtnum, String txtcall, String applist, String progress, String txtname, Date entrydate) {

		this.txtnum = txtnum;
		this.txtcall = txtcall;
		this.applist = applist;
		this.progress = progress;
		this.txtname = txtname;
		this.entrydate = entrydate;
	}

	public ApprovalVO(int txtnum, String applist, String progress, String txtname, Date entrydate) {

		this.txtnum = txtnum;
		this.applist = applist;
		this.progress = progress;
		this.txtname = txtname;
		this.entrydate = entrydate;
	}

	public String getTxtcall() {
		return txtcall;
	}

	public void setTxtcall(String txtcall) {
		this.txtcall = txtcall;
	}

	public String getApplist() {
		return applist;
	}

	public void setApplist(String applist) {
		this.applist = applist;
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

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Date getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public Date getMiddate() {
		return middate;
	}

	public void setMiddate(Date middate) {
		this.middate = middate;
	}

	public Date getFindate() {
		return findate;
	}

	public void setFindate(Date findate) {
		this.findate = findate;
	}

	public String getEno() {
		return eno;
	}

	public void setEno(String eno) {
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

	public String getMideno() {
		return mideno;
	}

	public void setMideno(String mideno) {
		this.mideno = mideno;
	}

	public String getFineno() {
		return fineno;
	}

	public void setFineno(String fineno) {
		this.fineno = fineno;
	}

	public String getVaclist() {
		return vaclist;
	}

	public void setVaclist(String vaclist) {
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

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

}