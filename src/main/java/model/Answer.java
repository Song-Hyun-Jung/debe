package model;

import java.sql.Date;

/**
 *������ ���� �亯 ������ ���� �ʿ��� ������ Ŭ����. QuestionAnswer ���̺�� ����
 */
public class Answer{
	private int answerId; //���� �亯 id
	private int postId; //���� �Խñ� id
	private String answerContent; //���� �亯 ����
	private String answerAdopt; //���� ä�� ����
	private Date answerDate; //���� �亯 �ۼ���
	private int userId; //���� �亯��
	
	
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
	
	//������
	public Answer(int answerId, int postId, String answerContent, String answerAdopt, Date answerDate, int userId) {
		super();
		this.answerId = answerId;
		this.postId = postId;
		this.answerContent = answerContent;
		this.answerAdopt = answerAdopt;
		this.answerDate = answerDate;
		this.userId = userId;
	}
	public Answer(int postId, String answerContent, int userId) { //�亯 ��Ͻ� ����ϴ� ������.
		// answerAdopt�� �⺻�� 'n', answerDate�� sysdate, answerId���� ������ ���� ��. 
		super();
		this.postId = postId;
		this.answerContent = answerContent;
		this.userId = userId;
	}
	
	
}