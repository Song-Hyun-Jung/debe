package model.service;

import model.dao.BookmarkDAO;
import model.dao.QuestionDAO;

import java.sql.SQLException;
import java.util.List;

import model.Bookmark;
import model.Question;

public class QuestionManager {

	private static QuestionManager questionMan = new QuestionManager();
	
	private QuestionDAO questionDAO;
	private BookmarkDAO bookmarkDAO;

	private QuestionManager() {
		try {
		questionDAO = new QuestionDAO();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static QuestionManager getInstance() { return questionMan; }
	
	public List<Question> displayAllQuestion() throws SQLException {
		return questionDAO.displayAllQuestion();
	}
	
	public int addQuestion(Question question) throws SQLException {
		//int result = questionDAO.addQuestion(question);
		int questionCode = questionDAO.addQuestionPost(question);
		int result = questionDAO.addQuestionQ(question, questionCode);

		System.out.println("addQuestion questionCode�� : "+questionCode);
		System.out.println("addQuestion result�� : "+result);
		
		return questionCode;
	}
	
	public void deleteQuestion(int questionCode) throws SQLException {
		int result = questionDAO.deleteQuestion(questionCode);
		System.out.println("deleteQuestion result�� : "+ result);
	}
	
	public Question displayQuestion(int questionCode) throws SQLException {
		return questionDAO.displayQuestion(questionCode);
	}
	
	public void bookmarkQuestion(int questionCode, int userId) throws SQLException {	//�ϸ�ũ �� ���¸� �ϸ�ũ�ϰ� �ƴϸ� �ϸ�ũ �����ϴ� �ɷ� �����ߴµ� �´��� �� �𸣰���
		if(bookmarkDAO.existingBookmark(userId, questionCode)) {
			bookmarkDAO.deleteBookmark(userId, questionCode);
		}
		else {
			Bookmark bookmark = new Bookmark(questionCode, userId);
			bookmarkDAO.createBookmark(bookmark);
		}
	}
	
}
