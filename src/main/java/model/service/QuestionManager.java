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
		bookmarkDAO = new BookmarkDAO();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static QuestionManager getInstance() { return questionMan; }
	
	public List<Question> displayAllQuestion() throws SQLException {
		return questionDAO.displayAllQuestion();
	}
	
	public int addQuestion(Question question) throws SQLException {
		int questionCode = questionDAO.addQuestionPost(question);
		int result = questionDAO.addQuestionQ(question, questionCode);

		System.out.println("addQuestion questionCode값 : "+questionCode);
		System.out.println("addQuestion result값 : "+result);
		
		return questionCode;
	}
	
	public void deleteQuestion(int questionCode) throws SQLException {
		int resultQuestionAnswer = questionDAO.deleteQuestionAnswer(questionCode);
		int resultBookmark = questionDAO.deleteQuestionBookmark(questionCode);	//누군가 이 질문 북마크했으면 거기서도 지움
		int resultQuestion = questionDAO.deleteQuestion(questionCode);
		int resultQuestionPost = questionDAO.deleteQuestionPost(questionCode);
		
		System.out.println("deleteQuestionAnswer에서 지운 답변 갯수 : "+ resultQuestionAnswer);
		System.out.println("deleteQuestion에서 지운 질문 갯수 : "+ resultQuestion);
		System.out.println("deleteQuestionPost에서 지운 질문 갯수 : "+ resultQuestionPost);
	}

	
	public Question displayQuestion(int questionCode) throws SQLException {
		return questionDAO.displayQuestion(questionCode);
	}
	
	public List<Question> displayNotSolveQuestion() throws SQLException {
		return questionDAO.displayNotSolveQuestion();
	}
	
	public List<Question> findQuestion(String keyword) throws SQLException {
		return questionDAO.findQuestion(keyword);
	}
	
	public void bookmarkQuestion(int questionCode, int userId) throws SQLException {	
		if (bookmarkDAO.existingBookmark(questionCode, userId)) {
			System.out.println("북마크 돼있던 상태라서 북마크에서 지움");
			bookmarkDAO.deleteBookmark(userId, questionCode);
		}
		else {
			Bookmark bookmark = new Bookmark(questionCode, userId);
			bookmarkDAO.createBookmark(bookmark);
			System.out.println("북마크에 추가");
		}
	}
	
	public boolean existingBookmarkQuestion(int questionCode, int userId) throws SQLException {
		return bookmarkDAO.existingBookmark(questionCode, userId);
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

	public List<Subject> getAllSubject() {		//DisplayQuesiton.jsp에 표시하기 위해서
		// TODO Auto-generated method stub
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList = questionDAO.getAllSubject();
		
		return subjectList;
	}

}
