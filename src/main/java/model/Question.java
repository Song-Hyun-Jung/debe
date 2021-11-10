package model;

import java.sql.Date;

/**
 *질문 관리를 위해 필요한 도메인 클래스. Question 테이블과 대응
 */
public class Question{
	private int postId; //게시글 id
	private String title; //질문 제목
	private Date postDate; //질문 작성일
	private String postContent; //질문 내용
	private int userId; //질문자
	private String questionLanguage; //질문 사용언어
	private String solve; //질문 해결 상태
	private String questionAdopt; //질문 채택 상태
	private int subjectId; //질문 관련 과목
	
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
	
	//생성자
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
	
	public Question(int postId, String title) {//마이페이지 나의 질문 조회에서 사용할 생성자
		super();
		this.postId = postId;
		this.title = title;
	}
	
}