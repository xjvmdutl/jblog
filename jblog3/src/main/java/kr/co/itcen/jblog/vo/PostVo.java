package kr.co.itcen.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String contents;
	private String written_date;
	private Long category_no;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWritten_date() {
		return written_date;
	}
	public void setWritten_date(String written_date) {
		this.written_date = written_date;
	}
	public Long getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Long category_no) {
		this.category_no = category_no;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", written_date=" + written_date
				+ ", category_no=" + category_no + "]";
	}
	
}
