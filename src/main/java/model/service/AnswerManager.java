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
 * ���� �亯 ���� API�� ����ϴ� �����ڵ��� ���� �����ϰ� �Ǵ� Ŭ����.
 * AnswerDAO�� �̿��Ͽ� �����ͺ��̽��� ������ ���� �۾��� �����ϵ��� �ϸ�,
 * �����ͺ��̽��� �����͵��� �̿��Ͽ� �����Ͻ� ������ �����ϴ� ������ �Ѵ�.
 * �����Ͻ� ������ ������ ��쿡�� �����Ͻ� �������� �����ϴ� Ŭ������ 
 * ������ �� �� �ִ�.
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
	
	//�亯 ���
	public void addAnswer(Answer answer) throws SQLException {
		if (answerDAO.addAnswer(answer.getPostId(), answer) == 0) {
			System.out.println("�亯�� ��ϵ��� �ʾҽ��ϴ�.");
		}
		return;
	}

	//���� �Ŵ���
	public void deleteAnswer(int answerCode) throws SQLException {
		if (answerDAO.deleteAnswer(answerCode) == 0) {
			System.out.println("�亯�� �������� �ʾҽ��ϴ�.");
		}
		return;
		
	}

	//ä�� �Ŵ���
	public void AdoptAnswer(int questionCode, int answerCode) throws SQLException{
		try {
			answerDAO.adoptAnswer(questionCode, answerCode);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("�亯�� ä�õ��� �ʾҽ��ϴ�.");
		}
		return;
	}

	//�亯 ��ü�� ��������
	public List<Answer> displayAllAnswer(String viewQuestionCode) throws SQLException{
		try {
			List<Answer> answers = new ArrayList<Answer>();
			answers = answerDAO.findAnswers(Integer.parseInt(viewQuestionCode));
			if(answers != null)
				return answers;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("�亯���� ã�� ���߽��ϴ�.");
		}
		return null;
	}
	

	
}
