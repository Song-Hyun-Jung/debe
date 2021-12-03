package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Bookmark;
import model.Question;
import model.User;
import model.Subject;

/**
 * 사용자 관리를 위해 데이터베이스 작업을 전담하는 DAO 클래스
 * ServiceUser 테이블에 사용자 정보를 추가, 수정, 삭제, 조회 수행 
 */
public class UserDAO {
	
	private JDBCUtil jdbcUtil = null;
	
	public UserDAO() {			
		jdbcUtil = new JDBCUtil();	// JDBCUtil 객체 생성
	}
	
	//사용자 정보 추가
	public int createUser(User user) throws SQLException {
		String sql = "INSERT INTO SERVICEUSER(userid, userpassword, username, usernickname, subjectid) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";		
		Object[] param = new Object[] {user.getUserId(), user.getUserPassword(), 
						user.getUserName(), user.getUserNickname(), user.getSubjectId() };				
		jdbcUtil.setSqlAndParameters(sql, param);	
						
		try {				
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {		
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;			
	}
	
	
	//사용자 정보 수정
	public int updateUser(User user) throws SQLException {
		String sql = "UPDATE SERVICEUSER "
					+ "SET userid=?, userpassword=?, usernickname=?, username=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {user.getUserId(), user.getUserPassword(), 
					user.getUserNickname(), user.getUserName(), 
					user.getUserId()};				
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	//사용자 정보 삭제 -->deletemanager에서 deleteUser, findUserList순으로 가져와서 List return
	public int deleteUser(int userId) throws SQLException {
		String sql = "DELETE FROM SERVICEUSER WHERE userid=?";		
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	
	//한 사용자 정보 조회
	public User findUser(int userId) throws SQLException {
        String sql = "SELECT userId, userPassword, userName, userNickname, userLevel, u.subjectId, s.subjectTitle "
        			+ "FROM ServiceUser u JOIN Subject s ON u.subjectId = s.subjectId "
        			+ "WHERE userid=? ";              
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			System.out.println(userId);
			if (rs.next()) {						
				User user = new User(		
					rs.getInt("userId"),
					rs.getString("userPassword"),
					rs.getString("userNickname"),
					rs.getString("userName"),
					rs.getInt("userLevel"),
					rs.getString("subjectTitle"),					
					rs.getInt("subjectId"));
				return user;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//모든 사용자 가져오기
	public List<User> findUserList() throws SQLException {
        String sql = "SELECT userId, userPassword, userName, userNickname, userLevel, subjectId "
    				+ "FROM ServiceUser "
        		    + "ORDER BY userId";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<User> userList = new ArrayList<User>();	
			while (rs.next()) {
				User user = new User(			
						rs.getInt("userId"),
						rs.getString("userPassword"),
						rs.getString("userNickname"),
						rs.getString("userName"),
						rs.getInt("userLevel"),					
						rs.getInt("subjectId")
					);
				userList.add(user);				
			}		
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//레벨 상위 3순위 조회->이름만 리스트로 1.레벨순 -> 2.고학번순 / (닉네임, 레벨)반환
	public List<User> findTop3User() throws SQLException {
        String sql = "SELECT userNickname, userLevel "
    				+ "FROM (SELECT userId, userNickname, userLevel FROM serviceUser "
        		    + "ORDER BY userLevel DESC, userId ASC) "
    				+ "WHERE ROWNUM <= 3";
		jdbcUtil.setSqlAndParameters(sql, null);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<User> userList = new ArrayList<User>();	
			while (rs.next()) {
				User user = new User(		
						rs.getString("userNickname"),
						rs.getInt("userLevel")
					);
				userList.add(user);			
			}		
			
			return userList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	
	//마이페이지 관련
	//사용자별 관심 과목 조회->과목 코드 반환. 관련과목 질문 추천시 사용
	public int findUserSubject(int userId) throws SQLException {
		String sql = "SELECT subjectid FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			int result;
			if(rs.next()) {
				result = rs.getInt("SUBJECTID");
				return result;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	//사용자별 관심 과목 조회->마이페이지 출력. 과목명 반환
		public Subject findUserSubjectName(int userId) throws SQLException {
			String sql = "SELECT subjectTitle, s.subjectId AS subjectId "
					+ "FROM ServiceUser u JOIN Subject s ON u.subjectId = s.subjectId "
					+ "WHERE userId = ?";
			Object[] param = new Object[] {userId};
						
			jdbcUtil.setSqlAndParameters(sql, param);	
				
			try {				
				ResultSet rs = jdbcUtil.executeQuery();	
				Subject userSubject = null;
				if(rs.next()) {
					userSubject = new Subject(
							rs.getInt("subjectId"),
							rs.getString("subjectTitle")
							);
					return userSubject;
				}
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			}
			finally {
				jdbcUtil.commit();
				jdbcUtil.close();	
			}		
			return null;
		}
		
	//사용자별 관심 과목 수정
	public int updateUserSubject(int userId, int subjectId) throws SQLException { //subject는 파라미터로 넘어오는 과목코드
		String sql = "UPDATE SERVICEUSER "
					+ "SET subjectid=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {subjectId, 
					userId};				
		jdbcUtil.setSqlAndParameters(sql, param);
			
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	//레벨 조회->현재 레벨 반환. 결과가 0이면 잘못된것
	public int getLevel(int userId) throws SQLException {
		String sql = "SELECT userlevel FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			int result;
			if(rs.next()) {
				result = rs.getInt("USERLEVEL");
				return result;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	//레벨업->레벨업 후 결과 반환.(변경된 개수)
	public int levelUp(int userId) throws SQLException {
		int level = getLevel(userId); //현재 레벨 가져오기
		String sql = "UPDATE SERVICEUSER "
					+ "SET userlevel=? "
					+ "WHERE userid=?";
		Object[] param = new Object[] {level + 1, 
					userId};				
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			int result = jdbcUtil.executeUpdate();	
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}

	
	//로그인, 회원가입 관련
	//사용자 닉네임 가져오기->메인에서 표시
	public String getNickname(int userId) throws SQLException {
		String sql = "SELECT userNickname FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();	
			String result;
			if(rs.next()) {
				result = rs.getString("usernickname");
				return result;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return null;
	}
	
	//사용자 비밀번호 가져오기->manager에서 입력값과 같은지 체크
	public int getPassword(int userId) throws SQLException {
		String sql = "SELECT userPassword FROM SERVICEUSER WHERE userid = ?";
		Object[] param = new Object[] {userId};
					
		jdbcUtil.setSqlAndParameters(sql, param);	
			
		try {				
			ResultSet rs = jdbcUtil.executeQuery();
			int result;
			if(rs.next()) {
				result = rs.getInt("userpassword");
				return result;
			}
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	
		}		
		return 0;
	}
	
	//사용자 닉네임 중복체크(이미 존재하는 닉네임인지 체크->false이면 닉네임 사용 불가) unique라 필요없을것같긴함...
	public boolean existingNickname(String userNickname) throws SQLException {
		String sql = "SELECT count(*) FROM SERVICEUSER WHERE userNickname=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userNickname});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
	
	//id 유무 체크(이미 사용자가 존재하는지 체크->false이면 사용자 추가 불가) unique라 필요없을것같긴함...
	public boolean existingUser(int userId) throws SQLException {
		String sql = "SELECT count(*) FROM SERVICEUSER WHERE userid=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {userId});	

		try {
			ResultSet rs = jdbcUtil.executeQuery();		
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return false;
	}
	
	//마이페이지 관련
	//나의 질문 조회
	public List<Question> findMyQuestions(int userId) throws SQLException {
        String sql = "SELECT p.postId, p.title, p.userId, p.postdate "
        			+ "FROM Post p, Question q WHERE p.postId = q.postId and p.userId = ? "
        			+ "ORDER BY p.postdate DESC";
        		
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();					
			List<Question> myQuestions = new ArrayList<Question>();	
			while (rs.next()) {
				Question question = new Question(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myQuestions.add(question);				
			}		
			return myQuestions;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//+나의 북마크 조회-Q&A
	public List<Bookmark> findMyQuestionBookmarks(int userId) throws SQLException {
        String sql = "SELECT b.postId, title " 
    				+ "FROM Bookmark b, "
    				+ "(SELECT p.postId, p.title, p.postdate FROM Post p, Question q WHERE p.postId = q.postId order by p.postdate DESC) t "
        		    + "WHERE t.postId = b.postId and b.userId = ? ";
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Bookmark> myQuestionBookmarks = new ArrayList<Bookmark>();	
			while (rs.next()) {
				Bookmark bookmark = new Bookmark(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myQuestionBookmarks.add(bookmark);				
			}		
			return myQuestionBookmarks;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
	//+나의 북마크 조회-추천문제
	public List<Bookmark> findMyRecommendBookmarks(int userId) throws SQLException {
        String sql = "SELECT b.postId, title "
    				+ "FROM Bookmark b, "
    				+ "(SELECT p.postId, p.title, p.postdate FROM Post p, Recommend r WHERE p.postId = r.postId order by p.postdate DESC) t "
        		    + "WHERE t.postId = b.postId and b.userId = ? ";
        
        Object[] param = new Object[] {userId};
		jdbcUtil.setSqlAndParameters(sql, param);		
					
		try {
			ResultSet rs = jdbcUtil.executeQuery();				
			List<Bookmark> myRecommendBookmarks = new ArrayList<Bookmark>();	
			while (rs.next()) {
				Bookmark bookmark = new Bookmark(			
						rs.getInt("postId"),
						rs.getString("title")
					);
				myRecommendBookmarks.add(bookmark);				
			}		
			return myRecommendBookmarks;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		
		}
		return null;
	}
	
}