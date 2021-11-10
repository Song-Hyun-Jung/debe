package model;

import java.sql.Date;

/**
 *���� ������ ���� �ʿ��� ������ Ŭ����. Question ���̺�� ����
 */
public class Question{
	private int postId; //�Խñ� id
	private String title; //���� ����
	private Date postDate; //���� �ۼ���
	private String postContent; //���� ����
	private int userId; //������
	private String questionLanguage; //���� �����
	private String solve; //���� �ذ� ����
	private String questionAdopt; //���� ä�� ����
	private int subjectId; //���� ���� ����
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuestionLanguage() {
		return questionLanguage;
	}
	public void setQuestionLanguage(String questionLanguage) {
		this.questionLanguage = questionLanguage;
	}
	public String getSolve() {
		return solve;
	}
	public void setSolve(String solve) {
		this.solve = solve;
	}
	public String getQuestionAdopt() {
		return questionAdopt;
	}
	public void setQuestionAdopt(String questionAdopt) {
		this.questionAdopt = questionAdopt;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	//������
	public Question(int postId, String title, Date postDate, String postContent, int userId, String questionLanguage,
			String solve, String questionAdopt, int subjectId) {
		super();
		this.postId = postId;
		this.title = title;
		this.postDate = postDate;
		this.postContent = postContent;
		this.userId = userId;
		this.questionLanguage = questionLanguage;
		this.solve = solve;
		this.questionAdopt = questionAdopt;
		this.subjectId = subjectId;
	}
	public Question(int postId, String title, Date postDate, String postContent, int userId) {
		super();
		this.postId = postId;
		this.title = title;
		this.postDate = postDate;
		this.postContent = postContent;
		this.userId = userId;
	}
	public Question(int postId, String questionLanguage, String solve, String questionAdopt, int subjectId) {
		super();
		this.postId = postId;
		this.questionLanguage = questionLanguage;
		this.solve = solve;
		this.questionAdopt = questionAdopt;
		this.subjectId = subjectId;
	}
	
	public Question(int postId, String title) {//���������� ���� ���� ��ȸ���� ����� ������
		super();
		this.postId = postId;
		this.title = title;
	}
	
}