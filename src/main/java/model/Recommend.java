package model;

import java.io.Serializable;
import java.sql.Date;

/**
 *추천문제 관리를 위해 필요한 도메인 클래스. Recommend 테이블과 대응
 */
public class Recommend implements Serializable{
	private int postId; //게시글 id
	private String title; //추천문제 제목
	private Date postDate; //추천문제 작성일
	private String postContent; //추천문제 내용
	private int userId; //추천문제 작성자
	private String difficulty; //추천문제 난이도
	private int recommendCount; //추천문제 추천수
	private String algorithm; //추천문제 관련 알고리즘
	private String userNickname;
	private int userLevel; //추천문제 작성자 경험치
	
	
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
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
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public int getRecommendCount() {
		return recommendCount;
	}
	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	
	//생성자
	public Recommend(int postId, String title, Date postDate, String postContent, int userId, String difficulty,
			int recommendCount, String algorithm, String userNickname, int userLevel) {
		super();
		this.postId = postId;
		this.title = title;
		this.postDate = postDate;
		this.postContent = postContent;
		this.userId = userId;
		this.difficulty = difficulty;
		this.recommendCount = recommendCount;
		this.algorithm = algorithm;
		this.userNickname = userNickname;
		this.userLevel= userLevel;
		}
	
	public Recommend(int postId, String title, Date postDate, String postContent, String difficulty,
			int recommendCount, String algorithm) {
		super();
		this.postId = postId;
		this.title = title;
		this.postDate = postDate;
		this.postContent = postContent;
		this.userId = userId;
		this.difficulty = difficulty;
		this.recommendCount = recommendCount;
		this.algorithm = algorithm;
	}
	
	public Recommend(int postId, String title, Date postDate, String postContent) {
		super();
		this.postId = postId;
		this.title = title;
		this.postDate = postDate;
		this.postContent = postContent;
	}
	public Recommend(String difficulty, int recommendCount, String algorithm) {
		super();
		this.difficulty = difficulty;
		this.recommendCount = recommendCount;
		this.algorithm = algorithm;
	}
	
	public Recommend(String title, int userId, String difficulty, String algorithm, String postContent) {	//addRecommend에서 사용하는 생성자
		super();
		this.title = title;
		this.userId = userId;
		this.difficulty = difficulty;
		this.algorithm = algorithm;
		this.postContent = postContent;
	}	

	public Recommend(int postId, String title) {
		super();
		this.postId = postId;
		this.title = title;
	}
	public Recommend() { }
	
	//문제 추천수 증가
	public int addRecommendCount() {
		return recommendCount + 1;
	}
	
	
}