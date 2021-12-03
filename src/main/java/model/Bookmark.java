package model;

public class Bookmark{
	private int userId;
	private int postId;
	private String title;
	
	public Bookmark(int postId, int userId) {
		super();
		this.userId = userId;
		this.postId = postId;
	}
	
	public Bookmark(int postId, String title) {
		super();
		this.postId = postId;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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