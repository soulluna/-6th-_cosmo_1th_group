package Board;

import java.sql.Timestamp;

public class CommentVO {
	private String eno;
	private String rank;
	private String ename;
	private int txtnum;
	private int comnum;
	private String comcont;
	private Timestamp comdate;
	
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public int getTxtnum() {
		return txtnum;
	}
	public void setTxtnum(int txtnum) {
		this.txtnum = txtnum;
	}
	public int getComnum() {
		return comnum;
	}
	public void setComnum(int comnum) {
		this.comnum = comnum;
	}
	public String getComcont() {
		return comcont;
	}
	public void setComcont(String comcont) {
		this.comcont = comcont;
	}
	public Timestamp getComdate() {
		return comdate;
	}
	public void setComdate(Timestamp comdate) {
		this.comdate = comdate;
	}
}
