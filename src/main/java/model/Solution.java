package model;

import java.sql.Date;

/**
 *추천문제에 대한 답변(솔루션) 관리를 위해 필요한 도메인 클래스. RecommendSolution 테이블과 대응
 */
public class Solution{
	private int solutionId; //추천문제 답변 id
	private int postId; //추천문제 게시글 id
	private String solutionContent; //추천문제 답변 내용
	private float solutionScore; //추천문제 답변별 평가점수
	private Date solutionDate; //추천문제 답변 날짜
	private int userId; //추천문제 답변 작성자
	
	
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
	
	//생성자
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
	
	//각 답변 별 평균 구해서 반환
	public float avgScore(float score) {
		return (solutionScore + score) / 2;
	}
	
	
	
	
}