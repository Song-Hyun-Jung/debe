package model;

public class Bookmark{
	private int userId;
	private int postId;
	
	public Bookmark(int postId, int userId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
}