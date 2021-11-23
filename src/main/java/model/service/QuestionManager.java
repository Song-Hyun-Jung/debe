package model.service;

import model.dao.BookmarkDAO;
import model.dao.QuestionDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Question;
import model.Subject;

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
		
		return result;
	}
	
	public void deleteQuestion(int questionCode) throws SQLException {
		int resultQuestionAnswer = questionDAO.deleteQuestionAnswer(questionCode);
		int resultQuestion = questionDAO.deleteQuestion(questionCode);
		int resultQuestionPost = questionDAO.deleteQuestionPost(questionCode);
		System.out.println("deleteQuestionAnswer���� ���� �亯 ���� : "+ resultQuestionAnswer);
		System.out.println("deleteQuestion���� ���� ���� ���� : "+ resultQuestion);
		System.out.println("deleteQuestionPost���� ���� ���� ���� : "+ resultQuestionPost);
	}

	
	public Question displayQuestion(int questionCode) throws SQLException {
		return questionDAO.displayQuestion(questionCode);
	}
	
	public void bookmarkQuestion(int questionCode, int userId) throws SQLException {	//�ϸ�ũ �� ���¸� �ϸ�ũ�ϰ� �ƴϸ� �ϸ�ũ �����ϴ� �ɷ� �����ߴµ� �´��� �� �𸣰���
		if (bookmarkDAO.existingBookmark(userId, questionCode)) {
			bookmarkDAO.deleteBookmark(userId, questionCode);
		}
		else {
			Bookmark bookmark = new Bookmark(questionCode, userId);
			bookmarkDAO.createBookmark(bookmark);
		}
	}

	public List<Question> filterQuestion(String filter, String language, String subjectName, String solved) throws SQLException {
		
		List<Question> questionList = new ArrayList<Question>();
		
		if (filter.equals("filterLS")) {
			questionList = questionDAO.filterLS(language, subjectName);
		}
		else if (filter.equals("filterSolved")) {
			questionList = questionDAO.filterSolved(solved);
		}
		
		
		return questionList;
	}

	public List<Subject> getAllSubject() {		//DisplayQuesiton.jsp�� ǥ���ϱ� ���ؼ�
		// TODO Auto-generated method stub
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList = questionDAO.getAllSubject();
		
		return subjectList;
	}

}
