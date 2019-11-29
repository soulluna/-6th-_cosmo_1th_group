package Main;

import java.sql.Timestamp;

public class DailySchdulVO {
	private int schnum;
	private Timestamp startDate;
	private Timestamp endDate;
	private String schname;
	private String schcont;
	private String eno;
	private String ename;
	private String rank;
	
	public int getSchnum() {
		return schnum;
	}
	public void setSchnum(int schnum) {
		this.schnum = schnum;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public String getSchname() {
		return schname;
	}
	public void setSchname(String schname) {
		this.schname = schname;
	}
	public String getSchcont() {
		return schcont;
	}
	public void setSchcont(String schcont) {
		this.schcont = schcont;
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
	
}
