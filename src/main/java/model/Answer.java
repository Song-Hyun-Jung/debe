package model;

import java.sql.Date;

/**
 *질문에 대한 답변 관리를 위해 필요한 도메인 클래스. QuestionAnswer 테이블과 대응
 */
public class Answer{
	private int answerId; //질문 답변 id
	private int postId; //질문 게시글 id
	private String answerContent; //질문 답변 내용
	private String answerAdopt; //질문 채택 상태
	private Date answerDate; //질문 답변 작성일
	private int userId; //질문 답변자
	
	
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerAdopt() {
		return answerAdopt;
	}
	public void setAnswerAdopt(String answerAdopt) {
		this.answerAdopt = answerAdopt;
	}
	public Date getAnswerDate() {
		return answerDate;
	}
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	//생성자
	public Answer(int answerId, int postId, String answerContent, String answerAdopt, Date answerDate, int userId) {
		super();
		this.answerId = answerId;
		this.postId = postId;
		this.answerContent = answerContent;
		this.answerAdopt = answerAdopt;
		this.answerDate = answerDate;
		this.userId = userId;
	}
	public Answer(int postId, String answerContent, int userId) { //답변 등록시 사용하는 생성자.
		// answerAdopt는 기본값 'n', answerDate는 sysdate, answerId에는 시퀀스 값이 들어감. 
		super();
		this.postId = postId;
		this.answerContent = answerContent;
		this.userId = userId;
	}
	
	
}