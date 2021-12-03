package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Question;
import model.Subject;
import model.User;
import model.dao.UserDAO;

public class UserManager {

	private static UserManager userMan = new UserManager();
	private UserDAO userDAO;
	
	private UserManager() {
		try {
			userDAO = new UserDAO();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static UserManager getInstance() { return userMan; }
	
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId())) {
			throw new ExistingUserException(user.getUserId() + "�� �����ϴ� ���̵��Դϴ�.");
		}
		return userDAO.createUser(user);
	}
	
	public int update(User user) throws SQLException {
		return userDAO.updateUser(user);
	}
	
	public int delete(int userId) throws SQLException {
		return userDAO.deleteUser(userId);
	}
	
	public User findUser(int userId) throws SQLException, UserNotFoundException {
		User user = userDAO.findUser(userId);
		if (user == null) {
			throw new UserNotFoundException(userId + "�� �������� �ʴ� ���̵��Դϴ�.");
		}
		return user;
	}
	
	public List<User> findUserList()  throws SQLException {
		return userDAO.findUserList();
	}
	
	public boolean login(int userId, String password) 
			throws SQLException, UserNotFoundException, PasswordMismatchException {
		User user = findUser(userId);
		if (user.isMatchPassword(password) == false) {
			throw new PasswordMismatchException("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
		return true;
	}
	
	//����� ��ŷ top3
	public List<User> findTop3User() throws SQLException {
		return userDAO.findTop3User();
	}
	
	//����� ���ɰ����, �ڵ� ��ȸ
	public Subject findUserSubjectName(int userId) throws SQLException{
		return userDAO.findUserSubjectName(userId);
	}
	
	//����� ���� ��ȸ
	public List<Question> findMyQuestions(int userId) throws SQLException{
		return userDAO.findMyQuestions(userId);
	}
	
	//����� Q&A �ϸ�ũ ��ȸ
	public List<Bookmark> findMyQuestionBookmarks(int userId) throws SQLException{
		return userDAO.findMyQuestionBookmarks(userId);
	}
	
	//����� ��õ���� �ϸ�ũ ��ȸ
	public List<Bookmark> findMyRecommendBookmarks(int userId) throws SQLException{
		return userDAO.findMyRecommendBookmarks(userId);
	}
	
	//����� ���� ����
	public int updateUserSubject(int userId, int subjectId) throws SQLException{
		return userDAO.updateUserSubject(userId, subjectId);
	}
	
	
}
