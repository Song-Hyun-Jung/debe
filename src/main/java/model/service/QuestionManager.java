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
	
	public void bookmarkQuestion(int questionCode, int userId) throws SQLException {	//북마크 된 상태면 북마크하고 아니면 북마크 해제하는 걸로 구현했는데 맞는지 잘 모르겠음
		if(bookmarkDAO.existingBookmark(userId, questionCode)) {
			bookmarkDAO.deleteBookmark(userId, questionCode);
		}
		else {
			Bookmark bookmark = new Bookmark(questionCode, userId);
			bookmarkDAO.createBookmark(bookmark);
		}
	}
	
}
