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
	
	public void addQuestion(Question question) throws SQLException {
		questionDAO.addQuestion(question);
	}
	
	public void deleteQuestion(int questionCode) throws SQLException {
		questionDAO.deleteQuestion(questionCode);
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
