package Board;

import java.sql.Date;

public class BoardVO {

	   private String detailName;
	   private int num;	   
	   private String title;
	   private String content;	   
	   private String id;
	   private Date writeDate;
	   private int count;
	   private int goods;
	   
public BoardVO(String detailName, int num, String title, String content, String id, Date writeDate, int count,
			int goods) {
		super();
		this.detailName = detailName;
		this.num = num;
		this.title = title;
		this.content = content;
		this.id = id;
		this.writeDate = writeDate;
		this.count = count;
		this.goods = goods;
	}
	public BoardVO() {
	// TODO Auto-generated constructor stub
}
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	   
}
