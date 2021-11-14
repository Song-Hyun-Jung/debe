package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import model.Answer;
import model.User;
import model.dao.AnswerDAO;
import model.dao.UserDAO;

/**
 * 질문 답변 관리 API를 사용하는 개발자들이 직접 접근하게 되는 클래스.
 * AnswerDAO를 이용하여 데이터베이스에 데이터 조작 작업이 가능하도록 하며,
 * 데이터베이스의 데이터들을 이용하여 비지니스 로직을 수행하는 역할을 한다.
 * 비지니스 로직이 복잡한 경우에는 비지니스 로직만을 전담하는 클래스를 
 * 별도로 둘 수 있다.
 */

public class AnswerManager {
	private static AnswerManager answerManager = new AnswerManager();
	private UserDAO userDAO;
	private AnswerDAO answerDAO;
	final Logger LOG = Logger.getGlobal();
	
	
	private AnswerManager() {
		try {
			userDAO = new UserDAO();
			answerDAO = new AnswerDAO();
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	public static AnswerManager getInstance() {
		return answerManager;
	}
	
	//답변 등록
	public void addAnswer(Answer answer) throws SQLException {
		if (answerDAO.addAnswer(answer.getPostId(), answer) == 0) {
			System.out.println("답변이 등록되지 않았습니다.");
		}
		return;
	}

	//삭제 매니저
	public void deleteAnswer(int answerCode) throws SQLException {
		if (answerDAO.deleteAnswer(answerCode) == 0) {
			System.out.println("답변이 삭제되지 않았습니다.");
		}
		return;
		
	}

	//채택 매니저
	public void AdoptAnswer(int questionCode, int answerCode) throws SQLException{
		try {
			answerDAO.adoptAnswer(questionCode, answerCode);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("답변이 채택되지 않았습니다.");
		}
		return;
	}

	//답변 객체들 가져오기
	public List<Answer> displayAllAnswer(String viewQuestionCode) throws SQLException{
		try {
			List<Answer> answers = new ArrayList<Answer>();
			answers = answerDAO.findAnswers(Integer.parseInt(viewQuestionCode));
			if(answers != null)
				return answers;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("답변들을 찾지 못했습니다.");
		}
		return null;
	}
	

	
}
