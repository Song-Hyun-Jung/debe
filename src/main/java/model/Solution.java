package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *��õ������ ���� �亯(�ַ��) ������ ���� �ʿ��� ������ Ŭ����. RecommendSolution ���̺�� ����
 */
public class Solution implements Serializable{
	private int solutionId; //��õ���� �亯 id
	private int postId; //��õ���� �Խñ� id
	private String solutionContent; //��õ���� �亯 ����
	private float solutionScore; //��õ���� �亯�� ������
	private Date solutionDate; //��õ���� �亯 ��¥
	private int userId; //��õ���� �亯 �ۼ���
	private String userNickname;
	private int userLevel; //�ַ�� �ۼ��� ����ġ
	
	
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getSolutionId() {
		return solutionId;
	}
	public void setSolutionId(int solutionId) {
		this.solutionId = solutionId;
	}
	public String getSolutionContent() {
		return solutionContent;
	}
	public void setSolutionContent(String solutionContent) {
		this.solutionContent = solutionContent;
	}
	public float getSolutionScore() {
		return solutionScore;
	}
	public void setSolutionScore(float solutionScore) {
		this.solutionScore = solutionScore;
	}
	public Date getSolutionDate() {
		return solutionDate;
	}
	public void setSolutionDate(Date solutionDate) {
		this.solutionDate = solutionDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	//������
	public Solution(int solutionId, int postId, String solutionContent, float solutionScore, Date solutionDate,
			int userId, String userNickname, int userLevel) {
		super();
		this.solutionId = solutionId;
		this.postId = postId;
		this.solutionContent = solutionContent;
		this.solutionScore = solutionScore;
		this.solutionDate = solutionDate;
		this.userId = userId;
		this.userNickname = userNickname;
		this.userLevel = userLevel;
	}
	
	public Solution(int postId, String solutionContent, int userId) { //�ַ�� ��Ͻ� ����ϴ� ������
		super();
		this.postId = postId;
		this.solutionContent = solutionContent;
		this.userId = userId;
	}
	
	public Solution() { }
	
	//�� �亯 �� ��� ���ؼ� ��ȯ
	public float avgScore(float score) {
		return (solutionScore + score) / 2;
	}
	
	
	
	
}