package model;

import java.sql.Date;

/**
 *��õ������ ���� �亯(�ַ��) ������ ���� �ʿ��� ������ Ŭ����. RecommendSolution ���̺�� ����
 */
public class Solution{
	private int solutionId; //��õ���� �亯 id
	private int postId; //��õ���� �Խñ� id
	private String solutionContent; //��õ���� �亯 ����
	private float solutionScore; //��õ���� �亯�� ������
	private Date solutionDate; //��õ���� �亯 ��¥
	private int userId; //��õ���� �亯 �ۼ���
	
	
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
	
	//������
	public Solution(int solutionId, int postId, String solutionContent, float solutionScore, Date solutionDate,
			int userId) {
		super();
		this.solutionId = solutionId;
		this.postId = postId;
		this.solutionContent = solutionContent;
		this.solutionScore = solutionScore;
		this.solutionDate = solutionDate;
		this.userId = userId;
	}
	
	public Solution() { }
	
	//�� �亯 �� ��� ���ؼ� ��ȯ
	public float avgScore(float score) {
		return (solutionScore + score) / 2;
	}
	
	
	
	
}