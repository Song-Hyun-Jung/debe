package model;

import java.sql.Date;

/**
 *��õ���� ������ ���� �ʿ��� ������ Ŭ����. Recommend ���̺�� ����
 */
public class Recommend{
	private int postId; //�Խñ� id
	private String title; //��õ���� ����
	private Date postDate; //��õ���� �ۼ���
	private String postContent; //��õ���� ����
	private int userId; //��õ���� �ۼ���
	private String difficulty; //��õ���� ���̵�
	private int recommendCount; //��õ���� ��õ��
	private String algorithm; //��õ���� ���� �˰���
	
	
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
	
	//������
	public Recommend(int postId, String title, Date postDate, String postContent, int userId, String difficulty,
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
	
	public Recommend() { }
	
	//���� ��õ�� ����
	public int addRecommendCount() {
		return recommendCount + 1;
	}
	
	
}