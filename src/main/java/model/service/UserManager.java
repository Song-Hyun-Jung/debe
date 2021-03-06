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
	
	//회원가입
	public int create(User user) throws SQLException, ExistingUserException {
		if (userDAO.existingUser(user.getUserId())) {
			throw new ExistingUserException(user.getUserId() + "는 존재하는 아이디입니다.");
		}
		if (userDAO.existingNickname(user.getUserNickname())) {
			throw new ExistingUserException(user.getUserNickname() + "는 존재하는 닉네임입니다.");
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
			throw new UserNotFoundException(userId + "는 존재하지 않는 아이디입니다.");
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
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
	
	//사용자 랭킹 top3
	public List<User> findTop3User() throws SQLException {
		return userDAO.findTop3User();
	}
	
	//사용자 관심과목명, 코드 조회
	public Subject findUserSubjectName(int userId) throws SQLException{
		return userDAO.findUserSubjectName(userId);
	}
	
	//사용자 질문 조회
	public List<Question> findMyQuestions(int userId) throws SQLException{
		return userDAO.findMyQuestions(userId);
	}
	
	//사용자 Q&A 북마크 조회
	public List<Bookmark> findMyQuestionBookmarks(int userId) throws SQLException{
		return userDAO.findMyQuestionBookmarks(userId);
	}
	
	//사용자 추천문제 북마크 조회
	public List<Bookmark> findMyRecommendBookmarks(int userId) throws SQLException{
		return userDAO.findMyRecommendBookmarks(userId);
	}
	
	//사용자 과목 수정
	public int updateUserSubject(int userId, int subjectId) throws SQLException{
		return userDAO.updateUserSubject(userId, subjectId);
	}
	
	//levelUp
	public int levelUpUser(int userId) throws SQLException{
		return userDAO.levelUpUser(userId);
	}
	
	//닉네임 중복 확인
	public boolean existingNickname(String userNickname) throws SQLException { //true일 경우 이미 존재하는 닉네임인것.
		return userDAO.existingNickname(userNickname);
	}
	
	
}
