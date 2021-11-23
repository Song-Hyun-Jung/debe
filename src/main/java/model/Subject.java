package model;


public class Subject {
	private int subjectId;
	private String subjectTitle;
	
	
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectTitle() {
		return subjectTitle;
	}
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	
	//»ý¼ºÀÚ
	public Subject(int subjectId, String subjectTitle) {
		super();
		this.subjectId = subjectId;
		this.subjectTitle = subjectTitle;
	}
}
