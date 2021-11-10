package model;

import java.sql.Date;

/**
 *吏덈Ц 愿�由щ�� �쐞�빐 �븘�슂�븳 �룄硫붿씤 �겢�옒�뒪. Question �뀒�씠釉붽낵 ���쓳
 */
public class Question{
	private int postId; //寃뚯떆湲� id
	private String title; //吏덈Ц �젣紐�
	private Date postDate; //吏덈Ц �옉�꽦�씪
	private String postContent; //吏덈Ц �궡�슜
	private int userId; //吏덈Ц�옄
	private String questionLanguage; //吏덈Ц �궗�슜�뼵�뼱
	private String solve; //吏덈Ц �빐寃� �긽�깭
	private String questionAdopt; //吏덈Ц 梨꾪깮 �긽�깭
	private int subjectId; //吏덈Ц 愿��젴 怨쇰ぉ
	
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
	
	public Question(String title, String postContent, int userId, String questionLanguage, int subjectId) {
		this.title = title;
		this.postContent = postContent;
		this.userId = userId;
		this.questionLanguage = questionLanguage;
		this.subjectId = subjectId;
	}
	
	public Question(int postId, String title) {//마이페이지 나의 질문 조회에서 사용할 생성자
		super();
		this.postId = postId;
		this.title = title;
	}
}
